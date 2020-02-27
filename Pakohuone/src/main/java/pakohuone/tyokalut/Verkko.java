package pakohuone.tyokalut;

import java.util.Arrays;
import pakohuone.sovelluslogiikka.Labyrintti;
import pakohuone.sovelluslogiikka.Avain;
import pakohuone.sovelluslogiikka.Huone;
import pakohuone.sovelluslogiikka.Ovi;

/**
 * Verkko on tietorakenne, joka lyläpitää tietoa siitä mihin avaimiin ja oviin
 * mistäkin avaimesta ja ovesta on mahdollista päästä ja kuinka pitkä tämä
 * reitti on. Ainoastaan yhden huoneen sisäiset matkat on tallennettu verkkoon.
 * int[][] verkko sisältää tiedon siitä kuinka pitkä matka kustakin kohteesta
 * kuhunkin kohteesene on. Arvo 0 tarkoittaa että tällaista kaarta ei ole
 * olemassa yhden huoneen sisällä. boolean[][] taas kertoo kussakin vaiheessa
 * mitkä yhteydet ovat kullakin hetkellä käytössä. Arvo true tarkoittaa että
 * kaarta pitkin voi kulkea, toisin sanoen huone, jossa kaari sijaitsee on
 * saavutettavissa labyrintin alkupisteestä
 */
public class Verkko {

    private Labyrintti laby;
    private int avaintenMaara;
    private int huoneidenMaara;
    //verkko kertoo pisteiden etäisyydet toisiinsa
    //0 = lähtöpiste.
    //1 - avaintenMaara = avaimia
    //avaintenMaara - 2*avaintenMaara+1 = ovia
    //2*avaintenMaara + 2 = maali
    private int[][] verkko;
    //onkoYhteysKaytossa kertoo mitä yhteyksiä on käytössä.
    //Tämä riippuu siitä mitä huoneita on saavutettu.
    //onkoYhteysKaytossa[n] kertoo käytännössä onko kohde n vielä saavutettu
    private boolean[] onkoYhteysKaytossa;
    private EtaisyydenEtsija etaEts = new EtaisyydenEtsija();
    private Dijkstra d;

    /**
     * Verkon konstruktori
     *
     * @param l labyrintti, josta verkko muodostetaan
     */
    public Verkko(Labyrintti l) {
        this.laby = l;
        avaintenMaara = laby.getAvaintenMaara();
        huoneidenMaara = laby.getHuoneidenMaara();
        verkko = new int[avaintenMaara + avaintenMaara + 2][avaintenMaara + avaintenMaara + 2];
        onkoYhteysKaytossa = new boolean[avaintenMaara + avaintenMaara + 2];
        for (int i = 0; i < avaintenMaara + 2; i++) {
            Arrays.fill(verkko[i], 0);
        }
        Arrays.fill(onkoYhteysKaytossa, false);
        onkoYhteysKaytossa[0] = true;
        onkoYhteysKaytossa[1] = true;
        tulostaVerkko();
        luoYhteydetHuoneidenSisalla();
        luoYhteydetLahtoon();
        luoYhteydetMaaliin();

        d = new Dijkstra(verkko);
        tulostaVerkko();
    }

    /**
     * Metodi käynnistää kaikkien labyrintin mukaisten yhteyksien muodostamisen
     * verkossa. for-loopissa käydään läpi kaikki huoneet, joiden sisäisiä
     * avaimia ja ovia yhdistellään käyden läpi kaikki mahdolliset yhdistelmät
     * avaimia ja ovia, mitä huoneen sisällä voi muodostaa. Tämän jälkeen
     * kutsutaan sopivaa metodia yhdistämiseen, riippuen siitä mitä kohteita
     * yhdistetään.
     */
    private void luoYhteydetHuoneidenSisalla() {
        Avain a;
        Avain b;
        Ovi o;
        Ovi p;
        int juoksija = -1;
        for (Huone h : laby.getHuoneet()) {
            juoksija++;
            //System.out.println("\nHuone nro " + juoksija + "\n");
            for (int i = 0; i < h.getAvaintenMaara(); i++) {
                a = h.getAvaimet()[i];
                for (int j = 0; j < h.getAvaintenMaara(); j++) {
                    b = h.getAvaimet()[j];
                    yhdistaAvainJaAvain(a, b);
                }
                for (int j = 0; j < h.getOvienMaara(); j++) {
                    o = h.getOvet()[j];
                    yhdistaAvainJaOvi(a, o);
                }
            }
            for (int i = 0; i < h.getOvienMaara(); i++) {
                o = h.getOvet()[i];
                for (int j = 0; j < h.getOvienMaara(); j++) {
                    p = h.getOvet()[j];
                    yhdistaOviJaOvi(o, p);
                }
            }
        }
    }

    /**
     * Metodi valmistelee kahden avaimen yhdistämisen keskenään verkossa ja
     * sitten kutsuu metodia yhdistaKohteet, joka toteuttaa yhdistämisen
     *
     * @param a ensimmäinen yhdistettävä avain
     * @param b toinen yhdistettävä avain
     */
    private void yhdistaAvainJaAvain(Avain a, Avain b) {
        //System.out.println("AVAINJAAVAIN");
        int ax = a.getSijaintiX();
        int ay = a.getSijaintiY();
        int bx = b.getSijaintiX();
        int by = b.getSijaintiY();
        //Jos avaimen kirjain = a, se sijaitsee verkossa sarakkeessa 1 jne
        int aNumero = ((int) a.getKirjain() - 96);
        int bNumero = ((int) b.getKirjain() - 96);
        yhdistaKohteet(ax, ay, aNumero, bx, by, bNumero);
    }

    /**
     * Metodi valmistelee oven ja avaimen yhdistämisen keskenään verkossa ja
     * sitten kutsuu metodia yhdistaKohteet, joka toteuttaa yhdistämisen
     *
     * @param a yhdistettävä avain
     * @param o yhdistettävä ovi
     */
    private void yhdistaAvainJaOvi(Avain a, Ovi o) {
        //System.out.println("AVAINJAOVI");
        int ax = a.getSijaintiX();
        int ay = a.getSijaintiY();
        int ox = (o.getAlkuX() + o.getLoppuX()) / 2;
        int oy = (o.getAlkuY() + o.getLoppuY()) / 2;
        //Jos avaimen kirjain = a, se sijaitsee verkossa sarakkeessa 1 jne
        int aNumero = ((int) a.getKirjain() - 96);
        //Jos oven kirjain = A, se sijaitsee verkossa sarakkeessa avaintenMaara + 1 jne
        int oNumero = (laby.getKuva()[ox][oy] - 64 + avaintenMaara);
        yhdistaKohteet(ax, ay, aNumero, ox, oy, oNumero);
    }

    /**
     * Metodi valmistelee kahden oven yhdistämisen keskenään verkossa ja sitten
     * kutsuu metodia yhdistaKohteet, joka toteuttaa yhdistämisen
     *
     * @param o ensimmäinen yhdistettävä ovi
     * @param p toinen yhdistettävä ovi
     */
    private void yhdistaOviJaOvi(Ovi o, Ovi p) {
        //System.out.println("OVIJAOVI");
        int ox = (o.getAlkuX() + o.getLoppuX()) / 2;
        int oy = (o.getAlkuY() + o.getLoppuY()) / 2;
        int px = (p.getAlkuX() + p.getLoppuX()) / 2;
        int py = (p.getAlkuY() + p.getLoppuY()) / 2;
        //Jos oven kirjain = A, se sijaitsee verkossa sarakkeessa avaintenMaara + 1 jne
        int oNumero = (laby.getKuva()[ox][oy] - 64 + avaintenMaara);
        int pNumero = (laby.getKuva()[px][py] - 64 + avaintenMaara);
        yhdistaKohteet(ox, oy, oNumero, px, py, pNumero);
    }

    private void luoYhteydetLahtoon() {
        Huone h = laby.getHuoneet()[1];
        for (Avain a : h.getAvaimet()) {
            if(a.getKirjain() == '@'){
                continue;
            }
            int ax = a.getSijaintiX();
            int ay = a.getSijaintiY();
            int aNumero = ((int) a.getKirjain() - 96);
            //System.out.println("Lähtö, avain: "+ax+", "+ay+", "+aNumero);
            yhdistaKohteet(ax, ay, aNumero, 1, 1, 0);
        }
        for (Ovi o : h.getOvet()) {
            if(o.getAlkuX() == 0 && o.getAlkuY() == 0){
                continue;
            }
            int ox = (o.getAlkuX() + o.getLoppuX()) / 2;
            int oy = (o.getAlkuY() + o.getLoppuY()) / 2;
            int oNumero = (laby.getKuva()[ox][oy] - 64 + avaintenMaara);
            //System.out.println("Lähtö, ovi: "+ox+", "+oy+", "+oNumero);
            yhdistaKohteet(ox, oy, oNumero, 1, 1, 0);
        }
    }

    private void luoYhteydetMaaliin() {
        Huone h = laby.getHuoneet()[laby.getHuoneidenMaara()];
        for (Avain a : h.getAvaimet()) {
            if(a.getKirjain() == '@'){
                continue;
            }
            int ax = a.getSijaintiX();
            int ay = a.getSijaintiY();
            int aNumero = ((int) a.getKirjain() - 96);
            //System.out.println("Maali, avain: "+ax+", "+ay+", "+aNumero);
            yhdistaKohteet(ax, ay, aNumero, laby.getKorkeus() - 1, laby.getLeveys() - 1, verkko[0].length -1);
        }
        for (Ovi o : h.getOvet()) {
            if(o.getAlkuX() == 0 && o.getAlkuY() == 0){
                continue;
            }
            int ox = (o.getAlkuX() + o.getLoppuX()) / 2;
            int oy = (o.getAlkuY() + o.getLoppuY()) / 2;
            int oNumero = (laby.getKuva()[ox][oy] - 64 + avaintenMaara);
            //System.out.println("Maali, ovi: "+ox+", "+oy+", "+oNumero);
            yhdistaKohteet(ox, oy, oNumero, laby.getKorkeus() - 1, laby.getLeveys() - 1, verkko[0].length -1);
        }
    }

    /**
     * Metodi yhdistää kaksi koordinaattia verkossa ja merkitsee niiden välisen
     * etäisyyden.
     *
     * @param ax ensimmäisen kohteen x
     * @param ay ensimmäisen kohteen y
     * @param an ensimmäisen kohteen sarakkeen numero verkossa
     * @param bx toisen kohteen x
     * @param by toisen kohteen y
     * @param bn toisen kohteen sarakkeen numero verkossa
     */
    private void yhdistaKohteet(int ax, int ay, int an, int bx, int by, int bn) {
        int etaisyys = etaEts.etsiEtaisyys(ax, ay, bx, by);
        //System.out.println("Yhdistetaan " + ax + "," + ay + "," + bx + "," + by);
        verkko[an][bn] = etaisyys;
        verkko[bn][an] = etaisyys;
    }

    /**
     * HUOM: //Luokkaa ei ole tarkoitus käyttää ohjelman normaalitoiminnassa, se
     * on luotu debuggaamista varten Metodi tulostaa verkon matriisina
     * nykytilassaan.
     */
    public void tulostaVerkko() {
        System.out.println("Tulostetaan verkko:");
        for (int i = 0; i < avaintenMaara + avaintenMaara + 2; i++) {
            for (int j = 0; j < avaintenMaara + avaintenMaara + 2; j++) {
                System.out.print(verkko[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public long etsiReitti(int alku, int loppu) {
        return d.hae(alku, loppu, onkoYhteysKaytossa);
    }

    public void avaaYhteyksia(int i) {
        onkoYhteysKaytossa[i] = true;
        //System.out.println("AVATTIIN YHTEYS ALKAEN " + i );
    }

    public void suljeYhteys(int i) {
        onkoYhteysKaytossa[i] = false;
    }
    
    public int getLeveys(){
        return verkko[0].length;
    }
}
