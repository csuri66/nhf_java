import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TermiteTest {
    @Test
    public void testResetPos() {
        Termite test = new Termite(new ArrayList<Command>());
        test.restPos();
        assertEquals(500,test.getCurrentPos().getX());
    }
    @Test
    public void testInBoundary() {
        Termite test = new Termite(new ArrayList<Command>());
        test.restPos();
        assertTrue(test.inBoundary());
    }
    @Test
    public void testSetCommands() {
        ArrayList<Command> testCommands= new ArrayList<Command>();
        testCommands.add(new Command(0,0,"N",0,0));
        testCommands.add(new Command(0,0,"L",0,0));
        testCommands.add(new Command(0,0,"U",0,0));
        Termite test = new Termite(new ArrayList<Command>());
        test.setCommands(testCommands);
        assertEquals("N",test.getCommands().getFirst().getDir());
    }
    @Test
    public void testExecuteTasks() {
        ArrayList<Command> testCommands= new ArrayList<Command>();
        testCommands.add(new Command(0,0,"N",1,0));
        testCommands.add(new Command(0,0,"R",1,0));
        testCommands.add(new Command(0,0,"L",1,0));
        testCommands.add(new Command(0,0,"U",1,0));
        Termite test = new Termite(testCommands);
        PixelDrawer testDrawer = new PixelDrawer();
        test.executeTasks(testDrawer);
        assertEquals(1,testDrawer.getPixelList().get(500).get(250).getState());
    }

}