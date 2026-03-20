import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class FallingItem {

    private int x, y;
    private int width, height;
    private Image image;
    private boolean isBad;

    public FallingItem(int startX) {
        this.x = startX;
        this.y = -60;
        this.width = 60;
        this.height = 60;

        generateRandomItem();
    }

    private void generateRandomItem() {
        Random rand = new Random();

        if (rand.nextBoolean()) {
            isBad = true;
            if (rand.nextBoolean()) image = new ImageIcon("res/Handcuffs.png").getImage();
            else image = new ImageIcon("res/Jail.png").getImage();
        }
        else {
            isBad = false;
            if (rand.nextBoolean()) image = new ImageIcon("res/Money.png").getImage();
            else image = new ImageIcon("res/Szavazat.png").getImage();
        }
    }

    public void update(int speed) {
        y = y + speed;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean isBad() {
        return isBad;
    }

    public int getY() {
        return y;
    }
}