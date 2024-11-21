import org.junit.Test;

import static org.junit.Assert.*;

public class PixelTest {
    @Test
    public void testConstructor() {
        Pixel test=new Pixel(0,0);
        assertEquals(0,test.getX());
    }
    @Test
    public void testSetterX() {
        Pixel test=new Pixel(0,0);
        test.setX(100);
        assertEquals(100,test.getX());
    }
    @Test
    public void testSetterState() {
        Pixel test=new Pixel(0,0);
        test.setState(1);
        assertEquals(1,test.getState());
    }
}