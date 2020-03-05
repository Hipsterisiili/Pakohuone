package pakohuone.tyokalut;

import java.util.Arrays;
    /**
     * Dijkstra on olio, joka sisältää toiminnallisuuden dijkstran algoritmin
     * toteuttamiseen verkossa. Se tutkii int[][] taulukkoa verkkona.
     * Verkossa sekä pysty- että vaaka-akselilla lukuarvot kuvaavat labyrintissa 
     * seuraavaa:
     * 0 = lähtöruutu
     * 1 - avaintenMaara = avaimet a, b, c ...
     * (avaintenmaara + 1) - ((avaintenmaara * 2) +  1) = ovet A, B, C ...
     * (avaintemaara * 2) + 2 = maaliruutu
     * esim verkko[0][4] voi kuvata esim matkaa lähtöruudun ja d:n välillä.
     */
public class Dijkstra {

    /** Mistä alkaen etäisyyttä selvitetään. */
    private int alku;
    /** Mihin asti etäisyyttä selvitetään. */
    private int loppu;
    /** Onko labyrintissä nyt mahdollista päästä tähän kohteeseen. */
    private boolean[] onkoYhteysKaytossa;
    /** Mikä on lyhin tähän asti löydetty matka kohteeseen. */
    private long[] lyhinMatka;
    /** Matriisi, joka kuvaa verkkoa, jossa etäisyyksiä selvitetään. */
    private int[][] verkko;
    /** kasitelty[i] = onko lyhin reitti i:hin jo selvitetty lopullisesti. */
    private boolean[] kasitelty;
    
    /**
     * Dijkstran algoritmin toteuttavan olion konstruktori.
     * @param int[][] matriisi = kaksiulotteinen taulukko joka sisältää tiedon 
     * siitä minkä avainten ja ovien välillä on minkäkin pitkä yhteys.
     */
    public Dijkstra(int[][] matriisi) {
        this.verkko = matriisi;
        this.lyhinMatka = new long[matriisi[0].length];
        this.kasitelty = new boolean[matriisi[0].length];
    }

    /**
     * Metodi aloittaa Dijkstran algoritmin mukaisen rekursiivisen haun
     * verkossa.
     * @param int a = Mistä alkaen etsitään reittiä.
     * @param int b = Mihin a:sta etsitään reittiä.
     * @param taul = mihin kohteisiin on tällä hetkellä vapaa pääsy.
     * @return long matka = mikä on lyhin matka a:n ja b:n välillä.
     */
    public long hae(int a, int b, boolean[] taul) {
        this.alku = a;
        this.loppu = b;
        this.onkoYhteysKaytossa = taul;
        Arrays.fill(lyhinMatka, Integer.MAX_VALUE);
        Arrays.fill(kasitelty, false);
        lyhinMatka[a] = 0;
        kasitelty[a] = true;

        return rekursio(a, b);
    }

    /**
     * Metodi toteuttaa Dijkstran algoritmin mukaisen rekursion muutamilla
     * muutoksilla perustoimintaan.
     *    ->Aluksi selvitetään lyhimmät matkat lähtöposteestä a
     *    ->Seuraavaksi tarkastetaan mikä kohde on lähimpänä a:ta tällä hetkellä. 
     *    ->Mikäli etäisyyttä johonkin kohteeseen voi pienentää kulkemalla 
     *      a:n kautta, sitä pienennetään
     *    ->Lopulta tämän a:ta lähimmän kohteen arvo lukitaan muuttamalla sitä
     *      vastaavaa arvoa taulukossa boolean[] kasitelty.
     *    ->Selvitetään mihin nyt on lyhin matka a:sta ja tehdään vaiheet uudelleen
     *      Jos ei ole sellaisia kohteita, joihin matkaa voi vielä lyhentää,
     *      lopetetaan metodin suoritus ja palautetaan int[] lyhinMatka:an 
     *      talletettu tieto lyhimmästä matkasta
     * 
     * @param int a = Mistä alkaen etsitään reittiä.
     * @param int b = Mihin a:sta etsitään reittiä.
     * @return long matka = mikä on lyhin matka a:n ja b:n välillä.
     */
    private long rekursio(int a, int b) {
        // Sijoitetaan lähtöpisteestä alkavat etäisyydet verkkoon
        for (int i = 0; i < verkko[0].length; i++) {
            if (verkko[a][i] > 0) {
                lyhinMatka[i] = verkko[a][i];
            }
        }
        kasitelty[a] = true;

        long nyky;
        long uusi;
        // lahimpana = kohde, johon tällä hetkellä on lyhin matka
        int lahimpana = mihinOnPieninMatka();
        // Sijoitetaan loput etäisyydet
        while (lahimpana != -1) {
            if (lahimpana == b) {
            // Tälle riville ei pitäisi olla mahdollista joutua, sillä metodin
            // suoritus katkeaa normaalikäytössä jo whilen ehdon muututtua
            // Koodi on tallessa odottamatonta virhetilannetta varten.
                break;
            }
            kasitelty[lahimpana] = true;
            
            for (int i = 0; i < verkko[0].length; i++) {
                if (verkko[i][lahimpana] != 0) {
                    nyky = lyhinMatka[i];
                    uusi = lyhinMatka[lahimpana] + verkko[i][lahimpana];
                    if (uusi < nyky) {
                        lyhinMatka[i] = uusi;
                    }
                }
            }
            lahimpana = mihinOnPieninMatka();
        }
        return lyhinMatka[b];
    }

        /**
     * Metodi selvittää mitä solmua rekursion tulee tutkia seuraavaksi.
     * Tämä solmu on se, johon on lyihn matka ja toteuttaa sewuraavat ehdot:
     * Solmuun on pääsy labyrintissa
     * Lyhintä matkaa solmuun ei ole vielä selvitetty lopullisesti.
     * 
     * @return int solmu = Mitä solmua rekursion tulee tutkia seuraavaksi.
     */
    private int mihinOnPieninMatka() {
        int palautus = -1;
        long pieninMatka = Long.MAX_VALUE;

        for (int i = 0; i < lyhinMatka.length; i++) {
            if (lyhinMatka[i] < pieninMatka
                    && onkoYhteysKaytossa[i]
                    && !kasitelty[i]) {

                pieninMatka = lyhinMatka[i];
                palautus = i;
            }
        }
        return palautus;
    }

}
