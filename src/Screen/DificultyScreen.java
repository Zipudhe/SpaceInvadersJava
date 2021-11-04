package Screen;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Font;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class DificultyScreen extends JPanel implements KeyListener, Runnable {

    private String title = "Dificuldade";
    private Font titleFont = new Font("Consolas", Font.BOLD, 32);
    private Font gameFont = new Font("Consolas", Font.BOLD, 18);

    private Thread gameLoop;

    private BufferedImage arrow;
    private int arrowPos = 300;

    private String nextScreen = "Menu";

    private JFrame frame;
    private MenuScreen menu;
    private GameScreen game;

    private boolean isVisible = true;

    static String dificulty;


    public DificultyScreen(JFrame frame, MenuScreen menu, GameScreen game, String dificulty) {

        this.frame = frame;
        this.menu = menu;
        this.game = game;
        this.setBackground(Color.BLACK);
        this.setBounds(0, 0, 1200, 720);
        this.setVisible(isVisible);

        DificultyScreen.dificulty = dificulty;

        gameLoop = new Thread(this);

        try {
            arrow = ImageIO.read(new File("src/assets/arrow.png"));
        } catch(IOException e) {
            System.out.println("Failed to load image");
        }

        gameLoop.start();
    }

    public void update(){}

    private void sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted..."+e);  
        }
    }

    @Override
    public void run() {
        while(true) {
            long startTime = System.currentTimeMillis();
            update();
            repaint();
            
            long finalTime = System.currentTimeMillis();
            long smooth = 16 - (finalTime - startTime);
            
            if(smooth > 0){
                sleep(smooth);                
            }
        }
    }

    public void paintComponent(Graphics graph) {
        super.paintComponent(graph);
        Graphics2D graph2D = (Graphics2D) graph.create();
        graph2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graph2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graph2D.setColor(Color.WHITE);
        
        graph2D.setFont(titleFont);
        graph2D.drawString(title, 460, 130);
        
        
        graph2D.setFont(gameFont);
        graph2D.drawString("Fácil", 520, 320);
        graph2D.drawString("Médio", 520, 360);
        graph2D.drawString("Difícil", 520, 400);
        
        this.draw(graph2D);
    }

    public void draw(Graphics2D g) {
        g.drawImage(
            arrow, 
            470, 
            arrowPos, 
            500 + 22, 
            arrowPos + 22, 
            0, 
            0, 
            arrow.getWidth(), 
            arrow.getHeight(),
            null
            );
    }


    @Override
    public void keyPressed(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_ENTER) {
            if(arrowPos == 300) {
                DificultyScreen.dificulty = "Facil";
            }

            if(arrowPos == 340) {
                DificultyScreen.dificulty = "Medio";
            }

            if(arrowPos == 380) {
                DificultyScreen.dificulty = "Dificil";
            }

            frame.remove(this);
            frame.removeKeyListener(this);
            menu.setScreen("Menu");
        }

        if(event.getKeyCode() == KeyEvent.VK_UP) {
            if(arrowPos > 301) {
                arrowPos -= 40;
            }
        }
        
        if(event.getKeyCode() == KeyEvent.VK_DOWN) {
            if(arrowPos < 379) {
                arrowPos += 40;
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    
}
