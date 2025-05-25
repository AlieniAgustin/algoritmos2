package examples.queens;

import engine.BreadthFirstEngine;
import engine.DepthFirstEngine;
import engine.IterativeDeepeningEngine;
import examples.jugs.JugsState;
import examples.jugs.JugsStateProblem;

import java.util.List;

import engine.BestFirstEngine;

public class QueenSearchApp {

    public static void main(String[] args) {
        int[][] m = {
                { 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 }
        };

        QueenStateProblem sp = new QueenStateProblem(m, null);

        /* App using Breadth-first search */

        BreadthFirstEngine<QueenState, QueenStateProblem> engineBfs = new BreadthFirstEngine<QueenState, QueenStateProblem>(
                sp);
        QueenState successBfS = engineBfs.performSearch();
        System.out.println();
        System.out.println("*** Result using Breadth-first search ***");
        System.out.println("Solution found? " + successBfS.toString());
        if (!(successBfS == null)) {
            System.out.print("Path to goal: ");
            List<QueenState> pathBfS = engineBfs.getPath();
            for (int i = 0; i < pathBfS.size(); i++) {
                QueenState current = (QueenState) pathBfS.get(i);
                System.out.print(current.toString());
            }
            System.out.println();
        }
        engineBfs.report();

        System.out.println();

        /* App using Depth-first search */

        DepthFirstEngine<QueenState, QueenStateProblem> engineDfs = new DepthFirstEngine<QueenState, QueenStateProblem>(
                sp);
        QueenState successDfS = engineDfs.performSearch();
        System.out.println();
        System.out.println("*** Result using Depth-first search ***");
        System.out.println("Solution found? " + successDfS.toString());
        if (!(successDfS == null)) {
            System.out.print("Path to goal: ");
            List<QueenState> pathDfS = engineDfs.getPath();
            for (int i = 0; i < pathDfS.size(); i++) {
                QueenState current = (QueenState) pathDfS.get(i);
                System.out.print(current.toString());
            }
            System.out.println();
        }
        engineDfs.report();

        System.out.println();

        /* App using Iterative-deepening search */

        IterativeDeepeningEngine<QueenState, QueenStateProblem> engineIterative = new IterativeDeepeningEngine<QueenState, QueenStateProblem>(
                sp);
        QueenState successIt = engineIterative.performSearch();
        System.out.println();
        System.out.println("*** Result using Iterative-deepening search ***");
        System.out.println("Solution found? " + successIt.toString());
        if (!(successIt == null)) {
            System.out.print("Path to goal: ");
            List<QueenState> pathIt = engineIterative.getPath();
            for (int i = 0; i < pathIt.size(); i++) {
                QueenState current = (QueenState) pathIt.get(i);
                System.out.print(current.toString());
            }
            System.out.println();
        }
        engineIterative.report();

        System.out.println();

        /* App using best-first search */
        BestFirstEngine<QueenState, QueenStateProblem> engineBest = new BestFirstEngine<QueenState, QueenStateProblem>(
                sp);
        QueenState successBest = engineBest.performSearch();
        System.out.println();
        System.out.println("*** Result using best-first search ***");
        System.out.println("Solution found? " + successBest.toString());
        if (!(successBest == null)) {
            System.out.print("Path to goal: ");
            List<QueenState> pathBest = engineBest.getPath();
            for (int i = 0; i < pathBest.size(); i++) {
                QueenState current = (QueenState) pathBest.get(i);
                System.out.print(current.toString());
            }
            System.out.println();
        }
        engineBest.report();

    }

}
