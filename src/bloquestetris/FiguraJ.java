
package bloquestetris;

import java.awt.*;

/**
 * Representa la figura Tetrimino en forma de J.
 * Extiende la clase abstracta Figura.
 */
public class FiguraJ extends Figura {



    /**
     * Constructor de la clase FiguraJ.
     * Inicializa la figura con una matriz específica.
     */
    public FiguraJ(){
        super(new int[][]{{0,1},{0,1},{1,1}});
        setColor(new Color(254, 81, 188));
    }
}
