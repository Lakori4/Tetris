/**
 * La clase Tetrimino representa una pieza individual del juego Tetris, con una forma, color y posición específicos.
 */
package tetris;

import java.awt.*;
import java.util.Random;

/**
 * La clase Tetrimino se utiliza para crear y gestionar las piezas del juego Tetris.
 */
public class Tetrimino {
    
    // Atributos de la clase

    private int[][] forma; // La forma actual del tetrimino.
    private Color color; // El color del tetrimino.
    private int x, y; // La posición (coordenadas X e Y) del tetrimino en el área de juego.

    
    /**
     * Este atributo almacena las distintas "versiones" de una figura cuando se rota a 0°, 90°,
     * 180° o 270°. Cada versión se representa como una matriz de enteros.
     */
    private int[][][] formas;
    
    private int rotacionActual;

    /**
     * Constructor para crear un tetrimino con una forma inicial.
     *
     * @param f La forma inicial del tetrimino representada como una matriz de enteros.
     */
    public Tetrimino(int[][] f){
    
        forma = f;
        initFormas();
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    private void initFormas(){
        formas = new int[4][][];
        for (int i = 0; i < 4; i++) {
            
            int r = forma[0].length;
            int c = forma.length;
            
            formas[i] = new int[r][c];
            
            for (int y = 0; y < r; y++) {
                for (int x = 0; x < c; x++) {
                    formas[i][y][x] = forma[c-x-1][y];
                }
            }
            
            forma = formas[i];
        }
    }
    
    /**
     * Inicializa el tetrimino y lo coloca en una posición aleatoria en la parte superior del área de juego.
     *
     * @param gridWidth Ancho del área de juego.
     */
    public void spawn(int gridWidth){
        
        Random r = new Random();
        rotacionActual = r.nextInt(formas.length);
        forma = formas[rotacionActual];
        
        y =  -getHeight(); //Es negativo porque asi aparece por encima del tablero
        x =  gridWidth/2;
    }
    
    
    /**
     * Obtiene la forma actual del tetrimino.
     *
     * @return La forma del tetrimino representada como una matriz de enteros.
     */
    //Getter para acceder a la forma del tetrimino
    public int[][] getForma(){
        return forma;
    }
    
    /**
     * Obtiene el color del tetrimino.
     *
     * @return El color del tetrimino.
     */
    //Getter para acceder al color
    public Color getColor(){
        return color;
    }
    
     /**
     * Obtiene la altura del tetrimino.
     *
     * @return La altura del tetrimino (número de filas en su forma).
     */
    public int getHeight() {
        return forma.length;
    }
    
    /**
     * Obtiene el ancho del tetrimino.
     *
     * @return El ancho del tetrimino (número de columnas en su forma).
     */
    public int getWidth(){
        return forma[0].length;
    }
    
     /**
     * Obtiene la coordenada Y (posición vertical) del tetrimino en el área de juego.
     *
     * @return La coordenada Y del tetrimino.
     */
    public int getY(){
        return y;
    }
    
    /**
     * Obtiene la coordenada X (posición horizontal) del tetrimino en el área de juego.
     *
     * @return La coordenada X del tetrimino.
     */
    public int getX(){
        return x;
    }
    
    /**
     * Mueve el tetrimino una posición hacia abajo en el área de juego.
     */
    public void moverAbajo(){
        y++;
    }
    
    /**
     * Mueve el tetrimino una posición hacia la izquierda en el área de juego.
     */
    public void moverIzq(){
        x--;
    }
    
    /**
     * Mueve el tetrimino una posición hacia la derecha en el área de juego.
     */
    public void moverDer(){
        x++;
    }
    
    /**
     * Rota el tetrimino en sentido horario.
     */
    public void rotar(){
        rotacionActual++;
        
        /*No puede exceder 3 porque tenemos solo 4 posibles rotaciones*/
        if (rotacionActual > 3) {
            rotacionActual = 0;
        }
        
        /*Rotación actual funciona como índice del array "formas" para identificar
        cual de las 4 posibles rotaciones de un tetrimino se necesita*/
        forma = formas[rotacionActual];
    }
    
    /**
     * Obtiene el límite inferior del tablero al que ha llegado el tetrimino.
     *
     * @return El límite inferior del tablero alcanzado por el tetrimino.
     */
    public int getLimiteTablero(){
        return y + getHeight();
    }
     /**
     * Obtiene el límite derecho del tablero al que ha llegado el tetrimino.
     *
     * @return El límite derecho del tablero alcanzado por el tetrimino.
     */
    public int getLimiteDerecha(){
        return x + getWidth();
    }
    /**
     * Obtiene el límite izquierdo del tablero al que ha llegado el tetrimino.
     *
     * @return El límite izquierdo del tablero alcanzado por el tetrimino.
     */
    public int getLimiteIzquierda(){
        return x;
    }

    public void setX(int newX){
        x = newX;
    }
    
    public void setY(int newY){
        y = newY;
    }
}
