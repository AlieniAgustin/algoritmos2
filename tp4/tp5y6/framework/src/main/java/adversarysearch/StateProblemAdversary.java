package adversarysearch;

import java.util.List;

/**
 * Interfaz que define los elementos básicos necesarios para
 * caracterizar un problema como un problema de búsqueda. Las
 * instancias de estos problemas deben implementar esta interfaz,
 * para poder usar estrategias de búsqueda adversaria.
 * 
 * @param <S> es la clase que caracteriza el estado del problema.
 * @author Nazareno Aguirre
 */
public interface StateProblemAdversary<S extends StateAdversary> {

  /**
   * Devuelve el estado inicial correspondiente al problema.
   * Las implementaciones concretas de AdversarySearchProblem deben
   * implementar este método para indicar el punto de partida de la
   * búsqueda (adversaria).
   * 
   * @return el estado inicial del problema.
   *         @pre. true.
   *         @post. se devuelve el estado inicial del problema.
   */
  S initialState();

  /**
   * Devuelve la lista de estados sucesores para un estado dado, en el
   * contexto del problema actual. Las implementaciones concretas de
   * StateProblemAdversary deben implementar este método para indicar
   * las reglas de avance (o reglas del juego) para la búsqueda.
   * 
   * @param s es el estado para el cual se están calculando los sucesores.
   * @return la lista de estados sucesores de s.
   *         @pre. s != null.
   *         @post. se devuelve la lista de estados sucesores de s.
   */
  List<S> getSuccessors(S s);

  /**
   * Indica el valor mínimo posible para un estado en el problema.
   * Junto con maxValue(), determina un intervalo dentro del cual
   * deben estar los valores de los estados. Este valor debe ser
   * estrictamente menor que maxValue().
   * 
   * @return un valor entero que representa el valor mínimo posible
   *         para los estados del problema.
   *         @pre. true.
   *         @post. se devuelve un valor entero que representa el valor mínimo
   *         posible para los estados.
   */
  int minValue();

  /**
   * Indica el valor máximo posible para un estado en el problema.
   * Junto con minValue(), determina un intervalo dentro del cual
   * deben estar los valores de los estados.
   * 
   * @return un valor entero que representa el valor máximo posible
   *         para los estados del problema. Este valor debe ser
   *         estrictamente mayor que minValue().
   *         @pre. true.
   *         @post. se devuelve un valor entero que representa el valor máximo
   *         posible para los estados.
   */
  int maxValue();
}
