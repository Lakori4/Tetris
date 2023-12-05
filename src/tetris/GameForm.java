package tetris;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



/**
 * La clase GameForm representa la interfaz gráfica de usuario del juego Tetris.
 * Incluye el área de juego, controles por teclado y la visualización de puntuación y nivel.
 * Esta clase hereda de javax.swing.JFrame.
 *
 * @author ginar
 */
public class GameForm extends JFrame {

    private static GameForm instance; //Instancia de Singleton
    private final GameArea ga;
    /**
     * Constructor de la clase GameForm que inicializa la interfaz de usuario y el área de juego.
     */
    private GameForm() {
        initComponents();
        ga = new GameArea (gameAreaPlaceholder,10);
        this.add(ga);
        
        initControls();
        startGame();
        AudioPlayer.main_theme = new AudioPlayer("resources/main_theme.wav", 0);
    }

    /**
     * Método estático para obtener la instancia única de GameForm.
     *
     * @return La instancia única de GameForm.
     */
    public static GameForm getInstance() {
        if (instance == null) {
            instance = new GameForm();

        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;

    }

    /**
     * Método para los controles por teclado
     *
     * Input map → Contiene las pulsaciones de teclas
     * Action map → Contiene las acciones de las teclas
     *
     * Inicializa los controles por teclado para el juego.
     */
    @SuppressWarnings("JavadocBlankLines")
    private void initControls(){
        
        InputMap im = this.getRootPane().getInputMap();
        ActionMap am = this.getRootPane().getActionMap();
        
        im.put(KeyStroke.getKeyStroke("RIGHT"),"right");
        im.put(KeyStroke.getKeyStroke("LEFT"),"left");
        im.put(KeyStroke.getKeyStroke("UP"),"up");
        im.put(KeyStroke.getKeyStroke("DOWN"),"down");
        im.put(KeyStroke.getKeyStroke("SPACE"),"space" );
        
        /* Las clases abstractas no pueden instanciarse, necesitamos la clase 
        Action que es abstracta. Para solucionar el hecho de que no puede 
        instanciarse creamos otra clase (clase anónima) y hacemos que esta herede de Action
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
        am.put("space",new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                while (ga.checkBottom()) {
                    ga.dropTetrimino();
                }
            }
        });
    }
    
    
    /**
     * Inicia el juego creando un nuevo hilo (Thread) para la lógica del juego.
     */
    public void startGame(){
        
        //El método start está disponible en la clase Thread y se hereda a GameThread
        new GameThread(ga, this).start();
    }
    
    /**
     * Actualiza la visualización de la puntuación en la interfaz de usuario.
     *
     * @param score Puntuación actual del juego.
     */
    public void updateScore(int score){
        scoreDisplay.setText("Score: " + score);
    }
    
    /**
     * Actualiza la visualización del nivel en la interfaz de usuario.
     *
     * @param nivel Nivel actual del juego.
     */
    public void updateNivel (int nivel){

        nivelDisplay.setText("Nivel: "+ nivel);
    }
    
  
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanel();
        gameAreaPlaceholder = new JPanel();
        scoreDisplay = new JLabel();
        nivelDisplay = new JLabel();
        btnMainMenu = new JButton();

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        gameAreaPlaceholder.setBackground(new java.awt.Color(238, 238, 238));
        gameAreaPlaceholder.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        GroupLayout gameAreaPlaceholderLayout = new GroupLayout(gameAreaPlaceholder);
        gameAreaPlaceholder.setLayout(gameAreaPlaceholderLayout);
        gameAreaPlaceholderLayout.setHorizontalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        gameAreaPlaceholderLayout.setVerticalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        scoreDisplay.setBackground(new java.awt.Color(255, 255, 255));
        scoreDisplay.setFont(new java.awt.Font("Bahnschrift", Font.PLAIN, 13)); // NOI18N
        scoreDisplay.setForeground(new java.awt.Color(255, 0, 51));
        scoreDisplay.setText("Score: 0");

        nivelDisplay.setBackground(new java.awt.Color(255, 255, 255));
        nivelDisplay.setFont(new java.awt.Font("Bahnschrift", Font.PLAIN, 14)); // NOI18N
        nivelDisplay.setForeground(new java.awt.Color(255, 0, 51));
        nivelDisplay.setText("Nivel: 1");

        btnMainMenu.setText("Menú principal");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed();
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnMainMenu)
                .addGap(30, 30, 30)
                .addComponent(gameAreaPlaceholder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scoreDisplay, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                    .addComponent(nivelDisplay, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(gameAreaPlaceholder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(scoreDisplay)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nivelDisplay))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnMainMenu)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

     void btnMainMenuActionPerformed() {//GEN-FIRST:event_btnMainMenuActionPerformed
        setVisible(false); //Esconde la ventana de juego
        dispose(); //Elimina la ventana de juego
        destroyInstance(); //Elimina la instancia Singleton
        AudioPlayer.main_theme.reset();

        new MainMenu(); //Llama al menú principal como si hubiera vuelto a empezar

    }//GEN-LAST:event_btnMainMenuActionPerformed

    
    public static void main(String[] args) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException |
                 InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //Ejecuta el menú principal
        new MainMenu();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnMainMenu;
    private JPanel gameAreaPlaceholder;
    private JPanel jPanel1;
    private JLabel nivelDisplay;
    private JLabel scoreDisplay;
    // End of variables declaration//GEN-END:variables
}
