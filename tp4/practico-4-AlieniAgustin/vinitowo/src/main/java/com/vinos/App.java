package com.vinos;

import java.util.List;

public class App {

    public static double precioVinos(List<Double> precios) {
        int inf = 0;
        int sup = precios.size() - 1;
        double ganancia = 0;
        int year = 1;

        while (inf <= sup) {
            double p1 = precios.get(inf);
            double pn = precios.get(sup);
            if (p1 >= pn) {
                sup--;
                ganancia += (pn * year);
            } else {
                inf++;
                ganancia += (p1 * year);
            }
            year++;
        }

        return ganancia;
    }

}
