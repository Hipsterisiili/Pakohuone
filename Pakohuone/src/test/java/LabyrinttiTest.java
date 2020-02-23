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
        
        for(int i = 0; i < 11 ; i++){
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
}
