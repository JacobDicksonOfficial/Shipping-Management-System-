package com.assignment1.ships.module.model;

import java.util.ArrayList;
import java.util.List;

public class Port {

    private String portName;  // The name of the port
    private String portCode;  // The unique code identifying the port
    private String country;  // The country where the port is located
    private String portType;  // The type of the port (e.g., "Container", "Bulk")
    private String coms;  // Communications information for the port
    private List<Ship> ships;  // List of ships associated with the port

    /**
     * Constructor to create a new Port object with the specified name, code, country, type, and communications.
     *
     * @param portName  The name of the port.
     * @param portCode  The unique code identifying the port.
     * @param country   The country where the port is located.
     * @param portType  The type of the port (e.g., "Container", "Bulk").
     * @param coms      The communications information for the port.
     */
    public Port(String portName, String portCode, String country, String portType, String coms) {
        this.portName = portName;
        this.portCode = portCode;
        this.country = country;
        this.portType = portType;
        this.coms = coms;
        this.ships = new ArrayList<>();  // Initialize the list of ships
    }

    /**
     * Gets the name of the port.
     *
     * @return The name of the port.
     */
    public String getPortName() {
        return portName;
    }

    /**
     * Gets the unique code identifying the port.
     *
     * @return The unique code identifying the port.
     */
    public String getPortCode() {
        return portCode;
    }

    /**
     * Gets the country where the port is located.
     *
     * @return The country where the port is located.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Gets the type of the port.
     *
     * @return The type of the port (e.g., "Container", "Bulk").
     */
    public String getPortType() {
        return portType;
    }

    /**
     * Gets the communications information for the port.
     *
     * @return The communications information for the port.
     */
    public String getComs() {
        return coms;
    }

    /**
     * Gets the list of ships associated with the port.
     *
     * @return The list of ships associated with the port.
     */
    public List<Ship> getShips() {
        return ships;
    }

    /**
     * Adds a ship to the port's list of ships.
     *
     * @param ship The ship to add to the port.
     */
    public void addShip(Ship ship) {
        ships.add(ship);
    }

    /**
     * Removes a ship from the port's list of ships.
     *
     * @param ship The ship to remove from the port.
     */
    public void removeShip(Ship ship) {
        ships.remove(ship);
    }
}
