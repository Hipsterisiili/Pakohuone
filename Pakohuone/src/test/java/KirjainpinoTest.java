
import org.junit.Test;
import static org.junit.Assert.*;

import pakohuone.tyokalut.Kirjainpino;

public class KirjainpinoTest {

    @Test
    public void PeekToimii() {

        Kirjainpino k = new Kirjainpino();
        k.add('a');
        assertTrue(k.peek() == 'a');
        k.add('b');
        k.pop();
        assertTrue(k.peek() == 'a');
    }

    @Test
    public void PopToimii() {

        Kirjainpino k = new Kirjainpino();
        k.add('a');
        assertTrue(k.pop() == 'a');
        k.add('a');
        k.add('b');
        k.pop();
        assertTrue(k.peek() == 'a');
    }

    @Test
    public void toStringToimii() {
        Kirjainpino k = new Kirjainpino();
        k.add('a');
        k.add('b');
        assertTrue("ab".equals(k.toString()));
        for (int i = 0; i < 31; i++) {
            k.add('c');
        }
        for (int i = 0; i < 15; i++) {
            k.pop();
        }
        assertTrue("abccccccccccccc".equals(k.toString()));
    }

    @Test
    public void LiikaaKirjaimia() {
        Kirjainpino k = new Kirjainpino();
        for (int i = 0; i < 29; i++) {
            k.add('a');
        }
        k.add('b');
        assertTrue(k.peek() == 'b');
        k.add('c');
        assertTrue(k.peek() == 'b');
    }

}
