package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends JFrame implements ActionListener {

    private JButton bMenu;
    private JLabel lScore;
    private JPanel panel;

    /**
     * @param score
     * Constructor de la ventana de GameOver y de su lógica.
     */
    public GameOver(int score) {
        AudioPlayer.main_theme.reset();

        AudioPlayer.game_over = new AudioPlayer("resources/game_over.wav", 1);
        //Crea una ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);

        //Creamos el panel con la distribución en caja y lo añadimos al JFrame
        panel = new JPanel();
        setLocationRelativeTo(null);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        getContentPane().add(panel);

        //Crea la etiqueta "lScore" para indicar al jugador su puntuación

        lScore = new JLabel("<html>Fin de la partida<br/>Puntuación: " + score, SwingConstants.CENTER);
            lScore.setFont(new Font("Arial", Font.PLAIN, 20));
            lScore.setAlignmentX(Component.CENTER_ALIGNMENT);


        //Crea el botón "bMenu" ajustando su tamaño y alineación y con el texto "Volver al menú principal"
        bMenu = new JButton("Volver al menú principal") {
            {
                setSize(250, 50);
                setFont(new Font("Arial", Font.PLAIN, 18));
                setMaximumSize(getSize());
                setAlignmentX(Component.CENTER_ALIGNMENT);
            }
        };

        bMenu.addActionListener(this);

        panel.add(Box.createRigidArea(new Dimension(300, 40)));
        panel.add(lScore);
        panel.add(Box.createRigidArea(new Dimension(300, 50)));
        panel.add(bMenu);


        //Hace la ventana visible
        setVisible(true);

    }


    /**
     * @param evt the event to be processed
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == bMenu) {
            dispose(); //Eliminamos la ventana de "GameOver"
            AudioPlayer.game_over.reset();
            MainMenu.game.btnMainMenuActionPerformed(); //Llamamos a la función del botón de la pantalla principal para eliminar el JFrame del juego
        }

    }
}
