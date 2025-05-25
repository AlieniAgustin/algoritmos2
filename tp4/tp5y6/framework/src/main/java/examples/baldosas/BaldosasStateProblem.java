package examples.baldosas;

import java.util.LinkedList;
import java.util.List;

import conventionalsearch.StateProblem;

public class BaldosasStateProblem implements StateProblem<BaldosasState> {
    private BaldosasState initial;

    public BaldosasStateProblem(int[][] m, BaldosasState parent) {
        initial = new BaldosasState(m, parent);
    }

    @Override
    public BaldosasState initialState() {
        return initial;
    }

    @Override
    public List<BaldosasState> getSuccessors(BaldosasState s) {
        List<BaldosasState> result = new LinkedList<>();

        int[][] originalMatrix = s.getMatrix();

        int[][] m1 = copyMatrix(originalMatrix);
        int[][] m2 = copyMatrix(originalMatrix);
        int[][] m3 = null;
        int[][] m4 = null;

        Tuple t = s.getIndexCero();
        int i = t.a;
        int j = t.b;

        if (i == 0 && j == 0) {
            m1[0][0] = m1[0][1];
            m1[0][1] = 0;

            m2[0][0] = m2[1][0];
            m2[1][0] = 0;
        } else if (i == 0 && j == 3) {
            m1[0][3] = m1[0][2];
            m1[0][2] = 0;

            m2[0][3] = m2[1][3];
            m2[1][3] = 0;
        } else if (i == 3 && j == 0) {
            m1[3][0] = m1[2][0];
            m1[2][0] = 0;

            m2[3][0] = m2[3][1];
            m2[3][1] = 0;
        } else if (i == 3 && j == 3) {
            m1[3][3] = m1[3][2];
            m1[3][2] = 0;

            m2[3][3] = m2[2][3];
            m2[2][3] = 0;
        } else {
            m3 = copyMatrix(originalMatrix);
            if (i == 0) {
                m1[0][j] = m1[0][j - 1];
                m1[0][j - 1] = 0;

                m2[0][j] = m2[0][j + 1];
                m2[0][j + 1] = 0;

                m3[0][j] = m3[1][j];
                m3[1][j] = 0;
            } else if (i == 3) {
                m1[3][j] = m1[3][j - 1];
                m1[3][j - 1] = 0;

                m2[3][j] = m2[3][j + 1];
                m2[3][j + 1] = 0;

                m3[3][j] = m3[2][j];
                m3[2][j] = 0;
            } else if (j == 0) {
                m1[i][0] = m1[i - 1][0];
                m1[i - 1][0] = 0;

                m2[i][0] = m2[i + 1][0];
                m2[i + 1][0] = 0;

                m3[i][0] = m3[i][1];
                m3[i][1] = 0;
            } else if (j == 3) {
                m1[i][3] = m1[i - 1][3];
                m1[i - 1][3] = 0;

                m2[i][3] = m2[i + 1][3];
                m2[i + 1][3] = 0;

                m3[i][3] = m3[i][2];
                m3[i][2] = 0;
            } else {
                m3 = copyMatrix(originalMatrix);
                m4 = copyMatrix(originalMatrix);

                m1[i][j] = m1[i - 1][j];
                m1[i - 1][j] = 0;

                m2[i][j] = m2[i + 1][j];
                m2[i + 1][j] = 0;

                m3[i][j] = m3[i][j - 1];
                m3[i][j - 1] = 0;

                m4[i][j] = m4[i][j + 1];
                m4[i][j + 1] = 0;
            }
        }

        result.add(new BaldosasState(m1, s));
        result.add(new BaldosasState(m2, s));
        if (m3 != null)
            result.add(new BaldosasState(m3, s));
        if (m4 != null)
            result.add(new BaldosasState(m4, s));

        return result;
    }

    public static int[][] copyMatrix(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = java.util.Arrays.copyOf(original[i], original[i].length);
        }
        return copy;
    }

}
