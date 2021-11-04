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

    private String nextScreen = "Menu";
    private String currentScreen = "Menu";

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
            if(menu.checkScreen() != "null") {
                String screen = menu.checkScreen();
                System.out.println(screen);
                if(screen == "Comecar") {
                    System.out.println(dificulty);
                    menu.stopThread();
                    janela.remove(menu);
                    janela.removeKeyListener(menu);
                    
                    game = new GameScreen(janela, ranking, rankingFile, dificulty);
                    janela.add(game);
                    janela.addKeyListener(game);
                    
                    // Evita que fique constantemente "re-mudando" para a tela que já foi trocada
                    screen = "null";
                    menu.setScreen("null");
                }

                if(screen == "Ranking") {
                    menu.stopThread();
                    janela.remove(menu);
                    janela.removeKeyListener(menu);
                    
                    rankingScreen = new RankingScreen(ranking, janela, menu);
                    janela.addKeyListener(rankingScreen);
                    janela.add(rankingScreen);
                    // Evita que fique constantemente "re-mudando" para a tela que já foi trocada
                    screen = "null";
                    menu.setScreen("null");
                }

                if(screen == "Menu") {
                    System.out.println("Zap");
                    menu.startThread();
                    janela.add(menu);
                    janela.addKeyListener(menu);
                    // Evita que fique constantemente "re-mudando" para a tela que já foi trocada
                    screen = "null";
                    menu.setScreen("null");
                }

                if(screen == "Dificuldade") {
                    menu.stopThread();
                    janela.remove(menu);
                    janela.removeKeyListener(menu);
                    
                    dificultyScreen = new DificultyScreen(janela, menu, game, dificulty);
                    janela.addKeyListener(dificultyScreen);
                    janela.add(dificultyScreen);
                    
                    // Evita que fique constantemente "re-mudando" para a tela que já foi trocada
                    screen = "null";
                    menu.setScreen("null");
                }
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

    public static void checkScreensStatus() {
    }

    public static void shouldChangeContext() {
        if
    }

}
