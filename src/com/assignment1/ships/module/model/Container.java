package com.assignment1.ships.module.model;

public class Container {

    private String containerCode;
    private double cubic;
    private String status;  // New field for status

    public Container(String containerCode, double cubic) {
        this.containerCode = containerCode;
        this.cubic = cubic;
        this.status = "Unloaded At Port";  // Default status when a container is created
    }

    public String getContainerCode() {
        return containerCode;
    }

    public double getCubic() {
        return cubic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setContainerCode(String containerCode) {
        this.containerCode = containerCode;
    }

    public void setCubic(double cubic) {
        this.cubic = cubic;
    }
}


