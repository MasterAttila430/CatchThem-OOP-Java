import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {

    private GameWindow window;
    private Player player;
    private Timer gameTimer;
    private ArrayList<FallingItem> items;
    private Random rand = new Random();
    private Image backgroundImage;

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private int score = 0;
    private int lives = 3;
    private int level = 1;

    private int timeElapsed = 0;
    private int timeTickCounter = 0;

    private int targetScore = 100;
    private int gameSpeed = 5;

    public GamePanel(GameWindow window) {
        this.window = window;
        setBackground(Color.DARK_GRAY);
        setLayout(null);
        setFocusable(true);
        addKeyListener(new MyKeyHandler());

        try {
            backgroundImage = ImageIO.read(new File("res/background.png"));
        } catch (IOException e) {
            System.err.println("Error: background (res/background.png).");
        }

        player = new Player();
        items = new ArrayList<>();

        gameTimer = new Timer(20, e -> {
            updateGame();
            repaint();
        });
    }

    public void startGame() {
        gameTimer.start();
        SoundManager.playMusic("music.wav");
    }

    private void updateGame() {
        if (leftPressed) player.moveLeft();
        if (rightPressed) player.moveRight();

        timeTickCounter++;
        if (timeTickCounter >= 50) {
            timeElapsed++;
            timeTickCounter = 0;
        }

        int spawnRate = Math.max(10, 50 - (level * 5));

        if (rand.nextInt(spawnRate) == 0) {
            int randomX = 100 + rand.nextInt(540);
            items.add(new FallingItem(randomX));
        }

        for (int i = 0; i < items.size(); i++) {
            FallingItem item = items.get(i);
            item.update(gameSpeed);

            if (player.getBounds().intersects(item.getBounds())) {
                if (item.isBad()) {
                    lives--;
                    SoundManager.playEffect("error.wav");
                } else {
                    score += 10;
                    SoundManager.playEffect("coin.wav");
                }
                items.remove(i);
                i--;
            }
            else if (item.getY() > 600) {
                items.remove(i);
                i--;
            }
        }

        if (score >= targetScore) {
            levelUp();
        }

        if (lives <= 0) {
            gameOver();
        }
    }

    private void levelUp() {
        gameTimer.stop();
        leftPressed = false;
        rightPressed = false;

        JOptionPane.showMessageDialog(this, "LEVEL COMPLETED!\nNext level: " + (level + 1));

        level++;

        if (level <= 5) {
            targetScore += 100;
        } else {
            targetScore += 200;
        }

        gameSpeed += 2;
        items.clear();

        gameTimer.start();
    }

    private void gameOver() {
        gameTimer.stop();
        SoundManager.stopMusic();
        SoundManager.playEffect("gameover.wav");

        ScoreManager.saveHighScore(score, level);

        JOptionPane.showMessageDialog(this, "GAME OVER!\nPOINTS: " + score);

        resetGame();
        window.switchPage("MENU");
    }

    public void resetGame() {
        score = 0;
        lives = 3;
        timeElapsed = 0;
        level = 1;
        targetScore = 100;
        gameSpeed = 5;
        items.clear();
        player = new Player();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 100, 0, 600, 600, null);
        } else {
            g.fillRect(100, 0, 600, 600);
        }

        player.draw(g);

        items.stream().forEach(item -> item.draw(g));

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("LIVES: " + lives, 20, 50);
        g.drawString("LEVELS: " + level, 20, 80);
        g.drawString("SCORE: " + score + " / " + targetScore, 710, 50);
        g.drawString("TIME: " + timeElapsed + " mp", 710, 80);

        g.drawLine(100, 0, 100, 600);
        g.drawLine(700, 0, 700, 600);
    }

    private class MyKeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = true;
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = true;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = false;
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = false;
        }
    }
}