
package tetris;

import java.awt.event.*;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;


/**
 *
 * @author ginar
 */
public class GameForm extends javax.swing.JFrame {

    private GameArea ga;
    /**
     * Creates new form GameForm
     */
    public GameForm() {
        initComponents();
        ga =new GameArea (gameAreaPlaceholder,10);
        this.add(ga);
        
        initControls();
        startGame();
    }

    //Metodo para los controles por teclado
    /* Input map -> Contiene las pulsaciones de teclas
       Action map -> Contiene las acciones de las teclas
    */
    
    private void initControls(){
        
        InputMap im = this.getRootPane().getInputMap();
        ActionMap am = this.getRootPane().getActionMap();
        
        im.put(KeyStroke.getKeyStroke("RIGHT"),"right");
        im.put(KeyStroke.getKeyStroke("LEFT"),"left");
        im.put(KeyStroke.getKeyStroke("UP"),"up");
        im.put(KeyStroke.getKeyStroke("DOWN"),"down");
        
        /* Las clases abstractas no pueden instanciarse, necesitamos la clase 
        Action que es abstracta. Para solucionar el hecho de que no puede 
        instanciarse creamos otra clase (clase anonima) y hacemos que esta herede de Action
        */
        
        
        am.put("right", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moverTetriminoDerecha();
            }
        });
        am.put("left", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moverTetriminoIzquierda();
            }
        });
        
        am.put("up", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.rotarTetrimino();
            }
        });
            
        am.put("down",new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.dropTetrimino();
            }
        });
    }
    
    //Metodo que al ejecturar el programa comienza el Thread del juego
    public void startGame(){
        
        //El metodo start esta disponible en la clase Thread y se hereda a GameThread
        new GameThread(ga, this).start();
    }
    
    //Metodos para realizar la actualización de el display de score y nivel
    public void updateScore(int score){
        scoreDisplay.setText("Score: " + score);
    }
    
    public void updateNivel (int nivel){
        nivelDisplay.setText("Nivel: "+ nivel);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        gameAreaPlaceholder = new javax.swing.JPanel();
        scoreDisplay = new javax.swing.JLabel();
        nivelDisplay = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        gameAreaPlaceholder.setBackground(new java.awt.Color(238, 238, 238));
        gameAreaPlaceholder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout gameAreaPlaceholderLayout = new javax.swing.GroupLayout(gameAreaPlaceholder);
        gameAreaPlaceholder.setLayout(gameAreaPlaceholderLayout);
        gameAreaPlaceholderLayout.setHorizontalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );
        gameAreaPlaceholderLayout.setVerticalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        scoreDisplay.setBackground(new java.awt.Color(255, 255, 255));
        scoreDisplay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        scoreDisplay.setForeground(new java.awt.Color(255, 0, 51));
        scoreDisplay.setText("Score: 0");

        nivelDisplay.setBackground(new java.awt.Color(255, 255, 255));
        nivelDisplay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nivelDisplay.setForeground(new java.awt.Color(255, 0, 51));
        nivelDisplay.setText("Nivel: 1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(gameAreaPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scoreDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nivelDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(gameAreaPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(scoreDisplay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nivelDisplay)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel gameAreaPlaceholder;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nivelDisplay;
    private javax.swing.JLabel scoreDisplay;
    // End of variables declaration//GEN-END:variables
}
