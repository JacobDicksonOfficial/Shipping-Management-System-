package com.assignment1.ships.module.model;

public class Pallet {

    private String company;
    private String typeOfGood;
    private double weight;
    private double size;

    public Pallet(String company, String typeOfGood, double weight, double size) {
        this.company = company;
        this.typeOfGood = typeOfGood;
        this.weight = weight;
        this.size = size;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTypeOfGood() {
        return typeOfGood;
    }

    public void setTypeOfGood(String typeOfGood) {
        this.typeOfGood = typeOfGood;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}

