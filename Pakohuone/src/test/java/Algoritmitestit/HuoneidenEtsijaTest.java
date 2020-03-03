package Algoritmitestit;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import pakohuone.sovelluslogiikka.Labyrintti;
import pakohuone.ui.LabyrintinLuoja;

public class HuoneidenEtsijaTest {
    //testattu käytännössä jo Labyrintti-luokan testeissä, mutta tässä luokassa
    //tehdään vielä tarkentava testi, siltä varalta jos nimenomaan
    //HuoneidenEtsijaan tulee häikkää
    
    @Test
    public void LoytyykoHuoneet(){
        LabyrintinLuoja ll = new LabyrintinLuoja();
        Labyrintti laby = ll.LuoLabyrintti1();
        
        assertTrue(laby.getHuoneTaulukko()[0][0] == 0);
        assertTrue(laby.getHuoneTaulukko()[12][12] == 0);
        assertTrue(laby.getHuoneTaulukko()[12][13] == 0);
        assertTrue(laby.getHuoneTaulukko()[1][1] == 1);
        assertTrue(laby.getHuoneTaulukko()[5][6] == 0);
        assertTrue(laby.getHuoneTaulukko()[8][6] == 0);
        assertTrue(laby.getHuoneTaulukko()[4][5] == 1);
        assertTrue(laby.getHuoneTaulukko()[4][7] == 2);
        assertTrue(laby.getHuoneTaulukko()[6][5] == 3);
        assertTrue(laby.getHuoneTaulukko()[6][7] == 4);
    }
}
