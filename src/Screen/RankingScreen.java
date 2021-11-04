package Screen;

import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Font;


public class RankingScreen extends JPanel implements KeyListener, Runnable {
    private String title = "Ranking";
    private boolean isVisible = true;
    private Font gameFont = new Font("Consolas", Font.BOLD, 18);
    public String[] ranking;
    private JFrame frame;
    private MenuScreen menu;
    private Thread gameLoop;
    // private File rankingFile;

    public RankingScreen(String[] ranking, JFrame janela, MenuScreen menu) {
        this.setBackground(Color.BLACK);
        this.setVisible(isVisible);
        this.setBounds(0,0,1200,720);
        this.ranking = ranking;
        this.frame = janela;
        gameLoop = new Thread(this);
        gameLoop.start();
        this.menu = menu;

    }

    public void paintComponent(Graphics graph) {
        super.paintComponent(graph);
    

        Graphics2D graph2D = (Graphics2D) graph.create();
        graph2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graph2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graph2D.setColor(Color.WHITE);
        graph2D.setFont(gameFont);
        this.setBounds(0, 0, 1200, 720);


        graph2D.drawString(this.title, 550, 100);

        for(int i = 0; i < ranking.length; i++) {
            graph2D.drawString("Pontuação: " + ranking[i], 510, 190 + (i * 40));
        }

        graph2D.setColor(Color.YELLOW);
        graph2D.drawString("Pressione ENTER para voltar ao menu", 420, 145);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true) {
            long startTime = System.currentTimeMillis();
            update();
            repaint();
            
            long finalTime = System.currentTimeMillis();
            long smooth = 16 - (finalTime - startTime);
            
            if(smooth > 0){
                sleep(smooth);
            }        }
    }

    public void update(){}


    private void sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void keyPressed(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_ENTER) {
            menu.setScreen("Menu");
            System.out.println("Pressed Enter");
            frame.remove(this);
            frame.removeKeyListener(this);
            System.out.println(menu.checkScreen());
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {}

    @Override
    public void keyTyped(KeyEvent arg0) {}
}
