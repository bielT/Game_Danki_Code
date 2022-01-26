
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, MouseListener {

    public static final int WIDTH = 640, HEIGHT = 480;
    public static int contador = 100;
    public static int pontuacao = 0;

    public static boolean clicado = false;
    public static int mx, my;

    public Spanner spawner;

    public static boolean gameOver = false;

    public Game() {
        Dimension dimension = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(dimension);

        this.addMouseListener(this);
        spawner = new Spanner();
    }

    private void render() {

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        if (!gameOver) {

            g.setColor(Color.GREEN);
            g.fillRect(Game.WIDTH / 2 - 170, 20, contador * 3, 20);
            g.setColor(Color.WHITE);
            g.drawRect(Game.WIDTH / 2 - 170, 20, 300, 20);

            spawner.render(g);
        } else {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 23));
            g.drawString("Game Over!" , WIDTH / 2 - 100, HEIGHT/2-50);

            g.drawString("Your final Scare Was: " +this.pontuacao, WIDTH / 2 - 180, HEIGHT/2);
            
            g.drawString(">> Press Enter to Play Again <<" , WIDTH / 2 - 200, HEIGHT/2+50);

        }
        bs.show();
    }

    private void update() {
        if (!gameOver) {
            spawner.update();
 
            if (contador <= 0) {
                contador = 100;
                gameOver = true;
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame jFrame = new JFrame("Thread");
        jFrame.add(game);
        jFrame.setLocationRelativeTo(null);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       jFrame.addKeyListener(new KeyListener(){
             @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_SPACE ){
                        Game.gameOver=false;
                    }
                }

                @Override
                public void keyTyped(KeyEvent e) {
                    
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }

        
        });
        jFrame.setVisible(true);

        new Thread(game).start();

    }

    @Override
    public void run() {
        while (true) {
            update();
            render();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        clicado = true;
        mx = e.getX();
        my = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
