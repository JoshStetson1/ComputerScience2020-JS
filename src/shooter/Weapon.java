package shooter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class Weapon {
    Screen s;
    int amount, speed, spread, damage;
    double fireRate;
    boolean isAuto;
    Random rand = new Random();
    long nowTime, time;
    
    public Weapon(Screen s, boolean isAuto, int amount, int speed, int spread, double fireRate, int damage){
        this.s = s;
        this.isAuto = isAuto;
        this.amount = amount;
        this.speed = speed;
        this.spread = spread;
        this.fireRate = fireRate;
        this.damage = damage;
        
        nowTime = System.nanoTime();
    }
    public void shoot(int mx, int my){
        if(!canShoot()) return;
        
        for(int i = 0; i < amount; i++){
            Bullet tempBullet = new Bullet(s, s.p.x+25, s.p.y+25, (int)((double)damage/1.5));
            
            double spreadX = (rand.nextDouble()-0.5) * spread;
            double spreadY = (rand.nextDouble()-0.5) * spread;
            double angle = Math.atan2(my - s.p.y, mx - s.p.x);
            tempBullet.dx = ((speed) * Math.cos(angle)) + spreadX;
            tempBullet.dy = ((speed) * Math.sin(angle)) + spreadY;
            tempBullet.damage = damage;

            s.l.b.add(tempBullet);
        }
        nowTime = System.nanoTime();
    }
    public boolean canShoot(){
        boolean canShoot = false;
        if(System.nanoTime() > nowTime + 1000000000*fireRate){
            canShoot = true;
        }
        
        return canShoot;
    }
}
