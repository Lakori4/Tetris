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



        //Crea el panel con

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

        bJugar.addActionListener(this);
        bSalir.addActionListener(this);

        panel.add(Box.createRigidArea(new Dimension(300, 40)));
        panel.add(bJugar);
        panel.add(Box.createRigidArea(new Dimension(300, 30)));
        panel.add(bSalir);

        //pack();

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bJugar) {
            java.awt.EventQueue.invokeLater(new Runnable() {


                public void run() {
                    new GameForm().setVisible(true);
                }
            });
        }

        if (e.getSource() == bSalir) {
            System.exit(0);
        }
    }
}
