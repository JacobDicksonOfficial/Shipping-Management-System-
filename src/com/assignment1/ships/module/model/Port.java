package com.assignment1.ships.module.model;

public class Port {

    private String portName;
    private String portCode;
    private String country;
    private String portType;  // New field for Port Type
    private String coms;      // New field for COMS

    public Port(String portName, String portCode, String country, String portType, String coms) {
        this.portName = portName;
        this.portCode = portCode;
        this.country = country;
        this.portType = portType;
        this.coms = coms;
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
}
