package com.solapamiento;

import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import com.solapamiento.Cita;

public class App {

    private static List<Cita> agregadas = new LinkedList<>();

    public static List<Cita> citasOptimas(List<Cita> citas) {
        Collections.sort(citas);
        for (Cita cita : citas)
            if (!solapa(cita))
                agregadas.add(cita);
        return agregadas;
    }

    private static boolean solapa(Cita cita) {
        for (Cita e : agregadas) {
            int inicioCita = cita.getHInicio();
            int finCita = cita.getHFin();
            int inicioe = e.getHInicio();
            int fine = e.getHFin();

            if ((inicioe <= inicioCita && inicioCita <= fine) || (inicioe <= finCita && finCita <= fine))
                return true;
        }
        return false;
    }

}
