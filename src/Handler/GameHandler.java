package Handler;

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
    static RankingScreen rankingScreen;
    static DificultyScreen dificultyScreen;
    static JFrame janela;

    static String dificulty = "Facil";

    static String previousScreen = "Menu";
    static String currentScreen = "Menu";

    static boolean shouldChange = false;
    public static void main(String[] args) {
        janela = new JFrame("Space Invaders");
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
        // game = new GameScreen(janela, ranking, rankingFile);
        menu = new MenuScreen(ranking);
        endGame = new FinalScreen(ranking, "Venceu", 20.0 , rankingFile);

        menu.setBounds(0, 0, 1200, 720);
        
        janela.addKeyListener(menu);
        janela.add(menu);

        janela.setVisible(true);

        while(true) {
            // Troca de contexto de cada tela
            if(shouldChange) {
                changeContext(rankingFile, ranking);
            }
        }
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

    public static void changeContext(File rankingFile, String[] ranking) {
        System.out.println(currentScreen);
        if(currentScreen == "Game") {
            setShouldChange(false);
            System.out.println(dificulty);
            menu.stopThread();
            janela.remove(menu);
            janela.removeKeyListener(menu);
            
            game = new GameScreen(janela, ranking, rankingFile, dificulty);
            janela.add(game);
            janela.addKeyListener(game);            
        }

        if(currentScreen == "Ranking") {
                setShouldChange(false);
                menu.stopThread();
                janela.remove(menu);
                janela.removeKeyListener(menu);
                
                rankingScreen = new RankingScreen(ranking, janela, menu);
                janela.addKeyListener(rankingScreen);
                janela.add(rankingScreen);
            }

        if(currentScreen == "Menu") {
            setShouldChange(false);
            System.out.println("Zap");
            menu.startThread();
            janela.add(menu);
            janela.addKeyListener(menu);
        }

        if(currentScreen == "Dificuldade") {
            setShouldChange(false);
            menu.stopThread();
            janela.remove(menu);
            janela.removeKeyListener(menu);
            
            dificultyScreen = new DificultyScreen(janela, menu, game, dificulty);
            janela.addKeyListener(dificultyScreen);
            janela.add(dificultyScreen);                
        }
    }

    public static void setShouldChange(boolean status) {
        shouldChange = status;
    }

    public static void setNextScreen(String screen) {
        if(currentScreen != screen) {
            previousScreen = currentScreen;
            currentScreen = screen;
            setShouldChange(true);
        }
    }
}
