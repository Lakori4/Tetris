import javax.swing.JFrame;
import java.awt.*;

public class Display {
    
    private JFrame frame;
    private String title;
    private int width, height;


    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay() {

        frame = new JFrame (title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);
        
        
    }

    public void game() {
        Graphics g = new Graphics();
        frame.paint(g);
        g.drawRect(10, 10, 10, 10);

        
    }



    
}
