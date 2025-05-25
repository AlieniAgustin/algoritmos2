package examples.tateti;

import engine.MinMaxEngine;
import adversarysearch.EngineAdversary;
import adversarysearch.StateAdversary;
import adversarysearch.StateProblemAdversary;
import java.util.Scanner;

public class TatetiSearchApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Quién juega primero? X (vos) o O (IA): ");
        String answer = scanner.nextLine().trim().toUpperCase();
        boolean max = true;
        if (answer.equals("O")) {
            max = false;
        }

        int[][] m = new int[3][3];
        TatetiStateAdversary tateti = new TatetiStateAdversary(max, m, null);
        TatetiStateProblemAdversary sp = new TatetiStateProblemAdversary(tateti);
        MinMaxEngine<TatetiStateAdversary, TatetiStateProblemAdversary> engine = new MinMaxEngine<TatetiStateAdversary, TatetiStateProblemAdversary>(
                sp, 3); // si pongo 9 es muy inteligente

        System.out.println("Estado inicial:");
        System.out.println(tateti);

        while (!tateti.end()) {
            if (tateti.isMax()) {
                // Turno del jugador (X)
                System.out.println("Tu turno (jugás con X). Ingresá fila y columna (0-2 0-2):");
                int fila = scanner.nextInt();
                int columna = scanner.nextInt();

                if (fila < 0 || fila > 2 || columna < 0 || columna > 2 || tateti.getM()[fila][columna] != 0) {
                    System.out.println("Movimiento inválido. Probá de nuevo.");
                    continue;
                }

                int[][] newM = copyMatrix(tateti.getM());
                newM[fila][columna] = 1;
                tateti = new TatetiStateAdversary(false, newM, tateti);

            } else {
                // Turno de la IA (O)
                System.out.println("Turno de la IA (jugando con O)...");
                tateti = engine.computeSuccessor(tateti);
                engine.report();
            }

            System.out.println(tateti);
        }

        System.out.println("Fin del juego.");
        int val = tateti.value();
        if (val == sp.maxValue())
            System.out.println("¡Ganaste!");
        else if (val == sp.minValue())
            System.out.println("La IA gana.");
        else
            System.out.println("Empate.");

        scanner.close();
    }

    private static int[][] copyMatrix(int[][] m) {
        int[][] a = new int[3][3];
        for (int i = 0; i < 3; i++)
            System.arraycopy(m[i], 0, a[i], 0, 3);
        return a;
    }
}
