import javax.swing.JFrame;
import java.util.Scanner;

import Screen.*;

import java.io.*;

import java.awt.Color;

public class GameHandler {
    
    static File ranking;
    static GameScreen game;
    static FinalScreen endGame;
    static MenuScreen menu;

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
        } catch(Exception e) {
            inputRanking = new Scanner("");
        }
        
        // Lê arquivo com os dados do ranking e coloca em um array
        for(int i = 0; i <= 9; i++) {
            ranking[i] = inputRanking.nextLine();
        }

        inputRanking.close();
        
        // Inicializa a tela do Jogo
        game = new GameScreen(janela, ranking, rankingFile);
        menu = new MenuScreen(ranking);
        endGame = new FinalScreen(ranking, "Venceu", 20.0 , rankingFile);

        game.setBounds(0, 0, 1200, 720);
        menu.setBounds(0, 0, 1200, 720);

        
        
        janela.addKeyListener(menu);
        janela.add(menu);

        janela.setVisible(true);

        // while(true) {
        //     System.out.println(game.isVisible());
        //     checkScreensStatus();
        // }

    }

    public static File createRankingFile() {
        // Inicializa a variável ranking com o caminho do arquivo.
        ranking = new File("./ranking.txt");
        try {
            // Tenta abrir um arquivo, caso ele não exista 
            // cria um arquivo com o nome que a função recebe
            ranking.createNewFile();
        } catch(IOException e ){
            // Não faz nada, apenas reporta o erro caso tenha algum
            // problema criando ou abrindo o arquivo.
            System.out.println("Failed to load file");
        }
        return ranking;
    }

    public static void checkScreensStatus() {
    }
}
