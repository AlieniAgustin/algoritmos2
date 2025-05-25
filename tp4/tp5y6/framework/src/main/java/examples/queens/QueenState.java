package examples.queens;

import informedsearch.StateInformed;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.HashMap;

public class QueenState implements StateInformed {
    private QueenState parent = null;
    private int[][] m;
    private int[] rows = new int[8];
    private int[] columns = new int[8];
    private Map<Integer, Integer> queens = new HashMap<>();

    public boolean repOK() {
        for (int k = 0; k < 8; k++)
            if (rows[k] > 1 || columns[k] > 1)
                return false;
        for (int i = 0; i < 8; i++) {
            if (rows[i] == 1) {
                for (int j = 0; j < 8; j++) {
                    if (m[i][j] == 1) {
                        // primero verifico que no hay nada en la diagonal arriba a la derecha
                        int row = i - 1;
                        int column = j + 1;
                        while (true) {
                            if (row < 0 || column >= 8)
                                break;
                            if (m[row][column] == 1)
                                return false;
                            row--;
                            column++;
                        }

                        // verifico que no hay nada en la diagonal arriba a la izquierda
                        row = i - 1;
                        column = j - 1;
                        while (true) {
                            if (row < 0 || column < 0)
                                break;
                            if (m[row][column] == 1)
                                return false;
                            row--;
                            column--;
                        }

                        // verifico que no hay nada en la diagonal abajo a la derecha
                        row = i + 1;
                        column = j + 1;
                        while (true) {
                            if (row >= 8 || column >= 8)
                                break;
                            if (m[row][column] == 1)
                                return false;
                            row++;
                            column++;
                        }

                        // verifico que no hay nada en la diagonal abajo a la izquierda
                        row = i + 1;
                        column = j - 1;
                        while (true) {
                            if (row >= 8 || column < 0)
                                break;
                            if (m[row][column] == 1)
                                return false;
                            row++;
                            column--;
                        }
                        break;
                    }
                }
            }
        }
        return true;
    }

    public QueenState() {
        this.m = new int[8][8];
    }

    public QueenState(int[][] m, QueenState parent) {
        this.m = m;
        this.parent = parent;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (m[i][j] == 1) {
                    rows[i]++; // para capturar errores
                    columns[j]++; // para capturar errores
                    queens.put(i, j);
                }
        if (!repOK())
            throw new IllegalArgumentException("invalid values");
    }

    /**
     * This State is field by field copied.
     * 
     * @return a copy of this QueenState.
     */
    @Override
    public QueenState clone() {
        int[][] mCopy = new int[8][8];
        for (int i = 0; i < 8; i++) {
            System.arraycopy(this.m[i], 0, mCopy[i], 0, 8);
        }
        QueenState s = new QueenState(mCopy, this.parent);
        s.setColumns(columns);
        s.setRows(rows);
        s.setQueens(queens);
        return s;
    }

    /**
     * Returns the parent of the current state.
     * 
     * @return the parent of the current state or null if this does not have a
     *         parent.
     */
    @Override
    public QueenState getParent() {
        return parent;

    }

    public int[][] getMatrix() {
        return m;
    }

    public int[] getRows() {
        return rows;
    }

    public int[] getColumns() {
        return columns;
    }

    public Map<Integer, Integer> getQueens() {
        return queens;
    }

    public void setMatrix(int[][] m) {
        this.m = m;
    }

    /**
     * Set the parent of the current state. This method
     * must be implemented by all concrete classes implementing State.
     * 
     * @param parent to be set to the current state.
     */
    public void setParent(QueenState parent) {
        this.parent = parent;
    }

    public void setRows(int[] rows) {
        this.rows = rows;
    }

    public void setColumns(int[] columns) {
        this.columns = columns;
    }

    public void setQueens(Map<Integer, Integer> queens) {
        this.queens = queens;
    }

    @Override
    public boolean isSuccess() {
        for (int k = 0; k < 8; k++)
            if (rows[k] != 1 || columns[k] != 1) // verifico que en todas las filas y columnas haya una reina
                return false;

        for (int i = 0; i < 8; i++) {
            // cc de c/reina
            int fst = i;
            int snd = queens.get(i);

            // primero verifico que no hay nada en la diagonal arriba a la derecha
            int row = fst - 1;
            int column = snd + 1;
            while (true) {
                if (row < 0 || column >= 8)
                    break;
                if (m[row][column] == 1)
                    return false;
                row--;
                column++;
            }

            // verifico que no hay nada en la diagonal arriba a la izquierda
            row = fst - 1;
            column = snd - 1;
            while (true) {
                if (row < 0 || column < 0)
                    break;
                if (m[row][column] == 1)
                    return false;
                row--;
                column--;
            }

            // verifico que no hay nada en la diagonal abajo a la derecha
            row = fst + 1;
            column = snd + 1;
            while (true) {
                if (row >= 8 || column >= 8)
                    break;
                if (m[row][column] == 1)
                    return false;
                row++;
                column++;
            }

            // verifico que no hay nada en la diagonal abajo a la izquierda
            row = fst + 1;
            column = snd - 1;
            while (true) {
                if (row >= 8 || column < 0)
                    break;
                if (m[row][column] == 1)
                    return false;
                row++;
                column--;
            }
        }
        return true;
    }

    /**
     * Checks whether 'this' is equal to another state. This must be implemented
     * by every concrete class implementing State.
     * 
     * @param other State object to compare with this.
     * @return true iff 'this' is equal, as a state, to 'other'.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof QueenState)) {
            return false;
        }

        QueenState otherOne = (QueenState) other;

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (m[i][j] != otherOne.getMatrix()[i][j])
                    return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Arrays.deepHashCode(m); // Para matriz 2D
        result = 31 * result + Arrays.hashCode(rows); // Para arreglo 1D
        result = 31 * result + Arrays.hashCode(columns); // Para arreglo 1D
        result = 31 * result + Objects.hashCode(queens); // Para el Map
        // No se incluye 'parent' para evitar ciclos o referencias profundas
        return result;
    }

    /**
     * Returns a representation as a string of the current state. This method
     * must be implemented by all concrete classes implementing State.
     * 
     * @return a String representation of the current state.
     */
    @Override
    public String toString() {
        String answer = "";
        for (int i = 0; i < 8; i++) {
            answer += "[";
            for (int j = 0; j < 8; j++) {
                answer += m[i][j];
                if (j < 7)
                    answer += ", ";
            }
            answer += "]\n";
        }
        answer += "\n\n";
        return answer;
    }

    @Override
    public int value() {
        int zeros = 0;
        for (int i = 0; i < 8; i++)
            if (rows[i] == 0)
                zeros++;
        return zeros;
    }
}
