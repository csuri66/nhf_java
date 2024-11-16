import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.util.ArrayList;

public class Main {
    static volatile boolean isDrawing = false;
    private static Thread drawingThread;
    public static void main(String[] args) throws InterruptedException {

        JFrame frame = new JFrame("Termite");
        JPanel panel = new JPanel();
        JButton Start = new JButton("Start");
        JButton Stop = new JButton("Stop");
        JButton Clear = new JButton("Clear");
        JButton ruleSetLoader = new JButton("Load / Save ruleset");
        JButton ruleSetInput = new JButton("Ruleset maker");

        Start.setPreferredSize(new Dimension(150, 50));
        ruleSetLoader.setPreferredSize(new Dimension(150, 50));
        ruleSetInput.setPreferredSize(new Dimension(150, 50));
        Stop.setPreferredSize(new Dimension(150, 50));
        Clear.setPreferredSize(new Dimension(150, 50));
        panel.add(Start);
        panel.add(Stop);
        panel.add(Clear);
        panel.add(ruleSetLoader);
        panel.add(ruleSetInput);
        frame.add(panel, BorderLayout.SOUTH);
        Icon imgIcon = new ImageIcon("xd.gif");
        JLabel label = new JLabel(imgIcon);
        label.setBounds(300, 250, 46, 14);
        panel.add(label);



        PixelDrawer Drawer = new PixelDrawer();
        frame.add(Drawer,BorderLayout.CENTER);
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        ArrayList<Command> test1=new ArrayList<>();
        test1.add(new Command(0,0,"N",1,1));
        test1.add(new Command(0,1,"N",1,1));
        test1.add(new Command(1,0,"L",1,0));
        test1.add(new Command(1,1,"N",0,0));

        Termite test=new Termite(test1);

        Clear.setEnabled(false);
        Start.addActionListener(e -> {
            isDrawing=true;
            Start.setEnabled(false);
            Stop.setEnabled(true);
            Clear.setEnabled(false);
            drawingThread = new Thread(() -> {
                int i=0;
                long steps=0;
                while(isDrawing&&test.inBoundary()) {
                    ++i;
                    test.executeTasks(Drawer);
                    if (i == 100) {
                        Drawer.update(Drawer.getGraphics());
                        i = 0;
                    }
                    ++steps;
                    System.out.println(steps);
                }
                if(!test.inBoundary()){
                    Stop.setEnabled(false);
                }
                SwingUtilities.invokeLater(() -> Start.setEnabled(true));
            });
            drawingThread.start();
        });
        Stop.addActionListener(e -> {
            isDrawing = false;
            if (drawingThread!= null && drawingThread.isAlive()) {
                drawingThread.interrupt();
            }
            Start.setEnabled(true);
            Clear.setEnabled(true);
            Stop.setEnabled(false);
        });
        Clear.addActionListener(e -> {
            Drawer.resetImage();
            test.restPos();
            Drawer.update(Drawer.getGraphics());
            Stop.setEnabled(false);
        });

    }
}