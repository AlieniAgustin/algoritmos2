package com.mochila;

import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

public class App {

    public static List<Item> knapsack(double maxWeight, List<Item> items) {
        List<Item> mochila = new LinkedList<>();
        Collections.sort(items);
        for (Item item : items) {
            double weight = item.getWeight();
            if (weight <= maxWeight) {
                mochila.add(item);
                maxWeight -= weight;
            }
        }
        return mochila;
    }

}
