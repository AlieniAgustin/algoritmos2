package adversarysearch;

public interface EngineAdversary<S extends StateAdversary, P extends StateProblemAdversary<S>> {

  /**
   * Devuelve la profundidad máxima que se usará para la búsqueda.
   * Este valor indica cuántos movimientos futuros del juego se van a
   * explorar para calcular el valor de un estado.
   * 
   * @return la profundidad máxima que se usará para la búsqueda.
   *         @pre. true.
   *         @post. se devuelve el valor de maxDepth.
   */
  int getMaxDepth();

  /**
   * Establece la profundidad máxima que se usará para la búsqueda.
   * Este valor indica cuántos movimientos futuros del juego se van a
   * explorar para calcular el valor de un estado.
   * 
   * @param maxDepth es el valor que se usará para asignar this.maxDepth.
   *                 @pre. maxDepth >= 1.
   *                 @post. this.maxDepth se establece con el valor de maxDepth.
   */
  void setMaxDepth(int maxDepth);

  /**
   * Devuelve el problema asociado a este motor de búsqueda.
   * 
   * @return una referencia al problema asociado con este motor de búsqueda.
   *         @pre. true.
   *         @post. se devuelve this.problem.
   */
  P getProblem();

  /**
   * Establece el problema asociado al motor de búsqueda.
   * 
   * @param p es el problema de búsqueda que se usará (para asignar a 'problem').
   *          @pre. p != null.
   *          @post. 'problem' se establece con p.
   */
  void setProblem(P p);

  /**
   * Inicia la búsqueda para calcular un valor para un estado. El
   * cálculo se realiza explorando el árbol de juego correspondiente
   * al problema que se está analizando, considerando al estado como
   * raíz, y con profundidad máxima igual a maxDepth.
   * 
   * @param state es el estado cuyo valor se quiere calcular.
   * @return el valor calculado para el estado.
   *         @pre. problem != null y state != null.
   *         @post. se calcula el valor para el estado, mediante una búsqueda
   *         en el árbol de juego con el estado como raíz y maxDepth
   *         como profundidad máxima.
   */
  int computeValue(S state);

  /**
   * Inicia la búsqueda para calcular el sucesor más prometedor
   * de un estado. El cálculo se realiza explorando el árbol de juego
   * correspondiente al problema que se está analizando, considerando
   * al estado como raíz, y con profundidad máxima igual a maxDepth.
   * 
   * @param state es el estado para el cual se calcula su sucesor más prometedor.
   * @return el sucesor más prometedor del estado. El método
   *         ruleApplied() del resultado indica qué regla llevó al
   *         nuevo estado.
   *         @pre. problem != null y state != null.
   *         @post. se calcula el sucesor más prometedor del estado,
   *         mediante una búsqueda en el árbol de juego con el estado
   *         como raíz y maxDepth como profundidad máxima.
   */
  S computeSuccessor(S state);

  /**
   * Reporta información sobre una búsqueda previamente ejecutada.
   * @pre. computeSuccessor(s) o computeValue(s) han sido
   * ejecutados y finalizados.
   * @post. se imprime un reporte sobre la última búsqueda realizada
   * en la salida estándar.
   */
  void report();
}
