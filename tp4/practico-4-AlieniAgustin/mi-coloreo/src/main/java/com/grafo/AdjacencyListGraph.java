package com.grafo;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

public class AdjacencyListGraph<T extends Comparable<? super T>> {

    // number of vertex
    private int V;

    // number of edges
    private int E;

    // adjacency list
    private List<Integer>[] adj;

    // Int -> T
    private T[] keys;

    // T -> Int
    private Map<T, Integer> map;

    // colored nodes
    private Map<Integer, Integer> coloredNodes;

    /*
     * @pre: v >= 0
     * 
     * @post: creates a graph where the capacity is v
     */
    public AdjacencyListGraph(int v) {
        if (v < 0)
            throw new IllegalArgumentException();

        V = 0;
        E = 0;
        adj = new LinkedList[v];
        keys = (T[]) new Comparable[v];
        map = new TreeMap<>();
    }

    /**
     * @post Returns the number of vertices in this graph.
     */
    public int V() {
        return V;
    }

    /**
     * @post Returns the number of edges in this graph.
     */
    public int E() {
        return E;
    }

    /**
     * @post Returns true if there is no vertex with label v
     *       in this graph.
     */
    public boolean containsVertex(T v) {
        return map.containsKey(v);
    }

    /**
     * @pre !containsVertex(v).
     * @post Adds the vertex with label v to this graph.
     */
    public void addVertex(T v) {
        if (containsVertex(v))
            throw new IllegalArgumentException();

        adj[V] = new LinkedList<>();
        keys[V] = v;
        map.put(v, V);
        V++;
    }

    /**
     * @pre v and w are vertices of the graph
     * @post Adds the undirected edge v-w to this graph.
     */
    public void addEdge(T v, T w) {
        if (!containsVertex(v) || !containsVertex(w))
            throw new IllegalArgumentException();

        int indexOfv = indexOf(v);
        int indexOfw = indexOf(w);

        adj[indexOfv].add(indexOfw);
        adj[indexOfw].add(indexOfv);
        E++;
    }

    /*
     * @pre: containsVertex(v)
     * 
     * @post: returns the integer associated with the vertex v
     */
    public int indexOf(T v) {
        if (!containsVertex(v))
            throw new IllegalArgumentException();

        return map.get(v);
    }

    /*
     * @pre 0 <= k < V
     * 
     * @post returns the value associated with the vertex whose associated integer
     * is k
     */
    public T valueOf(int k) {
        if (k < 0 || k >= V)
            throw new IllegalArgumentException();

        return keys[k];
    }

    /**
     * @post Returns a string representation of this graph.
     */
    public String toString() {
        String answer = "";
        for (int i = 0; i < V; i++) {
            answer += valueOf(i).toString() + ": ";
            for (int j : adj[i])
                answer += valueOf(j).toString() + " ";
            answer += "\n";
        }
        return answer;
    }

    /*
     * @pre containsVertex(v)
     * 
     * @post returns all vertices adjacent to v
     */
    public List<Integer> adj(T v) {
        if (!containsVertex(v))
            throw new IllegalArgumentException();

        return adj[indexOf(v)];
    }

    /*
     * @pre containsVertex(v)
     * 
     * @post returns all vertices adjacent to index v
     */
    public List<Integer> adj(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException();

        return adj[v];
    }

    /**
     * @pre visitingOrder.length = V && 0 <= visitingOrder[i] < V for all i
     * @post returns the number of colors with which the graph can be colored
     */
    public int coloredGraph(int[] visitingOrder) {
        if (visitingOrder.length != V)
            throw new IllegalArgumentException();
        for (int i = 0; i < visitingOrder.length; i++)
            if (visitingOrder[i] < 0 || visitingOrder[i] >= V)
                throw new IllegalArgumentException();

        coloredNodes = new HashMap<>();
        int colors = 0;
        while (coloredNodes.size() < visitingOrder.length) {
            for (int i = 0; i < visitingOrder.length; i++) {
                int v = visitingOrder[i];
                if (!coloredNodes.containsKey(v)) {
                    List<Integer> adjacent = adj(v);
                    boolean color = true;
                    for (int j = 0; j < adjacent.size() && color; j++) {
                        int e = adjacent.get(j);
                        if (coloredNodes.containsKey(e) && coloredNodes.get(e) == colors)
                            color = false;
                    }
                    if (color)
                        coloredNodes.put(v, colors);
                }
            }
            colors++;
        }
        return colors;
    }

}
