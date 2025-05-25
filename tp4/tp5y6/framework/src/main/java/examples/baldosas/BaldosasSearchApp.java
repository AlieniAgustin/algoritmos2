package examples.baldosas;

import engine.BreadthFirstEngine;
import engine.DepthFirstEngine;
import engine.IterativeDeepeningEngine;
import examples.jugs.JugsState;
import examples.jugs.JugsStateProblem;

import java.util.List;

import engine.BestFirstEngine;

public class BaldosasSearchApp {

    public static void main(String[] args) {
        int[][] m = {
                { 1, 2, 3, 4 },
                { 5, 0, 6, 8 },
                { 9, 10, 7, 11 },
                { 13, 14, 15, 12 }
        };

        BaldosasStateProblem sp = new BaldosasStateProblem(m, null);

        /* App using Breadth-first search */

        BreadthFirstEngine<BaldosasState, BaldosasStateProblem> engineBfs = new BreadthFirstEngine<BaldosasState, BaldosasStateProblem>(
                sp);
        BaldosasState successBfS = engineBfs.performSearch();
        System.out.println();
        System.out.println("*** Result using Breadth-first search ***");
        System.out.println("Solution found? " + successBfS.toString());
        if (!(successBfS == null)) {
            System.out.print("Path to goal: ");
            List<BaldosasState> pathBfS = engineBfs.getPath();
            for (int i = 0; i < pathBfS.size(); i++) {
                BaldosasState current = (BaldosasState) pathBfS.get(i);
                System.out.print(current.toString());
            }
            System.out.println();
        }
        engineBfs.report();

        System.out.println();

        /* App using Depth-first search */
        /*
         * DepthFirstEngine<BaldosasState, BaldosasStateProblem> engineDfs = new
         * DepthFirstEngine<BaldosasState, BaldosasStateProblem>(
         * sp);
         * BaldosasState successDfS = engineDfs.performSearch();
         * System.out.println();
         * System.out.println("*** Result using Depth-first search ***");
         * System.out.println("Solution found? " + successDfS.toString());
         * if (!(successDfS == null)) {
         * System.out.print("Path to goal: ");
         * List<BaldosasState> pathDfS = engineDfs.getPath();
         * for (int i = 0; i < pathDfS.size(); i++) {
         * BaldosasState current = (BaldosasState) pathDfS.get(i);
         * System.out.print(current.toString());
         * }
         * System.out.println();
         * }
         * engineDfs.report();
         */
        System.out.println();

        /* App using Iterative-deepening search */

        IterativeDeepeningEngine<BaldosasState, BaldosasStateProblem> engineIterative = new IterativeDeepeningEngine<BaldosasState, BaldosasStateProblem>(
                sp);
        BaldosasState successIt = engineIterative.performSearch();
        System.out.println();
        System.out.println("*** Result using Iterative-deepening search ***");
        System.out.println("Solution found? " + successIt.toString());
        if (!(successIt == null)) {
            System.out.print("Path to goal: ");
            List<BaldosasState> pathIt = engineIterative.getPath();
            for (int i = 0; i < pathIt.size(); i++) {
                BaldosasState current = (BaldosasState) pathIt.get(i);
                System.out.print(current.toString());
            }
            System.out.println();
        }
        engineIterative.report();

        System.out.println();

        /* App using best-first search */
        BestFirstEngine<BaldosasState, BaldosasStateProblem> engineBest = new BestFirstEngine<BaldosasState, BaldosasStateProblem>(
                sp);
        BaldosasState successBest = engineBest.performSearch();
        System.out.println();
        System.out.println("*** Result using best-first search ***");
        System.out.println("Solution found? " + successBest.toString());
        if (!(successBest == null)) {
            System.out.print("Path to goal: ");
            List<BaldosasState> pathBest = engineBest.getPath();
            for (int i = 0; i < pathBest.size(); i++) {
                BaldosasState current = (BaldosasState) pathBest.get(i);
                System.out.print(current.toString());
            }
            System.out.println();
        }
        engineBest.report();

    }

}
