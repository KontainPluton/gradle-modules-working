package another.io.another;

import org.junit.Test;
import static org.junit.Assert.*;

public class BaseExampleTest {

    @Test
    public void testAnother()
    {
        assert(true);
    }

    @Test
    public void testHelloFunction() {
        String msg = another.io.another.BaseExample.hello();
        assertEquals(msg,"Hello");
    }
}
