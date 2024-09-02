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

    private List<Port> ports;  // List to hold all ports
    private static final String PORTS_FILE_PATH = "src/com/assignment1/ships/module/data/ports.txt";  // File path for ports data
    private static final String SHIPS_FILE_PATH = "src/com/assignment1/ships/module/data/ships.txt";  // File path for ships data
    private static final String CONTAINERS_FILE_PATH = "src/com/assignment1/ships/module/data/containers.txt";  // File path for containers data
    private static final String PALLETS_FILE_PATH = "src/com/assignment1/ships/module/data/pallets.txt";  // File path for pallets data
    private static final String GOODS_FILE_PATH = "src/com/assignment1/ships/module/data/goods.txt";  // File path for goods data

    // Constructor initializes ports and loads data from files
    public PortBusinessLogic() {
        ports = loadPortsFromFile();  // Load ports data
        loadShipsFromFile();  // Load ships data
        loadContainersFromFile();  // Load containers data
        loadPalletsFromFile();  // Load pallets data
        loadGoodsFromFile();  // Load goods data
    }

    /**
     * Adds a new port to the list and saves the updated list to the file.
     *
     * @param port The port to be added.
     */
    public void addPort(Port port) {
        ports.add(port);
        savePortsToFile();  // Save updated ports data
    }

    /**
     * Deletes a port from the list and saves the updated list to the file.
     *
     * @param port The port to be deleted.
     */
    public void deletePort(Port port) {
        ports.remove(port);
        savePortsToFile();  // Save updated ports data
    }

    /**
     * Returns a list of all ports.
     *
     * @return List of all ports.
     */
    public List<Port> getAllPorts() {
        return ports;
    }

    /**
     * Returns a list of all pallets from all ports.
     *
     * @return List of all pallets.
     */
    public List<Pallet> getAllPallets() {
        List<Pallet> pallets = new ArrayList<>();
        for (Port port : ports) {
            for (Ship ship : port.getShips()) {
                for (Container container : ship.getContainers()) {
                    pallets.addAll(container.getPallets());
                }
            }
        }
        return pallets;
    }

    /**
     * Clears all ports from the list and saves the empty list to the file.
     */
    public void clearAllPorts() {
        ports.clear();
        savePortsToFile();  // Save cleared ports data
    }

    /**
     * Saves all containers data to the file.
     */
    public void saveContainersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONTAINERS_FILE_PATH))) {
            for (Port port : ports) {
                for (Ship ship : port.getShips()) {
                    for (Container container : ship.getContainers()) {
                        writer.write(ship.getImoNumber() + "," + container.getContainerCode() + "," + container.getCubic() + "," + container.getStatus());
                        writer.newLine();  // Write each container's data to the file
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves all ports data to the file.
     */
    public void savePortsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PORTS_FILE_PATH))) {
            for (Port port : ports) {
                writer.write(port.getPortName() + "," + port.getPortCode() + "," + port.getCountry() + "," + port.getPortType() + "," + port.getComs());
                writer.newLine();  // Write each port's data to the file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves all ships data to the file.
     */
    public void saveShipsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SHIPS_FILE_PATH))) {
            for (Port port : ports) {
                for (Ship ship : port.getShips()) {
                    writer.write(port.getPortCode() + "," + ship.getShipName() + "," + ship.getImoNumber() + "," + ship.getRegistration() + "," + ship.getUrl() + "," + ship.getCapacity() + "," + ship.getStatus());
                    writer.newLine();  // Write each ship's data to the file
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves all pallets data to the file.
     */
    public void savePalletsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PALLETS_FILE_PATH))) {
            for (Port port : ports) {
                for (Ship ship : port.getShips()) {
                    for (Container container : ship.getContainers()) {
                        for (Pallet pallet : container.getPallets()) {
                            writer.write(container.getContainerCode() + "," + pallet.getCompany() + "," + pallet.getTypeOfGood() + "," + pallet.getWeight() + "," + pallet.getSize());
                            writer.newLine();  // Write each pallet's data to the file
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves all goods data to the file.
     */
    public void saveGoodsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(GOODS_FILE_PATH))) {
            for (Port port : ports) {
                for (Ship ship : port.getShips()) {
                    for (Container container : ship.getContainers()) {
                        for (Pallet pallet : container.getPallets()) {
                            for (Good good : pallet.getGoods()) {
                                writer.write(container.getContainerCode() + "," + good.toFileString());
                                writer.newLine();  // Write each good's data to the file
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads goods data from the file and adds them to their respective pallets.
     */
    public void loadGoodsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(GOODS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);  // Split into container code and the rest
                String containerCode = parts[0];
                String goodData = parts[1];

                Container container = findContainerByCode(containerCode);
                if (container != null) {
                    Pallet pallet = container.getPallets().get(0);  // Assuming one pallet per container for simplicity
                    pallet.addGood(Good.fromFileString(goodData));  // Add good to pallet
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads ports data from the file.
     *
     * @return List of loaded ports.
     */
    public List<Port> loadPortsFromFile() {
        List<Port> loadedPorts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PORTS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    loadedPorts.add(new Port(parts[0], parts[1], parts[2], parts[3], parts[4]));  // Create and add a new port
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedPorts;
    }

    /**
     * Loads ships data from the file and adds them to their respective ports.
     */
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
                        port.addShip(ship);  // Add ship to port
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads containers data from the file and adds them to their respective ships.
     */
    public void loadContainersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CONTAINERS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {  // Check for correct number of fields
                    String imoNumber = parts[0];
                    Ship ship = findShipByImo(imoNumber);
                    if (ship != null) {
                        Container container = new Container(parts[1], Double.parseDouble(parts[2]));
                        container.setStatus(parts[3]);  // Set the status from the file
                        ship.addContainer(container);  // Add container to ship
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads pallets data from the file and adds them to their respective containers.
     */
    public void loadPalletsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PALLETS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) { // Check for correct number of fields
                    String containerCode = parts[0];
                    Container container = findContainerByCode(containerCode);
                    if (container != null) {
                        Pallet pallet = new Pallet(parts[1], parts[2], Double.parseDouble(parts[3]), Double.parseDouble(parts[4]));
                        container.addPallet(pallet);  // Add pallet to container
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to find a port by its code.
     *
     * @param portCode The code of the port to find.
     * @return The port with the specified code, or null if not found.
     */
    private Port findPortByCode(String portCode) {
        for (Port port : ports) {
            if (port.getPortCode().equals(portCode)) {
                return port;
            }
        }
        return null;
    }

    /**
     * Helper method to find a ship by its IMO number.
     *
     * @param imoNumber The IMO number of the ship to find.
     * @return The ship with the specified IMO number, or null if not found.
     */
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

    /**
     * Helper method to find a container by its code.
     *
     * @param containerCode The code of the container to find.
     * @return The container with the specified code, or null if not found.
     */
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
