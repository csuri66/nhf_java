import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Frame extends JFrame {
    ArrayList<ArrayList<Pixel>> pixelArray;
    Frame() {
        pixelArray = new ArrayList<ArrayList<Pixel>>();
        setTitle("Termesz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
        for (int i=0;i<800;++i){
            pixelArray.add(new ArrayList<Pixel>());
            for (int j=0;j<600;++j) {
                pixelArray.get(i).add(new Pixel(j,i));
                pixelArray.get(i).get(j).setState(true);
            }
        }
    }
    public void Update(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (int i=0;i<800;++i){
            for (int j=0;j<600;++j) {
                pixelArray.get(i).get(j).draw(g2d);
            }
        }
    }
}
