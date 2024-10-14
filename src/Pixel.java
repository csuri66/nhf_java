import javax.swing.*;
import java.awt.*;

public class Pixel {
    private int x;
    private int y;
    private boolean state;
    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
        state = false;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean getState() {
        return state;
    }
    public void setState(boolean in) {
        state=in;
    }
    public void draw(Graphics g) {
        if(state){
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawLine(x,y,x,y);
        }
    }
}
