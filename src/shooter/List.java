package shooter;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;

public class List {
    Screen s;
    LinkedList<Bullet> b = new LinkedList<>();
    LinkedList<Block> B = new LinkedList();
    LinkedList<Zombie> z = new LinkedList();
    
    Bullet bullet;
    Block block;
    Zombie zombie;
    
    public List(Screen s){
        this.s = s;
    }
    public void tick(){
        for(int i = 0; i < b.size(); i++){//update all bullets
            bullet = b.get(i);
            bullet.tick();
        }
        for(int i = 0; i < z.size(); i++){//update all zombies
            zombie = z.get(i);
            if(!s.p.isDead) zombie.tick();
        }
        
        collisions();
    }
    public void paint(Graphics g){
        for(int i = 0; i < B.size(); i++){//paint all blocks
            block = B.get(i);
            block.paint(g);
        }
        for(int i = 0; i < b.size(); i++){//paint all bullets
            bullet = b.get(i);
            bullet.paint(g);
        }
        for(int i = 0; i < z.size(); i++){//paint all zombies
            zombie = z.get(i);
            zombie.paint(g);
        }
    }
    public void collisions(){
        for(int i = 0; i < B.size(); i++){
            block = B.get(i);
            
            //player-block collisions
            if(s.p.Top().intersects(block.Block())) s.p.y = block.y+50;
            if(s.p.Bottom().intersects(block.Block())) s.p.y = block.y-50;
            if(s.p.Right().intersects(block.Block())) s.p.x = block.x-50;
            if(s.p.Left().intersects(block.Block())) s.p.x = block.x+50;
        }
        for(int i = 0; i < z.size(); i++){
            zombie = z.get(i);
            if(zombie.Zombie().intersects(s.p.Player())) s.p.isDead = true;
            
            boolean attack = true;
            for(int h = 0; h < B.size(); h++){
                block = B.get(h);
                //zombie-block collisions
                if(zombie.Top().intersects(block.Block())) zombie.y = block.y+51;
                if(zombie.Bottom().intersects(block.Block())) zombie.y = block.y-51;
                if(zombie.Right().intersects(block.Block())) zombie.x = block.x-51;
                if(zombie.Left().intersects(block.Block())) zombie.x = block.x+51;
                //raycast
                Line2D raycast = new Line2D.Double((int)s.p.x+25, (int)s.p.y+25, (int)zombie.x+25, (int)zombie.y+25);
                if(raycast.intersects(block.Block())){
                    attack = false;
                }
            }
            if(attack) zombie.attack = true;
            for(int h = 0; h < z.size(); h++){
                Zombie tempZombie = z.get(h);
                //zombie-zombie collisions
                if(tempZombie.Top().intersects(zombie.Zombie())) tempZombie.y = zombie.y+51;
                if(tempZombie.Bottom().intersects(zombie.Zombie())) tempZombie.y = zombie.y-51;
                if(tempZombie.Right().intersects(zombie.Zombie())) tempZombie.x = zombie.x-51;
                if(tempZombie.Left().intersects(zombie.Zombie())) tempZombie.x = zombie.x+51;
            }
        }
        
        for(int i = 0; i < b.size(); i++){
            bullet = b.get(i);
            
            for(int h = 0; h < B.size(); h++){
                block = B.get(h);
                if(bullet.bullet().intersects(block.Block())) b.remove(bullet);
            }
            for(int h = 0; h < z.size(); h++){
                zombie = z.get(h);
                if(bullet.bullet().intersects(zombie.Zombie())){
                    zombie.health -= bullet.damage;
                    b.remove(bullet);
                }
            }
        }
    }
    
    public void removeAll(){
        while(!B.isEmpty() || !b.isEmpty() || !z.isEmpty()){
            for(int i = 0; i < B.size(); i++){
                block = B.get(i);
                B.remove(block);
            }
            for(int i = 0; i < b.size(); i++){
                bullet = b.get(i);
                b.remove(bullet);
            }
            for(int i = 0; i < z.size(); i++){
                zombie = z.get(i);
                z.remove(zombie);
            }
        }
    }
}
/*
//zombie-player collisions
                if(zombie.Top().intersects(s.p.Player())) zombie.y = s.p.y+51;
                if(zombie.Bottom().intersects(s.p.Player())) zombie.y = s.p.y-51;
                if(zombie.Right().intersects(s.p.Player())) zombie.x = s.p.x-51;
                if(zombie.Left().intersects(s.p.Player())) zombie.x = s.p.x+51;
*/