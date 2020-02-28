
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

public class HuoneAvainOviTest {

    Huone h = new Huone();
    Avain a = new Avain(1, 2, 'a');
    Avain aa = new Avain(3, 4, 'b');
    Ovi o = new Ovi(1, 2, 3, 4);
    Ovi oo = new Ovi(5, 6, 7, 8);

    @Test
    public void HuoneenLuonti() {
        assertTrue(h.getOvet()[0].getAlkuX() == 0);
        assertTrue(h.getOvet()[0].getAlkuY() == 0);
        assertTrue(h.getOvet()[0].getLoppuX() == 0);
        assertTrue(h.getOvet()[0].getLoppuY() == 0);
        assertTrue(h.getOvet()[0].getLoppuY() == 0);
        assertTrue(h.getAvaimet()[0].getSijaintiX() == 0);
        assertTrue(h.getAvaimet()[0].getSijaintiY() == 0);
        assertTrue(h.getAvaimet()[0].getKirjain() == '@');
        assertTrue(h.getAvaimet()[0].getOvi().getAlkuX() == 0);
        assertTrue(h.getAvaimet()[0].getOvi().getAlkuY() == 0);
        assertTrue(h.getAvaimet()[0].getOvi().getLoppuX() == 0);
        assertTrue(h.getAvaimet()[0].getOvi().getLoppuX() == 0);
    }

    @Test
    public void AvaimetToimii() {
        a = new Avain(1, 2, 'a');
        aa = new Avain(3, 4, 'b');
        o = new Ovi(1, 2, 3, 4);
        oo = new Ovi(5, 6, 7, 8);
        a.setOvi(o);
        assertTrue(a.getOvi() == o);
        h.lisaaAvain(a);
        h.lisaaAvain(aa);
        aa.setOvi(oo);
        assertTrue(h.getAvaimet()[0].getOvi() == o);
        assertTrue(h.getAvaimet()[1].getOvi() == oo);
    }

    @Test
    public void OvetToimii() {
        assertTrue(o.getAlkuX() == 1);
        assertTrue(o.getLoppuY() == 4);
        assertTrue(oo.getLoppuX() == 7);
        assertTrue(oo.getAlkuY() == 6);
    }

    @Test
    public void EiVoiLisataLiikaaAvaimiaTaiOvia() {
        for (int i = 0; i < 32; i++) {
            h.lisaaAvain(a);
            h.lisaaOvi(o);
        }
        h.lisaaAvain(aa);
        h.lisaaOvi(oo);
        assertTrue(h.getOvet()[29] != oo);
        assertTrue(h.getAvaimet()[29] != aa);
    }

    @Test
    public void AvainToString() {
        //Juuri luodun avaimen tulostus
        String aTekstina
                = "x: 1 y: 2\n"
                + "Oven sijainti x: 0 y: 0";
        assertEquals(a.toString(), aTekstina);

        //Valmiin avaimen tulostus
        a.setOvi(o);
        aTekstina
                = "x: 1 y: 2\n"
                + "Oven sijainti x: 2 y: 3";
        assertEquals(a.toString(), aTekstina);
        
        //Yritys tulostaa avainta jota ei ole olemassa
        String olematonAvainTekstina
                = "x: 0 y: 0\n"
                + "Oven sijainti x: 0 y: 0";
        assertEquals(h.getAvaimet()[3].toString(), olematonAvainTekstina);
    }
    
    @Test
    public void OviToString() {
        //Normaalin oven tulostus
        String oTekstina
                = "Oven sijainti x: 2 y: 3";
        assertEquals(o.toString(), oTekstina);
        
        //Yritys tulostaa avainta jota ei ole olemassa
        String olematonOviTekstina
                = "Oven sijainti x: 0 y: 0";
        assertEquals(h.getOvet()[3].toString(), olematonOviTekstina);
    }
    
    @Test
    public void AvaimenPoisto() {
        h = new Huone();
        a = new Avain(1, 2, 'a');
        aa = new Avain(3, 4, 'b');
        o = new Ovi(1, 2, 3, 4);
        oo = new Ovi(5, 6, 7, 8);
        h.lisaaAvain(a);
        h.lisaaAvain(aa);
        assertTrue(h.getAvaintenMaara() == 2);
        assertEquals(h.getAvaimet()[1].getKirjain(), aa.getKirjain());
        h.poistaAvain();
        assertTrue(h.getAvaintenMaara() == 1);
        assertTrue(h.getAvaimet()[1].getKirjain() == '@');
    }
    @Test
    public void OvenPoisto() {
        h = new Huone();
        a = new Avain(1, 2, 'a');
        aa = new Avain(3, 4, 'b');
        o = new Ovi(1, 2, 3, 4);
        oo = new Ovi(5, 6, 7, 8);
        h.lisaaOvi(o);
        h.lisaaOvi(oo);
        assertTrue(h.getOvienMaara() == 2);
        assertEquals(h.getOvet()[1].getAlkuX(), oo.getAlkuX());
        h.poistaOvi();
        assertTrue(h.getOvienMaara() == 1);
        assertFalse(h.getOvet()[1].getAlkuX() == oo.getAlkuX());
    }
}
