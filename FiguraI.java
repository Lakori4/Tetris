
package bloquestetris;

import tetris.Tetrimino;

/**
 *
 * @author ginar
 */
public class FiguraI extends Figura{
    public FiguraI(){
        super(new int[][]{{1,1,1,1}});
    }
    
    /**
     * Se hace override del método rotar de la superclase Tetrimino, pues cuando
     * se rota la figura I sin este método, la rotación se ve rara, entonces con 
     * este método la rotación es más amigable
     */
    @Override
    public void rotar(){
        super.rotar();
        
        if (this.getWidth() == 1) {
            this.setX(this.getX() +1);
            this.setY(this.getY() -1);
        }
        else{
            this.setX(this.getX() - 1);
            this.setY(this.getY() + 1);
        }
    }
}
