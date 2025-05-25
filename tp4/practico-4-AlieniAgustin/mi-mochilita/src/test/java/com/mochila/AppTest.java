package com.mochila;

import junit.framework.TestCase; // JUnit 4 import

import org.junit.Test; // JUnit 4 import

import static org.junit.Assert.*; // JUnit 4 assertions

import java.util.List;
import java.util.LinkedList;

public class AppTest {

    @Test
    public void test() {
        Item i1 = new Item(2, 20);
        Item i2 = new Item(5, 30);
        Item i3 = new Item(10, 50);
        Item i4 = new Item(5, 10);
        List<Item> items = new LinkedList<>();
        items.add(i3);
        items.add(i4);
        items.add(i2);
        items.add(i1);
        List<Item> res = App.knapsack(16, items);
        assertEquals(res.toString(), "[weight=2.0;value=20.0, weight=5.0;value=30.0, weight=5.0;value=10.0]");
    }
}
