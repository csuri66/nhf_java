import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Pixel {
    private int x;
    private int y;
    private int state;
    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
        state = 0;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int newX) {
        x=newX;
    }
    public void setY(int newY) {
        y=newY;
    }
    public int getState() {
        return state;
    }
    public void setState(int in) {
        state=in;
    }
}
