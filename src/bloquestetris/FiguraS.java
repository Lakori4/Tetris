
package bloquestetris;


import java.awt.*;

/**
 * Representa la figura Tetrimino en forma de S.
 * Extiende la clase abstracta Figura.
 */
public class FiguraS extends Figura {
    /**
     * Constructor de la clase FiguraS.
     * Inicializa la figura con una matriz específica.
     */
    public FiguraS(){
        super(new int[][]{{0,1,1},{1,1,0}});
        setColor(new Color(249,1,0));
    }
}
