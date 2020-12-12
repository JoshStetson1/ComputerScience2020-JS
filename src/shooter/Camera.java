package shooter;

public class Camera {
    Screen s;
    
    public Camera(Screen s){
        this.s = s;
    }
    public int x(){
        int x = -((int)s.p.x+25)+s.getWidth()/2;
        int imgWidth = s.lm.levelImg.getWidth()*50;
        
        if(x > 0) x = 0;//left limit
        if(x < -imgWidth+s.getWidth()) x = -imgWidth+s.getWidth();//right limit
        
        return x;
    }
    public int y(){
        int y = -((int)s.p.y+25)+s.getHeight()/2;
        int imgHeight = s.lm.levelImg.getHeight()*50;
        
        if(y > 0) y = 0;//top limit
        else if(y < -imgHeight+s.getHeight()) y = -imgHeight+s.getHeight();//bottom limit
        
        return y;
    }
}
