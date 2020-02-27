package pakohuone.sovelluslogiikka;
import pakohuone.sovelluslogiikka.Avain;
import pakohuone.sovelluslogiikka.Huone;
import pakohuone.sovelluslogiikka.Ovi;
//import pakohuone.sovelluslogiikka.Huone;
//import pakohuone.tyokalut.EtaisyydenEtsija;
import pakohuone.algoritmit.HuoneidenEtsija;
import pakohuone.algoritmit.NopeimmanReitinEtsija;
import pakohuone.algoritmit.ReittienEtsija;

public class Labyrintti {
    private char[][] kuva;
    private int[][] huoneTaulukko;
    private int leveys, korkeus;
    private int avaintenMaara;
    private int huoneidenMaara; 
    private boolean onkoReittejaEtsitty;
    private HuoneidenEtsija huoEts;
    private ReittienEtsija reiEts;
    private NopeimmanReitinEtsija nre;
    private Ovi[] ovet;
    private Avain[] avaimet;
    private Huone[] huoneet;
    private String[] jarjestykset;
    /**
   * Labyrintti on olio, joka sisältää tiedon kaikesta mitä sen sisällä on,
   * kuten avaimista, ovista, seinistä, huoneista, sekä avainten ja ovien 
   * välisistä yhteyksistä. Osan näistä tiedoista olio saa sen sisltämältä
 oliolta HuoneidenEtsija huoEts.
   */
    public Labyrintti(char[][] taulukko) {
        kuva = taulukko;
        huoEts = new HuoneidenEtsija(taulukko);
        huoEts.tulkitse();
        huoneTaulukko = huoEts.getHuoneTaulukko();
        korkeus = taulukko.length - 1;
        leveys = taulukko[0].length - 1;
        ovet = huoEts.getOvet();
        avaimet = huoEts.getAvaimet();
        huoneet = huoEts.getHuoneet();
        jarjestykset = new String[1];
        jarjestykset[0] = "temp";
        onkoReittejaEtsitty = false;
        avaintenMaara = huoEts.getAvaintenMaara();
        huoneidenMaara = huoEts.getHuoneidenMaara();
        huoneidenMaara = huoneTaulukko[korkeus-1][leveys-1];
        reiEts = new ReittienEtsija(this);
    }

    public char[][] getKuva() {
        return kuva;
    }

    public int[][] getHuoneTaulukko() {
        return huoneTaulukko;
    }
    
    public String etsiReitit(){
        reiEts.etsi();
        String palautus = "Löydetyt reitit:";
        jarjestykset = reiEts.getAvainLista();
        for (String sana : jarjestykset){
            //System.out.println("löydetty järjestys " + sana);
            palautus = palautus + "\n" + sana;
        }
        onkoReittejaEtsitty = true;
        return palautus;
    }
    
    public String etsiParasReitti(){
        System.out.println(jarjestykset[0]);
        if(jarjestykset.length == 0){
            return "Ei löydettyjä reittejä";
        }
        if(jarjestykset[0].equals("temp")){
            return "Ei löydettyjä reittejä, etsi kaikki reitit ensin";
        }
        nre = new NopeimmanReitinEtsija(jarjestykset, this);
        String nopeinReitti = nre.laskeNopeinReitti();
        System.out.println("Nopein reitti = " + nopeinReitti);
        return nopeinReitti;
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

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }
    
}
