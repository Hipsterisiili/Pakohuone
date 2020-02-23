
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

public class HuoneTest {

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
}
