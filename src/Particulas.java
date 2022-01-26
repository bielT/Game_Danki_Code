
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Particulas  extends Rectangle {
    private Color color;
    private int speed;
     private int rotation; 
     
     public double dx, dy,xa,ya;
     
     public int time=0;
     
     
    public Particulas(int x, int y,int width,int height,Color color ){
        super(x,y,width, height);

        this.color=color;
         
        speed =8;
        xa = x;
        ya = y;
        dx  = new Random().nextGaussian();
        dy =  new Random().nextGaussian();
        
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
        xa+=dx*speed;
        ya+=dy*speed;
        
        time++;
    }
    public void render(Graphics g ){
        
        g.setColor(this.color);
        g.fillRect((int)xa,(int)ya,width,height);

    }
}
