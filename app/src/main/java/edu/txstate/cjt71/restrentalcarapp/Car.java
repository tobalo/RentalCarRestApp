package edu.txstate.cjt71.restrentalcarapp;

/**
 * Created by dell on 4/25/2018.
 */

public class Car {
    private String id;
    private String name;
    private String brand;
    private String color;
    private double costPerDay;

    public Car(String id, String name, String brand, String color, double costPerDay) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.color = color;
        this.costPerDay = costPerDay;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
    }

    @Override
    public String toString() {
        return  brand + " " + name;
    }
}
