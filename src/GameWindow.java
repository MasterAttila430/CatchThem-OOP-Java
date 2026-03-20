import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    CardLayout cardLayout;
    JPanel mainPanel;

    GamePanel gamePanel;
    HighScorePanel highScorePanel;
    MenuPanel menuPanel;

    public GameWindow() {
        setTitle("Money Rain - 100% Projekt");
        setSize(860, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        menuPanel = new MenuPanel(this);
        gamePanel = new GamePanel(this);
        highScorePanel = new HighScorePanel(this);

        mainPanel.add(menuPanel, "MENU");
        mainPanel.add(gamePanel, "GAME");
        mainPanel.add(highScorePanel, "HIGHSCORE");

        add(mainPanel);
        setVisible(true);
    }

    public void switchPage(String pageName) {
        cardLayout.show(mainPanel, pageName);

        if (pageName.equals("GAME")) {
            gamePanel.startGame();
            gamePanel.requestFocusInWindow();
        }

        if (pageName.equals("HIGHSCORE")) {
            highScorePanel.updateScore();
        }
    }
}