
package bloquestetris;


import java.awt.*;

/**
 * Representa la figura Tetrimino en forma de T.
 * Extiende la clase abstracta Figura.
 */
public class FiguraT extends Figura {

    /**
     * Constructor de la clase FiguraT.
     * Inicializa la figura con una matriz específica.
     */
    public FiguraT(){
        super(new int[][]{{1,1,1},{0,1,0}});
        setColor(new Color(157,2,150));
    }
}
