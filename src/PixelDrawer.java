import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PixelDrawer extends JPanel {
    private BufferedImage image;
    private ArrayList<ArrayList<Pixel>> pixelList=new ArrayList<>();
    private int width;
    private int height;
    public PixelDrawer() {
        // Create a BufferedImage with a width and height
        width = 800;
        height = 600;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Set individual pixels
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if(pixelList.get(x).get(y).getState()==1)
                    image.setRGB(x, y, Color.WHITE.getRGB());
            }
        }
        g.drawImage(image, 0, 0, null);
    }
}
