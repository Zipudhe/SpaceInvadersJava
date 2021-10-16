package Screen;

import Entity.Bullet;
import Entity.EnemyShip;
import Entity.Ship;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;

public class GameScreen extends JPanel implements Runnable, KeyListener {

    private Ship mainShip;
    private ArrayList<Bullet> bullets;
    private ArrayList<EnemyShip> enemies;
    private Thread gameLoop;
    private BufferedImage enemyDrwaing;
    private double points;
    private Font gameFont = new Font("Consolas", Font.BOLD, 18);
    private String gameStatus;
    private JFrame instanceFrame;
    private String[] ranking;
    private File rankingFile;

    public GameScreen(JFrame frame, String[] ranking, File rankingFile) {
        this.setBackground(Color.BLACK);
        gameLoop = new Thread(this);
        mainShip = new Ship();
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<EnemyShip>();
        this.gameStatus = "none";
        points = 0;
        this.instanceFrame = frame;
        this.ranking = ranking;
        this.rankingFile = rankingFile;

        try {
            enemyDrwaing = ImageIO.read(new File("src/assets/enemy1.png"));
        } catch(IOException e ) {
            System.out.println("Falied to load enemy image");
        }
        
        gameLoop.start();

        for(int i = 0; i < 2; i++) {
            int row = i/10;
            // int colmun = i%2;
            enemies.add(new EnemyShip( enemyDrwaing, 100 + (i%10 * 60), 100 + (50 * row), 25, 30, 100));
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
    
    private void update() {
        mainShip.update();
        for(EnemyShip enemy: enemies) {
            if(enemy.getPosY() >= 720 - 40) {
                gameStatus = "Perdeu";
            }

            enemy.update();
        }
        
        if(this.points == 20) {
            gameStatus = "Venceu";
        }

        try {
            for(Bullet bullet: bullets) {
                bullet.update();
                // bullet.handleColision(enemy)
                if(bullet.getPosY() < 0) {
                    bullets.remove(bullet);
                } else {
                    for(EnemyShip enemy: enemies) {
                        boolean shoudlDelete = bullet.handleColision(enemy);
                        if(shoudlDelete) {
                            bullets.remove(bullet);
                            if(enemy.checkIsDestroyed()) {
                                this.points += 10;
                                enemies.remove(enemy);
                            }
                        }
                    }
                }
            }
        } catch(Exception e) {
            // Do nothing
        }

        if(this.gameStatus != "none") {

            FinalScreen finalScreen = new FinalScreen(ranking, this.gameStatus, this.points, rankingFile);
            this.addKeyListener(finalScreen);
            instanceFrame.add(finalScreen);
            this.setVisible(false);
        }

    }
    
    public void paintComponent(Graphics graph) {
        super.paintComponent(graph);
    

        Graphics2D graph2D = (Graphics2D) graph.create();
        graph2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graph2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graph2D.setColor(Color.WHITE);
        graph2D.setFont(gameFont);
        graph2D.drawString("PONTUAÇÃO: " + this.points, 100, 20);

        if(gameStatus == "derrota") {
            graph2D.drawString(
            "VOCÊ PERDEU! \n PONTUAÇÃO: " + this.points,
            450,
            360);
        }

        if(gameStatus == "vitoria") {
            graph2D.drawString(
            "VOCÊ VENCEU! \n PONTUAÇÃO: " + this.points,
            450,
            360);
        }

        mainShip.draw(graph2D);
        
        for(EnemyShip enemy: enemies) {
            enemy.draw(graph2D);
        }

        if(mainShip.canShoot()) {
            bullets.add(mainShip.shoot());
        }

        for(Bullet bullet: bullets) {
            bullet.draw(graph2D);
        }

    }

    private void sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        // if()

        if(event.getKeyCode() == KeyEvent.VK_D) {
            mainShip.moveRight();
        }
        
        if(event.getKeyCode() == KeyEvent.VK_A) {
            mainShip.moveLeft();
        }   

        if(event.getKeyCode() == KeyEvent.VK_W) {
            mainShip.moveForward();
        }
        
        if(event.getKeyCode() == KeyEvent.VK_S) {
            mainShip.moveBackward();
        }  
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }

}

