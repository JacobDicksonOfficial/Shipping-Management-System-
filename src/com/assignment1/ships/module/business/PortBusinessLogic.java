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
        ports = loadPortsFromFile();
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
        ports.clear();  // Clear the in-memory list of ports
        savePortsToFile();  // Save the empty list to the file to persist the change
    }


    private void savePortsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Port port : ports) {
                writer.write(port.getPortName() + "," + port.getPortCode() + "," + port.getCountry() + "," + port.getPortType() + "," + port.getComs());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Port> loadPortsFromFile() {
        List<Port> loadedPorts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
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
}


