import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighScorePanel extends JPanel {

    GameWindow window;
    JLabel scoreLabel;

    public HighScorePanel(GameWindow window) {
        this.window = window;
        setBackground(new Color(44, 62, 80));
        setLayout(null);

        JLabel title = new JLabel("BEST ", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.ORANGE);
        title.setBounds(0, 50, 860, 50);
        add(title);

        scoreLabel = new JLabel("Loading", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 40));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(0, 150, 860, 60);
        add(scoreLabel);

        JButton backButton = new JButton("BACK TO MENU");
        backButton.setBounds(330, 400, 200, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.switchPage("MENU");
            }
        });
        add(backButton);
    }

    public void updateScore() {
        String result = ScoreManager.loadHighScoreString();
        scoreLabel.setText(result);
    }
}