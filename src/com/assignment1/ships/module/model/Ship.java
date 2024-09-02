package com.assignment1.ships.module.model;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private String shipName;  // The name of the ship
    private String imoNumber;  // The IMO (International Maritime Organization) number of the ship
    private String registration;  // The registration code of the ship
    private String url;  // The URL associated with the ship (e.g., tracking or information link)
    private int capacity;  // The capacity of the ship, typically in TEU or DWT
    private String status;  // The current status of the ship (e.g., "At Port", "At Sea")
    private List<Container> containers;  // List of containers currently on the ship

    /**
     * Constructor to create a new Ship object with the specified name, IMO number,
     * registration, URL, and capacity. The ship's status is initialized to "At Port".
     *
     * @param shipName     The name of the ship.
     * @param imoNumber    The IMO number of the ship.
     * @param registration The registration code of the ship.
     * @param url          The URL associated with the ship.
     * @param capacity     The capacity of the ship, typically in TEU or DWT.
     */
    public Ship(String shipName, String imoNumber, String registration, String url, int capacity) {
        this.shipName = shipName;
        this.imoNumber = imoNumber;
        this.registration = registration;
        this.url = url;
        this.capacity = capacity;
        this.status = "At Port";  // Default status set to "At Port"
        this.containers = new ArrayList<>();  // Initialize the list of containers
    }

    // Getters and setters

    /**
     * Gets the name of the ship.
     *
     * @return The name of the ship.
     */
    public String getShipName() {
        return shipName;
    }

    /**
     * Gets the IMO number of the ship.
     *
     * @return The IMO number of the ship.
     */
    public String getImoNumber() {
        return imoNumber;
    }

    /**
     * Gets the registration code of the ship.
     *
     * @return The registration code of the ship.
     */
    public String getRegistration() {
        return registration;
    }

    /**
     * Gets the URL associated with the ship.
     *
     * @return The URL associated with the ship.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets the capacity of the ship.
     *
     * @return The capacity of the ship.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Gets the current status of the ship.
     *
     * @return The current status of the ship (e.g., "At Port", "At Sea").
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the current status of the ship.
     *
     * @param status The new status of the ship (e.g., "At Port", "At Sea").
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the list of containers currently on the ship.
     *
     * @return The list of containers on the ship.
     */
    public List<Container> getContainers() {
        return containers;
    }

    /**
     * Adds a container to the ship.
     *
     * @param container The container to add to the ship.
     */
    public void addContainer(Container container) {
        containers.add(container);
    }

    /**
     * Removes a container from the ship.
     *
     * @param container The container to remove from the ship.
     */
    public void removeContainer(Container container) {
        containers.remove(container);
    }
}
