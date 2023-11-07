/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetris;

import java.awt.*;
import javax.swing.JPanel;


/**
 *
 * @author ginar
 */
public class GameArea extends JPanel{
    
    
    private int gridRows;
    private int gridColumns;
    
    /*Atributo fondo que simboliza el area donde se encuentran los tetriminos
    que han llegado hasta el limite del tablero o estan conjuntas a otras piezas*/
    private Color[][] background;
    
    //Atributo para indicar el tamaño de una sola celda
    private int gridCellSize;
    
    //Atributo para crear los tetriminos (De tipo tetrimino que a su vez es una matriz)
    // Se utilizara 1 para indicar si un cuadrado debe tener color y 0 si debe estar en blanco 
    private Tetrimino tetrimino;
    
    
    
    
    //Constructor para dibujar el area del juego donde apareceran los tetriminos
    public GameArea(JPanel placeholder, int columns){
        //El placeholder es el diseño creado en la clase GameForm qe sirve como referencia para el tamaño del area
        
        //Placeholder invisible para que no se vea en pantalla
        //placeholder.setVisible(false); COMENTADO PORQUE NOS INTERESA QUE SE VEA
        
        //Usamos el area del placeholder como parametros para crear el area del juego
        setBounds(placeholder.getBounds());
        setBorder(placeholder.getBorder());
        setBackground(placeholder.getBackground());
        
        
        //Para obtener el tamaño de una celda, debemos dividir el tamaño de el placeholder entre el numero de columnas
       gridColumns = columns;
        gridCellSize = getBounds().width / gridColumns;
        gridRows = getBounds().height / gridCellSize;
        
       background = new Color[gridRows][gridColumns];
      
    }
    
    
    
    //Metodo para spawnear los tetriminos
    public void spawnTetrimino(){
       tetrimino = new Tetrimino( new int[][]{{1,0},{1,0},{1,1}});
       tetrimino.spawn(gridColumns);
    }
    
    //Metodo para comprobar si el tetrimino esta fuera de los limites
    public boolean tetriminoFueraLimite(){
        if (tetrimino.getY() < 0) {
            tetrimino = null;
            return true;
        }
        return false;
    }
    
    
    //Metodo para comprobar si el tetrimino puede seguir cayendo o no
    public boolean caerTetrimino(){
        
        /*Revisamos primero si la pieza ha tocado el limite , sino la movemos
        abajo, en cuanto llega al limite, hacemos que el tetrimino permanezca 
        en la pantalla haciendolo parte del background
        */
        if (checkBottom() == false){ 
            
           
            return false;
        }
        
        tetrimino.moverAbajo();
        
        /*Se manda llamar el metodo repaint pues tenemos que borrar y dibujar otra vez
        el tetrimino para que parezca que esta cayendo*/
        repaint();
        
        return true;
    }
    
    //Metodos para mover el tetrimino a los lados
    /*En todos los metodos que involucre mover o rotar el tetrimino debe agregarse
    el metodo repaint para que el movimiento del tetrimino sea mas smooth
    */
    public void moverTetriminoDerecha(){
        if(tetrimino == null) return;
        if (checkRight() == false) {
            return;
        }
        
        tetrimino.moverDer();
        repaint();
    }
    
     public void moverTetriminoIzquierda(){
         if(tetrimino == null) return;
         if (checkLeft() == false ) {
             return;
         }
         
        tetrimino.moverIzq();
        repaint();
    }
    
    //Metodo para hacer caer al tetrimino instantaneamente hasta abajo
     public void dropTetrimino(){
         if(tetrimino == null) return;
         while(checkBottom()){
            tetrimino.moverAbajo();
         }
         repaint();
     }
     
     //Metodo para rotar el tetrimino
     public void rotarTetrimino(){
         if(tetrimino == null) return;
         tetrimino.rotar();
         repaint();
     }
     
    
    /*Metodo para revisar si el tetrimino puede moverse o no dependiendo de si
    ha tocado el limite del tablero o alguna pieza
    */
    private boolean checkBottom(){
        if (tetrimino.getLimiteTablero() == gridRows) {
            return false;
        }
        
        /*Esta seccion sirve para comprobar si al momento de caer, el tetrimino
        esta tocando otra pieza
        */
        int[][] forma = tetrimino.getForma();
        int w = tetrimino.getWidth();
        int h = tetrimino.getHeight();
        
        for (int col = 0; col < w; col++) {
            for (int row = h-1; row >= 0; row--) {
                
                if (forma[row][col]!= 0) {
                    int x = col + tetrimino.getX();
                    int y = row + tetrimino.getY()+ 1;
                    if(y < 0) break;
                    if (background[y][x] != null) return false;
                    break;
                }
            }
        }
        
        
        return true;
    }
    
    private boolean checkLeft(){
        if (tetrimino.getLimiteDerecha() == 0) {
            return false;
        }
        
        int[][] forma = tetrimino.getForma();
        int w = tetrimino.getWidth();
        int h = tetrimino.getHeight();
        
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                
                if (forma[row][col]!= 0) {
                    int x = col + tetrimino.getX()-1;
                    int y = row + tetrimino.getY();
                    if(y < 0) break;
                    if (background[y][x] != null) return false;
                    break;
                }
            }
        }
        
        return true;
    }
    
    private boolean checkRight(){
        if (tetrimino.getLimiteDerecha() == gridColumns) {
            return false;
        }
        
        int[][] forma = tetrimino.getForma();
        int w = tetrimino.getWidth();
        int h = tetrimino.getHeight();
        
        for (int row = 0; row < h; row++) {
            for (int col = w-1; col >= 0; col--) {
                
                if (forma[row][col]!= 0) {
                    int x = col + tetrimino.getX()+1;
                    int y = row + tetrimino.getY();
                    if(y < 0) break;
                    if (background[y][x] != null) return false;
                    break;
                }
            }
        }
        
        return true;
    }
    
    
    public int limpiarLineas(){
        
        boolean lineaCompleta;
        int lineasLimpiadas = 0;
        
        for (int r = gridRows-1; r >= 0; r--) {
            
            lineaCompleta = true;
            for (int c = 0; c < gridColumns; c++) { //Caso 1. Linea completa
                if (background[r][c]== null) {
                    
                    lineaCompleta = false;
                    break; //Caso 2. Linea no completa
                }
            }
            
            if (lineaCompleta) {
                
                lineasLimpiadas++; /*Se incrementa cuando se limpia una linea y esta
                actualiza el score*/
                limpiarLinea(r);
                shiftDown(r);
                limpiarLinea(0);
                r++;
                repaint();
            }
        }
        return lineasLimpiadas;
    }

    private void limpiarLinea(int r){
        for (int i = 0; i < gridColumns; i++) {
                    background[r][i] = null;
        }
    }
    
    private void shiftDown(int r){
        for (int row = r; row > 0; row--) {
            for (int col = 0; col < gridColumns; col++) {
                
                background[row][col] = background[row-1][col];
            }
        }
    }
    
    public void moverTetriminioABackground(){
        int[][] forma = tetrimino.getForma();
        int h = tetrimino.getHeight();
        int w = tetrimino.getWidth();
        
        int xPos = tetrimino.getX();
        int yPos = tetrimino.getY();
        
        Color color = tetrimino.getColor();
        
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                if (forma[r][c] == 1) {
                    //Recordar que 1 es igual a que tiene color
                    background[r+ yPos][c + xPos] = color;
                }
            }
        }
    }
    
    //Metodo para dibujar los tetriminos
    private void drawTetrimino(Graphics g){
        
        //Creacion de variables locales que toman como valor el retorno de los metodos de tetrimino
        //Esto para hacer más facil el uso de las mismas en las líneas por debajo
        int h = tetrimino.getHeight();
        int w = tetrimino.getWidth();
        Color c = tetrimino.getColor();
        int[][] f = tetrimino.getForma();
        
        
        
        for (int row  = 0; row < h ; row++) {
            for (int col = 0; col < w; col++) {
                
                //( 1 igual a que debe tener color)
                if (f[row][col]== 1) {
                    
                    int x = (tetrimino.getX() + col) * gridCellSize;
                    int y = (tetrimino.getY() + row) * gridCellSize;
                    
                    drawGridSquare(g, c, x, y);
                }
            }
        }
    }
    
    //Metodo para dibujar el fondo
    private void drawBackground(Graphics g){
        Color color;
        
        for (int r = 0; r < gridRows; r++) {
            for (int c = 0; c < gridColumns; c++) {
                color = background[r][c];
                
                //Interpretaremos null como = no tiene color
                if (color != null) {
                    int x = c * gridCellSize;
                    int y = r * gridCellSize;
                    
                    drawGridSquare(g, color, x, y );
                }
                
            }
        }
    }
    
    private void drawGridSquare(Graphics g, Color color, int x, int y){
                    //Color del tetrimino
                    g.setColor(color);
                    //Relleno de color
                    g.fillRect(x, y, gridCellSize, gridCellSize);
                    //Color de los cuadros que conforman al tetrimino
                    g.setColor(Color.black);
                    //Dibujar 
                    g.drawRect(x, y, gridCellSize, gridCellSize);
    }
    
    //Método para dibujar componentes del juego
    @Override
    protected void paintComponent(Graphics g){
        
        //Llamada al método de la superclase JPanel
        super.paintComponent(g);
        
      
        //Llamada al metodo para dibujar los tetriminos
        drawTetrimino(g);
        
        //Llamada al metodo para dibujar el fondo
        drawBackground(g);
   
    }
}
