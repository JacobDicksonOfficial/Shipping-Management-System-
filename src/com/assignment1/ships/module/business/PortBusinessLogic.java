package com.assignment1.ships.module.business;

import com.assignment1.ships.module.model.Container;
import com.assignment1.ships.module.model.Good;
import com.assignment1.ships.module.model.Pallet;
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
    private static final String CONTAINERS_FILE_PATH = "src/com/assignment1/ships/module/data/containers.txt";
    private static final String PALLETS_FILE_PATH = "src/com/assignment1/ships/module/data/pallets.txt";
    private static final String GOODS_FILE_PATH = "src/com/assignment1/ships/module/data/goods.txt";  // New file for goods

    public PortBusinessLogic() {
        ports = loadPortsFromFile();
        loadShipsFromFile();
        loadContainersFromFile();
        loadPalletsFromFile();
        loadGoodsFromFile(); // Load goods after pallets are loaded
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

    public void saveContainersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONTAINERS_FILE_PATH))) {
            for (Port port : ports) {
                for (Ship ship : port.getShips()) {
                    for (Container container : ship.getContainers()) {
                        writer.write(ship.getImoNumber() + "," + container.getContainerCode() + "," + container.getCubic() + "," + container.getStatus());
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void savePalletsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PALLETS_FILE_PATH))) {
            for (Port port : ports) {
                for (Ship ship : port.getShips()) {
                    for (Container container : ship.getContainers()) {
                        for (Pallet pallet : container.getPallets()) {
                            writer.write(container.getContainerCode() + "," + pallet.getCompany() + "," + pallet.getTypeOfGood() + "," + pallet.getWeight() + "," + pallet.getSize());
                            writer.newLine();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // New methods to save and load goods
    public void saveGoodsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(GOODS_FILE_PATH))) {
            for (Port port : ports) {
                for (Ship ship : port.getShips()) {
                    for (Container container : ship.getContainers()) {
                        for (Pallet pallet : container.getPallets()) {
                            for (Good good : pallet.getGoods()) {
                                writer.write(container.getContainerCode() + "," + good.toFileString());
                                writer.newLine();
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadGoodsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(GOODS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);  // Split into container code and the rest
                String containerCode = parts[0];
                String goodData = parts[1];

                Container container = findContainerByCode(containerCode);
                if (container != null) {
                    Pallet pallet = container.getPallets().get(0); // Assuming one pallet per container for simplicity
                    pallet.addGood(Good.fromFileString(goodData));
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

    public void loadContainersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CONTAINERS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {  // Updated to handle status field
                    String imoNumber = parts[0];
                    Ship ship = findShipByImo(imoNumber);
                    if (ship != null) {
                        Container container = new Container(parts[1], Double.parseDouble(parts[2]));
                        container.setStatus(parts[3]);  // Set the status from the file
                        ship.addContainer(container);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadPalletsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PALLETS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) { // Company, Type of Good, Weight, Size
                    String containerCode = parts[0];
                    Container container = findContainerByCode(containerCode);
                    if (container != null) {
                        Pallet pallet = new Pallet(parts[1], parts[2], Double.parseDouble(parts[3]), Double.parseDouble(parts[4]));
                        container.addPallet(pallet);
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

    private Ship findShipByImo(String imoNumber) {
        for (Port port : ports) {
            for (Ship ship : port.getShips()) {
                if (ship.getImoNumber().equals(imoNumber)) {
                    return ship;
                }
            }
        }
        return null;
    }

    private Container findContainerByCode(String containerCode) {
        for (Port port : ports) {
            for (Ship ship : port.getShips()) {
                for (Container container : ship.getContainers()) {
                    if (container.getContainerCode().equals(containerCode)) {
                        return container;
                    }
                }
            }
        }
        return null;
    }
}
