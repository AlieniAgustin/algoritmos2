package examples.baldosas;

import informedsearch.StateInformed;
import java.util.ArrayList;
import java.util.List;

public class BaldosasState implements StateInformed {
  private BaldosasState parent = null;
  private int[][] m;

  public boolean repOK() {
    if (m.length != 4 || m[0].length != 4)
      return false;
    List<Integer> visited = new ArrayList<>();

    for (int i = 0; i < 4; i++)
      for (int j = 0; j < 4; j++) {
        int e = m[i][j];
        if (visited.contains(e) || e < 0 || e > 15)
          return false;
        visited.add(e);
      }

    return true;
  }

  public BaldosasState(int[][] m) {
    this.m = m;
    if (!repOK()) {
      throw new IllegalArgumentException("invalid value");
    }
  }

  public BaldosasState(int[][] m, BaldosasState parent) {
    this.m = m;
    this.parent = parent;
    if (!repOK()) {
      throw new IllegalArgumentException("invalid values");
    }
  }

  /**
   * This State is field by field copied.
   * 
   * @return a copy of this JugsState.
   */
  @Override
  public BaldosasState clone() {
    int[][] mCopy = new int[4][4];
    for (int i = 0; i < 4; i++) {
      System.arraycopy(this.m[i], 0, mCopy[i], 0, 4);
    }
    return new BaldosasState(mCopy, this.parent);
  }

  /**
   * Returns the parent of the current state.
   * 
   * @return the parent of the current state or null if this does not have a
   *         parent.
   */

  @Override
  public StateInformed getParent() {
    return parent;

  }

  public int[][] getMatrix() {
    return m;
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
  public void setParent(BaldosasState parent) {
    this.parent = parent;
  }

  @Override
  public boolean isSuccess() {
    int n = 1;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        if (i == 3 && j == 3) {
          if (m[i][j] != 0)
            return false;
        } else {
          if (m[i][j] != n++)
            return false;
        }
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

    if (!(other instanceof BaldosasState)) {
      return false;
    }

    BaldosasState otherOne = (BaldosasState) other;

    for (int i = 0; i < 4; i++)
      for (int j = 0; j < 4; j++)
        if (m[i][j] != otherOne.getMatrix()[i][j])
          return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        result = 31 * result + m[i][j];
      }
    }
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
    String answer = "[";
    for (int i = 0; i < 4; i++)
      for (int j = 0; j < 4; j++) {
        answer += m[i][j];
        if (i != 3 || j != 3)
          answer += ", ";
      }
    answer += "]";
    return answer;
  }

  @Override
  public int value() {
    int n = 1;
    int sum = 0;
    for (int i = 0; i < 4; i++)
      for (int j = 0; j < 4; j++) {
        if (m[i][j] != n)
          sum++;
        n++;
      }
    if (m[3][3] == 0)
      sum--;
    return sum;
  }

  public Tuple getIndexCero() {
    for (int i = 0; i < 4; i++)
      for (int j = 0; j < 4; j++)
        if (m[i][j] == 0)
          return new Tuple(i, j);
    return null; // nunca entraria aca
  }

}
