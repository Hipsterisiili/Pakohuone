package Tyokalutestit;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import pakohuone.tyokalut.Verkko;
import pakohuone.tyokalut.Dijkstra;
import pakohuone.ui.LabyrintinLuoja;
import pakohuone.sovelluslogiikka.Labyrintti;

public class DijkstraTest {

    public Labyrintti laby;
    public LabyrintinLuoja labLuo = new LabyrintinLuoja();
    public Verkko v;
    public Dijkstra d;
    public int[][] taul;
    public boolean[] yhteydetSaatavilla;

    @Before
    public void alustus() {
        v = new Verkko(labLuo.LuoLabyrintti1());
        yhteydetSaatavilla = new boolean[11];
        taul = new int[11][11];
        for (int i = 0; i < 11; i++) {
            yhteydetSaatavilla[i] = false;
            for (int j = 0; j < 11; j++) {
                taul[i][j] = 0;
            }
        }
    }

    @Test
    public void DijkstraToimii() {
        taul[1][5] = 100;
        taul[5][1] = 100;
        taul[5][10] = 1;
        taul[10][5] = 1;
        yhteydetSaatavilla[1] = true;
        yhteydetSaatavilla[5] = true;
        d = new Dijkstra(taul);
        assertEquals(101, d.hae(1, 10, yhteydetSaatavilla));
    }

    @Test
    public void LyhytMatka() {
        taul[1][10] = 123;
        taul[10][1] = 123;
        yhteydetSaatavilla[1] = true;
        d = new Dijkstra(taul);
        assertEquals(123, d.hae(1, 10, yhteydetSaatavilla));
    }

    @Test
    public void PitkaMatka() {
        taul[1][9] = 1;
        taul[9][1] = 1;
        taul[9][2] = 20;
        taul[2][9] = 20;
        taul[2][8] = 300;
        taul[8][2] = 300;
        taul[8][3] = 4000;
        taul[3][8] = 4000;
        taul[3][7] = 50000;
        taul[7][3] = 50000;
        taul[7][4] = 600000;
        taul[4][7] = 600000;
        taul[4][6] = 1;
        taul[6][4] = 1;
        taul[6][5] = 1;
        taul[5][6] = 1;
        taul[5][10] = 1;
        taul[10][5] = 1;
        for (int i = 0; i < 10; i++) {
            yhteydetSaatavilla[i] = true;
        }
        d = new Dijkstra(taul);
        assertEquals(654324, d.hae(1, 10, yhteydetSaatavilla));
    }

    @Test
    public void montaMatkaaKaikkiAvoinna() {
        //Luodaan oikeasti lyhin polku
        taul[1][9] = 1;
        taul[9][1] = 1;
        taul[9][2] = 20;
        taul[2][9] = 20;
        taul[2][8] = 300;
        taul[8][2] = 300;
        taul[8][3] = 4000;
        taul[3][8] = 4000;
        taul[3][7] = 50000;
        taul[7][3] = 50000;
        taul[7][4] = 600000;
        taul[4][7] = 600000;
        taul[4][6] = 1;
        taul[6][4] = 1;
        taul[6][5] = 1;
        taul[5][6] = 1;
        taul[5][10] = 1;
        taul[10][5] = 1;
        //Oikoteitä, jotka ovat kuitenkin pitempiä kuin lyhin polku
        taul[1][6] = 800000;
        taul[6][1] = 800000;
        taul[9][10] = 700000;
        taul[10][9] = 700000;
        taul[4][10] = 5;
        taul[10][4] = 5;

        for (int i = 0; i < 10; i++) {
            yhteydetSaatavilla[i] = true;
        }
        d = new Dijkstra(taul);
        assertEquals(654324, d.hae(1, 10, yhteydetSaatavilla));
    }

    @Test
    public void montaMatkaaOsaKiinni() {
        taul[1][9] = 1;
        taul[1][10] = 700000;
        taul[1][6] = 800000;
        taul[9][2] = 20;
        taul[2][8] = 300;
        taul[8][3] = 4000;
        taul[3][7] = 50000;
        taul[7][4] = 600000;
        taul[4][6] = 1;
        taul[4][10] = 5;
        taul[6][5] = 1;
        taul[5][10] = 1;
        taul[9][1] = 1;
        taul[10][1] = 700000;
        taul[6][1] = 800000;
        taul[2][9] = 20;
        taul[8][2] = 300;
        taul[3][8] = 4000;
        taul[7][3] = 50000;
        taul[4][7] = 600000;
        taul[6][4] = 1;
        taul[10][4] = 5;
        taul[5][6] = 1;
        taul[10][5] = 1;

        for (int i = 0; i < 10; i++) {
            yhteydetSaatavilla[i] = true;
        }
        yhteydetSaatavilla[2] = false;

        d = new Dijkstra(taul);
        assertEquals(700000, d.hae(1, 10, yhteydetSaatavilla));
    }
}
