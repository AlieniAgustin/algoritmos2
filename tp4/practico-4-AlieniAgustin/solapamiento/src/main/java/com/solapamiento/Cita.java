package com.solapamiento;

public class Cita implements Comparable<Cita> {
    private int prioridad;
    private int hInicio;
    private int hFin;

    public Cita(int hInicio, int hFin, int prioridad) {
        this.hInicio = hInicio;
        this.hFin = hFin;
        this.prioridad = prioridad;
    }

    public int getHInicio() {
        return hInicio;
    }

    public int getHFin() {
        return hFin;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public int compareTo(Cita other) {
        return Integer.compare(other.getPrioridad(), this.prioridad); // mayor a menor prioridad
    }

    public String toString() {
        return "P=" + prioridad + ";I=" + hInicio + ";F=" + hFin;
    }

}
