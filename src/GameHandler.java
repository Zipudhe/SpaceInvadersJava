import java.awt.Color;

import javax.swing.JFrame;

import Screen.GameScreen;

public class GameHandler {
    
    public static void main(String[] args) {
        JFrame janela = new JFrame("Space Invaders");
        janela.setSize(1200, 720);
        janela.setLayout(null);
        janela.setBackground(Color.BLACK);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GameScreen game = new GameScreen();
        game.setBounds(0, 0, 1200, 720);
        janela.addKeyListener(game);
        
        janela.add(game);

        janela.setVisible(true);
    }
}
