package com.assignment1.ships.module.model;

public class Port {

    private String portName;
    private String portCode;
    private String country;

    public Port(String portName, String portCode, String country) {
        this.portName = portName;
        this.portCode = portCode;
        this.country = country;
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
}
