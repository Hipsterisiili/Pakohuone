package pakohuone.Main;
import pakohuone.sovelluslogiikka.Avain;
import pakohuone.sovelluslogiikka.Huone;
import pakohuone.sovelluslogiikka.Ovi;
//import pakohuone.sovelluslogiikka.Huone;
//import pakohuone.tyokalut.EtaisyydenEtsija;
import pakohuone.tyokalut.HuoneidenEtsinta;
import pakohuone.tyokalut.ReittienEtsija;

public class Labyrintti {
    private char[][] kuva;
    private int[][] huoneTaulukko;
    private int leveys, korkeus;
    private int avaintenMaara;
    private int huoneidenMaara; 
    private HuoneidenEtsinta huoEts;
    private ReittienEtsija reiEts;
    private Ovi[] ovet;
    private Avain[] avaimet;
    private Huone[] huoneet;
    /**
   * Labyrintti on olio, joka sisältää tiedon kaikesta mitä sen sisällä on,
   * kuten avaimista, ovista, seinistä, huoneista, sekä avainten ja ovien 
   * välisistä yhteyksistä. Osan näistä tiedoista olio saa sen sisltämältä
   * oliolta HuoneidenEtsinta huoEts.
   */
    public Labyrintti(char[][] taulukko) {
        kuva = taulukko;
        huoEts = new HuoneidenEtsinta(taulukko);
        huoEts.tulkitse();
        huoneTaulukko = huoEts.getHuoneTaulukko();
        korkeus = taulukko.length - 1;
        leveys = taulukko[0].length - 1;
        ovet = huoEts.getOvet();
        avaimet = huoEts.getAvaimet();
        huoneet = huoEts.getHuoneet();
        avaintenMaara = huoEts.getAvaintenMaara();
        huoneidenMaara = huoEts.getHuoneidenMaara();
        reiEts = new ReittienEtsija(this);
        reiEts.etsi();
    }

    public char[][] getKuva() {
        return kuva;
    }

    public int[][] getHuoneTaulukko() {
        return huoneTaulukko;
    }
     /**
   * Tulostaa labyrintin siten, että kussakin ruudussa on merkki, joka
   * kertoo mitä ruudussa on. Piste tarkoittaa tyhjää tilaa, # seinää,
   * pieni kirjain tarkoittaa avainta ja iso kirjain ovea
   */
    public void tulostaLabyrintti() {
        kuva[1][1] = '+'; // ASCIIssa 43
        kuva[korkeus - 1][leveys - 1] = '*'; // ASCIIssa 42
        System.out.print("x");
        for(int i = 1; i < leveys; i++) {
            System.out.print(" " + i);
        }
        System.out.println("");
        for (int i = 0; i < korkeus + 1; i++) {
            for (int j = 0; j < leveys+1; j++) {
                System.out.print(kuva[i][j] + " ");
            }
            if(i == 0) {
                System.out.println("  y");
            } else {
                System.out.println("  " + i);
            }
        }
        kuva[1][1] = '.'; // ASCIIssa 43
        kuva[korkeus - 1][leveys - 1] = '.'; // ASCIIssa 42
    }
    /**
   * Tulostaa labyrintin siten, että kussakin ruudussa on numero, joka
   * kertoo mihin huoneeseen kyseinen ruutu kuuluu.
   */
    public void tulostaHuoneet() {
        for (int i = 0; i < korkeus + 1; i++) {
            for (int j = 0; j < leveys + 1; j++) {
                if (huoneTaulukko[i][j] == 0) {
                    System.out.print("# ");
                } else {
                    System.out.print(huoneTaulukko[i][j] + " ");
                }
            }
            System.out.println("");
        }
    }

    public Ovi[] getOvet() {
        return ovet;
    }

    public Avain[] getAvaimet() {
        return avaimet;
    }
    public Huone[] getHuoneet() {
        return huoneet;
    }
    
    public int getAvaintenMaara() {
        return this.avaintenMaara;
    }
    public int getHuoneidenMaara() {
        return this.huoneidenMaara;
    }
}
