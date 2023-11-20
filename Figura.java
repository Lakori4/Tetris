
package bloquestetris;

import tetris.Tetrimino;


public abstract class Figura extends Tetrimino {
    // Constructor que recibe la matriz para la figura
    public Figura(int[][] matriz) {
        super(matriz);
    }

    // Implementación predeterminada del método rotar
    @Override
    public void rotar() {
        // Lógica de rotación común a todas las figuras, si la hay
        super.rotar();
    }
}
