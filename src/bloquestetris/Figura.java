
package bloquestetris;

import tetris.Tetrimino;


/**
 * Clase abstracta que representa una figura Tetrimino.
 * Extiende la clase Tetrimino.
 */
public abstract class Figura extends Tetrimino {

    /**
     * Constructor de la clase Figura.
     * Inicializa la figura con una matriz dada.
     *
     * @param matriz La matriz que representa la figura.
     */
    public Figura(int[][] matriz) {
        super(matriz);
    }

    /**
     * Implementación predeterminada del método rotar.
     * Lógica de rotación común para todas las figuras.
     */

}
