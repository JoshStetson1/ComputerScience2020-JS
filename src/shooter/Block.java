package shooter;
import java.awt.*;

public class Block {
    Screen s;
    int x, y;
    
    public Block(Screen s, int x, int y){
        this.s = s;
        this.x = x;
        this.y = y;
    }
    public void paint(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(x, y, 50, 50);
        
        g.setColor(Color.black);
        g.drawRect(x, y, 50, 50);
    }
    public Rectangle Block(){
        return new Rectangle(x, y, 50, 50);
    }
    public Rectangle Top(){
        return new Rectangle(x+10, y, 30, 10);
    }
    public Rectangle Bottom(){
        return new Rectangle(x+10, y+40, 30, 10);
    }
    public Rectangle Right(){
        return new Rectangle(x+40, y+10, 10, 30);
    }
    public Rectangle Left(){
        return new Rectangle(x, y+10, 10, 30);
    }
}
