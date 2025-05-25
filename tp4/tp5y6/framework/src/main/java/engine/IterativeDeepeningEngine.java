package engine;

import conventionalsearch.Engine;
import conventionalsearch.State;
import conventionalsearch.StateProblem;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Title: IterativeDeepeningEngine
 * Description: Class IterativeDeepeningEngine implements a Iterative-deepening
 * search
 * strategy which can be used with any instance of StateProblem.
 * 
 * @author Nazareno Aguirre
 */
public class IterativeDeepeningEngine<S extends State, P extends StateProblem<S>> implements Engine<S, P> {

    /**
     * Internal representation of the StateProblem.
     */
    private P sp;

    /**
     * path stores the path to the goal.
     */
    private List<S> path;

    private boolean found;

    private S goal;

    /**
     * Constructor for class IterativeDeepeningEngine.
     * @pre. true.
     * @post. Lists path is initialized as empty.
     */
    public IterativeDeepeningEngine() {
        path = new LinkedList<S>();
    }

    /**
     * Constructor for class IterativeDeepeningEngine.
     * 
     * @param sp is the search problem associated with the engine
     *           being created.
     *           @pre. p!=null.
     *           @post. A reference to p is stored in field problem.
     */
    public IterativeDeepeningEngine(P sp) {
        this.sp = sp;
        path = new LinkedList<S>();
    }

    /**
     * Starts the search for successful states for problem, following a
     * iterative-deepening strategy.
     * 
     * @return true iff a successful state is found.
     *         @pre. problem!=null.
     *         @post. the search is performed, the path in list path, and true is
     *         returned iff a
     *         successful state is found.
     */
    public S performSearch() {

        found = false;
        goal = null;
        int i = 1;
        while (!found) {
            path.clear();
            goal = performSearch(sp.initialState(), i);
            i++;
        }

        if (!(goal == null)) {
            S s = goal;
            while (!(s == null)) {
                path.add(0, s);
                s = (S) s.getParent();
            }
        }
        return goal;
    }

    private S performSearch(S s, int limit) {
        return performSearch(s, limit, new LinkedList<S>());
    }

    private S performSearch(S s, int limit, List<S> visited) {
        visited.add(s);
        if (s.isSuccess()) {
            found = true;
            return s;
        } else if (limit == 0)
            return null;
        else {
            for (S succ : sp.getSuccessors(s)) {
                if (!visited.contains(succ)) {
                    S result = performSearch(succ, limit - 1, visited);
                    if (result != null)
                        return result;
                }
            }
        }
        return null;
    }

    /**
     * Returns the path to a previously calculated successful state for problem.
     * 
     * @return the list of nodes corresponding to the path from the root to
     *         the successful node.
     *         @pre. performSearch() has been executed and finished successfully.
     *         @post. the path to the found goal node is returned.
     */
    public List<S> getPath() {
        return path;
    } // end of getPath()

    /**
     * Reports information regarding a previously executed search.
     * @pre. performSearch() has been executed and finished.
     * @post. A report regarding the search is printed to standard output.
     * This report consists of .
     */
    public void report() {
        System.out.println("Length of path to state when search finished: " + path.size());

    } // end of report()

}
