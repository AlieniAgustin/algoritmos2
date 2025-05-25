package engine;

import adversarysearch.EngineAdversary;
import adversarysearch.StateAdversary;
import adversarysearch.StateProblemAdversary;
import conventionalsearch.State;

public class MinMaxEngine<S extends StateAdversary, P extends StateProblemAdversary<S>>
        implements EngineAdversary<S, P> {

    private int maxDepth;
    private S promisingSuccessor = null;
    private P sp;

    /**
     * @pre maxDepth >= 1
     */
    public MinMaxEngine(P sp, int maxDepth) {
        if (maxDepth < 1)
            throw new IllegalArgumentException();

        this.sp = sp;
        this.maxDepth = maxDepth;
    }

    /**
     * Devuelve la profundidad máxima que se usará para la búsqueda.
     * Este valor indica cuántos movimientos futuros del juego se van a
     * explorar para calcular el valor de un estado.
     * 
     * @return la profundidad máxima que se usará para la búsqueda.
     *         @pre. true.
     *         @post. se devuelve el valor de maxDepth.
     */
    public int getMaxDepth() {
        return maxDepth;
    }

    /**
     * Establece la profundidad máxima que se usará para la búsqueda.
     * Este valor indica cuántos movimientos futuros del juego se van a
     * explorar para calcular el valor de un estado.
     * 
     * @param maxDepth es el valor que se usará para asignar this.maxDepth.
     *                 @pre. maxDepth >= 1.
     *                 @post. this.maxDepth se establece con el valor de maxDepth.
     */
    public void setMaxDepth(int maxDepth) {
        if (maxDepth < 1)
            throw new IllegalArgumentException();

        this.maxDepth = maxDepth;
    }

    /**
     * Devuelve el problema asociado a este motor de búsqueda.
     * 
     * @return una referencia al problema asociado con este motor de búsqueda.
     *         @pre. true.
     *         @post. se devuelve this.problem.
     */
    public P getProblem() {
        return sp;
    }

    /**
     * Establece el problema asociado al motor de búsqueda.
     * 
     * @param p es el problema de búsqueda que se usará (para asignar a 'problem').
     *          @pre. p != null.
     *          @post. 'problem' se establece con p.
     */
    public void setProblem(P p) {
        if (p == null)
            throw new IllegalArgumentException();

        this.sp = p;
    }

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
    public int computeValue(S state) {

        return computeValue(state, 0);
    }

    private int computeValue(S state, int depth) {
        if (state.end() || depth == maxDepth) {
            return state.value();
        } else {
            int max = sp.minValue(); // -inf
            int min = sp.maxValue(); // +inf

            for (S s : sp.getSuccessors(state)) {
                if (state.isMax())
                    max = Math.max(max, computeValue(s, depth + 1));
                else
                    min = Math.min(min, computeValue(s, depth + 1));
            }

            if (state.isMax())
                return max;
            else
                return min;
        }
    }

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
    public S computeSuccessor(S state) {
        if (sp == null || state == null)
            throw new IllegalArgumentException();

        S promisingSuccessor = null;
        if (!state.end()) {
            int max = sp.minValue();
            int min = sp.maxValue();
            for (S s : sp.getSuccessors(state)) {
                int value = computeValue(s, 1); // no desde 0 porque ya baje una profundidad
                if (state.isMax()) {
                    if (value >= max) {
                        max = value;
                        promisingSuccessor = s;
                    }
                } else {
                    if (value <= min) {
                        min = value;
                        promisingSuccessor = s;
                    }
                }
            }
        }
        this.promisingSuccessor = promisingSuccessor;
        return promisingSuccessor;
    }

    /**
     * Reporta información sobre una búsqueda previamente ejecutada.
     * @pre. computeSuccessor(s) o computeValue(s) han sido
     * ejecutados y finalizados.
     * @post. se imprime un reporte sobre la última búsqueda realizada
     * en la salida estándar.
     */
    public void report() {
        System.out.println(promisingSuccessor.ruleApplied());
        System.out.println(promisingSuccessor.toString());
    }
}
