package Tyokalutestit;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import pakohuone.sovelluslogiikka.Labyrintti;
import pakohuone.tyokalut.Dijkstra;
import pakohuone.tyokalut.Verkko;
import pakohuone.ui.LabyrintinLuoja;

public class VerkkoTest {
    
    public int[][] taulukko;
    public Dijkstra d;
    public LabyrintinLuoja ll = new LabyrintinLuoja();
    public Labyrintti laby;
    public Verkko v1;
    public Verkko v2;
    public Verkko v5;
    
    @Before
    public void alustus(){
        v1 = new Verkko(ll.LuoLabyrintti1());
        v2 = new Verkko(ll.LuoLabyrintti2());
        v5 = new Verkko(ll.LuoLabyrintti5());
    }
    
    @Test
    public void verkkoToimiiEnnenAvaamista() {
        assertEquals(v1.etsiReitti(1, 3), 2147483647);
        assertEquals(v1.etsiReitti(4, 5), 4);
        assertEquals(v1.etsiReitti(5, 4), 4);
    }
    
    @Test
    public void verkkoToimiiAvaamisilla() {
        assertEquals(v1.etsiReitti(0, 9), 2147483647);
        v1.avaaYhteyksia(1);
        v1.avaaYhteyksia(5);
        v1.avaaYhteyksia(4);
        v1.avaaYhteyksia(8);
        assertEquals(v1.etsiReitti(0, 9), 21);
    }
    
    @Test
    public void verkkoToimiiSulkemisilla() {
        assertEquals(v1.etsiReitti(0, 9), 2147483647);
        v1.avaaYhteyksia(1);
        v1.avaaYhteyksia(5);
        v1.avaaYhteyksia(4);
        v1.avaaYhteyksia(8);
        v1.suljeYhteys(4);
        //Nopein reitti suljettiin, mutta reitti löytyy yhä
        assertEquals(v1.etsiReitti(0, 9), 21);
    }
    
    @Test
    public void verkkoToimiiMonimutkainenReitti() {
        assertEquals(v2.etsiReitti(0, 7), 2147483647);
        v2.avaaYhteyksia(1); // avataan a
        assertEquals(v2.etsiReitti(0, 7), 2147483647);
        v2.avaaYhteyksia(12); // avataan E
        assertEquals(v2.etsiReitti(0, 7), 2147483647);
        v2.avaaYhteyksia(14); // avataan G
        assertEquals(v2.etsiReitti(0, 7), 2147483647);
        //Avataan toimiva reitti
        v2.avaaYhteyksia(11); // avataan D
        assertEquals(v2.etsiReitti(0, 7), 30);
        //Avataan parempi reitti
        v2.avaaYhteyksia(8); // avataan A
        assertEquals(v2.etsiReitti(0, 7), 14);
        //suljetaan huonompi reitti
        v2.suljeYhteys(11); // suljetaan D
        assertEquals(v2.etsiReitti(0, 7), 14);
        //suljetaan parempi reitti
        v2.suljeYhteys(8); // suljetaan A
        assertEquals(v2.etsiReitti(0, 7), 2147483647);
    }
    
    @Test
    public void verkkoToimiiMatkaMaaliin() {
        v5.avaaYhteyksia(20); // avataan t
        assertEquals(v5.etsiReitti(20, 41), 2); // t - maali
        assertEquals(v5.etsiReitti(19, 41), 2147483647); // S - maali
    }
    
}
