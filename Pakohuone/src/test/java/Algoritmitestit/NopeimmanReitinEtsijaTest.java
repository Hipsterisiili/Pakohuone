package Algoritmitestit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import pakohuone.Main.LabyrintinLuoja;
import pakohuone.sovelluslogiikka.Labyrintti;

public class NopeimmanReitinEtsijaTest {

    LabyrintinLuoja ll = new LabyrintinLuoja();
    Labyrintti laby1 = ll.LuoLabyrintti1();
    Labyrintti laby2 = ll.LuoLabyrintti2();
    Labyrintti laby3 = ll.LuoLabyrintti3();

    @Test
    public void NopeinteReittiaEiVoiSelvittaaJosReittejaEiOleLoydetty() {

        assertEquals(laby1.etsiParasReitti(),
                "Reittejä ei ole vielä etsitty");
    }

    @Test
    public void MikaOnNopeinReittiLaby1() {

        laby1.etsiReitit();

        assertEquals(laby1.etsiParasReitti(),
                "\"ad\" \n"
                + "pituus = 25");
    }

    @Test
    public void MikaOnNopeinReittiLaby2() {

        laby2.etsiReitit();

        assertEquals(laby2.etsiParasReitti(),
                "\"afeb\" \n"
                + "pituus = 51");
    }

    @Test
    public void MikaOnNopeinReittiLaby3() {

        laby3.etsiReitit();

        assertEquals(laby3.etsiParasReitti(),
                "\"abcfdegi\" \n"
                + "pituus = 102");
    }

    @Test
    public void ReittienEtsintaIlmanTulostusta() {
        assertEquals(laby1.etsiReitit(), 11);
        assertEquals(laby2.etsiReitit(), 1031);
        assertEquals(laby3.etsiReitit(), 47);
    }

    @Test
    public void KunReittejaEiOle() {
        int korkeus = 15;
        //Luotavan labyrintin leveys
        int leveys = 15;
        char[][] labyrintti = new char[korkeus + 1][leveys + 1];

        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                labyrintti[i][j] = '.';
            }
        }
        //Luodaan seiniä labyrinttiin, # = seinä Labyrintin reunat ovat aina seiniä
        for (int i = 0; i < leveys; i++) {
            labyrintti[0][i] = '#';
            labyrintti[korkeus][i] = '#';
            labyrintti[10][i] = '#';
            labyrintti[6][i] = '#';
        }

        for (int i = 0; i < korkeus; i++) {
            labyrintti[i][0] = '#';
            labyrintti[i][leveys] = '#';
            labyrintti[i][12] = '#';
            labyrintti[i][5] = '#';
        }
        //Iso kirjain A = ovi ja pieni kirjain a = avain. avain avaa aina
        //sen kirjainta vastaaman ison kirjaimen omaavan oven
        labyrintti[1][2] = 'a';
        labyrintti[2][2] = 'b';
        labyrintti[3][2] = 'c';
        labyrintti[4][2] = 'd';
        labyrintti[5][2] = 'e';
        labyrintti[1][13] = 'f';
        labyrintti[2][13] = 'g';
        labyrintti[3][13] = 'h';
        labyrintti[4][13] = 'i';
        labyrintti[5][13] = 'j';

        labyrintti[6][1] = 'A';
        labyrintti[6][2] = 'B';
        labyrintti[6][3] = 'C';
        labyrintti[6][4] = 'D';
        labyrintti[10][1] = 'E';
        labyrintti[10][2] = 'F';
        labyrintti[10][3] = 'G';
        labyrintti[10][4] = 'H';
        labyrintti[11][5] = 'I';
        labyrintti[12][12] = 'J';

        labyrintti[korkeus][leveys] = '#'; // ASCIIssa 35

        Labyrintti l = new Labyrintti(labyrintti);
        
        assertTrue("Reittejä ei ole vielä etsitty".equals(l.etsiParasReitti()));
        
        l.etsiReitit();
        
        assertTrue("Labyrintistä ei löydy reittejä".equals(l.etsiParasReitti()));
    }
}
