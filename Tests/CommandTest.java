import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommandTest {
    @Test
    public void testConstructor() {
        Command test= new Command(0,0,"N",0,0);
        assertEquals(0,test.getState());
    }
    @Test
    public void testToString() {
        Command test= new Command(0,0,"N",0,0);
        assertEquals("(0-0-N-0-0)",test.toString());
    }
    @Test
    public void testGetDir() {
        Command test= new Command(0,0,"N",0,0);
        assertEquals("N",test.getDir());
    }

}