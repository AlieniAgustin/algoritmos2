package com.monedas;

import java.util.List;
import java.util.ArrayList;

public class App {

    public static List<Integer> monedasCambio(List<Integer> monedas, List<Integer> cantidadMonedas, int cambio) {
        if (cambio <= 0 || monedas.size() != cantidadMonedas.size())
            throw new IllegalArgumentException();

        List<Integer> res = new ArrayList<>();
        boolean hayCambioExacto = monedasCambio(monedas, cantidadMonedas, cambio, res);

        if (hayCambioExacto)
            return res;
        else
            return new ArrayList<>();
    }

    private static boolean monedasCambio(List<Integer> monedas, List<Integer> cantidadMonedas, int cambio,
            List<Integer> res) {
        if (cambio < 0)
            throw new IllegalArgumentException();
        else if (cambio == 0)
            return true;
        else {
            for (int i = 0; i < monedas.size(); i++) {
                int k = cantidadMonedas.get(i);
                int m = monedas.get(i);
                if (m <= cambio && k > 0) {
                    k--;
                    res.add(m);
                    cantidadMonedas.set(i, k);
                    return monedasCambio(monedas, cantidadMonedas, cambio - m, res);
                }
            }
            return false;
        }
    }

}
