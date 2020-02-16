package pakohuone.tyokalut;

import java.util.Arrays;
import pakohuone.Main.Labyrintti;
import pakohuone.sovelluslogiikka.Avain;
import pakohuone.sovelluslogiikka.Huone;
import pakohuone.sovelluslogiikka.Ovi;

/**
 * Luokan tehtävä on selvittää kaikki mahdolliset järjestykset, jossa avaimet
 * voi poimia ja muodostaa niistä järjestyksistä merkkijonoja muodossa abcdefg
 */
public class ReittienEtsija {

    private Labyrintti l;
    private Huone[] huoneLista;
    private String[] avainLista;
    private int mahdollisetReitit = 0;
    private int avaintenMaara;
    private Kirjainpino sana;
    private Leveyshaku leveyshaku = new Leveyshaku();

    //onkoAvainTutkittu[n] = onko kirjain n jo merkkijonossa
    private boolean[] onkoAvainTutkittu;
    //onkoOviAuki[n] = onko ovea n vastaava avain jo poimittu
    private boolean[] onkoOviAuki;
    //onkoHuoneSaavutettu[n] = onko huone n nyt auki olevassa osassa labyrinttia
    private boolean[] onkoHuoneSaavutettu;
    //avaimetTarjolla[n] > 0 -> Avain on tällä hetkellä auki olevassa osassa labyrinttia
    private int[] avaimetTarjolla;
    //huoneMatriisi[a][b] kertoo aukeaako huone b kun a aukeaa.
    private boolean[][] huoneMatriisi;
    //huonetaulukko[a][b] = onko ruudussa seinä, vai huonetta (seinä = 0, huone = huoneen numero)
    private int[][] huonetaulukko;

    public ReittienEtsija(Labyrintti parametri) {
        this.l = parametri;
        this.huoneLista = l.getHuoneet();
        this.avaintenMaara = l.getAvaintenMaara();
        this.sana = new Kirjainpino();

        this.onkoAvainTutkittu = new boolean[avaintenMaara];
        this.onkoOviAuki = new boolean[avaintenMaara];
        this.onkoHuoneSaavutettu = new boolean[l.getHuoneidenMaara() + 1];
        this.avaimetTarjolla = new int[l.getAvaintenMaara()];
        this.huonetaulukko = l.getHuoneTaulukko();
        this.huoneMatriisi = new boolean[l.getHuoneidenMaara() + 1][l.getHuoneidenMaara() + 1];
        Arrays.fill(onkoAvainTutkittu, Boolean.FALSE);
        Arrays.fill(onkoOviAuki, Boolean.FALSE);
        Arrays.fill(onkoHuoneSaavutettu, Boolean.FALSE);
        Arrays.fill(avaimetTarjolla, 0);
        for (int i = 0; i <= l.getHuoneidenMaara(); i++) {
            Arrays.fill(huoneMatriisi[i], false);
        }
        onkoHuoneSaavutettu[1] = true;
    }

    /**
     * Luokka etsii annetusta labyrintistä kaikki mahdolliset järjestykset,
     * joissa avaimia voi poimia, siten että päädytään maaliin.
     */
    public String[] etsi() {
        this.mahdollisetReitit = 0;
        //Taulukko, joka sisältää mahdolliset avainjärjestykset merkkijonona
        //muodossa abcdefg. Taulukossa on oltava tilaa n! missä n = avainten 
        //määrä, sillä pahimmillaan palautettavia avainlistoja voi olla 
        //juuri niin monta
        this.avainLista = new String[kertoma(l.getAvaintenMaara())];

        etsiReitteja();
        return avainLista;
    }

    /**
     * Luokka käynnistää mahdollisten avainjärjestysten rekursiivisen haun
     */
    private void etsiReitteja() {
        Avain a;
        for (int i = 0; i < l.getHuoneet()[1].getAvaintenMaara(); i++) {
            a = l.getHuoneet()[1].getAvaimet()[i];
            if (a.getKirjain() == '@') {
                continue;
            }
            //System.out.println((int) a.getKirjain() - 97);
            //System.out.println(avaintenMaara);
            avaimetTarjolla[(int) a.getKirjain() - 97]++;
        }
        hajaannu();
    }

    /**
     * Luokka "hajauttaa" rekursiivista hakua, luoden uuden suorituspolun
     * jokaisela vielä huoneessa h olevalle noutamattomalle avaimelle
     */
    private void hajaannu() {
        System.out.println("\nHAJAANNU\n");
        int huoneAlku;
        int huoneLoppu;
        int kirjaimenArvo;
        Ovi oviX;
        Avain avainX;

        //boolean muuttuukoOnkoAvainTutkittuLopussa;
        for (int i = 0; i < l.getAvaintenMaara(); i++) {
            //muuttuukoOnkoAvainTutkittuLopussa = true;
            if (avaimetTarjolla[i] > 0 && !onkoAvainTutkittu[i]) {

                Avain a = l.getAvaimet()[i];
                if (a.getKirjain() == '@') {
                    return;
                }

                kirjaimenArvo = (int) a.getKirjain() - 97;

                /*if (avaimetTarjolla[kirjaimenArvo] > 0) {
                    muuttuukoOnkoAvainTutkittuLopussa = false;
                }*/
                onkoAvainTutkittu[kirjaimenArvo] = true;
                onkoOviAuki[kirjaimenArvo] = true;
                oviX = l.getOvet()[kirjaimenArvo];
                huoneAlku = huonetaulukko[oviX.getAlkuX()][oviX.getAlkuY()];
                huoneLoppu = huonetaulukko[oviX.getLoppuX()][oviX.getLoppuY()];

                sana.add(a.getKirjain());
                System.out.println("YhdistaHuoneet ------------- ALKU // sana: " + sana.toString());
                YhdistaHuoneet(huoneAlku, huoneLoppu);

                System.out.println("YhdistaHuoneet ------------- LOPPU // sana: " + sana.toString());
                sana.pop();

                onkoOviAuki[kirjaimenArvo] = false;
                //if (true) {
                onkoAvainTutkittu[kirjaimenArvo] = false;
                //}
            }
        }
    }

    /**
     * Metodi aloittaa huoneiden yhdistämisen. Prosessi on melko erilainen
     * riippuen siitä onko huoneita a ja b jo yhdistetty vai ei. Tilanteen
     * mukaan kutsutaan metodeja pakohuone.tyokalut.ReittienEtsija.OnJaEi ja
     * pakohuone.tyokalut.ReittienEtsija.EiJaEi, joilla on erilaiset
     * toiminnallisuudet.
     *
     * @param a Ensimmäinen kahdesta yhdistettävästä huoneesta, joiden välistä
     * on avattu ovi.
     * @param b Toinen kahdesta yhdistettävästä huoneesta, joiden välistä on
     * avattu ovi.
     */
    private void YhdistaHuoneet(int a, int b) {

        if (leveyshaku.hae(this.huoneMatriisi)) {
            avainLista[mahdollisetReitit] = sana.toString();
            mahdollisetReitit++;
            System.out.println("\n#####\nLÖYTYI UUSI REITTI: " + sana.toString() + "\n#####\n");
            return;
        }
        if (onkoHuoneSaavutettu[a] && onkoHuoneSaavutettu[b]) {
            System.out.println(a + " oli saavutettu, " + b + " oli saavutettu");
            huoneidenLinkitys(a, b);
            hajaannu();
            huoneidenIrrotus(a, b);
            return;
        }
        if (onkoHuoneSaavutettu[a] && !onkoHuoneSaavutettu[b]) {
            System.out.println(a + " oli saavutettu, " + b + " ei ollut saavutettu");
            huoneidenLinkitys(a, b);
            OnJaEi(a, b);
            huoneidenIrrotus(a, b);
            return;
        }
        if (!onkoHuoneSaavutettu[a] && onkoHuoneSaavutettu[b]) {
            System.out.println(b + " oli saavutettu, " + a + " ei ollut saavutettu");
            huoneidenLinkitys(a, b);
            OnJaEi(b, a);
            huoneidenIrrotus(a, b);
            return;
        }
        if (!onkoHuoneSaavutettu[a] && !onkoHuoneSaavutettu[b]) {
            System.out.println(a + " ei ollut saavutettu, " + b + " ei ollut saavutettu");
            huoneidenLinkitys(a, b);
            EiJaEi(a, b);
            huoneidenIrrotus(a, b);
        }
    }

    /**
     * Metodi yhdistää kaksi huonetta, joista toisessa on jo vierailtu ja
     * aloittaa sitte rekursion uudelleen tilanteesta, jossa huoneet on jo
     * yhdistetty. Kun rekursio on suoritettu loppuun, palautetaan huoneiden
     * sisällöt ja tiedot niissä vierailemisesta tilaan, jossa oltiin ennen
     * huoneiden yhdistämistä
     *
     * @param a Jo vieraillun huoneen numero
     * @param b Huoneeseen a "yhdistettävän huoneen numero"
     */
    private void OnJaEi(int a, int b) {
        System.out.println("ONJAEI ALKU (" + a + "," + b + ")");
        onkoHuoneSaavutettu[b] = true;
        for (int i = 0; i < huoneLista[b].getAvaintenMaara(); i++) {
            avaimetTarjolla[(int) huoneLista[b].getAvaimet()[i].getKirjain() - 97]++;
        }
        hajaannu();
        //Poista saavutetuista avaimista äsken lisätyt avaimet
        System.out.println("ONJAEI LOPPU (" + a + "," + b + ")");
        onkoHuoneSaavutettu[b] = false;
        for (int i = 0; i < huoneLista[b].getAvaintenMaara(); i++) {
            avaimetTarjolla[(int) huoneLista[b].getAvaimet()[i].getKirjain() - 97]--;
        }

    }

    private void EiJaEi(int a, int b) {
        System.out.println("EIJAEI ALKU (" + a + "," + b + ")");
        int aSaiYlimaaraisiaAvaimia = 0;
        int bSaiYlimaaraisiaAvaimia = 0;
        int aSaiYlimaaraisiaOvia = 0;
        int bSaiYlimaaraisiaOvia = 0;

        for (int i = 0; i < huoneLista[a].getAvaintenMaara(); i++) {
            huoneLista[b].lisaaAvain(huoneLista[a].getAvaimet()[i]);
            bSaiYlimaaraisiaAvaimia++;
            //System.out.println("B SAI AVAIMEN " + huoneLista[a].getAvaimet()[i].toString());
        }
        for (int i = 0; i < huoneLista[b].getAvaintenMaara() - bSaiYlimaaraisiaAvaimia; i++) {
            huoneLista[a].lisaaAvain(huoneLista[b].getAvaimet()[i]);
            aSaiYlimaaraisiaAvaimia++;
            //System.out.println("A SAI AVAIMEN " + huoneLista[b].getAvaimet()[i].toString());
        }
        for (int i = 0; i < huoneLista[a].getOvienMaara(); i++) {
            huoneLista[b].lisaaOvi(huoneLista[a].getOvet()[i]);
            bSaiYlimaaraisiaOvia++;
            //System.out.println("B SAI OVEN " + huoneLista[a].getOvet()[i].toString());
        }
        for (int i = 0; i < huoneLista[b].getOvienMaara() - bSaiYlimaaraisiaOvia; i++) {
            huoneLista[a].lisaaOvi(huoneLista[b].getOvet()[i]);
            aSaiYlimaaraisiaOvia++;
            //System.out.println("A SAI OVEN " + huoneLista[b].getOvet()[i].toString());
        }

        hajaannu();

        //Palautetaan tilanne sellaiseksi kuin se oli tätä metodia kutsuttaessa
        System.out.println("EIJAEI LOPPU (" + a + "," + b + ")");
        for (int i = 0; i < bSaiYlimaaraisiaAvaimia; i++) {
            huoneLista[b].poistaAvain();
        }
        for (int i = 0; i < aSaiYlimaaraisiaAvaimia; i++) {
            huoneLista[a].poistaAvain();
        }
        for (int i = 0; i < bSaiYlimaaraisiaOvia; i++) {
            huoneLista[b].poistaAvain();
        }
        for (int i = 0; i < aSaiYlimaaraisiaOvia; i++) {
            huoneLista[a].poistaAvain();
        }
    }

    private void huoneidenLinkitys(int a, int b) {
        huoneMatriisi[a][b] = true;
        huoneMatriisi[b][a] = true;
    }

    private void huoneidenIrrotus(int a, int b) {
        huoneMatriisi[a][b] = false;
        huoneMatriisi[b][a] = false;
    }
    
    private boolean onkoMaaliSaavutettavissa(){
        return leveyshaku.hae(huoneMatriisi);
    }

    public String[] getAvainLista() {

        String[] palautus = new String[mahdollisetReitit + 1];
        for (int i = 0; i <= mahdollisetReitit; i++) {
            palautus[i] = avainLista[i];
        }
        return palautus;
    }

    /**
     * Luokka palauttaa parametrina annetun luvun kertoman luku!
     *
     * @param luku Luku, jonka kertoma palautetaan
     * @return parametrin luku kertoma
     */
    private int kertoma(int luku) {
        for (int i = luku - 1; i > 0; i--) {
            luku = luku * i;
        }
        return luku;
    }

}
