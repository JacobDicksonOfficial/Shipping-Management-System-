package com.assignment1.ships.module.model;

import java.util.ArrayList;
import java.util.List;

public class Port {

    private String portName;
    private String portCode;
    private String country;
    private String portType;
    private String coms;
    private List<Ship> ships;

    public Port(String portName, String portCode, String country, String portType, String coms) {
        this.portName = portName;
        this.portCode = portCode;
        this.country = country;
        this.portType = portType;
        this.coms = coms;
        this.ships = new ArrayList<>();
    }

    public String getPortName() {
        return portName;
    }

    public String getPortCode() {
        return portCode;
    }

    public String getCountry() {
        return country;
    }

    public String getPortType() {
        return portType;
    }

    public String getComs() {
        return coms;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public void removeShip(Ship ship) {
        ships.remove(ship);
    }
}
