import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    static volatile boolean isDrawing = false;
    private static Thread drawingThread;
    private static Timer drawingTimer;
    public static void main(String[] args) throws InterruptedException {

        JFrame frame = new JFrame("Termite");
        JPanel panel = new JPanel();

        JButton Start = new JButton("Start");
        JButton Stop = new JButton("Stop");
        JButton Clear = new JButton("Clear");
        JButton ruleSetLoader = new JButton("Load / Save ruleset");
        JButton ruleSetInput = new JButton("Ruleset maker");
        Icon imgIcon = new ImageIcon("xd.gif");
        JLabel label = new JLabel(imgIcon);

        Start.setPreferredSize(new Dimension(150, 50));
        ruleSetLoader.setPreferredSize(new Dimension(150, 50));
        ruleSetInput.setPreferredSize(new Dimension(150, 50));
        Stop.setPreferredSize(new Dimension(150, 50));
        Clear.setPreferredSize(new Dimension(150, 50));
        label.setBounds(300, 250, 46, 14);

        panel.add(Start);
        panel.add(Stop);
        panel.add(Clear);
        panel.add(ruleSetLoader);
        panel.add(ruleSetInput);
        panel.add(label);

        frame.add(panel, BorderLayout.SOUTH);

        PixelDrawer Drawer = new PixelDrawer();
        frame.add(Drawer,BorderLayout.CENTER);


        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        ArrayList<Command> commands= new ArrayList<>();
        Termite termite=new Termite(commands);

        Clear.setEnabled(false);
        Stop.setEnabled(false);

        Start.addActionListener(e -> {
            isDrawing=true;
            Start.setEnabled(false);
            Stop.setEnabled(true);
            Clear.setEnabled(false);


            drawingThread = new Thread(() -> {
                for(int i = 0; i< commands.size(); ++i){
                    System.out.println(commands.get(i).toString());
                }
                drawingTimer = new Timer(32,f->Drawer.update(Drawer.getGraphics()));
                drawingTimer.start();
                int delay=0;
                while(isDrawing&&termite.inBoundary()) {
                    if(delay==1000){
                        termite.executeTasks(Drawer);
                        delay=0;
                    }
                    ++delay;
                }
                if(!termite.inBoundary()){
                    Stop.setEnabled(false);
                    Clear.setEnabled(true);
                    drawingTimer.stop();
                }
                SwingUtilities.invokeLater(() -> Start.setEnabled(true));
            });
            drawingThread.start();
        });

        Stop.addActionListener(e -> {
            isDrawing = false;
            if (drawingThread!= null && drawingThread.isAlive()) {
                drawingThread.interrupt();
                drawingTimer.stop();
            }
            Start.setEnabled(true);
            Clear.setEnabled(true);
            Stop.setEnabled(false);
        });

        ruleSetInput.addActionListener(e -> {

            JButton inputCommand=new JButton("Hozzáad");
            JButton inputCommandDelete=new JButton("Törlés");
            JFrame inputFrame=new JFrame("Ruleset Maker");
            JTextField inputText=new JTextField();
            JPanel inputPanel=new JPanel();

            inputText.setPreferredSize(new Dimension(150, 30));

            inputPanel.add(inputText);
            inputPanel.add(inputCommand);
            inputPanel.add(inputCommandDelete);

            inputFrame.add(inputPanel);

            inputCommand.addActionListener(f -> {
                String command = inputText.getText();
                String[] asd= command.split("-");
                if(asd.length==5)
                    termite.getCommands().add(new Command(Integer.parseInt(asd[0]),Integer.parseInt(asd[1]),asd[2],Integer.parseInt(asd[3]),Integer.parseInt(asd[4])));
            });

            inputCommandDelete.addActionListener(f -> {
                if(termite.getCommands().size()>0){
                    termite.getCommands().remove(termite.getCommands().size()-1);
                }
            });

            inputFrame.setSize(300,200);
            inputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            inputFrame.setVisible(true);

        });

        Clear.addActionListener(e -> {
            drawingTimer.stop();
            Drawer.resetImage();
            termite.restPos();
            Drawer.update(Drawer.getGraphics());
            Stop.setEnabled(false);
            Clear.setEnabled(false);
        });

        ruleSetLoader.addActionListener(e -> {
            JButton inputCommand=new JButton("Elment");
            JButton inputCommandDelete=new JButton("Betölt");
            JFrame inputFrame=new JFrame("Ruleset Loader");
            JTextField inputText=new JTextField();
            JPanel inputPanel=new JPanel();

            inputText.setPreferredSize(new Dimension(150, 30));

            inputPanel.add(inputText);
            inputPanel.add(inputCommand);
            inputPanel.add(inputCommandDelete);

            inputFrame.add(inputPanel);

            inputCommand.addActionListener(f -> {
                String command = inputText.getText();
                if(command.length()==0){
                    command= String.valueOf(System.nanoTime()%100);
                }
                try (FileOutputStream fileOut = new FileOutputStream(command+".txt");
                     ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

                    if(termite.getCommands().size()>0){
                        objectOut.writeObject(termite.getCommands());
                    }

                } catch (IOException h) {
                    h.printStackTrace();
                }
            });

            inputCommandDelete.addActionListener(f -> {
                String command = inputText.getText();
                try (FileInputStream fileIn = new FileInputStream(command+".txt");
                     ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

                    ArrayList<Command> temp = (ArrayList<Command>) objectIn.readObject();
                    termite.setCommands(new ArrayList<>(temp));

                } catch (IOException | ClassNotFoundException h) {
                    h.printStackTrace();
                }
            });

            inputFrame.setSize(300,200);
            inputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            inputFrame.setVisible(true);
        });

    }
}