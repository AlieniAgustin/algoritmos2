package com.vinos;

import junit.framework.TestCase; // JUnit 4 import

import org.junit.Test; // JUnit 4 import

import static org.junit.Assert.*; // JUnit 4 assertions

import java.util.List;
import java.util.LinkedList;

public class AppTest {

    @Test
    public void test() {
        List<Double> precios = new LinkedList<>();
        precios.add(7.0);
        precios.add(1.0);
        precios.add(9.0);
        precios.add(14.0);
        precios.add(1.0);
        assertEquals(124.0, App.precioVinos(precios), 0.0001);
    }

}
