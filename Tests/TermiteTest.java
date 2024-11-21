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
        Termite test = new Termite(testCommands);
        assertEquals("N",test.getCommands().getFirst().getDir());
    }

}