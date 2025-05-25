package com.arboles;

/**
 * se van a almacenar en las listas de adyacencias objetos de tipo
 * DirectedEdge, que representan las aristas del grafo
 */

public class DirectedEdge implements Comparable<DirectedEdge> {
    public int from;
    public int to;
    public double weight;

    /**
     * @post inicializa una arista dirigida desde el vertice from hasta el vertice
     *       to con el
     *       peso dado
     */
    public DirectedEdge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(DirectedEdge other) {
        return Double.compare(this.weight, other.weight); // de menor a mayor segun peso
    }
}