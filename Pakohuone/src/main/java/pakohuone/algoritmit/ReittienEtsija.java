package pakohuone.algoritmit;

import pakohuone.tyokalut.Syvyyshaku;
import pakohuone.tyokalut.Kirjainpino;
import java.util.Arrays;
import pakohuone.sovelluslogiikka.Labyrintti;
import pakohuone.sovelluslogiikka.Avain;
import pakohuone.sovelluslogiikka.Huone;
import pakohuone.sovelluslogiikka.Ovi;

/**
 * Luokan tehtävä on selvittää kaikki mahdolliset järjestykset, jossa avaimet
 * voi poimia ja muodostaa niistä järjestyksistä merkkijonoja muodossa abcdefg
 */
public class ReittienEtsija {

    private Labyrintti l;
    private int korkeus;
    private int leveys;
    private Huone[] huoneLista;
    private String[] avainLista;
    private int mahdollisetReitit = 0;
    private int avaintenMaara;
    private Kirjainpino sana;
    private Syvyyshaku syvyyshaku = new Syvyyshaku();

    //onkoAvainTutkittu[n] = onko kirjain n jo merkkijonossa
    private boolean[] onkoAvainTutkittu;
    //onkoOviAuki[n] = onko ovea n vastaava avain jo poimittu
    private boolean[] onkoOviAuki;
    //onkoHuoneSaavutettu[n] = onko huone n nyt auki olevassa osassa labyrinttia
    //private boolean[] onkoHuoneSaavutettu;
    //avaimetTarjolla[n] > 0 -> Avain on tällä hetkellä auki olevassa osassa labyrinttia
    private int[] avaimetTarjolla;
    //huoneMatriisi[a][b] kertoo aukeaako huone b kun a aukeaa.
    private int[][] huoneMatriisi;
    //huonetaulukko[a][b] = onko ruudussa seinä, vai huonetta (seinä = 0, huone = huoneen numero)
    private int[][] huonetaulukko;

    public ReittienEtsija(Labyrintti parametri) {
        this.l = parametri;
        this.korkeus = l.getKorkeus();
        this.leveys = l.getLeveys();
        this.huoneLista = l.getHuoneet();
        this.avaintenMaara = l.getAvaintenMaara();
        this.sana = new Kirjainpino();

        this.onkoAvainTutkittu = new boolean[avaintenMaara];
        this.onkoOviAuki = new boolean[avaintenMaara];
        //this.onkoHuoneSaavutettu = new boolean[l.getHuoneidenMaara() + 1];
        this.avaimetTarjolla = new int[l.getAvaintenMaara()];
        this.huonetaulukko = l.getHuoneTaulukko();
        this.huoneMatriisi = new int[l.getHuoneidenMaara() + 1][l.getHuoneidenMaara() + 1];
        Arrays.fill(onkoAvainTutkittu, Boolean.FALSE);
        Arrays.fill(onkoOviAuki, Boolean.FALSE);
        //Arrays.fill(onkoHuoneSaavutettu, Boolean.FALSE);
        Arrays.fill(avaimetTarjolla, 0);
        for (int i = 0; i <= l.getHuoneidenMaara(); i++) {
            Arrays.fill(huoneMatriisi[i], 0);
        }
        //onkoHuoneSaavutettu[1] = true;
    }

    /**
     * Luokka etsii annetusta labyrintistä kaikki mahdolliset järjestykset,
     * joissa avaimia voi poimia, siten että päädytään maaliin.
     *
     * @ret avainLista = taulukko merkkijonoja, jotka kuvaavat mahdollisia
     * ratkaisuja labyrintissa
     */
    public String[] etsi() {
        this.mahdollisetReitit = 0;
        //Taulukko, joka sisältää mahdolliset avainjärjestykset merkkijonona
        //muodossa abcdefg. Taulukossa on oltava tilaa n! missä n = avainten 
        //määrä, sillä pahimmillaan palautettavia avainlistoja voi olla 
        //juuri niin monta
        this.avainLista = new String[montakoAvainJartestystaVoiSyntya(l.getAvaintenMaara())];

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
        //System.out.println("\nHAJAANNU /// sana: " + sana + "\n");
        int huoneAlku;
        int huoneLoppu;
        int kirjaimenArvo;
        Ovi oviX;
        Avain avainX;

        for (int i = 0; i < l.getAvaintenMaara(); i++) {
            //System.out.println("haj kirj: " + i + " OAT: " + onkoAvainTutkittu[i] + " AT: " +avaimetTarjolla[i] );
            if (avaimetTarjolla[i] > 0 && !onkoAvainTutkittu[i]) {
                //System.out.println("TUTKIMATON AVAIN");
                Avain a = l.getAvaimet()[i];
                if (a.getKirjain() == '@') {
                    return;
                }

                kirjaimenArvo = (int) a.getKirjain() - 97;

                onkoAvainTutkittu[kirjaimenArvo] = true;
                onkoOviAuki[kirjaimenArvo] = true;
                oviX = l.getOvet()[kirjaimenArvo];
                huoneAlku = huonetaulukko[oviX.getAlkuX()][oviX.getAlkuY()];
                huoneLoppu = huonetaulukko[oviX.getLoppuX()][oviX.getLoppuY()];

                sana.add(a.getKirjain());
                //System.out.println("YhdistaHuoneet ------------- ALKU // sana: " + sana.toString());
                YhdistaHuoneet(huoneAlku, huoneLoppu);

                //System.out.println("YhdistaHuoneet ------------- LOPPU // sana: " + sana.toString());
                sana.pop();

                onkoOviAuki[kirjaimenArvo] = false;
                onkoAvainTutkittu[kirjaimenArvo] = false;
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
        boolean saavutettuA = syvyyshaku.haeArvolla(huoneMatriisi, a);
        boolean saavutettuB = syvyyshaku.haeArvolla(huoneMatriisi, b);
        //System.out.println("SAAVUTETTUA = " + saavutettuA + " ///// SAAVUTETTUB = " + saavutettuB);

        if (saavutettuA && saavutettuB) {
            //System.out.println(a + " oli saavutettu, " + b + " oli saavutettu");
            huoneidenLinkitys(a, b);
            onkoMaaliSaavutettavissa();
            hajaannu();
        } else if (saavutettuA && !saavutettuB) {
            //System.out.println(a + " oli saavutettu, " + b + " ei ollut saavutettu");
            huoneidenLinkitys(a, b);
            OnJaEi(a, b);
        } else if (!saavutettuA && saavutettuB) {
            //System.out.println(b + " oli saavutettu, " + a + " ei ollut saavutettu");
            huoneidenLinkitys(a, b);
            OnJaEi(b, a);
        } else if (!saavutettuA && !saavutettuB) {
            //System.out.println(a + " ei ollut saavutettu, " + b + " ei ollut saavutettu");
            huoneidenLinkitys(a, b);
            EiJaEi(a, b);
        }
        huoneidenIrrotus(a, b);
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
        //System.out.println("ONJAEI ALKU (" + a + "," + b + ")");
        for (int i = 0; i < huoneLista[b].getAvaintenMaara(); i++) {
            avaimetTarjolla[(int) huoneLista[b].getAvaimet()[i].getKirjain() - 97]++;
        }
        onkoMaaliSaavutettavissa();
        hajaannu();
        //Poista saavutetuista avaimista äsken lisätyt avaimet
        //System.out.println("ONJAEI LOPPU (" + a + "," + b + ")");
        for (int i = 0; i < huoneLista[b].getAvaintenMaara(); i++) {
            avaimetTarjolla[(int) huoneLista[b].getAvaimet()[i].getKirjain() - 97]--;
        }

    }

    private void EiJaEi(int a, int b) {
        //System.out.println("EIJAEI ALKU (" + a + "," + b + ")");

        onkoMaaliSaavutettavissa();
        hajaannu();

        //System.out.println("EIJAEI LOPPU (" + a + "," + b + ")");
    }

    /**
     * Metodi "yhdistää" kaksi huonetta kasvattamalla huoneMatriisi[a][b] arvoa.
     * HUOM: huoneMatriisi[a][b] kertoo montako avointa ovea huoneiden a ja b
     * välillä on
     *
     * @param a = ensimmäisen yhdistettävistä huoneista numero
     * @param b = toisen yhdistettävistä huoneista numero
     */
    private void huoneidenLinkitys(int a, int b) {
        //System.out.println("Yhdistetään " + a + " ja " + b);
        huoneMatriisi[a][b]++;
        huoneMatriisi[b][a]++;
    }

    /**
     * Metodi "erottaa" kaksi huonetta kutistamalla huoneMatriisi[a][b] arvoa.
     * HUOM: huoneMatriisi[a][b] kertoo montako avointa ovea huoneiden a ja b
     * välillä on
     *
     * @param a = ensimmäisen irrotettavista huoneista numero
     * @param b = toisen irrotettavista huoneista numero
     */
    private void huoneidenIrrotus(int a, int b) {
        //System.out.println("Irrotetaan " + a  + " ja  " + b);
        huoneMatriisi[a][b]--;
        huoneMatriisi[b][a]--;
    }

    /**
     * Metodi selvittää onko tällä hetkellä muodostettu kirjainpino sellainen,
     * jonka avulla labyrintti on mahdollista ratkaista.
     *
     * @return onko nykyisillä huoneiden yhdistämisillä mahdollista saavuttaa
     * huone, jossa maali sijaitsee (huone, jonka numero on sama kuin
     * labyrintissä olevien huoneiden määrä)
     */
    private boolean onkoMaaliSaavutettavissa() {
        /*Tässä koodi huonematriisin tulostamiseen
        for(int i = 1 ; i <= huonetaulukko[korkeus-1][leveys-1] ; i++){
            for(int j = 1 ; j <= huonetaulukko[korkeus-1][leveys-1] ; j++){
                System.out.print(huoneMatriisi[i][j] + " ");
            }
            System.out.println("");
        }*/
        //System.out.println(syvyyshaku.hae(this.huoneMatriisi));
        if (syvyyshaku.hae(this.huoneMatriisi)) {
            avainLista[mahdollisetReitit] = sana.toString();
            mahdollisetReitit++;
            //System.out.println("\n#####\nLÖYTYI UUSI REITTI: " + sana.toString() + "\n#####\n");
            return true;
        }
        return false;
    }

    public String[] getAvainLista() {

        String[] palautus = new String[mahdollisetReitit + 1];
        palautus[0] = null;
        for (int i = 0; i <= mahdollisetReitit; i++) {
            palautus[i] = avainLista[i];
        }
        return palautus;
    }

    /**
     * Luokka palauttaa parametrina annetun luvun kertoman. Palautusarvoa
     * käytetään taulukon avainlista koon määrittelemiseen. (kun avaimia on n,
     * pahimmassa mahdollisessa tapauksessa mahdollisia rtkaisuja on n+1!)
     *
     * @param luku = Avainten määrä
     * @return kuinka monta mahdollista avainjärjestystä n avaimesta voi seurata
     */
    private int montakoAvainJartestystaVoiSyntya(int luku) {
        int palautus = 0;
        int juoksija;
        for (int a = 1; a <= luku; a++) {
            juoksija = luku;
            for (int i = luku; i >= a; i--) {
                juoksija = juoksija * i;
            }
            if (palautus + juoksija < 0) {
                return Integer.MAX_VALUE;
            }
            palautus += juoksija;
            //System.out.println("palautus: " + palautus);
        }
        return palautus;
    }

}
