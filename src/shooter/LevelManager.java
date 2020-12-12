package shooter;
import java.awt.image.BufferedImage;

public class LevelManager {
    Screen s;
    
    BufferedImage levelImg;
    
    public LevelManager(Screen s){
        this.s = s;
    }
    public void makeLevel(String path){
        levelImg = s.loadImage(path + ".png");
        
        createLevel(levelImg);
    }
    public void createLevel(BufferedImage level){
        int w = level.getWidth();
        int h = level.getHeight();
        
        for(int xx = 0; xx < w; xx++){
            for(int yy = 0; yy < h; yy++){
                int pixel = level.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                
                if(red == 127 && green == 127 && blue == 127) s.l.B.add(new Block(s, xx*50, yy*50));
                if(red == 255 && green == 0 && blue == 0) s.l.z.add(new Zombie(s, xx*50, yy*50));
                if(red == 0 && green == 0 && blue == 255){
                    s.p.x = xx*50;
                    s.p.y = yy*50;
                }
            }
        }
    }
}
