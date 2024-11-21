import org.junit.Test;

import static org.junit.Assert.*;

public class PixelDrawerTest {

    @Test
    public void testConstructor() {
        PixelDrawer test = new PixelDrawer();
        assertEquals(500,test.getPixelList().get(0).size());
    }

    @Test
    public void getPixelList() {
        PixelDrawer test = new PixelDrawer();
        assertEquals(0,test.getPixelList().get(0).get(0).getState());
    }

    @Test
    public void resetImage() {
        PixelDrawer test = new PixelDrawer();
        test.getPixelList().get(0).get(0).setState(1);
        test.resetImage();
        assertEquals(0,test.getPixelList().get(0).get(0).getState());
    }
}