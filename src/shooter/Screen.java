package shooter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Screen extends JPanel implements ActionListener, KeyListener, MouseListener{
    Timer t = new Timer(10, this);
    Player p = new Player(this);
    MainMenu m = new MainMenu(this);
    List l = new List(this);
    LevelManager lm = new LevelManager(this);
    Camera cam = new Camera(this);
    
    int posX, posY;
    String state = "menu";//state in which the game is in
    
    public Screen(int x, int y){
        posX = x;
        posY = y;
        
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        lm.makeLevel("testLevel");
        t.start();
    }
    public void actionPerformed(ActionEvent e) {
        if(state.equals("menu")){
            
        }
        else if(state.equals("play"))//play
        {
            if(!p.isDead) p.tick();
            l.tick();
        }
        
        repaint();
    }
    public void paint(Graphics g){
        g.clearRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        if(state.equals("menu")){
            this.setBackground(new Color(50, 50, 50));
            
            m.paint(g);
        }
        else if(state.equals("play"))//play
        {
            this.setBackground(new Color(50, 150, 50));
            
            g2.translate(cam.x(), cam.y());

            l.paint(g);
            p.paint(g);

            g2.translate(-cam.x(), -cam.y());
        }
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_A) p.keys[0] = true;
        if(key == KeyEvent.VK_D) p.keys[1] = true;
        if(key == KeyEvent.VK_W) p.keys[2] = true;
        if(key == KeyEvent.VK_S) p.keys[3] = true;
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_A) p.keys[0] = false;
        if(key == KeyEvent.VK_D) p.keys[1] = false;
        if(key == KeyEvent.VK_W) p.keys[2] = false;
        if(key == KeyEvent.VK_S) p.keys[3] = false;
    }
    public void mousePressed(MouseEvent e) {
        if(state.equals("menu")){
            m.mouseClicked(e);
        }
        else if(state.equals("play"))//play
        {
            if(!p.isDead){
                if(p.weapon.isAuto) p.shooting = true;
                else p.weapon.shoot(e.getX()-cam.x()-25, e.getY()-cam.y()-25);
            } else p.mouseClicked(e);
        }
    }
    public void mouseReleased(MouseEvent e) {
        if(state.equals("play") && p.weapon.isAuto) p.shooting = false;
    }
    
    public BufferedImage loadImage(String path){
        BufferedImage tempImage = null;
        try {
            tempImage = ImageIO.read(new FileInputStream(path));
        } catch (IOException ex) {
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempImage;
    }
    public BufferedImage flipImage(BufferedImage img){
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage flipped = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                BufferedImage pixel = new BufferedImage(1, 1, 1);
                if(pixel.getTransparency() > 0) flipped.setRGB((width-1)-x, y, img.getRGB(x, y));
            }
        }
        return flipped;
    }
    public void rotateImage(BufferedImage img, int degree, int x, int y, Graphics2D g2){
        int w = img.getWidth();
        int h = img.getHeight();
        g2.rotate(Math.toRadians(degree), w/2+x, h/2+y);
        g2.drawImage(img, x, y, this);
        g2.rotate(Math.toRadians(-degree), w/2+x, h/2+y);
    }
    
    public void Reset(){
        l.removeAll();
        lm.makeLevel("testLevel");
        p.isDead = false;
    }
    
    public void mouseClicked(MouseEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
