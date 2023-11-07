
package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;


public class GameThread extends Thread {
    
    private GameArea ga;
    private GameForm gf;
    private int score;
    private int nivel = 1;
    private int scorePorNivel = 2;
    
    private int pausa = 1000;
    private int velocidadPorNivel = 200;
    
    public GameThread(GameArea ga, GameForm gf){
        this.ga = ga;
        this.gf = gf;
    }
    
    @Override
    public void run(){
        
        /*Se crea un loop infinito que hace que caigan los tetriminos, es
        nuestro loop principal del juego*/
        while(true){
            
            ga.spawnTetrimino();
            while(ga.caerTetrimino() == true){
                /*Se mete dentro de una excepcion pues hay que especificar que pasa 
                si por algun motivo el .sleep se interrumpe
                */
                try {
                    /* Metodo de la clase Thread para esperar unos segundos y hacer 
                    que el tetrimino se vuelva a dibujar para simular que cae
                    */
                    Thread.sleep(pausa);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                if (ga.tetriminoFueraLimite()) {
                    
                    System.out.println("GAME OVER");
                    break;
                }
                ga.moverTetriminioABackground();
                score += ga.limpiarLineas();
                gf.updateScore(score);
                
                int nvl = score / scorePorNivel + 1; //Añadimos 1 porque nivel esta inicializado a 0
                if (nvl > nivel) {
                 
                  nivel = nvl;
                  gf.updateNivel(nivel);
                  pausa -= velocidadPorNivel;
            }
            
        }
    }
}
