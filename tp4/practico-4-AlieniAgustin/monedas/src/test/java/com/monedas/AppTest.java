package com.monedas;

import junit.framework.TestCase; // JUnit 4 import

import org.junit.Test; // JUnit 4 import

import static org.junit.Assert.*; // JUnit 4 assertions
import java.util.List;
import java.util.ArrayList;

public class AppTest {

    @Test
    public void test1() {
        int cambio = 63;
        List<Integer> monedas = new ArrayList<>();
        monedas.add(25);
        monedas.add(10);
        monedas.add(5);
        monedas.add(1);
        List<Integer> cantidadMonedas = new ArrayList<>();
        cantidadMonedas.add(0);
        cantidadMonedas.add(4);
        cantidadMonedas.add(2);
        cantidadMonedas.add(2);
        assertTrue(App.monedasCambio(monedas, cantidadMonedas, cambio).isEmpty());
    }

    @Test
    public void test2() {
        int cambio = 63;
        List<Integer> monedas = new ArrayList<>();
        monedas.add(25);
        monedas.add(10);
        monedas.add(5);
        monedas.add(1);
        List<Integer> cantidadMonedas = new ArrayList<>();
        cantidadMonedas.add(2);
        cantidadMonedas.add(3);
        cantidadMonedas.add(0);
        cantidadMonedas.add(4);
        assertEquals("[25, 25, 10, 1, 1, 1]", App.monedasCambio(monedas, cantidadMonedas, cambio).toString());
    }

    @Test
    public void test3() {
        int cambio = 25;
        List<Integer> monedas = new ArrayList<>();
        monedas.add(7);
        monedas.add(5);
        monedas.add(1);
        List<Integer> cantidadMonedas = new ArrayList<>();
        cantidadMonedas.add(4);
        cantidadMonedas.add(0);
        cantidadMonedas.add(10);
        assertEquals("[7, 7, 7, 1, 1, 1, 1]", App.monedasCambio(monedas, cantidadMonedas, cambio).toString());
    }

    @Test
    public void test4() {
        int cambio = 25;
        List<Integer> monedas = new ArrayList<>();
        monedas.add(7);
        monedas.add(5);
        monedas.add(1);
        List<Integer> cantidadMonedas = new ArrayList<>();
        cantidadMonedas.add(4);
        cantidadMonedas.add(9);
        cantidadMonedas.add(10);
        assertEquals("[7, 7, 7, 1, 1, 1, 1]", App.monedasCambio(monedas, cantidadMonedas, cambio).toString());
    }
}
