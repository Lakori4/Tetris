
package bloquestetris;


import java.awt.*;

/**
 * Representa la figura Tetrimino en forma de Z.
 * Extiende la clase abstracta Figura.
 */
public class FiguraZ extends Figura {

    /**
     * Constructor de la clase FiguraZ.
     * Inicializa la figura con una matriz específica.
     */
    public FiguraZ(){
        super(new int[][]{{1,1,0},{0,1,1}});
        setColor(new Color(104,182,36));
    }
}
