package com.assignment1.ships.module.model;

public class Good {

    private String name;
    private double price;
    private double weight;

    public Good(String name, double price, double weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Convert Good to a String format for saving to file
    public String toFileString() {
        return name + "," + price + "," + weight;
    }

    // Create a Good from a String read from the file
    public static Good fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        String name = parts[0];
        double price = Double.parseDouble(parts[1]);
        double weight = Double.parseDouble(parts[2]);
        return new Good(name, price, weight);
    }
}
