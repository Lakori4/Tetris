
package bloquestetris;


/**
 * Representa la figura Tetrimino en forma de S.
 * Extiende la clase abstracta Figura.
 */
public class FiguraS extends Figura {

    /**
     * Constructor de la clase FiguraS.
     * Inicializa la figura con una matriz especÃ­fica.
     */
    public FiguraS(){
        super(new int[][]{{0,1,1},{1,1,0}});
    }
}
