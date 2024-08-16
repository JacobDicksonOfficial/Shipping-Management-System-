package com.assignment1.ships.module.business;

import com.assignment1.ships.module.model.Port;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PortBusinessLogic {

    private List<Port> ports;
    private static final String FILE_PATH = "src/com/assignment1/ships/module/data/ports.txt";

    public PortBusinessLogic() {
        // Initialize the ports list by loading from the file
        ports = loadPortsFromFile();
    }

    // Method to add a new port and save it to the file
    public void addPort(Port port) {
        ports.add(port);
        savePortsToFile();
    }

    // Method to save the list of ports to the file
    public void savePortsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Port port : ports) {
                writer.write(port.getPortName() + "," + port.getPortCode() + "," + port.getCountry() + "," + port.getPortType() + "," + port.getComs());
                writer.newLine();  // Add a new line after each port entry
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load the list of ports from the file
    public List<Port> loadPortsFromFile() {
        List<Port> loadedPorts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {  // Ensure there are exactly 5 parts (portName, portCode, country, portType, coms)
                    loadedPorts.add(new Port(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedPorts;
    }

    // Method to get all ports
    public List<Port> getAllPorts() {
        return ports;
    }
}

