package com.grafo;

import junit.framework.TestCase; // JUnit 4 import

import org.junit.Test; // JUnit 4 import

import static org.junit.Assert.*; // JUnit 4 assertions

public class AppTest {

    @Test
    public void test1() {
        AdjacencyListGraph<String> g = new AdjacencyListGraph<>(8);
        g.addVertex("a");
        g.addVertex("b");
        g.addVertex("c");
        g.addVertex("d");
        g.addVertex("e");
        g.addVertex("f");
        g.addVertex("g");
        g.addVertex("h");
        g.addEdge("a", "b");
        g.addEdge("a", "c");
        g.addEdge("a", "d");
        g.addEdge("a", "e");
        g.addEdge("b", "c");
        g.addEdge("b", "d");
        g.addEdge("e", "f");
        g.addEdge("e", "g");
        g.addEdge("f", "g");
        g.addEdge("f", "h");
        g.addEdge("g", "h");
        g.addEdge("h", "b");
        int[] visitingOrder = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
        assertEquals(g.coloredGraph(visitingOrder), 4);
    }

    @Test
    public void test2() {
        AdjacencyListGraph<String> g = new AdjacencyListGraph<>(8);
        g.addVertex("a");
        g.addVertex("b");
        g.addVertex("c");
        g.addVertex("d");
        g.addVertex("e");
        g.addVertex("f");
        g.addVertex("g");
        g.addVertex("h");
        g.addEdge("a", "b");
        g.addEdge("a", "c");
        g.addEdge("a", "d");
        g.addEdge("a", "e");
        g.addEdge("b", "c");
        g.addEdge("b", "d");
        g.addEdge("e", "f");
        g.addEdge("e", "g");
        g.addEdge("f", "g");
        g.addEdge("f", "h");
        g.addEdge("g", "h");
        g.addEdge("h", "b");
        int[] visitingOrder = new int[] { 0, 5, 1, 6, 4, 2, 3, 7 };
        assertEquals(g.coloredGraph(visitingOrder), 3);
    }

}
