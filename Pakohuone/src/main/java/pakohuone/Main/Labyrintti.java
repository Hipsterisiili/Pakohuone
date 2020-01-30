package pakohuone.Main;
import pakohuone.sovelluslogiikka.Avain;
import pakohuone.sovelluslogiikka.Ovi;
//import pakohuone.sovelluslogiikka.Huone;
//import pakohuone.tyokalut.EtaisyydenEtsija;
import pakohuone.tyokalut.HuoneidenEtsinta;

public class Labyrintti {
    private char[][] kuva;
    private int[][] huoneet;
    private int leveys, korkeus;
    private int avaintenMaara;
    private HuoneidenEtsinta huoEts;
    private Ovi[] ovet;
    private Avain[] avaimet;
    
    public Labyrintti(char[][] taulukko) {
        kuva = taulukko;
        huoEts = new HuoneidenEtsinta(taulukko);
        huoEts.tulkitse();
        huoneet = huoEts.getHuoneTaulukko();
        korkeus = taulukko.length - 1;
        leveys = taulukko[0].length - 1;
        ovet = huoEts.getOvet();
        avaimet = huoEts.getAvaimet();
        avaintenMaara = huoEts.getAvaintenMaara();
    }

    public char[][] getKuva() {
        return kuva;
    }

    public int[][] getHuoneet() {
        return huoneet;
    }
     /**
   * Tulostaa labyrintin siten, että kussakin ruudussa on merkki, joka
   * kertoo mitä ruudussa on. Piste tarkoittaa tyhjää tilaa, # seinää,
   * pieni kirjain tarkoittaa avainta ja iso kirjain ovea
   */
    public void tulostaLabyrintti() {
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
    }
    /**
   * Tulostaa labyrintin siten, että kussakin ruudussa on numero, joka
   * kertoo mihin huoneeseen kyseinen ruutu kuuluu.
   */
    public void tulostaHuoneet() {
        for (int i = 0; i < korkeus + 1; i++) {
            for (int j = 0; j < leveys + 1; j++) {
                if (huoneet[i][j] == 0) {
                    System.out.print("# ");
                } else {
                    System.out.print(huoneet[i][j] + " ");
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
    
    public int getAvaintenMaara() {
        return this.avaintenMaara;
    }
}
