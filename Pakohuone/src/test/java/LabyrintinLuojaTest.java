
import org.junit.Test;
import static org.junit.Assert.*;
import pakohuone.Main.LabyrintinLuoja;
import pakohuone.sovelluslogiikka.Labyrintti;

public class LabyrintinLuojaTest {

    LabyrintinLuoja ll = new LabyrintinLuoja();

    Labyrintti l1 = ll.LuoLabyrintti1();
    Labyrintti l2 = ll.LuoLabyrintti2();
    Labyrintti l3 = ll.LuoLabyrintti3();
    int l1Korkeus = l1.getKorkeus();
    int l1Leveys = l1.getLeveys();
    int l2Korkeus = l2.getKorkeus();
    int l2Leveys = l2.getLeveys();
    int l3Korkeus = l3.getKorkeus();
    int l3Leveys = l3.getLeveys();
    char[][] l1Kuva = l1.getKuva();
    char[][] l2Kuva = l2.getKuva();
    char[][] l3Kuva = l3.getKuva();
    int[][] l1Huoneet = l1.getHuoneTaulukko();
    int[][] l2Huoneet = l2.getHuoneTaulukko();
    int[][] l3Huoneet = l3.getHuoneTaulukko();

    @Test
    public void labyrinttienMitatOikein() {

        assertTrue(l1Korkeus == 12);
        assertTrue(l1Leveys == 13);
        assertTrue(l2Korkeus == 15);
        assertTrue(l2Leveys == 20);
        assertTrue(l3Korkeus == 15);
        assertTrue(l3Leveys == 15);
    }

    @Test
    public void labyrinttienAlkuOikein() {
        assertTrue(l1Kuva[0][0] == '#');
        assertTrue(l2Kuva[0][0] == '#');
        assertTrue(l2Kuva[0][0] == '#');
        //Huom. labyrintti ei tallenna erikseen alkupistettä ja loppupistettä
        //Merkkeinä + ja *, koska niiden sijainti on muutenkin pääteltävissä
        assertTrue(l1Kuva[1][1] == '.');
        assertTrue(l2Kuva[1][1] == '.');
        assertTrue(l2Kuva[1][1] == '.');
    }

    @Test
    public void labyrinttienLoppuOikein() {
        assertTrue(l1Kuva[l1Korkeus][l1Leveys] == '#');
        assertTrue(l2Kuva[l2Korkeus][l2Leveys] == '#');
        assertTrue(l2Kuva[l3Korkeus][l3Leveys] == '#');
        //Huom. labyrintti ei tallenna erikseen alkupistettä ja loppupistettä
        //Merkkeinä + ja *, koska niiden sijainti on muutenkin pääteltävissä
        assertTrue(l1Kuva[l1Korkeus - 1][l1Leveys - 1] == '.');
        assertTrue(l2Kuva[l2Korkeus - 1][l2Leveys - 1] == '.');
        assertTrue(l2Kuva[l3Korkeus - 1][l3Leveys - 1] == '.');
    }

    @Test
    public void labyrinttienSisällötOikein() {
        assertTrue(l1Kuva[4][6] == '#');
        assertTrue(l1Kuva[5][7] == '#');
        assertTrue(l1Kuva[2][2] == 'a');
        assertTrue(l1Kuva[5][2] == 'A');

        assertTrue(l2Kuva[9][12] == '#');
        assertTrue(l2Kuva[10][11] == '#');
        assertTrue(l2Kuva[13][4] == 'f');
        assertTrue(l2Kuva[5][12] == 'B');

        assertTrue(l3Kuva[10][6] == '#');
        assertTrue(l3Kuva[9][5] == '#');
        assertTrue(l3Kuva[13][4] == 'f');
        assertTrue(l3Kuva[6][10] == 'F');
    }

    @Test
    public void huoneetOikein() {
        //tarkistetaan että huoneita on oikea määrä
        assertTrue(l1Huoneet[l1Korkeus - 1][l1Leveys - 1] == 4);
        assertTrue(l2Huoneet[l2Korkeus - 1][l2Leveys - 1] == 6);
        assertTrue(l3Huoneet[l3Korkeus - 1][l3Leveys - 1] == 9);

        //tarkistetaan että seinät on merkitty arvolla 0
        assertTrue(l1Huoneet[5][5] == 0);
        assertTrue(l2Huoneet[2][12] == 0);
        assertTrue(l3Huoneet[15][14] == 0);

        //tarkistetaan että huoneilla on oikeat numerot
        assertTrue(l1Huoneet[6][5] == 3);
        assertTrue(l2Huoneet[9][13] == 3);
        assertTrue(l3Huoneet[7][6] == 5);
    }
}
