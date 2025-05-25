package examples.tateti;

import adversarysearch.EngineAdversary;
import adversarysearch.StateAdversary;
import adversarysearch.StateProblemAdversary;
import java.util.List;
import java.util.LinkedList;

public class TatetiStateProblemAdversary implements StateProblemAdversary<TatetiStateAdversary> {

    private TatetiStateAdversary initial;

    public TatetiStateProblemAdversary(TatetiStateAdversary initial) {
        this.initial = initial;
    }

    public List<TatetiStateAdversary> getSuccessors(TatetiStateAdversary s) {
        if (s == null)
            throw new IllegalArgumentException();

        int n = 1;
        if (!s.isMax()) // a los nuevos tableros les pondre 1 o -1 dependiendo de si toca max o min
            n = -1;
        List<TatetiStateAdversary> answer = new LinkedList<>();
        int[][] m = s.getM();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (m[i][j] == 0) {
                    int[][] newM = copyMatrix(m);
                    newM[i][j] = n;
                    boolean isMaxState = !s.isMax();
                    TatetiStateAdversary newState = new TatetiStateAdversary(isMaxState, newM, s);
                    answer.add(newState);
                }
        return answer;
    }

    private int[][] copyMatrix(int[][] m) {
        int[][] a = new int[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                a[i][j] = m[i][j];

        return a;
    }

    public int minValue() {
        return -500;
    }

    public int maxValue() {
        return 500;
    }

    public TatetiStateAdversary initialState() {
        return initial;
    }
}
