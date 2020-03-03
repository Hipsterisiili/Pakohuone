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

    // kuva ylläpitää tietoa labyrintin ruutujen sisällöistä
    private char[][] kuva;
    // huoneTaulukko ylläpitää tietoa siitä missä huoneessa kukin ruutu on
    private int[][] huoneTaulukko;
    // leveys, korkeus = labyrintin mitat
    private int leveys, korkeus;
    // avaintenMaara, huoneidenMaara = montako avainta ja ovea labyrintissa on
    private int avaintenMaara;
    private int huoneidenMaara;
    // onkoReittejaEtsitty = onko ReittienEtsinta.etsi() jo ajettu
    private boolean onkoReittejaEtsitty;
    // huoEts = olio joka selvittää mihin huoneeseen kukin ruutu kuuluu
    private HuoneidenEtsija huoEts;
    // reiEts = olio joka selvittää maaliin johtavat reitit
    private ReittienEtsija reiEts;
    // nre = olio joka selvittää nopeimman reitin maaliin
    private NopeimmanReitinEtsija nre;
    // Taulukot, jotka sisältävät labyrintin sisällön olioina:
    private Ovi[] ovet;
    private Avain[] avaimet;
    private Huone[] huoneet;
    private String[] jarjestykset;

    /**
     * Labyrintti on olio, joka sisältää tiedon kaikesta mitä sen sisällä on,
     * kuten avaimista, ovista, seinistä, huoneista, sekä avainten ja ovien
     * välisistä yhteyksistä. Osan näistä tiedoista olio saa sen sisältämältä
     * oliolta HuoneidenEtsija huoEts.
     *
     * @param taulukko = taulukko char-arvoja, johon labyrintti perustuu
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
        huoneidenMaara = huoneTaulukko[korkeus - 1][leveys - 1];
        reiEts = new ReittienEtsija(this);
    }
    
    /**
     * Metodi käynnistää maaliin johtavien reittien etsimisen käyttäen apuna
     * ReittienEtsijaa reiEts
     *
     * @ret Integer montako mahodllista reittiä on.
     */
    public int etsiReitit() {
        onkoReittejaEtsitty = true;
        if (!onkoLabyrintissaSeinia()) {
            jarjestykset = new String[1];
            jarjestykset[0] = "+*";
            return 1;
        }
        reiEts.etsi();
        jarjestykset = reiEts.getAvainLista();
        int juoksija = 0;
        if (jarjestykset[0] == null) {
            System.out.println("Ei reittejä");
            return 0;
        };
        return jarjestykset.length;
    }

    /**
     * Metodi käynnistää maaliin johtavien reittien etsimisen käyttäen apuna
     * ReittienEtsijaa reiEts
     *
     * @return String kaikki maaliin johtavat reitit listattuna merkkijonoon
     */
    public String etsiJaTulostaReitit() {
        onkoReittejaEtsitty = true;
        if (!onkoLabyrintissaSeinia()) {
            jarjestykset = new String[1];
            jarjestykset[0] = "+*";
            return "+*";
        }
        reiEts.etsi();
        String palautus = "Löydetyt reitit:";
        jarjestykset = reiEts.getAvainLista();
        int juoksija = 0;
        if (jarjestykset[0] == null) {
            System.out.println("Ei reittejä");
            return "Ei reittejä";
        }
        for (String sana : jarjestykset) {
            //System.out.println("löydetty järjestys " + sana);
            palautus += " / " + sana;
            juoksija++;
            if (juoksija % 10 == 0) {
                palautus += "\n";
            }
        }
        return palautus;
    }

    /**
     * Metodi käynnistää parhaan reitin etsimisen käyttäen apuna
     * NopeimmanReitinEtsijaa nre
     *
     * @return String paras maaliin johtava reitti merkkijonona
     */
    public String etsiParasReitti() {
        if (!onkoReittejaEtsitty) {
            return "Reittejä ei ole vielä etsitty";
        }
        if ("+*".equals(jarjestykset[0])) {
            return "Maaliin pääsee ilman avaimia"
                    + "\nMatka = " + (korkeus + leveys - 4);
        }
        if (jarjestykset[0] == (null)) {
            return "Labyrintistä ei löydy reittejä";
        }
        nre = new NopeimmanReitinEtsija(jarjestykset, this);
        String nopeinReitti = nre.laskeNopeinReitti();
        //System.out.println("Nopein reitti = " + nopeinReitti);
        return nopeinReitti;
    }

    /**
     * Tarkistetaan onko labyrintissä seiniä. HUOM. jos seiniä ei ole, tiedetään
     * että labyrinstissä voi kulkea vapaasti lyhintä tietä maaliin
     *
     * @return onko labyrintissä seiniä
     */
    private boolean onkoLabyrintissaSeinia() {
        int j = 1;
        for (int i = 1; i < korkeus; i++) {
            if (j < leveys - 1) {
                j++;
            }
            if (kuva[i][j] == '#') {
                return true;
            }
        }
        return false;
    }

    /**
     * Tulostaa labyrintin siten, että kussakin ruudussa on merkki, joka kertoo
     * mitä ruudussa on. Piste tarkoittaa tyhjää tilaa, # seinää, pieni kirjain
     * tarkoittaa avainta ja iso kirjain ovea
     */
    public String tulostaLabyrintti() {
        String palautus = "";
        kuva[1][1] = '+'; // ASCIIssa 43
        kuva[korkeus - 1][leveys - 1] = '*'; // ASCIIssa 42
        palautus = palautus + "x";
        for (int i = 1; i < leveys; i++) {
            palautus = palautus + " " + i;
        }
        palautus = palautus + "\n";
        for (int i = 0; i < korkeus + 1; i++) {
            for (int j = 0; j < leveys + 1; j++) {
                palautus = palautus + kuva[i][j] + " ";
            }
            if (i == 0) {
                palautus = palautus + " y\n";
            } else {
                palautus = palautus + "  " + i + "\n";
            }
        }
        kuva[1][1] = '.';
        kuva[korkeus - 1][leveys - 1] = '.';
        return palautus;
    }

    /**
     * Tulostaa labyrintin siten, että kussakin ruudussa on numero, joka kertoo
     * mihin huoneeseen kyseinen ruutu kuuluu.
     *
     * @return taulukon huoeet kuvattuna matriisina
     */
    public String tulostaHuoneet() {
        String palautus = "";
        for (int i = 0; i < korkeus + 1; i++) {
            for (int j = 0; j < leveys + 1; j++) {
                if (huoneTaulukko[i][j] == 0) {
                    palautus = palautus + "# ";
                } else {
                    palautus = palautus + huoneTaulukko[i][j] + " ";
                }
            }
            palautus = palautus + "\n";
        }
        return palautus;
    }
    
    public char[][] getKuva() {
        return kuva;
    }

    public int[][] getHuoneTaulukko() {
        return huoneTaulukko;
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
