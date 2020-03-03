package Tyokalutestit;

import org.junit.Test;
import static org.junit.Assert.*;

import pakohuone.sovelluslogiikka.Labyrintti;
import pakohuone.Main.Main;
import pakohuone.sovelluslogiikka.Avain;
import pakohuone.sovelluslogiikka.Ovi;
import pakohuone.sovelluslogiikka.Huone;
import pakohuone.tyokalut.EtaisyydenEtsija;
import pakohuone.algoritmit.HuoneidenEtsija;

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
