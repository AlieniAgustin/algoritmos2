package examples.tateti;

import adversarysearch.EngineAdversary;
import adversarysearch.StateAdversary;
import adversarysearch.StateProblemAdversary;

public class TatetiStateAdversary implements StateAdversary {

    private boolean isMaxState;
    private int[][] m;
    private TatetiStateAdversary parent = null;

    public TatetiStateAdversary() {
        this.m = new int[3][3];
        this.isMaxState = true;
    }

    // if isMaxState empiezo yo jugando, else cpu
    public TatetiStateAdversary(boolean isMaxState, int[][] m, TatetiStateAdversary parent) {
        if (m.length != 3 || m[0].length != 3)
            throw new IllegalArgumentException();

        this.isMaxState = isMaxState;
        this.m = m;
        this.parent = parent;
    }

    public void setIsMaxState(boolean isMaxState) {
        this.isMaxState = isMaxState;
    }

    public void setM(int[][] m) {
        if (m.length != 3 || m[0].length != 3)
            throw new IllegalArgumentException();

        this.m = m;
    }

    public void setParent(TatetiStateAdversary parent) {
        this.parent = parent;
    }

    public boolean isMax() {
        return isMaxState;
    }

    public int[][] getM() {
        return m;
    }

    public TatetiStateAdversary getParent() {
        return parent;
    }

    public boolean end() {
        int zeros = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (m[i][j] == 0)
                    zeros++;
        if (zeros == 0) {
            return true; // matriz llena
        } else {
            if (verifyRows(0)) // si en la fila 0 hay un ganador
                return true;
            if (verifyRows(1)) // si en la fila 1 hay un ganador
                return true;
            if (verifyRows(2)) // si en la fila 2 hay un ganador
                return true;
            if (verifyColumns(0)) // si en la columna 1 hay un ganador
                return true;
            if (verifyColumns(1)) // si en la columna 2 hay un ganador
                return true;
            if (verifyColumns(2)) // si en la columna 3 hay un ganador
                return true;
            if (verifyDiagonals()) // si en alguna diagonal hay un ganador
                return true;

            return false;
        }
    }

    private boolean verifyRows(int i) {
        return quantitynRow(i, 1) == 3 || quantitynRow(i, -1) == 3;
    }

    private boolean verifyColumns(int j) {
        return quantitynColumn(j, 1) == 3 || quantitynColumn(j, -1) == 3;
    }

    private boolean verifyDiagonals() {
        return quantitynDiagonal1(1) == 3 || quantitynDiagonal2(1) == 3 || quantitynDiagonal1(-1) == 3
                || quantitynDiagonal2(-1) == 3;
    }

    private int quantitynRow(int i, int n) {
        int quantity = 0;
        for (int j = 0; j < 3; j++)
            if (m[i][j] == n)
                quantity++;
        return quantity;
    }

    private int quantitynColumn(int j, int n) {
        int quantity = 0;
        for (int i = 0; i < 3; i++)
            if (m[i][j] == n)
                quantity++;
        return quantity;
    }

    private int quantitynDiagonal1(int n) {
        int quantity = 0;
        for (int k = 0; k < 3; k++)
            if (m[k][k] == n)
                quantity++;
        return quantity;
    }

    private int quantitynDiagonal2(int n) {
        int quantity = 0;
        if (n == m[0][2])
            quantity++;
        if (n == m[1][1])
            quantity++;
        if (n == m[2][0])
            quantity++;
        return quantity;
    }

    public int value() {
        if (end())
            if (verifyVictory(1))
                return 500;
            else if (verifyVictory(-1))
                return -500;
            else
                return 0;

        int n;
        if (isMax())
            n = 1;
        else
            n = -1;
        return value(n);
    }

    public int value(int n) {
        int sum = 0;
        // voy a verificar si hay 3 en linea con el valor n
        for (int k = 0; k < 3; k++) {
            if (quantitynRow(k, n) == 2 && quantitynRow(k, -n) == 0)
                sum++;
            if (quantitynRow(k, -n) == 2 && quantitynRow(k, n) == 0)
                sum--;
            if (quantitynColumn(k, n) == 2 && quantitynColumn(k, -n) == 0)
                sum++;
            if (quantitynColumn(k, -n) == 2 && quantitynColumn(k, n) == 0)
                sum--;
        }

        if (quantitynDiagonal1(n) == 2 && quantitynDiagonal1(-n) == 0)
            sum++;
        if (quantitynDiagonal1(n) == 0 && quantitynDiagonal1(-n) == 2)
            sum--;
        if (quantitynDiagonal2(n) == 2 && quantitynDiagonal2(-n) == 0)
            sum++;
        if (quantitynDiagonal2(n) == 0 && quantitynDiagonal2(-n) == 2)
            sum--;
        return sum;
    }

    private boolean verifyVictory(int n) {
        for (int k = 0; k < 3; k++)
            if (quantitynRow(k, n) == 3 || quantitynColumn(k, n) == 3)
                return true;
        return quantitynDiagonal1(n) == 3 || quantitynDiagonal2(n) == 3;
    }

    public String toString() {
        String answer = "\n";

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (m[i][j] == 1)
                    answer += "X";
                else if (m[i][j] == 0)
                    answer += "-";
                else
                    answer += "O";
            }
            answer += "\n";
        }

        return answer;
    }

    /**
     * Devuelve un objeto que representa la regla aplicada que llevó
     * al estado actual.
     * 
     * @return un objeto que representa la regla aplicada que llevó al
     *         estado actual. Si el estado es el estado inicial, se
     *         devuelve null.
     *         @pre. true.
     *         @post. se devuelve un objeto que representa la regla aplicada que
     *         llevó al estado actual. Si el estado es el inicial, se devuelve null.
     *         TODO Reemplazar Object por una clase o interfaz más específica.
     */
    public String ruleApplied() {
        if (parent == null)
            return null;

        int[][] parentBoard = parent.getM();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (parentBoard[i][j] != m[i][j]) {
                    String player = (m[i][j] == 1) ? "X" : "O";
                    return "Jugador " + player + " colocó en (" + i + "," + j + ")";
                }
            }
        }
        return "Sin cambios detectados"; // fallback
    }

    public TatetiStateAdversary clon() {
        return new TatetiStateAdversary(isMaxState, m, parent);
    }

    public boolean isSuccess() {
        return end();
    }

}
