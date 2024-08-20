package com.assignment1.ships.module.model;

public class Container {

    private String containerCode;
    private double cubic;

    public Container(String containerCode, double cubic) {
        this.containerCode = containerCode;
        this.cubic = cubic;
    }

    public String getContainerCode() {
        return containerCode;
    }

    public double getCubic() {
        return cubic;
    }

    public void setContainerCode(String containerCode) {
        this.containerCode = containerCode;
    }

    public void setCubic(double cubic) {
        this.cubic = cubic;
    }
}

