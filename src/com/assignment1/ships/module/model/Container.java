package com.assignment1.ships.module.model;

import java.util.ArrayList;
import java.util.List;

public class Container {

    private String containerCode;  // Unique code identifying the container
    private double cubic;  // Cubic capacity of the container
    private String status;  // Status of the container (e.g., "Loaded", "Unloaded")
    private List<Pallet> pallets;  // List of pallets contained within the container

    /**
     * Constructor to create a new container with the specified code and cubic capacity.
     *
     * @param containerCode The unique code identifying the container.
     * @param cubic The cubic capacity of the container.
     */
    public Container(String containerCode, double cubic) {
        this.containerCode = containerCode;
        this.cubic = cubic;
        this.pallets = new ArrayList<>();  // Initialize the list of pallets
    }

    /**
     * Gets the container code.
     *
     * @return The unique code identifying the container.
     */
    public String getContainerCode() {
        return containerCode;
    }

    /**
     * Sets the container code.
     *
     * @param containerCode The unique code identifying the container.
     */
    public void setContainerCode(String containerCode) {
        this.containerCode = containerCode;
    }

    /**
     * Gets the cubic capacity of the container.
     *
     * @return The cubic capacity of the container.
     */
    public double getCubic() {
        return cubic;
    }

    /**
     * Sets the cubic capacity of the container.
     *
     * @param cubic The cubic capacity of the container.
     */
    public void setCubic(double cubic) {
        this.cubic = cubic;
    }

    /**
     * Gets the status of the container.
     *
     * @return The status of the container.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the container.
     *
     * @param status The status of the container (e.g., "Loaded", "Unloaded").
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the list of pallets contained within the container.
     *
     * @return The list of pallets in the container.
     */
    public List<Pallet> getPallets() {
        return pallets;
    }

    /**
     * Adds a pallet to the container.
     *
     * @param pallet The pallet to add to the container.
     */
    public void addPallet(Pallet pallet) {
        pallets.add(pallet);
    }

    /**
     * Removes a pallet from the container.
     *
     * @param pallet The pallet to remove from the container.
     */
    public void removePallet(Pallet pallet) {
        pallets.remove(pallet);
    }
}


