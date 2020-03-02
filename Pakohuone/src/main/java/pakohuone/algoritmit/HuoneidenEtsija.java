package pakohuone.algoritmit;

import pakohuone.sovelluslogiikka.Avain;
import pakohuone.sovelluslogiikka.Huone;
import pakohuone.sovelluslogiikka.Ovi;

public class HuoneidenEtsija {

    private char[][] labyrintti;
    private int[][] huoneTaulukko;
    private Avain[] avaimet;
    private Ovi[] ovet;
    private Huone[] huoneet;
    private int korkeus;
    private int leveys;
    private int avaintenMaara = 0;

    private int huoneidenMaara = 0;
    private char seina = '#';
    private char tyhja = '.';

    /**
     * HuoneidenEtsinta muodostaa kuvan siitä mitä labyrintissa on eli millaisia
     * huoneita siinä on ja mitä avaimia ja ovia huoneesta löytyy. Käytännössä
     * tämä olio pureskelee annetun parametrin hyödynnettävään muotoon. Lisäksi
     * Huoneidenetsinta sisältää sen privaatteja palauttavat metodit (mallia
     * .getXXXXX), jotta esim. Labyrintti, joka sisältää oman
     * HuoneidenEtsinta-olion voi selvittää esim siihen kuuluvien huoneiden
     * luonteen.
     */
    public HuoneidenEtsija(char[][] laby) {
        this.labyrintti = laby;
        this.korkeus = laby.length;
        this.leveys = laby[0].length;
        this.huoneTaulukko = new int[korkeus][leveys];
        this.avaimet = new Avain[26];
        this.ovet = new Ovi[26];

        for (int i = 1; i < korkeus - 1; i++) {
            for (int j = 1; j < leveys - 1; j++) {
                huoneTaulukko[i][j] = 0;
            }
        }
        for (int i = 0; i < 26; i++) {
            //Luodaan placeholderit avaimille, jotka eivät ole vielä olemassa
            avaimet[i] = new Avain(0, 0, '@');
            //Luodaan placeholderit oville, jotka eivät ole vielä olemassa
            ovet[i] = new Ovi(0, 0, 0, 0);
        }
    }

    /**
     * Metodi tulkitse pureskelee annetun labyrintin hyödylliseen muotoon Se
     * selvittää itse ensin huoneiden muodot metodin taytaHuoneNumeroilla avulla
     * ja tunnistaa avainten ja ovien paikat. Sitten se kutsuu metodia
     * TeeAvainTaiOvi, joka luo avaimet ja ovet tunnistettuihin paikkoihin.
     *
     * @return kaksiuloitteinen taulukko, jossa kussakin ruudussa numero, joka
     * kertoo mihin huoneeseen se kuuluu.
     */
    public int[][] tulkitse() {
        taytaNollilla();
        for (int i = 1; i < korkeus - 1; i++) {
            for (int j = 1; j < leveys - 1; j++) {
                int ylempi = labyrintti[i][j - 1];
                int vasen = labyrintti[i - 1][j];
                int tama = labyrintti[i][j];
                if ((ylempi == 35 || (ylempi < 91 && ylempi > 64))
                        && (vasen == 35 || (vasen < 91 && vasen > 64))
                        && (tama == 46 || (tama < 123 && tama > 96))) {
                    taytaHuoneNumeroilla(i, j);
                }
                if (labyrintti[i][j] != seina && labyrintti[i][j] != tyhja) {
                    teeAvainTaiOvi(i, j, labyrintti[i][j]);
                }
            }
        }
        yhdistaAvaimetJaOvet();
        muodostaHuoneetListaksi();
        return huoneTaulukko;
    }

    /**
     * Metodi täyttää taulukon "huoneTaulukko" arvot nollilla, eli käytännössä
     * nollaa koko taulukon.
     */
    private void taytaNollilla() {
        for (int i = 1; i < korkeus - 1; i++) {
            for (int j = 1; j < leveys - 1; j++) {
                huoneTaulukko[i][j] = 0;
            }
        }
    }

    /**
     * Täyttää annetusta pisteestä alkavan huoneen huoneen numerolla. Metodi
     * tunnistaa huoneen rajat for-looppien avulla.
     *
     * @param alkux huoneen pienin x-koordinaatti
     * @param alkuy huoneen pienin y-koordinaatti.
     */
    private void taytaHuoneNumeroilla(int alkux, int alkuy) {
        //System.out.println("TÄYTETÄÄN HUONE NUMEROLLA " + (huoneidenMaara + 1));
        //System.out.println("ALKAEN " + alkux + ", " + alkuy);
        int a = alkux;
        int b = alkuy;
        huoneidenMaara++;
        while ((int) labyrintti[alkux][b] != 35
                && !(((int) labyrintti[alkux][b] < 91)
                && (int) labyrintti[alkux][b] > 64)) {
            while (labyrintti[a][alkuy] != seina
                    && !(((int) labyrintti[a][alkuy] < 91)
                    && (int) labyrintti[a][alkuy] > 64)) {
                huoneTaulukko[a][b] = huoneidenMaara;
                a++;
            }
            a = alkux;
            b++;
        }
    }

    /**
     * Metodi tunnistaa mitä annetussa koordinaatissa on ja luo sen
     *
     * @param x x-koordinaatti, johon ollaan luomassa uutta kohdetta
     * @param y y-koordinaatti, johon ollaan luomassa uutta kohdetta
     * @param c kirjain, joka kertoo onko kohde avain vai ovi (iso vai pieni
     * kirjain) ja myös yksilöi kohteen muista.
     */
    private void teeAvainTaiOvi(int x, int y, char c) {
        int arvo = c;

        if (arvo == '*') {
            //Tässä ruudussa on maali, johon ohjelman on suorituksen lopussa saavuttava
            return;
        }
        //ISOT KIRJAIMET ASCIISSA OVAT 65-90
        //Tallennetaan avaimet taulukkoon avaimetseuraavasti: 
        //avaimet[0] = Avain a, avaimet[3] = Avain d;
        if (arvo > 96) {
            arvo -= 97;
            avaimet[arvo] = new Avain(x, y, c);
            avaintenMaara++;
            //PIENET KIRJAIMET ASCIISSA OVAT 97-122
            //Tallennetaan ovet taulukkoon ovet seuraavasti: 
            //ovet[0] = Ovi A, ovet[3] = Ovi D;
        } else {
            arvo -= 65;
            //TARKASTELLAAN KUMPAAN SUUNTAAN OVI AUKEAA
            //System.out.print("TEHDÄÄN OVI " + x + "," + y + ", " + c);
            if (labyrintti[x - 1][y] == tyhja || labyrintti[x - 1][y] > 96) {
                //System.out.println(" x-suunnassa");
                ovet[arvo] = new Ovi(x - 1, y, x + 1, y);
            } else {
                //System.out.println(" y-suunnassa");
                ovet[arvo] = new Ovi(x, y - 1, x, y + 1);
            }
        }
    }

    /**
     * Metodi yhdistää avaimet ja ovet Se käy läpi koko avaimet-taulun ja aina
     * kun löytyy olemassa oleva avain, etsitään sen kirjainta vastannut ovi ja
     * lisätään avaimelle sille kuuluva ovi edellisen placeholderin tilalle.
     */
    private void yhdistaAvaimetJaOvet() {
        for (int i = 0; i < 26; i++) {
            if (avaimet[i].getKirjain() != '@') {
                //System.out.println("YHDISTYS");
                //System.out.println("avain: " + avaimet[i].getSijaintiX()
                //        + "," + avaimet[i].getSijaintiY());
                avaimet[i].setOvi(ovet[i]);
            }
        }
    }

    /**
     * Metodi tarkastaa montako huonetta labyrintissa on (jo selvillä tässä
     * vaiheessa ja sitten kutsuu kahta metodia. Ensimmäinen lisää kaikki
     * avaimet niihin huoneisiin, joihin ne kuuluvat. Toinen tekee saman oville
     * (huom, kukin ovi on periaatteessa kahdessa huoneessa samaan aikaan)
     */
    private void muodostaHuoneetListaksi() {
        //Huom: huonetta 0 ei ole olemassa.
        this.huoneet = new Huone[this.huoneidenMaara + 1];
        for (int i = 0; i <= huoneidenMaara; i++) {
            huoneet[i] = new Huone();
        }
        lisaaAvaimiaHuoneisiin();
        lisaaOviaHuoneisiin();
    }

    /**
     * Metodi lisää kaikki avaimet niitä vastaaviin huoneisiin. Vastaava huone
     * on helppo selvittää taulukosta int[][] huoneTaulukko, kun tuntee avaimen
     * omat koordinaatit;
     */
    private void lisaaAvaimiaHuoneisiin() {
        int tempX;
        int tempY;
        int huonenumero;
        for (Avain a : avaimet) {
            //Merkistä @ tunnistaa olemattoman huoneen
            if (a.getKirjain() == '@') {
                return;
            }
            tempX = a.getSijaintiX();
            tempY = a.getSijaintiY();
            huonenumero = huoneTaulukko[tempX][tempY];
            huoneet[huonenumero].lisaaAvain(a);
            //System.out.println("LISÄTTY HUONEESEEN " + huonenumero + " " + a.toString());
        }
    }

    /**
     * Metodi lisää kaikki ovet niitä vastaaviin huoneisiin. Vastaava huone on
     * helppo selvittää taulukosta int[][] huoneTaulukko, kun tuntee oven omat
     * koordinaatit;
     */
    private void lisaaOviaHuoneisiin() {
        int tempX;
        int tempY;
        int huonenumero;
        for (Ovi o : ovet) {
            if (o.getAlkuX() == 0 && o.getAlkuY() == 0) {
                return;
            }
            tempX = o.getAlkuX();
            tempY = o.getAlkuY();
            huonenumero = huoneTaulukko[tempX][tempY];
            huoneet[huonenumero].lisaaOvi(o);
            tempX = o.getLoppuX();
            tempY = o.getLoppuY();
            huonenumero = huoneTaulukko[tempX][tempY];
            huoneet[huonenumero].lisaaOvi(o);
        }
    }

    public int[][] getHuoneTaulukko() {
        return huoneTaulukko;
    }

    public Avain[] getAvaimet() {
        return avaimet;
    }

    public Ovi[] getOvet() {
        return ovet;
    }

    public Huone[] getHuoneet() {
        return huoneet;
    }

    public int getAvaintenMaara() {
        return this.avaintenMaara;
    }

    public int getHuoneidenMaara() {
        return this.avaintenMaara;
    }
}
