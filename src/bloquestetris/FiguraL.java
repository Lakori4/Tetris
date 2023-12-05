
package bloquestetris;

import java.awt.*;

/**
 * Representa la figura Tetrimino en forma de L.
 * Extiende la clase abstracta Figura.
 */
public class FiguraL extends Figura {

    /**
     * Constructor de la clase FiguraL.
     * Inicializa la figura con una matriz específica.
     */
    public FiguraL(){

        super(new int[][]{{1,0},{1,0},{1,1}});
        setColor(new Color(254,141,0));
    }
}
