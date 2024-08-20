package com.assignment1.ships.module.model;

public class Ship {

    private String shipName;
    private String imoNumber;
    private String registration;
    private String url;
    private int capacity;

    public Ship(String shipName, String imoNumber, String registration, String url, int capacity) {
        this.shipName = shipName;
        this.imoNumber = imoNumber;
        this.registration = registration;
        this.url = url;
        this.capacity = capacity;
    }

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
}
