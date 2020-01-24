package pakohuone.Main;
import pakohuone.sovelluslogiikka.Avain;
import pakohuone.sovelluslogiikka.Ovi;
import pakohuone.sovelluslogiikka.Huone;
import pakohuone.tyokalut.EtaisyydenEtsija;
import pakohuone.tyokalut.HuoneidenEtsinta;

public class Labyrintti {
    private char[][] kuva;
    private int[][] huoneet;
    private int leveys, korkeus;
    private HuoneidenEtsinta huoEts;
    private Ovi[] ovet;
    private Avain[] avaimet;
    
    public Labyrintti(char[][] taulukko){
        kuva = taulukko;
        huoEts = new HuoneidenEtsinta(taulukko);
        huoEts.tulkitse();
        huoneet = huoEts.getHuoneet();
        korkeus = taulukko.length;
        leveys = taulukko[0].length;
        ovet = huoEts.getOvet();
        avaimet = huoEts.getAvaimet();
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
    public void tulostaLabyrintti(){
        System.out.print("x");
        for(int i = 1 ; i < leveys ; i++){
            System.out.print(" " + i);
        }
        System.out.println("");
        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < korkeus; j++) {
                System.out.print(kuva[j][i] + " ");
            }
            if(i == 0){
                System.out.println("  y");
            }else {
                System.out.println("  " + i);
            }
        }
    }
    /**
   * Tulostaa labyrintin siten, että kussakin ruudussa on numero, joka
   * kertoo mihin huoneeseen kyseinen ruutu kuuluu
   */
    public void tulostaHuoneet(){
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                if (huoneet[j][i] == 0) {
                    System.out.print("# ");
                } else {
                    System.out.print(huoneet[j][i] + " ");
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
    
    
}
