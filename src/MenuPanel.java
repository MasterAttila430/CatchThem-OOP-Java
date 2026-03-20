import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    GameWindow window;

    public MenuPanel(GameWindow window) {
        this.window = window;
        setBackground(Color.CYAN);
        setLayout(null);

        JLabel titleLabel = new JLabel("MONEY RAIN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setBounds(280, 50, 400, 50);
        add(titleLabel);

        JButton startButton = new JButton("START GAME");
        startButton.setBounds(300, 150, 250, 50);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.switchPage("GAME");
            }
        });
        add(startButton);

        JButton scoreButton = new JButton("RECORDS");
        scoreButton.setBounds(300, 230, 250, 50);
        scoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.switchPage("HIGHSCORE");
            }
        });
        add(scoreButton);

        JButton exitButton = new JButton("EXIT");
        exitButton.setBounds(300, 310, 250, 50);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exitButton);
    }
}