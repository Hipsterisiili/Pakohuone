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
                "Ei löydettyjä reittejä, etsi kaikki reitit ensin");
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
                "\"abcfgdeil\" \n"
                + "pituus = 78");
    }

    public void ReittienEtsintaIlmanTulostusta() {
        assertEquals(laby1.etsiReitit(), 11);
        assertEquals(laby2.etsiReitit(), 1031);
        assertEquals(laby3.etsiReitit(), 195);
    }
}
