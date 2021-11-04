package Screen;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.text.html.parser.Entity;

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

import java.lang.InterruptedException;

import Handler.GameHandler;


public class MenuScreen extends JPanel implements KeyListener, Runnable {
    private String title = "SPACE INVADERS";
    private String subtitle = "By Lucas Gonçalves Ramalho";
    private String[] ranking;
    private Thread gameLoop;

    private Font titleFont = new Font("Consolas", Font.BOLD, 32);
    private Font subtitleFont = new Font("Consolas", Font.BOLD, 12);
    private Font gameFont = new Font("Consolas", Font.BOLD, 18);

    private BufferedImage arrow;
    private int arrowPos = 300;

    private String nextScreen;
    private Boolean isRunning = true;

    GameHandler handler;

    public MenuScreen(String[] ranking) {
        this.ranking = ranking;
        this.setBackground(Color.BLACK);
        gameLoop = new Thread(this);
        this.setBounds(0, 0, 1200, 720);        

        gameLoop.start();
        try {
            arrow = ImageIO.read(new File("src/assets/arrow.png"));
        } catch(IOException e) {
            System.out.println("Failed to load image");
        }

    }
    
    @Override
    public void run() {
        while(isRunning) {
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

    public void update() {
        if(!isRunning) {
            try {
                this.gameLoop.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted..."+e);  
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
        
        graph2D.setFont(subtitleFont);
        graph2D.drawString(subtitle, 500, 150);
        
        graph2D.setFont(gameFont);
        graph2D.drawString("Começar", 520, 320);
        graph2D.drawString("Dificuldade", 520, 360);
        graph2D.drawString("Ranking", 520, 400);
        
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
        // Lida com a posição da imagem do arrow
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

        if(event.getKeyCode() == KeyEvent.VK_ENTER) {
            if(arrowPos == 300) {
                this.nextScreen = "Comecar";
            }

            if(arrowPos == 340) {
                this.nextScreen = "Dificuldade";
            }

            if(arrowPos == 380) {
                this.nextScreen = "Ranking";
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent arg0) {}
    
    @Override
    public void keyTyped(KeyEvent arg0) {}

    public String checkScreen() {
        return this.nextScreen;
    }

    public void setScreen(String screen) {
        this.nextScreen = screen;
    }

    public void stopThread() {
        this.isRunning = false;
    }

    public void startThread() {
        this.isRunning = true;
    }
}