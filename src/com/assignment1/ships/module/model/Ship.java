package com.assignment1.ships.module.model;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private String shipName;
    private String imoNumber;
    private String registration;
    private String url;
    private int capacity;
    private String status;
    private List<Container> containers;

    public Ship(String shipName, String imoNumber, String registration, String url, int capacity) {
        this.shipName = shipName;
        this.imoNumber = imoNumber;
        this.registration = registration;
        this.url = url;
        this.capacity = capacity;
        this.status = "At Port";
        this.containers = new ArrayList<>();
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

    public List<Container> getContainers() {
        return containers;
    }

    public void addContainer(Container container) {
        containers.add(container);
    }

    public void removeContainer(Container container) {
        containers.remove(container);
    }
}
