package com.assignment1.ships.module.model;

import java.util.ArrayList;
import java.util.List;

public class Pallet {

    private String company;  // The company associated with the pallet
    private String typeOfGood;  // The type of goods on the pallet
    private double weight;  // The weight of the pallet
    private double size;  // The size of the pallet
    private List<Good> goods;  // List of goods contained on the pallet

    /**
     * Constructor to create a new Pallet object with the specified company, type of good, weight, and size.
     *
     * @param company   The company associated with the pallet.
     * @param typeOfGood The type of goods on the pallet.
     * @param weight    The weight of the pallet.
     * @param size      The size of the pallet.
     */
    public Pallet(String company, String typeOfGood, double weight, double size) {
        this.company = company;
        this.typeOfGood = typeOfGood;
        this.weight = weight;
        this.size = size;
        this.goods = new ArrayList<>();  // Initialize the list of goods
    }

    /**
     * Gets the company associated with the pallet.
     *
     * @return The company associated with the pallet.
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the company associated with the pallet.
     *
     * @param company The company associated with the pallet.
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Gets the type of goods on the pallet.
     *
     * @return The type of goods on the pallet.
     */
    public String getTypeOfGood() {
        return typeOfGood;
    }

    /**
     * Sets the type of goods on the pallet.
     *
     * @param typeOfGood The type of goods on the pallet.
     */
    public void setTypeOfGood(String typeOfGood) {
        this.typeOfGood = typeOfGood;
    }

    /**
     * Gets the weight of the pallet.
     *
     * @return The weight of the pallet.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the pallet.
     *
     * @param weight The weight of the pallet.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Gets the size of the pallet.
     *
     * @return The size of the pallet.
     */
    public double getSize() {
        return size;
    }

    /**
     * Sets the size of the pallet.
     *
     * @param size The size of the pallet.
     */
    public void setSize(double size) {
        this.size = size;
    }

    /**
     * Gets the list of goods on the pallet.
     *
     * @return The list of goods on the pallet.
     */
    public List<Good> getGoods() {
        return goods;
    }

    /**
     * Adds a good to the pallet.
     *
     * @param good The good to add to the pallet.
     */
    public void addGood(Good good) {
        goods.add(good);
    }

    /**
     * Removes a good from the pallet.
     *
     * @param good The good to remove from the pallet.
     */
    public void removeGood(Good good) {
        goods.remove(good);
    }

    /**
     * Loads goods onto the pallet from a list of strings. Each string represents a good in a file format.
     *
     * @param goodsStrings A list of strings representing goods in a file format.
     */
    public void loadGoods(List<String> goodsStrings) {
        for (String goodString : goodsStrings) {
            this.goods.add(Good.fromFileString(goodString));  // Convert each string to a Good object and add it to the list
        }
    }

    /**
     * Saves the goods on the pallet to a list of strings. Each string represents a good in a file format.
     *
     * @return A list of strings representing goods in a file format.
     */
    public List<String> saveGoods() {
        List<String> goodsStrings = new ArrayList<>();
        for (Good good : goods) {
            goodsStrings.add(good.toFileString());  // Convert each Good object to a string and add it to the list
        }
        return goodsStrings;
    }
}
