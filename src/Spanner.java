
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;



public class Spanner {
    public int timer  = 0; 

    public List<RectObj> rectangles=new ArrayList<RectObj>() ;
    public List<Particulas> particulas =new ArrayList<Particulas>();
    public void update(){
        timer++;
        if(timer % 60 ==0){
            int a=new Random().nextInt(460-40)+40;
            rectangles.add(new RectObj(0,a,40,40));
        }
        for(int i=0 ; i <rectangles.size();i++){
                 RectObj current = rectangles.get(i);
                 
                rectangles.get(i).update();
                
                if(current.x > Game.WIDTH){
                    rectangles.remove(current);
                    Game.contador--;
                }
                if(Game.clicado){
                    if(Game.mx>= current .x && Game.mx <current.x+current.width){
                        if(Game.my>= current .y && Game.my <current.y+current.height){
                            rectangles.remove(current);
                            Game.pontuacao++;
                            if(Game.contador <100)
                                Game.contador++;
                                Game.clicado= false;
                            
                                for(int n = 0 ; n<  50;n++){
                                     particulas.add(new Particulas(current.x,current.y,8,8,current.getColor()));
                                     
                                }
                        }
                      
                    }
                }
            }
         Game.my=0;
          Game.mx=0;
        for(int n = 0 ; n<  particulas.size();n++){
             particulas.get(n).update();
             
             Particulas part = particulas.get(n);
             if(part.time >= 60){
                 particulas.remove(part);
             }
        }
    } 
    
    public void render(Graphics g ){
        for(int i = 0 ; i <rectangles.size();i++){
            RectObj current = rectangles.get(i);
            Graphics2D g2  = (Graphics2D) g;
            g2.rotate(Math.toRadians(current.getRotation()),current.x+current.width/2,current.y+current.height/2);
            
            g2.setColor(current.getColor());
            g2.fillRect(current.x ,current.y,current.width, current.height);
            g2.rotate(Math.toRadians(-current.getRotation()),current.x+current.width/2,current.y+current.height/2);
            
        }
        
        for(int n = 0 ; n<  particulas.size();n++){
             particulas.get(n).render(g);
        }
        
        
    }
}
