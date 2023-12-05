
package bloquestetris;

import java.awt.*;

/**
 * Representa la figura Tetrimino en forma de O.
 * Extiende la clase abstracta Figura.
 */
public class FiguraO extends Figura {

    /**
     * Constructor de la clase FiguraO.
     * Inicializa la figura con una matriz específica.
     */
    public FiguraO(){

        super(new int[][]{{1,1},{1,1}});
        setColor(new Color(249,255,0));
    }
}
