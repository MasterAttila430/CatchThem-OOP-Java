import javax.swing.*;
import java.awt.*;

public class Player {
    private int x, y;
    private int width, height;
    private Image image;

    public Player() {
        ImageIcon icon = new ImageIcon("res/Politikus.png");
        image = icon.getImage();
        width = 120;
        height = 120;

        x = 400 - (width / 2);
        y = 430;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public void moveLeft() {
        x = x - 15;
        if (x < 100) {
            x = 100;
        }
    }

    public void moveRight() {
        x = x + 15;
        if (x > 700 - width) {
            x = 700 - width;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x + 30, y + 20, width - 60, height - 40);
    }
}