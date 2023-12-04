/**
 * La clase GameArea representa el área de juego en el juego de Tetris, donde se muestran
 * los tetriminos y se gestiona la interacción con el jugador.
 */
package tetris;

import bloquestetris.*;

import java.awt.*;
import java.util.Random;
import javax.swing.JPanel;


/**
 * La clase GameArea extiende JPanel y representa el área de juego principal en Tetris.
 */
public class GameArea extends JPanel{
    
     // Atributos de la clase
    private final int gridRows;
    private final int gridColumns;
    
    /**
     * Matriz que representa el fondo del área de juego, donde los tetriminos
     * han llegado a los límites del tablero o están adyacentes a otras piezas.
     */
    private final Color[][] background;
    
     /**
     * Tamaño de una sola celda en el área de juego.
     */
    private final int gridCellSize;
    
   /**
     * Representa el tetrimino actual que se controla en el juego.
     */
    // Se utilizará 1 para indicar si un cuadrado debe tener color y 0 si debe estar en blanco
    private Tetrimino tetrimino;
    
    //Array que contiene todos los tipos de tetriminos
    private final Tetrimino[] tetriminos;
    
    
    
    /**
     * Constructor para crear el área de juego con base en un panel de referencia.
     *
     * @param placeholder El panel de referencia que determina el tamaño del área de juego.
     * @param columns     El número de columnas en el área de juego.
     */
    public GameArea(JPanel placeholder, int columns){
        //El placeholder es el diseño creado en la clase GameForm qe sirve como referencia para el tamaño del área
        
        //Placeholder invisible para que no se vea en pantalla
        //placeholder.setVisible(false); COMENTADO PORQUE NOS INTERESA QUE SE VEA
        
        //Usamos el área del placeholder como parameters para crear el área del juego
        setBounds(placeholder.getBounds());
        setBorder(placeholder.getBorder());
        setBackground(placeholder.getBackground());
        
        
        //Para obtener el tamaño de una celda, debemos dividir el tamaño del placeholder entre el número de columnas
       gridColumns = columns;
        gridCellSize = getBounds().width / gridColumns;
        gridRows = getBounds().height / gridCellSize;
        
       background = new Color[gridRows][gridColumns];
      
       tetriminos = new Tetrimino[]{new FiguraI(), new FiguraJ(), new FiguraL(),
           new FiguraO(), new FiguraS(), new FiguraT(), new FiguraZ()};
    }
    
    
    
   /**
     * Inicializa y muestra un nuevo tetrimino en el área de juego.
     */
    public void spawnTetrimino(){
       Random r = new Random();
       tetrimino = tetriminos[r.nextInt(tetriminos.length)];
       tetrimino.spawn(gridColumns);
    }
    
   /**
     * Comprueba si el tetrimino actual está fuera de los límites del área de juego.
     *
     * @return true si el tetrimino está fuera de los límites, de lo contrario, false.
     */
    public boolean tetriminoFueraLimite(){
        if (tetrimino.getY() < 0) {
            tetrimino = null;
            return true;
        }
        return false;
    }
    
    
    /**
     * Hace que el tetrimino actual caiga una posición hacia abajo en el área de juego.
     *
     * @return true si el tetrimino puede seguir cayendo, de lo contrario, false.
     */
    public boolean caerTetrimino(){
        
        /*Revisamos primero si la pieza ha tocado el límite, si no la movemos
        abajo, en cuanto llega al límite, hacemos que el tetrimino permanezca
        en la pantalla haciéndolo parte del background
        */
        if (!checkBottom()){
            
           
            return false;
        }
        
        tetrimino.moverAbajo();
        
        /*Se manda llamar el método repaint, pues tenemos que borrar y dibujar otra vez
        el tetrimino para que parezca que está cayendo*/
        repaint();
        
        return true;
    }
    
    /**
     * Mueve el tetrimino actual hacia la derecha en el área de juego, si es posible.
     */
    /*En todos los métodos que involucre mover o rotar el tetrimino debe agregarse
    el método repaint para que el movimiento del tetrimino sea más smooth
    */
    public void moverTetriminoDerecha(){
        if(tetrimino == null) return;
        if (!checkRight()) {
            return;
        }
        
        tetrimino.moverDer();
        repaint();
    }
    
     public void moverTetriminoIzquierda(){
         if(tetrimino == null) return;
         if (!checkLeft()) {
             return;
         }
         
        tetrimino.moverIzq();
        repaint();
    }
    
   /**
     * Hace que el tetrimino actual caiga instantáneamente hasta la parte inferior del área de juego.
     */
     public void dropTetrimino(){
         if(tetrimino == null) return;
         else if (checkBottom()){
            tetrimino.moverAbajo();
         }
         repaint();
     }
     
      /**
     * Rota el tetrimino actual .
     */
     public void rotarTetrimino(){
         if(tetrimino == null) return;
         tetrimino.rotar();
         if (tetrimino.getLimiteIzquierda() < 0) {
             tetrimino.setX(0);
         }
         if (tetrimino.getLimiteDerecha() >= gridColumns) {
             tetrimino.setX(gridColumns - tetrimino.getWidth());
         }
         if (tetrimino.getLimiteTablero() >= gridRows) {
             tetrimino.setY(gridRows - tetrimino.getHeight());
         }
         repaint();
     }
     
    
    /*Método para revisar si el tetrimino puede moverse o no dependiendo de si
    ha tocado el límite del tablero o alguna pieza
    */
    protected boolean checkBottom(){
        if (tetrimino.getLimiteTablero() == gridRows) {
            return false;
        }
        
        /*Esta sección sirve para comprobar si al momento de caer, el tetrimino
        está tocando otra pieza
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
    
    /**
     * Limpia las líneas completas en el área de juego y realiza un desplazamiento hacia abajo
     * de las filas restantes.
     *
     * @return El número de líneas limpiadas.
     */
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
                
                lineasLimpiadas++; /*Se incrementa cuando se limpia una línea y esta
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
            if (gridColumns >= 0) System.arraycopy(background[row - 1], 0, background[row], 0, gridColumns);
        }
    }
    
    /**
     * Mueve el tetrimino actual al fondo del área de juego y lo agrega al fondo.
     */
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
    
    //Método para dibujar los tetriminos
    private void drawTetrimino(Graphics g){
        
        //Creación de variables locales que toman como valor el retorno de los métodos de tetrimino
        //Esto para hacer más fácil el uso de las mismas en las líneas por debajo
        int h = tetrimino.getHeight();
        int w = tetrimino.getWidth();
        Color c = tetrimino.getColor();
        int[][] f = tetrimino.getForma();
        
        
        
        for (int row  = 0; row < h ; row++) {
            for (int col = 0; col < w; col++) {
                
                //(1 igual a que debe tener color)
                if (f[row][col]== 1) {
                    
                    int x = (tetrimino.getX() + col) * gridCellSize;
                    int y = (tetrimino.getY() + row) * gridCellSize;
                    
                    drawGridSquare(g, c, x, y);
                }
            }
        }
    }
    
    //Método para dibujar el fondo
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
        
      
        //Llamada al método para dibujar los tetriminos
        drawTetrimino(g);
        
        //Llamada al método para dibujar el fondo
        drawBackground(g);
   
    }
}
