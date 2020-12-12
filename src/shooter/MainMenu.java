package shooter;
import java.awt.*;
import java.awt.event.*;

public class MainMenu implements MouseListener{
    Screen s;
    String state = "menu";//what state the game is in, or what screen to load
    
    public MainMenu(Screen s){
        this.s = s;
    }
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        //graphics/ drawing stuff
        if(state.equals("menu"))
        {
            Font f = new Font("arial", Font.BOLD, 75);
            FontMetrics metrics = g.getFontMetrics(f);
            int length = metrics.stringWidth("ZOMBIE SHOOTER");
            g.setFont(f);
            g.setColor(Color.black);
            g.drawString("ZOMBIE SHOOTER", s.getWidth()/2-length/2+2, 102);
            g.setColor(new Color(235, 0, 0));
            g.drawString("ZOMBIE SHOOTER", s.getWidth()/2-length/2, 100);

            f = new Font("arial", Font.BOLD, 50);
            g.setFont(f);
            metrics = g.getFontMetrics(f);
            g.setColor(Color.white);

            length = metrics.stringWidth("PLAY");
            g.drawString("PLAY", s.getWidth()/2-length/2, 300);

            length = metrics.stringWidth("HELP");
            g.drawString("HELP", s.getWidth()/2-length/2, 400);

            length = metrics.stringWidth("QUIT");
            g.drawString("QUIT", s.getWidth()/2-length/2, 500);
        }
        else if(state.equals("weapon"))//weapon select
        {
            Font f = new Font("arial", Font.BOLD, 50);
            g.setFont(f);
            FontMetrics metrics = g.getFontMetrics(f);
            g.setColor(Color.white);

            int length = metrics.stringWidth("SHOTGUN");
            g.drawString("SHOTGUN", s.getWidth()/2-length/2, 200);

            length = metrics.stringWidth("MACHINE GUN");
            g.drawString("MACHINE GUN", s.getWidth()/2-length/2, 300);

            length = metrics.stringWidth("SNIPER");
            g.drawString("SNIPER", s.getWidth()/2-length/2, 400);
        }
    }
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        
        if(state.equals("menu")){
            if(x > 350 && x < 500){
                if(y > 250 && y < 300) state = "weapon";
                if(y > 350 && y < 400) state = "help";
                if(y > 450 && y < 500) System.exit(0);
            }
        } else if(state.equals("weapon")){
            if(x > 350 && x < 500){
                if(y > 150 && y < 200 || y > 250 && y < 300 || y > 350 && y < 400) s.state = "play";//if a gun is click, start the game
                
                if(y > 150 && y < 200) s.p.weapon = new Weapon(s, false, 5, 15, 5, 0.5, 20);//shotgun
                if(y > 250 && y < 300) s.p.weapon = new Weapon(s, true, 1, 15, 3, 0.1, 15);//machine gun
                if(y > 350 && y < 400) s.p.weapon = new Weapon(s, false, 1, 20, 0, 1.5, 100);//sniper
            }
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
