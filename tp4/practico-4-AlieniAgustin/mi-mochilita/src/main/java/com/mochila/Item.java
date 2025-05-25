package com.mochila;

public class Item implements Comparable<Item> {
    private double weight;
    private double value;
    private double density;

    public Item(double weight, double value) {
        this.weight = weight;
        this.value = value;
        obtainDensity();
    }

    private void obtainDensity() {
        density = value / weight;
    }

    public double getWeight() {
        return weight;
    }

    public double getValue() {
        return value;
    }

    public double getDensity() {
        return density;
    }

    public String toString() {
        return "weight=" + weight + ";value=" + value;
    }

    @Override
    public int compareTo(Item other) {
        return Double.compare(other.getDensity(), this.density); // de mayor a menor
    }
}
