package shooter;
import java.awt.*;

public class Zombie {
    Screen s;
    Weapon weapon;
    double x, y, dx, dy;
    
    double maxMoveSpeed = 2;
    double moveSpeed = 0.2;
    double acc = 0.5; double dcc = 0.25;
    
    int health = 100;
    boolean attack;
    
    boolean[] keys = {false, false, false, false};
    
    public Zombie(Screen s, int x, int y){
        this.s = s;
        this.x = x;
        this.y = y;
    }
    public void tick(){
        x += dx;
        y += dy;
        
        if(attack) Movement();
        
        if(health  <= 0) s.l.z.remove(this);
    }
    public void paint(Graphics g){
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 50, 50);
        g.setColor(Color.black);
        g.drawRect((int)x, (int)y, 50, 50);
    }
    public void Movement(){
        if(s.p.x < x){
            keys[0] = true;
            keys[1] = false;
        }
        else{
            keys[1] = true;
            keys[0]= false;
        }
        if(s.p.y < y){
            keys[2] = true;
            keys[3] = false;
        }
        else{
            keys[3] = true;
            keys[2]= false;
        }
        
        
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
    
    public Rectangle Zombie(){
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
}