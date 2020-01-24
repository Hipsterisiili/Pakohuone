import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import pakohuone.Main.Labyrintti;
import pakohuone.Main.Main;
import pakohuone.sovelluslogiikka.Avain;
import pakohuone.sovelluslogiikka.Ovi;
import pakohuone.sovelluslogiikka.Huone;
import pakohuone.tyokalut.EtaisyydenEtsija;
import pakohuone.tyokalut.HuoneidenEtsinta;

public class HuoneTest {
    Huone h = new Huone();
    Avain a = new Avain(1,2);
    Avain aa = new Avain(3,4);
    Ovi o = new Ovi(1,2,3,4);
    Ovi oo = new Ovi(5,6,7,8);
    
    @Test
    public void HuoneenLuonti(){
        assertTrue(h.getOvet()[0] == null);
        assertTrue(h.getAvaimet()[0] == null);   
    }
    @Test
    public void AvaimetToimii(){
        h.LisaaAvain(a);
        a.setOvi(o);
        assertTrue(a.getOvi() == o);
        h.LisaaAvain(a);
        h.LisaaAvain(aa);
        aa.setOvi(oo);
        assertTrue(h.getAvaimet()[0].getOvi() == o);
        assertTrue(h.getAvaimet()[1].getOvi() == oo);
    }
    
    @Test
    public void OvetToimii(){
        assertTrue(o.getAlkuX() == 1);
        assertTrue(o.getLoppuY() == 4);
        assertTrue(oo.getLoppuX() == 7);
        assertTrue(oo.getAlkuY() == 6);
    }
    
    @Test
    public void EiVoiLisataLiikaaAvaimiaTaiOvia(){
        for(int i = 0; i < 100 ; i++){
            h.LisaaAvain(a);
            h.LisaaOvi(o);
        }
        h.LisaaAvain(aa);
        h.LisaaOvi(oo);
        assertTrue(h.getOvet()[99] != oo);
        assertTrue(h.getAvaimet()[99] != aa);
    }
}