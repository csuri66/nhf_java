import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Main {

    // Külön thread vézgi a rajzolást, ezekhez szukséges változók
    static volatile boolean isDrawing = false;
    private static Thread drawingThread;
    private static Timer drawingTimer;

    public static void main(String[] args) throws InterruptedException {

        //Menü blokk, a menü CardLayout-al van megoldva, ahová paneleket rakok majd azokat előveszem
        JFrame frame = new JFrame("Termite");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel cardPanel = new JPanel(new CardLayout());

        JPanel menuPanel = new JPanel();
        JPanel inputPanel=new JPanel();
        JPanel loaderPanel=new JPanel();
        PixelDrawer Drawer = new PixelDrawer(); // Ez egy JPanel-t extendelő class

        cardPanel.add(Drawer,"canvas");
        cardPanel.add(menuPanel,"menu");
        cardPanel.add(inputPanel,"input");
        cardPanel.add(loaderPanel,"loader");

        //gombok a panelekre
        JButton Start = new JButton("Start");
        JButton Stop = new JButton("Stop");
        JButton BackAbsolute = new JButton("Back");
        JButton BackNormal = new JButton("Back");
        JButton Clear = new JButton("Clear");
        JButton Restart = new JButton("Restart");
        JButton ruleSetLoader = new JButton("Load / Save ruleset");
        JButton ruleSetInput = new JButton("Ruleset maker");
        JButton inputCommand=new JButton("Hozzáad");
        JButton inputCommandDelete=new JButton("Törlés");
        JButton saverCommand=new JButton("Elment");
        JButton loaderCommand=new JButton("Betölt");

        Icon imgIcon = new ImageIcon("xd.gif"); //vicces gif
        JLabel label = new JLabel(imgIcon);


        JTextField inputText=new JTextField();
        inputText.setPreferredSize(new Dimension(150, 30));

        inputPanel.add(inputText);
        inputPanel.add(inputCommand);
        inputPanel.add(inputCommandDelete);

        Start.setPreferredSize(new Dimension(150, 50));
        ruleSetLoader.setPreferredSize(new Dimension(150, 50));
        ruleSetInput.setPreferredSize(new Dimension(150, 50));

        menuPanel.add(Start);
        menuPanel.add(ruleSetLoader);
        menuPanel.add(ruleSetInput);
        menuPanel.add(label);

        loaderPanel.add(loaderCommand);
        loaderPanel.add(saverCommand);

        //A bufferedimage nem akar viselkedni JPanel-be rakva, így  a gombok abszolut pozicionálása a legértelemsebb megoldás
        Restart.setBounds(50,550,200,50);
        Stop.setBounds(275,550,200,50);
        Clear.setBounds(500,550,200,50);
        BackAbsolute.setBounds(725,550,200,50);

        Drawer.setLayout(null);
        Drawer.add(Restart);
        Drawer.add(Stop);
        Drawer.add(Clear);
        Drawer.add(BackAbsolute);


        CardLayout c1 = (CardLayout) cardPanel.getLayout();
        frame.add(cardPanel);

        frame.setVisible(true);

        c1.show(cardPanel,"menu");


        ArrayList<Command> commands= new ArrayList<>();
        Termite termite=new Termite(commands);

        //Gombokhoz a műveleteik

        Start.addActionListener(e -> {
            isDrawing=true;
            c1.show(cardPanel,"canvas");
            Stop.setEnabled(true);
            Clear.setEnabled(false);

            drawingThread = new Thread(() -> {

                drawingTimer = new Timer(8,f-> Drawer.repaint()); //~120 fps
                drawingTimer.start();
                int flickerDelay=0;
                while(isDrawing&&termite.inBoundary()) {
                    if(flickerDelay==1000){ //Java grafikát elátkozom, de ezzel legalább nem ronda
                        termite.executeTasks(Drawer);
                        flickerDelay=0;
                    }
                    ++flickerDelay;
                }
                if(!termite.inBoundary()){
                    Stop.setEnabled(false);
                    Clear.setEnabled(true);
                    drawingTimer.stop();
                }
            });
            drawingThread.start();
        });
        Restart.addActionListener(e -> {
            isDrawing=true;
            c1.show(cardPanel,"canvas");
            Stop.setEnabled(true);
            Clear.setEnabled(false);
            drawingTimer.stop();
            Drawer.resetImage();
            termite.restPos();
            Drawer.update(Drawer.getGraphics());
            drawingThread = new Thread(() -> {

                drawingTimer = new Timer(8,f-> Drawer.repaint());
                drawingTimer.start();
                int flickerDelay=0;
                while(isDrawing&&termite.inBoundary()) {
                    if(flickerDelay==1000){
                        termite.executeTasks(Drawer);
                        flickerDelay=0;
                    }
                    ++flickerDelay;
                }
                if(!termite.inBoundary()){
                    Stop.setEnabled(false);
                    Clear.setEnabled(true);
                    drawingTimer.stop();
                }
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
        BackAbsolute.addActionListener(e -> {
            if(isDrawing){
                isDrawing = false;
                if (drawingThread!= null && drawingThread.isAlive()) {
                    drawingThread.interrupt();
                    drawingTimer.stop();
                }
            }
            c1.show(cardPanel,"menu");
        });
        BackNormal.addActionListener(e -> {
            if(isDrawing){
                isDrawing = false;
                if (drawingThread!= null && drawingThread.isAlive()) {
                    drawingThread.interrupt();
                    drawingTimer.stop();
                }
            }
            c1.show(cardPanel,"menu");
        });
        ruleSetInput.addActionListener(e -> {
            inputPanel.add(BackNormal);
            c1.show(cardPanel,"input");

            inputCommand.addActionListener(f -> {
                String command = inputText.getText();
                String[] userInput= command.split("-");
                if(userInput.length==5)
                    termite.getCommands().add(new Command(Integer.parseInt(userInput[0]),Integer.parseInt(userInput[1]),userInput[2],Integer.parseInt(userInput[3]),Integer.parseInt(userInput[4])));
            });

            inputCommandDelete.addActionListener(f -> {
                if(!termite.getCommands().isEmpty()){
                    termite.getCommands().removeLast();
                }
            });


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
            loaderPanel.add(BackNormal);
            c1.show(cardPanel,"loader");

            saverCommand.addActionListener(f -> {
                JFileChooser fileChooser= new JFileChooser();
                int response = fileChooser.showSaveDialog(null);
                if(response==JFileChooser.APPROVE_OPTION){
                    File file= new File (fileChooser.getSelectedFile().getAbsolutePath());
                    try (FileOutputStream fileOut = new FileOutputStream(file);
                         ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

                        if(!termite.getCommands().isEmpty()){
                            objectOut.writeObject(termite.getCommands());
                        }

                    } catch (IOException h) {
                        h.printStackTrace();
                    }
                }

            });

            loaderCommand.addActionListener(f -> {
                JFileChooser fileChooser= new JFileChooser();
                int response = fileChooser.showOpenDialog(null);
                if(response==JFileChooser.APPROVE_OPTION){
                    File file=new File (fileChooser.getSelectedFile().getAbsolutePath());
                    try (FileInputStream fileIn = new FileInputStream(file);
                         ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

                        ArrayList<Command> temp = (ArrayList<Command>) objectIn.readObject();
                        termite.setCommands(new ArrayList<>(temp));

                    } catch (IOException | ClassNotFoundException h) {
                        h.printStackTrace();
                    }
                }

            });

       });

    }
}