
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import pakohuone.sovelluslogiikka.Labyrintti;
import pakohuone.Main.Main;
import pakohuone.sovelluslogiikka.Avain;
import pakohuone.sovelluslogiikka.Ovi;
import pakohuone.sovelluslogiikka.Huone;
import pakohuone.tyokalut.EtaisyydenEtsija;
import pakohuone.algoritmit.HuoneidenEtsija;

public class LabyrinttiTest {

    char[][] labyrintti;
    Labyrintti laby;

    @Before
    public void SetUp() {
        labyrintti = new char[11][11];

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                labyrintti[i][j] = '.';
            }
        }
        for (int i = 0; i < 11; i++) {
            labyrintti[0][i] = '#';
            labyrintti[10][i] = '#';
            labyrintti[5][i] = '#';
        }

        for (int i = 0; i < 11; i++) {
            labyrintti[i][0] = '#';
            labyrintti[i][10] = '#';
            labyrintti[i][6] = '#';
        }
        labyrintti[5][2] = 'A';
        labyrintti[3][6] = 'B';
        labyrintti[5][8] = 'C';
        labyrintti[2][2] = 'a';
        labyrintti[7][4] = 'b';
        labyrintti[2][8] = 'c';

        laby = new Labyrintti(labyrintti);
    }

    @Test
    public void AvaintenSijainnit() {
        assertTrue(laby.getAvaimet()[0].getSijaintiX() == 2);
        assertTrue(laby.getAvaimet()[0].getSijaintiY() == 2);
        assertTrue(laby.getAvaimet()[1].getSijaintiX() == 7);
        assertTrue(laby.getAvaimet()[1].getSijaintiY() == 4);
    }

    @Test
    public void OvienSijainnitOviXSuunnassa() {
        assertTrue(laby.getOvet()[0].getAlkuX() == 4);
        assertTrue(laby.getOvet()[0].getAlkuY() == 2);
        assertTrue(laby.getOvet()[0].getLoppuX() == 6);
        assertTrue(laby.getOvet()[0].getLoppuY() == 2);
    }

    @Test
    public void OvienSijainnitOviYSuunnassa() {
        assertTrue(laby.getOvet()[1].getAlkuX() == 3);
        assertTrue(laby.getOvet()[1].getAlkuY() == 5);
        assertTrue(laby.getOvet()[1].getLoppuX() == 3);
        assertTrue(laby.getOvet()[1].getLoppuY() == 7);
    }

    @Test
    public void kuvaOikein() {
        assertTrue(laby.getKuva()[0][0] == '#');
        assertTrue(laby.getKuva()[1][1] == '.');
        assertTrue(laby.getKuva()[2][2] == 'a');
        assertTrue(laby.getKuva()[3][6] == 'B');
    }

    @Test
    public void HuoneetOikein() {
        assertTrue(laby.getHuoneTaulukko()[0][0] == 0);
        assertTrue(laby.getHuoneTaulukko()[1][1] == 1);
        assertTrue(laby.getHuoneTaulukko()[4][9] == 2);
        assertTrue(laby.getHuoneTaulukko()[10][10] == 0);
    }

    @Test
    public void HuoneidenTulostusOikein() {
        String verrokki
                = "# # # # # # # # # # # \n"
                + "# 1 1 1 1 1 # 2 2 2 # \n"
                + "# 1 1 1 1 1 # 2 2 2 # \n"
                + "# 1 1 1 1 1 # 2 2 2 # \n"
                + "# 1 1 1 1 1 # 2 2 2 # \n"
                + "# # # # # # # # # # # \n"
                + "# 3 3 3 3 3 # 4 4 4 # \n"
                + "# 3 3 3 3 3 # 4 4 4 # \n"
                + "# 3 3 3 3 3 # 4 4 4 # \n"
                + "# 3 3 3 3 3 # 4 4 4 # \n"
                + "# # # # # # # # # # # \n"
                + "";
        assertEquals(laby.tulostaHuoneet(), (verrokki));
    }

    @Test
    public void LabyrintinTulostusOikein() {
        String verrokki
                = "x 1 2 3 4 5 6 7 8 9\n"
                + "# # # # # # # # # # #  y\n"
                + "# + . . . . # . . . #   1\n"
                + "# . a . . . # . c . #   2\n"
                + "# . . . . . B . . . #   3\n"
                + "# . . . . . # . . . #   4\n"
                + "# # A # # # # # C # #   5\n"
                + "# . . . . . # . . . #   6\n"
                + "# . . . b . # . . . #   7\n"
                + "# . . . . . # . . . #   8\n"
                + "# . . . . . # . . * #   9\n"
                + "# # # # # # # # # # #   10\n"
                + "";
        assertEquals(laby.tulostaLabyrintti(), (verrokki));
    }
}
