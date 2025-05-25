package com.arboles;

import java.util.List;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.PriorityQueue;

public class ListAdjacencyGraphNoDirWeight<T extends Comparable<? super T>> {

    // cant v
    private int V;
    // cant a
    private int E;
    // T -> int
    private TreeMap<T, Integer> map;
    // int -> T
    private T[] keys;
    // listas de adyacencia
    private List<DirectedEdge>[] adj;

    /**
     * @pre 0 <= v
     */
    public ListAdjacencyGraphNoDirWeight(int v) {
        if (v < 0)
            throw new IllegalArgumentException();

        this.V = 0;
        this.E = 0;
        map = new TreeMap<>();
        keys = (T[]) new Comparable[v];
        adj = new List[v];
    }

    /**
     * @post retorna la cantidad de vertices del grafo
     */
    public int V() {
        return V;
    }

    /**
     * @post retorna la cantidad de aristas del grafo
     */
    public int E() {
        return E;
    }

    /**
     * @pre !containsVertex(v)
     * @post agrega el vertice con la etiqueta v al grafo
     */
    public void addVertex(T v) {
        if (containsVertex(v))
            throw new IllegalArgumentException();

        int i = V;
        map.put(v, i);
        keys[i] = v;
        adj[i] = new LinkedList<>();
        V++;
    }

    /**
     * @post retorna true sii hay un vertice con la etiqueta v en el grafo
     */
    public boolean containsVertex(T v) {
        return map.containsKey(v);
    }

    /**
     * @pre 0 <= e.from < V && 0 <= e.to < V
     * @post agrega la arista no dirigida v-w al grafo
     */
    public void addEdge(DirectedEdge e) {
        if (e.from < 0 || e.from >= V || e.to < 0 || e.to >= V)
            throw new IllegalArgumentException();

        adj[e.from].add(e);
        DirectedEdge e2 = new DirectedEdge(e.to, e.from, e.weight);
        adj[e.to].add(e2);
        E++;
    }

    private int indexOf(T v) {
        if (!containsVertex(v))
            throw new IllegalArgumentException();

        return map.get(v);
    }

    private T nameOf(int n) {
        if (n < 0 || n >= V)
            throw new IllegalArgumentException();

        return keys[n];
    }

    public String toString() {
        String answer = "";
        for (int i = 0; i < V; i++) {
            answer += nameOf(i) + ": ";
            for (DirectedEdge e : adj(i))
                answer += nameOf(e.to) + " (" + e.weight + ") ";
            answer += "\n";
        }
        return answer;
    }

    /**
     * @pre 0 <= v < V
     * @post retorna la lista de las aristas que salen del vertice cuyo entero
     *       asociado es v
     */
    public List<DirectedEdge> adj(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException();

        return adj[v];
    }

    public ListAdjacencyGraphNoDirWeight<T> prim(int v) {
        if (v < 0 || v >= this.V())
            throw new IllegalArgumentException();

        int i = 0;
        ListAdjacencyGraphNoDirWeight<T> g = new ListAdjacencyGraphNoDirWeight<>(this.V());
        PriorityQueue<DirectedEdge> pq = new PriorityQueue<>();
        g.addVertex(this.nameOf(v));

        while (g.V() < this.V()) {
            for (DirectedEdge d : this.adj(v))
                if (!g.containsVertex(this.nameOf(d.to)))
                    pq.add(d);

            DirectedEdge d;
            while (true) {
                d = pq.poll();
                if (!g.containsVertex(this.nameOf(d.to)))
                    break;
            }

            v = d.to;
            g.addVertex(this.nameOf(v));
            DirectedEdge e = new DirectedEdge(i, i + 1, d.weight);
            i++;
            g.addEdge(e);
        }
        return g;
    }
}