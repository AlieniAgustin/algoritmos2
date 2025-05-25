package adversarysearch;

import conventionalsearch.State;

/**
 * Interfaz que define los requerimientos básicos sobre los estados,
 * necesarios al caracterizar problemas como problemas de búsqueda adversaria.
 * Las definiciones de estados para problemas adversarios particulares deben
 * implementar esta interfaz, de modo que puedan utilizarse estrategias
 * generales
 * de búsqueda adversaria.
 * 
 * @author Nazareno Aguirre
 */

public interface StateAdversary extends State {
  /**
   * Devuelve el padre del estado actual. Este método debe ser
   * implementado por todas las clases concretas que implementen StateAdversary.
   * 
   * @return el padre del estado actual o null si no tiene padre.
   */
  public StateAdversary getParent();

  /**
   * Indica si el estado actual es un estado max o no.
   * Si el estado actual no es un estado max, se asume que
   * es un estado min.
   * 
   * @return true si y solo si 'this' es un estado max.
   *         @pre. true.
   *         @post. se devuelve true si y solo si 'this' es un estado max.
   */
  boolean isMax();

  /**
   * Indica si este estado es un estado final, es decir,
   * un estado sin sucesores.
   * 
   * @return true si y solo si el estado es un estado final.
   *         @pre. this != null.
   *         @post. se devuelve true si y solo si this es un estado final.
   */
  boolean end();

  /**
   * Calcula el valor de este estado. Si el estado es una hoja
   * (estado final), entonces este valor es exacto e indica el
   * resultado del juego. Si el estado no es final, entonces
   * este valor es una estimación aproximada. Su estimación juega
   * un rol crucial en el rendimiento de la búsqueda.
   * Este valor debe ser mayor que minValue() y menor que maxValue().
   * 
   * @return un valor entero que representa el valor del estado.
   *         @pre. this != null.
   *         @post. se devuelve un valor entero que representa el valor del
   *         estado.
   */
  int value();

  /**
   * Verifica si 'this' es igual a otro estado.
   * 
   * @param other es el estado con el que se compara 'this'.
   * @return true si y solo si 'this' es igual, como estado, a 'other'.
   *         @pre. other != null.
   *         @post. se devuelve true si y solo si 'this' es igual, como estado,
   *         a 'other'.
   */
  @Override
  boolean equals(Object other);

  /**
   * Devuelve una representación en forma de cadena del estado actual.
   * 
   * @return una cadena que representa el estado actual.
   *         @pre. true.
   *         @post. se devuelve una representación textual del estado actual.
   */
  @Override
  String toString();

  /**
   * Devuelve un objeto que representa la regla aplicada que llevó
   * al estado actual.
   * 
   * @return un objeto que representa la regla aplicada que llevó al
   *         estado actual. Si el estado es el estado inicial, se
   *         devuelve null.
   *         @pre. true.
   *         @post. se devuelve un objeto que representa la regla aplicada que
   *         llevó al estado actual. Si el estado es el inicial, se devuelve null.
   *         TODO Reemplazar Object por una clase o interfaz más específica.
   */
  String ruleApplied();

}
