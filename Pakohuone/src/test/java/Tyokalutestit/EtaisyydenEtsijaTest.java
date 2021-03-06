package Tyokalutestit;

import org.junit.Test;
import static org.junit.Assert.*;

import pakohuone.tyokalut.EtaisyydenEtsija;

public class EtaisyydenEtsijaTest {
    
    @Test
    public void etaisyydetOikein() {
        EtaisyydenEtsija e = new EtaisyydenEtsija();
        assertTrue(e.etsiEtaisyys(1,1,2,2) == 2);
        assertTrue(e.etsiEtaisyys(2,10,3,9) == 2);
        assertTrue(e.etsiEtaisyys(3,3,3,3) == 0);
        assertTrue(e.etsiEtaisyys(1,10,10,1) == 18);
    }
    
}
