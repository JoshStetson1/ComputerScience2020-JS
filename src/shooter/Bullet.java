package shooter;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Bullet {
    Screen s;
    double x, y, dx, dy;
    
    int damage, hw;
    
    public Bullet(Screen s, double x, double y, int hw){
        this.s = s;
        if(hw > 35) this.hw = 35;
        else this.hw = hw;
        this.x = x-(this.hw/2);
        this.y = y-(this.hw/2);
    }
    public void tick(){
        x += dx;
        y += dy;
    }
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g.setColor(Color.black);
        
        Ellipse2D.Double bullet = new Ellipse2D.Double((int)x, (int)y, hw, hw);
        g2.fill(bullet);
    }
    public Rectangle bullet(){
        return new Rectangle((int)x, (int)y, 10, 10);
    }
}
