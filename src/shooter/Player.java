package shooter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Player implements MouseListener{
    Screen s;
    Weapon weapon;
    double x, y, dx, dy;
    
    double maxMoveSpeed = 5;
    double acc = 0.5; double dcc = 0.25;
    double moveSpeed = 0.2;
    
    boolean shooting, isDead;
    
    boolean[] keys = {false, false, false, false};
    
    public Player(Screen s){
        this.s = s;
    }
    public void tick(){
        x += dx;
        y += dy;
        
        Movement();
        
        if(weapon.isAuto) autoGun();
    }
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        if(!isDead){
            g.setColor(Color.white);
            g.fillRect((int)x, (int)y, 50, 50);
            g.setColor(Color.black);
            g.drawRect((int)x, (int)y, 50, 50);
        }
        
        deathScreen(g2);
    }
    public void onScreen(Graphics2D g2){
        
    }
    public void deathScreen(Graphics2D g2){
        if(!isDead) return;
        
        g2.translate(-s.cam.x(), -s.cam.y());
        
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, s.getWidth(), s.getHeight());
        
        g2.setColor(new Color(225, 0, 0));
        Font f = new Font("arial", Font.BOLD, 100);
        FontMetrics metrics = g2.getFontMetrics(f);
        int length = metrics.stringWidth("EATEN");
        g2.setFont(f);
        g2.drawString("EATEN", s.getWidth()/2-length/2, 100);
        
        g2.setColor(new Color(225, 225, 225));
        f = new Font("arial", Font.BOLD, 50);
        metrics = g2.getFontMetrics(f);
        length = metrics.stringWidth("RETRY");
        g2.setFont(f);
        g2.drawString("RETRY", s.getWidth()/2-length/2, 300);
        
        length = metrics.stringWidth("EXIT");
        g2.setFont(f);
        g2.drawString("EXIT", s.getWidth()/2-length/2, 400);
    }
    
    public void Movement(){
        if(keys[0] && dx > -maxMoveSpeed) dx -= acc;
        else if(keys[1] && dx < maxMoveSpeed) dx += acc;
        else if(!keys[0] && !keys[1]){
            if(dx > 0) dx -= dcc;
            else if(dx < 0) dx += dcc;
        }
        
        if(keys[2] && dy > -maxMoveSpeed) dy -= acc;
        else if(keys[3] && dy < maxMoveSpeed) dy += acc;
        else if(!keys[2] && !keys[3]){
            if(dy > 0) dy -= dcc;
            else if(dy < 0) dy += dcc;
        }
        
    }
    public void autoGun(){
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        int mouseX = b.x-s.posX-40-s.cam.x();
        int mouseY = b.y-s.posY-65-s.cam.y();
        if(shooting) weapon.shoot(mouseX, mouseY);
    }
    
    public Rectangle Player(){
        return new Rectangle((int)x, (int)y, 50, 50);
    }
    public Rectangle Top(){
        return new Rectangle((int)x+10, (int)y, 30, 10);
    }
    public Rectangle Bottom(){
        return new Rectangle((int)x+10, (int)y+40, 30, 10);
    }
    public Rectangle Right(){
        return new Rectangle((int)x+40, (int)y+10, 10, 30);
    }
    public Rectangle Left(){
        return new Rectangle((int)x, (int)y+10, 10, 30);
    }

    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        
        if(mx > 325 && mx < 505){
            if(my > 250 && my < 310) s.Reset();
            if(my > 350 && my < 410) s.state = "menu";
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
