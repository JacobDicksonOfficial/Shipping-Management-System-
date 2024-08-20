package com.assignment1.ships.module.model;

public class Ship {

    private String shipName;
    private String imoNumber;
    private String registration;
    private String url;
    private int capacity;
    private String status;  // New field for ship status

    public Ship(String shipName, String imoNumber, String registration, String url, int capacity) {
        this.shipName = shipName;
        this.imoNumber = imoNumber;
        this.registration = registration;
        this.url = url;
        this.capacity = capacity;
        this.status = "At Port";  // Default status when a ship is created
    }

    // Getters and setters
    public String getShipName() {
        return shipName;
    }

    public String getImoNumber() {
        return imoNumber;
    }

    public String getRegistration() {
        return registration;
    }

    public String getUrl() {
        return url;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
