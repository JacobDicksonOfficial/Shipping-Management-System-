package com.assignment1.ships.module.business;

import com.assignment1.ships.module.model.Port;
import com.assignment1.ships.module.model.Ship;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PortBusinessLogic {

    private List<Port> ports;
    private static final String PORTS_FILE_PATH = "src/com/assignment1/ships/module/data/ports.txt";
    private static final String SHIPS_FILE_PATH = "src/com/assignment1/ships/module/data/ships.txt";

    public PortBusinessLogic() {
        ports = loadPortsFromFile();
        loadShipsFromFile();  // Load ships after ports are loaded
    }

    public void addPort(Port port) {
        ports.add(port);
        savePortsToFile();
    }

    public void deletePort(Port port) {
        ports.remove(port);
        savePortsToFile();
    }

    public List<Port> getAllPorts() {
        return ports;
    }

    public void clearAllPorts() {
        ports.clear();
        savePortsToFile();
    }

    public void savePortsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PORTS_FILE_PATH))) {
            for (Port port : ports) {
                writer.write(port.getPortName() + "," + port.getPortCode() + "," + port.getCountry() + "," + port.getPortType() + "," + port.getComs());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveShipsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SHIPS_FILE_PATH))) {
            for (Port port : ports) {
                for (Ship ship : port.getShips()) {
                    writer.write(port.getPortCode() + "," + ship.getShipName() + "," + ship.getImoNumber() + "," + ship.getRegistration() + "," + ship.getUrl() + "," + ship.getCapacity() + "," + ship.getStatus());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Port> loadPortsFromFile() {
        List<Port> loadedPorts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PORTS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    loadedPorts.add(new Port(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedPorts;
    }

    public void loadShipsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SHIPS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String portCode = parts[0];
                    Port port = findPortByCode(portCode);
                    if (port != null) {
                        Ship ship = new Ship(parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]));
                        ship.setStatus(parts[6]);  // Set the status from the file
                        port.addShip(ship);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Port findPortByCode(String portCode) {
        for (Port port : ports) {
            if (port.getPortCode().equals(portCode)) {
                return port;
            }
        }
        return null;
    }
}
