import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import pakohuone.Main.Labyrintti;
import pakohuone.Main.Main;
import pakohuone.sovelluslogiikka.Avain;
import pakohuone.sovelluslogiikka.Ovi;
import pakohuone.sovelluslogiikka.Huone;
import pakohuone.tyokalut.EtaisyydenEtsija;
import pakohuone.algoritmit.HuoneidenEtsinta;

public class HuoneidenEtsintaTest {

    char[][] labyrintti;
    HuoneidenEtsinta h;

    @Before
    public void SetUp() {
        labyrintti = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                labyrintti[i][j] = '.';
            }
        }
        for (int i = 0; i < 10; i++) {
            labyrintti[0][i] = '#';
            labyrintti[9][i] = '#';
            labyrintti[i][0] = '#';
            labyrintti[i][9] = '#';
            labyrintti[5][i] = '#';
            labyrintti[i][7] = '#';
        }
        h = new HuoneidenEtsinta(labyrintti);
    }

    @Test
    public void huoneetOikein() {
        int[][] huoneet = h.tulkitse();

        assertTrue(huoneet[0][0] == 0
                && huoneet[1][1] == 1
                && huoneet[5][5] == 0
                && huoneet[8][8] == 4
        );
    }

    @Test
    public void labyrinttiOikein() {
        int[][] huoneet = h.tulkitse();
        char seina = '#';
        char ruutu = '.';
        assertTrue(labyrintti[0][0] == seina
                && labyrintti[1][1] == ruutu
                && labyrintti[5][5] == seina
                && labyrintti[8][8] == ruutu
        );
    }
    
    @Test
    public void olemattomatAvaimet() {
        int[][] huoneet = h.tulkitse();
        
        assertTrue(h.getAvaimet()[10].getKirjain() == '@');
        assertTrue(h.getAvaimet()[10].getSijaintiX() == 0 
                && h.getAvaimet()[10].getSijaintiY() == 0);
    }
}




