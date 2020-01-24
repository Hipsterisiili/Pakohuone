package pakohuone.tyokalut;

import pakohuone.sovelluslogiikka.Avain;
import pakohuone.sovelluslogiikka.Huone;
import pakohuone.sovelluslogiikka.Ovi;

public class HuoneidenEtsinta {

    private char[][] labyrintti;
    private int[][] huoneet;
    private Avain[] avaimet;
    private Ovi[] ovet;
    private int korkeus;
    private int leveys;

    private int huoneidenMaara = 0;
    private char seina = '#';
    private char tyhja = '.';
    
    /**
   * HuoneidenEtsinta muodostaa kuvan siitä mitä labyrintissa on eli
   * millaisia huoneita siinä on ja mitä avaimia ja ovia huoneesta
   * löytyy. Käytännössä tämä olio pureskelee annetun parametrin 
   * hyödynnettävään muotoon.
   */
    public HuoneidenEtsinta(char[][] laby) {
        this.labyrintti = laby;
        this.korkeus = laby.length;
        this.leveys = laby[0].length;
        this.huoneet = new int[korkeus][leveys];
        this.avaimet = new Avain[26];
        this.ovet = new Ovi[26];

        for (int i = 1; i < korkeus - 1; i++) {
            for (int j = 1; j < leveys - 1; j++) {
                huoneet[i][j] = 0;
            }
        }
        for (int i = 0; i < 26; i++) {
            avaimet[i] = new Avain(0, 0);
        }
    }

    /**
   * Metodi tulkitse pureskelee annetun labyrintin hyödylliseen muotoon
   * Se selvittää itse ensin huoneiden muodot metodin luoHuone avulla ja 
   * tunnistaa avainten ja ovien paikat. Sitten se kutsuu metodia 
   * TeeAvainTaiOvi, joka luo avaimet ja ovet tunnistettuihin paikkoihin. 
   * @return kaksiuloitteinen taulukko, jossa kussakin ruudussa numero,
   * joka kertoo mihin huoneeseen se kuuluu.
   */
    public int[][] tulkitse() {
        taytaNollilla();
        for (int i = 1; i < korkeus - 1; i++) {
            for (int j = 1; j < leveys - 1; j++) {
                if (labyrintti[i][j - 1] == seina
                        && labyrintti[i - 1][j] == seina
                        && labyrintti[i][j] == tyhja) {
                    luoHuone(i, j);
                }
                if (labyrintti[i][j] != seina && labyrintti[i][j] != tyhja) {
                    TeeAvainTaiOvi(i, j, labyrintti[i][j]);
                }
            }
        }
        yhdistaAvaimetJaOvet();
        return huoneet;
    }
    /**
   * Metodi täyttää taulukon "huoneet" arvot nollilla, eli käytännössä nollaa
   * koko taulukon
   */
    private void taytaNollilla(){
        for (int i = 1; i < korkeus - 1; i++) {
            for (int j = 1; j < leveys - 1; j++) {
                huoneet[i][j] = 0;
            }
        }
    }

     /**
   * Täyttää annetusta pisteestä alkavan huoneen huoneen numerolla. Metodi
   * tunnistaa huoneen rajat for-looppien avulla.
   * @param alkux huoneen pienin x-koordinaatti
   * @param alkuy huoneen pienin y-koordinaatti. 
   */
    private void luoHuone(int alkux, int alkuy) {
        int a = alkux;
        int b = alkuy;
        huoneidenMaara++;
        while (labyrintti[alkux][b] != seina) {
            while (labyrintti[a][alkuy] != seina) {
                huoneet[a][b] = huoneidenMaara;
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
    private void TeeAvainTaiOvi(int x, int y, char c) {
        int arvo = c;
        //System.out.println("OLLAAN TEKEMÄSSÄ " + c);

        //ISOT KIRJAIMET 65-90
        if (arvo > 96) {
            //System.out.println("TEHDÄÄN AVAIN " + x + "," + y + ", " + c);
            arvo -= 97;
            avaimet[arvo] = new Avain(x, y);

            //PIENET KIRJAIMET 97-122
        } else {
            arvo -= 65;
            //TARKASTELLAAN KUMPAAN SUUNTAAN OVI AUKEAA
            //System.out.print("TEHDÄÄN OVI " + x + "," + y + ", " + c);
            if (labyrintti[x - 1][y] == tyhja) {
                //System.out.println(" x-suunnassa");
                ovet[arvo] = new Ovi(x - 1, y, x + 1, y);
            } else {
                //System.out.println(" y-suunnassa");
                ovet[arvo] = new Ovi(x, y - 1, x, y + 1);
            }
        }
    }

    /**
     * Metodi yhdistää avaimet ja ovet
     * Se käy läpi koko avaimet-taulun ja aina kun löytyy olemassa oleva 
     * avain, etsitään sen kirjainta vastannut ovi ja lisätään avaimelle
     * sille kuuluva ovi edellisen proxyn tilalle.
     */
    private void yhdistaAvaimetJaOvet() {
        for (int i = 0; i < 26; i++) {
            if (avaimet[i].getSijaintiX() != 0) {
                System.out.println("YHDISTYS");
                System.out.println("avain: " + avaimet[i].getSijaintiX()
                        + "," + avaimet[i].getSijaintiY());
                avaimet[i].setOvi(ovet[i]);
            }
        }
    }

    public int[][] getHuoneet() {
        return huoneet;
    }

    public Avain[] getAvaimet() {
        return avaimet;
    }

    public Ovi[] getOvet() {
        return ovet;
    }
}
