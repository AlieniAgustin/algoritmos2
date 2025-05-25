package informedsearch;

import java.util.Comparator;

public class StateComparators {

    public static <S extends StateInformed> Comparator<S> byHeuristic() {
        return Comparator.comparingInt(S::value);
        // al hacer un poll de una priorityQueue, se va a sacar el de menor value
    }

}
