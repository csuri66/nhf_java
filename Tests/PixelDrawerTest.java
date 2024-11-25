import org.junit.Test;

import static org.junit.Assert.*;

public class PixelDrawerTest {

    @Test
    public void testConstructor() {
        PixelDrawer test = new PixelDrawer();
        assertEquals(500,test.getPixelList().getFirst().size());
    }

    @Test
    public void getPixelList() {
        PixelDrawer test = new PixelDrawer();
        assertEquals(0,test.getPixelList().getFirst().getFirst().getState());
    }

    @Test
    public void resetImage() {
        PixelDrawer test = new PixelDrawer();
        test.getPixelList().getFirst().getFirst().setState(1);
        test.resetImage();
        assertEquals(0,test.getPixelList().getFirst().getFirst().getState());
    }
}