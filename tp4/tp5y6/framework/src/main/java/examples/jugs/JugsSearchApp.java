package examples.jugs;

import engine.BreadthFirstEngine;
import engine.DepthFirstEngine;
import engine.IterativeDeepeningEngine;

import java.util.List;

public class JugsSearchApp {
  /**
   * Main for Jugs App.
   * 
   * @param args contents Jug A and contents Jug B are expected.
   */

  public static void main(String[] args) {

    int a = 1;
    int b = 3;

    JugsStateProblem sp1 = new JugsStateProblem(a, b);

    /* App using Breadth-first search */
    BreadthFirstEngine<JugsState, JugsStateProblem> engineBfs = new BreadthFirstEngine<JugsState, JugsStateProblem>(
        sp1);
    JugsState successBfS = engineBfs.performSearch();
    System.out.println();
    System.out.println("*** Result using Breadth-first search ***");
    System.out.println("Solution found? " + successBfS.toString());
    if (!(successBfS == null)) {
      System.out.print("Path to goal: ");
      List<JugsState> pathBfS = engineBfs.getPath();
      for (int i = 0; i < pathBfS.size(); i++) {
        JugsState current = (JugsState) pathBfS.get(i);
        System.out.print(current.toString());
      }
      System.out.println();
    }
    engineBfs.report();

    System.out.println();

    /* App using Depth-first search */
    DepthFirstEngine<JugsState, JugsStateProblem> engineDfs = new DepthFirstEngine<JugsState, JugsStateProblem>(
        sp1);
    JugsState successDfS = engineDfs.performSearch();
    System.out.println();
    System.out.println("*** Result using Depth-first search ***");
    System.out.println("Solution found? " + successDfS.toString());
    if (!(successDfS == null)) {
      System.out.print("Path to goal: ");
      List<JugsState> pathDfS = engineDfs.getPath();
      for (int i = 0; i < pathDfS.size(); i++) {
        JugsState current = (JugsState) pathDfS.get(i);
        System.out.print(current.toString());
      }
      System.out.println();
    }
    engineDfs.report();

    System.out.println();

    /* App using Iterative-deepening search */
    IterativeDeepeningEngine<JugsState, JugsStateProblem> engineIterative = new IterativeDeepeningEngine<JugsState, JugsStateProblem>(
        sp1);
    JugsState successIt = engineIterative.performSearch();
    System.out.println();
    System.out.println("*** Result using Iterative-deepening search ***");
    System.out.println("Solution found? " + successIt.toString());
    if (!(successIt == null)) {
      System.out.print("Path to goal: ");
      List<JugsState> pathIt = engineIterative.getPath();
      for (int i = 0; i < pathIt.size(); i++) {
        JugsState current = (JugsState) pathIt.get(i);
        System.out.print(current.toString());
      }
      System.out.println();
    }
    engineIterative.report();

  }

}
