package com.assignment1.ships.module.model;

import java.util.ArrayList;
import java.util.List;

public class Pallet {

    private String company;
    private String typeOfGood;
    private double weight;
    private double size;
    private List<Good> goods;

    public Pallet(String company, String typeOfGood, double weight, double size) {
        this.company = company;
        this.typeOfGood = typeOfGood;
        this.weight = weight;
        this.size = size;
        this.goods = new ArrayList<>();
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

    public List<Good> getGoods() {
        return goods;
    }

    public void addGood(Good good) {
        goods.add(good);
    }

    public void removeGood(Good good) {
        goods.remove(good);
    }

    // Load goods from a list of strings (from the file)
    public void loadGoods(List<String> goodsStrings) {
        for (String goodString : goodsStrings) {
            this.goods.add(Good.fromFileString(goodString));
        }
    }

    // Save goods to a list of strings (for saving to the file)
    public List<String> saveGoods() {
        List<String> goodsStrings = new ArrayList<>();
        for (Good good : goods) {
            goodsStrings.add(good.toFileString());
        }
        return goodsStrings;
    }
}
