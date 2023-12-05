
package bloquestetris;

import java.awt.*;



/**
 *
 * @author ginar
 */
public class FiguraI extends Figura {


    /**
     * Constructor de la clase FiguraI.
     * Inicializa la figura con una matriz específica.
     */
    public FiguraI(){

        super(new int[][]{{1,1,1,1}});
        setColor(new Color(0, 228, 255));
    }

    /**
     * Sobrescribe el método rotar de la superclase Tetrimino.
     * Ajusta la rotación para una apariencia más amigable.
     */
    @Override
    public void rotar(){
        super.rotar();

        if (this.getWidth() == 1) {
            this.setX(this.getX() + 1);
            this.setY(this.getY() - 1);
        }
        else{
            this.setX(this.getX() - 1);
            this.setY(this.getY() + 1);
        }
    }
}
