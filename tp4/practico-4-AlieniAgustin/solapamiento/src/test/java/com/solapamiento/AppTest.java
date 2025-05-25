package com.solapamiento;

import junit.framework.TestCase; // JUnit 4 import

import org.junit.Test; // JUnit 4 import

import java.util.List;
import java.util.LinkedList;

import static org.junit.Assert.*; // JUnit 4 assertions

public class AppTest {

    @Test
    public void test() {
        Cita c1 = new Cita(1, 3, 10);
        Cita c2 = new Cita(2, 5, 11);
        Cita c3 = new Cita(5, 6, 3);
        List<Cita> citas = new LinkedList<>();
        citas.add(c1);
        citas.add(c2);
        citas.add(c3);
        String expected = "[P=" + 11 + ";I=" + 2 + ";F=" + 5 + "]";
        assertEquals(expected, App.citasOptimas(citas).toString());
    }
}
