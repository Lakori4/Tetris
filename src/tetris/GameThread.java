
/**
 * La clase GameThread es un hilo que controla la lógica principal del juego Tetris,
 * incluyendo la caída de tetriminos, la gestión de puntuación y niveles, y la actualización
 * de la interfaz de usuario.
 */
package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase extiende Thread y se encarga de ejecutar la lógica principal del juego Tetris
 * en un hilo separado.
 */
public class GameThread extends Thread {
    
   // Atributos de la clase

    private GameArea ga; // El área de juego en la que se desarrolla el juego.
    private GameForm gf; // El formulario de juego que muestra la interfaz de usuario.
    private int score = 0; // La puntuación actual del jugador.
    private int nivel = 1; // El nivel actual del juego.
    private int scorePorNivel = 2; // Puntuación requerida para avanzar de nivel.

    private int pausa = 1000; // Pausa inicial entre caídas de tetriminos (en milisegundos).
    private int velocidadPorNivel = 50; // Reducción de la pausa por nivel (en milisegundos).

    
    
    
    /**
     * Constructor de la clase GameThread que recibe el área de juego y el formulario de juego
     * como parámetros.
     *
     * @param ga El área de juego en la que se desarrolla el juego.
     * @param gf El formulario de juego que muestra la interfaz de usuario.
     */
    public GameThread(GameArea ga, GameForm gf){
        this.ga = ga;
        this.gf = gf;
    }
    
    /**
     * El método run() se encarga de ejecutar la lógica principal del juego en un bucle infinito.
     * Controla la caída de los tetriminos, la puntuación, los niveles y la actualización de la interfaz de usuario.
     */
    @Override
    public void run(){
        
        /*Se crea un loop infinito que hace que caigan los tetriminos, es
        nuestro loop principal del juego*/
        while(true){
            
            ga.spawnTetrimino();
            while(ga.caerTetrimino())
            {
                /*Se mete dentro de una excepción, pues hay que especificar que pasa
                si por algún motivo el .sleep se interrumpe
                */
                try {
                    /* Método de la clase Thread para esperar unos segundos y hacer
                    que el tetrimino se vuelva a dibujar para simular que cae
                    */
                    Thread.sleep(pausa);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                if (ga.tetriminoFueraLimite()) {
                    
                    new GameOver(score);
                    break;
                }
                ga.moverTetriminioABackground();
                score += ga.limpiarLineas();
                gf.updateScore(score);
                
                int nvl = score / scorePorNivel + 1; //Añadimos 1 porque nivel está inicializado a 0
                if (nvl > nivel) {
                 
                  nivel = nvl;
                  gf.updateNivel(nivel);
                  pausa -= velocidadPorNivel;
            }
            
        }
    }
}
