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
        loadShipsFromFile();  // Load ships after loading ports
    }

    public void addPort(Port port) {
        ports.add(port);
        savePortsToFile();
    }

    public void deletePort(Port port) {
        ports.remove(port);
        savePortsToFile();
        saveShipsToFile();
    }

    public List<Port> getAllPorts() {
        return ports;
    }

    public void clearAllPorts() {
        ports.clear();
        savePortsToFile();
        saveShipsToFile();
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
        System.out.println("Saving ships to ships.txt...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SHIPS_FILE_PATH))) {
            for (Port port : ports) {
                for (Ship ship : port.getShips()) {
                    System.out.println("Saving ship: " + ship.getShipName() + " for port: " + port.getPortName());
                    writer.write(port.getPortCode() + "," + ship.getShipName() + "," + ship.getImoNumber() + "," + ship.getRegistration() + "," + ship.getUrl() + "," + ship.getCapacity());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Port> loadPortsFromFile() {
        List<Port> loadedPorts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PORTS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Port port = new Port(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    loadedPorts.add(port);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedPorts;
    }

    private void loadShipsFromFile() {
        System.out.println("Loading ships from ships.txt...");
        try (BufferedReader reader = new BufferedReader(new FileReader(SHIPS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String portCode = parts[0];
                    String shipName = parts[1];
                    String imoNumber = parts[2];
                    String registration = parts[3];
                    String url = parts[4];
                    int capacity = Integer.parseInt(parts[5]);

                    // Find the port that corresponds to this ship
                    for (Port port : ports) {
                        if (port.getPortCode().equals(portCode)) {
                            Ship ship = new Ship(shipName, imoNumber, registration, url, capacity);
                            port.addShip(ship);
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
