import javax.swing.*;

public class DrawerThread implements Runnable {
    private PixelDrawer Drawer;
    private JFrame frame;
    private Termite test;
    public DrawerThread(PixelDrawer Drawer, JFrame frame, Termite test) {
        this.Drawer = Drawer;
        this.frame = frame;
        this.test = test;
    }
    public void run()  {
        int i=0;
        long steps=0;
        while(steps!=5000){
            ++i;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            test.executeTasks(Drawer);
            if(i==50){
                frame.update(Drawer.getGraphics());
                i=0;
            }
            ++steps;
            System.out.println(steps);
        }
    }
}
