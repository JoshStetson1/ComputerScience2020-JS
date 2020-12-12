package shooter;
import java.awt.GridLayout;
import javax.swing.JFrame;

public class Shooter {

    public static void main(String[] args) {
        JFrame f = new JFrame("Zombie Shooter");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(850, 585);
        f.setLayout(new GridLayout(1, 1, 0, 0));
        f.setLocationRelativeTo(null);
        //f.setResizable(false);
        Screen s = new Screen(f.getX(), f.getY());
        f.add(s);
        f.setVisible(true);
    }
    
}
