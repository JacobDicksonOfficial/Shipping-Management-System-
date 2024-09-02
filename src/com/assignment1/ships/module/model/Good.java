package com.assignment1.ships.module.model;

public class Good {

    private String name;  // Name of the good
    private double price;  // Price of the good
    private double weight;  // Weight of the good

    /**
     * Constructor to create a new Good object with the specified name, price, and weight.
     *
     * @param name   The name of the good.
     * @param price  The price of the good.
     * @param weight The weight of the good.
     */
    public Good(String name, double price, double weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    /**
     * Gets the name of the good.
     *
     * @return The name of the good.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the good.
     *
     * @param name The name of the good.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the good.
     *
     * @return The price of the good.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the good.
     *
     * @param price The price of the good.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the weight of the good.
     *
     * @return The weight of the good.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the good.
     *
     * @param weight The weight of the good.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Converts the Good object to a String format suitable for saving to a file.
     *
     * @return A string representation of the good in the format "name,price,weight".
     */
    public String toFileString() {
        return name + "," + price + "," + weight;
    }

    /**
     * Creates a Good object from a String that was read from a file.
     *
     * @param fileString A string representation of a good in the format "name,price,weight".
     * @return A new Good object with the name, price, and weight parsed from the string.
     */
    public static Good fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        String name = parts[0];
        double price = Double.parseDouble(parts[1]);
        double weight = Double.parseDouble(parts[2]);
        return new Good(name, price, weight);
    }
}
