
package tetris;

import java.awt.*;
import java.util.Random;


public class Tetrimino {
    
    private int[][] forma;
    private Color color;
    private int x, y;
    
    /*Este atributo se diferencia del atributo "forma" pues guarda las distintas
    "versiones" que existen de una misma figura cuando esta se rota a 0°, 90°, 180°
    0 270°*/
    private int[][][] formas;
    
    private int rotacionActual;
    private Color[] coloresDisponibles = {Color.CYAN, Color.MAGENTA, Color.ORANGE};
    
    //Constructor para crear el tetrimino
    public Tetrimino(int[][] f){
    
        forma = f;
        initFormas();
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
    
    public void spawn(int gridWidth){
        
        Random r = new Random();
        rotacionActual = r.nextInt(formas.length);
        forma = formas[rotacionActual];
        
        y =  -getHeight(); //Es negativo porque asi aparece por encima del tablero
        x = r.nextInt(gridWidth- getWidth());
        color = coloresDisponibles[r.nextInt(coloresDisponibles.length)];
        
    }
    
    //Getter para acceder a la forma del tetrimino
    public int[][] getForma(){
        return forma;
    }
    
    //Getter para acceder al color
    public Color getColor(){
        return color;
    }
    
    public int getHeight() {
        return forma.length;
    }
    
    public int getWidth(){
        return forma[0].length;
    }
    
    public int getY(){
        return y;
    }
    
    public int getX(){
        return x;
    }
    public void moverAbajo(){
        y++;
    }
    public void moverIzq(){
        x--;
    }
    public void moverDer(){
        x++;
    }
    public void rotar(){
        rotacionActual++;
        
        /*No puede exceder 3 porque tenemos solo 4 posibles rotaciones*/
        if (rotacionActual > 3) {
            rotacionActual = 0;
        }
        
        /*Rotacion actual funciona como indice del array "formas" para identificar
        cual de las 4 posibles rotaciones de un tetrimino se necesita*/
        forma = formas[rotacionActual];
    }
    
    public int getLimiteTablero(){
        return y + getHeight();
    }
    
    public int getLimiteDerecha(){
        return x + getWidth();
    }
    
    public int getLimiteIzquierda(){
        return x;
    }
    
}
