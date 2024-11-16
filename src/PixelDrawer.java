import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PixelDrawer extends JPanel {
    private BufferedImage image;
    private ArrayList<ArrayList<Pixel>> pixelList=new ArrayList<>();
    private final int width;
    private final int height;
    public PixelDrawer() {
        width = 1000;
        height = 500;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixelList.add(new ArrayList<>());
                pixelList.get(x).add(new Pixel(x, y));
            }
        }
    }
    public ArrayList<ArrayList<Pixel>> getPixelList(){
        return pixelList;
    }
    public void resetImage(){
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixelList.get(x).get(y).setState(0);
            }
        }
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if(pixelList.get(x).get(y).getState()==1)
                    image.setRGB(x, y, Color.RED.getRGB());
                else{
                    image.setRGB(x, y, Color.WHITE.getRGB());
                }

            }
        }
        g.drawImage(image, 0, 0, null);
    }
}
