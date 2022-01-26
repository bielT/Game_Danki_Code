
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

public class RectObj extends Rectangle {
    private Color color;
    private int speed;
     private int rotation; 
    public RectObj(int x, int y,int width,int height){
        super(x,y,width, height);

        color = new Color (new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255));
        
        speed =new Random().nextInt(10-4)+4;
        rotation= 0 ;
   }
    
    public Color getColor() {
        return color;
    }

    public int getSpeed() {
        return speed;
    }

    public int getRotation() {
        return rotation;
    }
    
    public void update() {
        x+=speed;
        rotation++; 
        if(rotation >= 360 )
                    rotation =0;
        
    }
    
}
