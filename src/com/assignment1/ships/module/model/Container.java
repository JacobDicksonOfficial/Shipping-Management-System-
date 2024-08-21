package com.assignment1.ships.module.model;

import java.util.ArrayList;
import java.util.List;

public class Container {

    private String containerCode;
    private double cubic;
    private String status;
    private List<Pallet> pallets;

    public Container(String containerCode, double cubic) {
        this.containerCode = containerCode;
        this.cubic = cubic;
        this.pallets = new ArrayList<>();
    }

    public String getContainerCode() {
        return containerCode;
    }

    public void setContainerCode(String containerCode) {
        this.containerCode = containerCode;
    }

    public double getCubic() {
        return cubic;
    }

    public void setCubic(double cubic) {
        this.cubic = cubic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Pallet> getPallets() {
        return pallets;
    }

    public void addPallet(Pallet pallet) {
        pallets.add(pallet);
    }

    public void removePallet(Pallet pallet) {
        pallets.remove(pallet);
    }
}


