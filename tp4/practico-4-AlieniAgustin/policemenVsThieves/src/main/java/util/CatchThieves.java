package util;

/**
 * Greedy Policemen catch thieves.
 *
 * @author sonia
 *
 */

public final class CatchThieves {

  /**
   * Returns the maximum number of thieves that can be caught.
   * Each policeman can catch only one thief which is at most k away from him.
   * 
   * @param seqTP     is the sequence of thieves and policemen, t or p.
   * @param distanceK represent the units away from the policemen to catch a
   *                  thieve.
   * @return the maximum number of thieves that can be caught.
   */
  public final int maxCatch(final char[] seqTP, final int distanceK) {
    if (seqTP == null || distanceK < 0)
      throw new IllegalArgumentException();

    int output = 0;
    for (int i = 0; i < seqTP.length; i++) {
      if (seqTP[i] == 'P') {
        boolean encontrado = false;

        for (int j = i - distanceK; j < i && !encontrado; j++)
          if (0 <= j && seqTP[j] == 'T') {
            seqTP[j] = 'X';
            encontrado = true;
            output++;
          }

        for (int j = i + distanceK; j > i && !encontrado; j--)
          if (j < seqTP.length && seqTP[j] == 'T') {
            seqTP[j] = 'X';
            encontrado = true;
            output++;
          }

      }
    }
    return output;
  }
}
