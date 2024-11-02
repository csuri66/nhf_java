import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Termite");
        PixelDrawer panel = new PixelDrawer();
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        ArrayList<Command> test1=new ArrayList<>();
        test1.add(new Command(0,0,"N",1,0));
        Termite test=new Termite(test1);
        for(int i=0;i<100;++i){
            test.executeTasks(panel);
            frame.update(frame.getGraphics());
        }
    }
}