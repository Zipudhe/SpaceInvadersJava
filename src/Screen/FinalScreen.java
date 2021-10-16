package Screen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JPanel;

import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Font;






public class FinalScreen extends JPanel implements KeyListener, Runnable {

    private String title;
    private boolean isVisible = true;
    private Font gameFont = new Font("Consolas", Font.BOLD, 18);
    public String[] ranking;
    private double currentPoints;
    private File rankingFile;

    FinalScreen(String[] ranking, String title, double finalPoints, File rankingFile) {
        this.setBackground(Color.BLACK);
        this.setVisible(isVisible);
        this.setBounds(0, 0, 1200, 720);
        this.title = title;
        this.currentPoints = finalPoints;
        this.ranking = ranking;
        this.rankingFile = rankingFile;
    }

    public void setVisibility(boolean visibility) {
        this.isVisible = visibility;
    }

    public void paintComponent(Graphics graph) {
        super.paintComponent(graph);
    

        Graphics2D graph2D = (Graphics2D) graph.create();
        graph2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graph2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graph2D.setColor(Color.WHITE);
        graph2D.setFont(gameFont);

        String finalMessage = "Você " + this.title + "!";

        graph2D.drawString(finalMessage, 520, 130 );
        graph2D.drawString("Sua pontuação: " + this.currentPoints, 500, 230 );

        graph2D.drawString("Ranking", 550, 290);

        for(int i = 0; i < ranking.length; i++) {
            graph2D.drawString("Pontuação: " + ranking[i], 510, 330 + (i * 40));
        }

        updateRanking();
    }


    @Override
    public void run() {
        while(true) {
            long startTime = System.currentTimeMillis();
            update();
            repaint();
            
            long finalTime = System.currentTimeMillis();

            // controls frame hate base on comuter performance
            long smooth = 16 - (finalTime - startTime);
            
            if(smooth > 0){
                sleep(smooth);
            }
        }
    }


    private void update() {

    }

    private void updateRanking() {

        for(int i = 0; i < ranking.length; i++) {
            if(this.currentPoints > Double.parseDouble(ranking[i])) {
                ranking[i] = String.valueOf(this.currentPoints);
                break;
            }
        }

        try {
            // FileWriter writeFile = new FileWriter(rankingFile);
            PrintWriter pw = new PrintWriter(rankingFile);
            for(String point: ranking) {
                pw.println(point);
            }
            pw.close();

            // writeFile.write("10.0");
            // writeFile.close();
            System.out.println("Successfully wrote on file");
        } catch(IOException e) {
            System.out.print("Failed to write on file");
            e.printStackTrace();
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
        // TODO Auto-generated method stub
        if(event.getKeyCode() == KeyEvent.VK_D) {
            System.out.println("arrow dow");
        }

        if(event.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("arrow UP");
        }

        if(event.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("Enter");
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
