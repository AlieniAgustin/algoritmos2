package com.arboles;

import junit.framework.TestCase; // JUnit 4 import

import org.junit.Test; // JUnit 4 import

import static org.junit.Assert.*; // JUnit 4 assertions

public class AppTest {

    @Test
    public void test() {
        ListAdjacencyGraphNoDirWeight<String> g = new ListAdjacencyGraphNoDirWeight<>(6);
        g.addVertex("a");
        g.addVertex("b");
        g.addVertex("c");
        g.addVertex("d");
        g.addVertex("e");
        g.addVertex("f");
        DirectedEdge e0 = new DirectedEdge(0, 1, 3.0);
        DirectedEdge e1 = new DirectedEdge(0, 5, 5.0);
        DirectedEdge e2 = new DirectedEdge(0, 4, 6.0);
        DirectedEdge e3 = new DirectedEdge(1, 2, 1.0);
        DirectedEdge e4 = new DirectedEdge(1, 5, 4.0);
        DirectedEdge e5 = new DirectedEdge(2, 3, 6.0);
        DirectedEdge e6 = new DirectedEdge(2, 5, 4.0);
        DirectedEdge e7 = new DirectedEdge(3, 4, 8.0);
        DirectedEdge e8 = new DirectedEdge(3, 5, 5.0);
        DirectedEdge e9 = new DirectedEdge(4, 5, 2.0);
        g.addEdge(e0);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        g.addEdge(e7);
        g.addEdge(e8);
        g.addEdge(e9);

        // String expected = "a: b (3.0) \nb: a (3.0) c (1.0) f (4.0) \nc: b (1.0) \nd:
        // f (5.0) \ne: f (2.0) \nf: b (4.0) e (2.0) d (5.0) \n";
        // assertEquals(g.prim(0).toString(), expected);
        ListAdjacencyGraphNoDirWeight<String> g2 = g.prim(0);
        assertEquals(g2.V(), g.V());
        assertEquals(g2.E(), g.E());
    }
}
