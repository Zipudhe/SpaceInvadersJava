import javax.swing.JFrame;
import java.util.Scanner;

import Screen.GameScreen;

import java.io.*;

import java.awt.Color;

public class GameHandler {
    
    static File ranking;

    public static File createRankingFile() {
        ranking = new File("./ranking.txt");

        try {
            ranking.createNewFile();
        } catch(IOException e ){
            // do nothing... means to file already exists.
            System.out.println("Failed to load file");
        }
        return ranking;
    }
    public static void main(String[] args) {
        JFrame janela = new JFrame("Space Invaders");
        String[] ranking = new String[10];
        Scanner inputRanking;
        File rankingFile = createRankingFile();
        janela.setSize(1200, 720);
        janela.setLayout(null);
        janela.setBackground(Color.BLACK);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try {
            inputRanking = new Scanner(rankingFile);
            System.out.println("Loaded from file");
        } catch(Exception e) {
            System.out.println("Unable to load ranking file");
            inputRanking = new Scanner("");
        }
        
        for(int i = 0; i < 9; i++) {
            if(inputRanking.hasNextLine()) {
                ranking[i] = inputRanking.nextLine();
            }
        }

        inputRanking.close();
        

        GameScreen game = new GameScreen(janela, ranking, rankingFile);
        game.setBounds(0, 0, 1200, 720);
        janela.addKeyListener(game);
        
        janela.add(game);

        janela.setVisible(true);

    }
}
