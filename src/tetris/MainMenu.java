package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
* Esta clase se encarga de mostrar el menú principal, donde
* se muestran opciones para jugar y para salir.
* */

public class MainMenu extends JFrame implements ActionListener {
    //Botones para jugar y para salir;
    private JButton bJugar, bSalir;
    private JPanel panel;
    public static GameForm game;

    /**
     *Constructor para crear la ventana del menú principal que será llamado
     * al iniciar el juego y cada vez que el usuario decida volver al menú principal
     */

    public MainMenu () {

        //Crea la ventana
        setTitle("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,250);

        //Creamos el panel con la distribución en caja y lo añadimos al JFrame
        panel = new JPanel();
        setLocationRelativeTo(null);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        getContentPane().add(panel);



        //Crea los botones "Jugar" y "Salir", con sus respectivos tamaños, tamaño de fuente y alineación.

        bJugar = new JButton("Jugar") {
            {
                setSize(150, 75);
                setFont(new Font("Arial", Font.BOLD, 40));
                setMaximumSize(getSize());
                setAlignmentX(Component.CENTER_ALIGNMENT);
            }
        };
        bSalir = new JButton("Salir"){
            {
                setSize(75, 50);
                setFont(new Font("Arial", Font.PLAIN, 20));
                setMaximumSize(getSize());
                setAlignmentX(Component.CENTER_ALIGNMENT);
            }
        };

        //Genera los "listeners" para los botones.
        bJugar.addActionListener(this);
        bSalir.addActionListener(this);

        //Crea espacios intermedios entre los botones y el JFrame
        panel.add(Box.createRigidArea(new Dimension(300, 40)));
        panel.add(bJugar);
        panel.add(Box.createRigidArea(new Dimension(300, 30)));
        panel.add(bSalir);

        //Hace la ventana visible
        setVisible(true);
    }

    /**
     * @param e the event to be processed
     * Método para "escuchar" a los botones "Jugar" y "Salir".
     * "Jugar" ejecuta el juego, y "Salir" termina el programa.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bJugar) {
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {


                public void run() {
                    game = GameForm.getInstance(); //Ejecuta el juego
                    dispose();

                    game.setVisible(true);
                }
            });
        }

        if (e.getSource() == bSalir) {
            System.exit(0); //Sale del juego
        }
    }
}
