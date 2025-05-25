package examples.queens;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import conventionalsearch.StateProblem;

public class QueenStateProblem implements StateProblem<QueenState> {
    private QueenState initial;

    public QueenStateProblem(int[][] m, QueenState parent) {
        initial = new QueenState(m, parent);
    }

    @Override
    public QueenState initialState() {
        return initial;
    }

    @Override
    public List<QueenState> getSuccessors(QueenState s) {
        List<QueenState> result = new LinkedList<>();
        QueenState state = s.clone();
        int[] rows = state.getRows();
        int[] columns = state.getColumns();
        Map<Integer, Integer> queens = state.getQueens();
        int[][] m = state.getMatrix();
        for (int i = 0; i < 8; i++) {
            if (!queens.containsKey(i)) { // si la fila i no tiene reina
                for (int j = 0; j < 8; j++) {
                    if (columns[j] == 0) {// si la columna j no tiene reina => intento poner reina en
                                          // ij sii no hay problema con diagonales
                        // primero verifico que no hay nada en la diagonal arriba a la derecha
                        int row = i - 1;
                        int column = j + 1;
                        boolean searching = true;
                        while (searching) {
                            if (row < 0 || column >= 8)
                                break;
                            if (m[row][column] == 1)
                                searching = false;
                            row--;
                            column++;
                        }

                        // verifico que no hay nada en la diagonal arriba a la izquierda
                        row = i - 1;
                        column = j - 1;
                        while (searching) {
                            if (row < 0 || column < 0)
                                break;
                            if (m[row][column] == 1)
                                searching = false;
                            row--;
                            column--;
                        }

                        // verifico que no hay nada en la diagonal abajo a la derecha
                        row = i + 1;
                        column = j + 1;
                        while (searching) {
                            if (row >= 8 || column >= 8)
                                break;
                            if (m[row][column] == 1)
                                searching = false;
                            row++;
                            column++;
                        }

                        // verifico que no hay nada en la diagonal abajo a la izquierda
                        row = i + 1;
                        column = j - 1;
                        while (searching) {
                            if (row >= 8 || column < 0)
                                break;
                            if (m[row][column] == 1)
                                searching = false;
                            row++;
                            column--;
                        }

                        if (searching) {
                            int[][] mCopy = new int[8][8];
                            for (int k = 0; k < 8; k++)
                                System.arraycopy(m[k], 0, mCopy[k], 0, 8);
                            int[] rowsCopy = Arrays.copyOf(rows, rows.length);
                            int[] columnsCopy = Arrays.copyOf(columns, columns.length);
                            Map<Integer, Integer> queensCopy = new HashMap<>(queens);
                            mCopy[i][j] = 1;
                            rowsCopy[i] = 1;
                            columnsCopy[j] = 1;
                            queensCopy.put(i, j);
                            QueenState state2 = new QueenState(mCopy, state);
                            state2.setColumns(columnsCopy);
                            state2.setRows(rowsCopy);
                            state2.setQueens(queensCopy);
                            result.add(state2);
                        }
                    }
                }
            }
        }
        return result;
    }
}
