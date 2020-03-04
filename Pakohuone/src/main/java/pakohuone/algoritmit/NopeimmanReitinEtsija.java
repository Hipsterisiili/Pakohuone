package pakohuone.algoritmit;

import java.util.Arrays;
import pakohuone.sovelluslogiikka.Labyrintti;
import pakohuone.tyokalut.Verkko;

/**
 * Olio tarkastelee löydettyjä maaliin johtavia reittejä labyrintissä ja
 * selvittää mikä niistä on lyhin.
 */
public class NopeimmanReitinEtsija {
    /**Taulukko, joka sisältää löytyneet reitit merkkijonoina*/
    private String[] kaikkiReitit;
    /** Labyrintti, jota tutkitaan*/
    private Labyrintti laby;
    /** Verkko, joka labyrintista luodaan*/
    Verkko v;
    /** Nopein tähän asti löydetty reitti*/
    private String nopeinReitti = "Nopeinta reittiä ei ole vielä selvitetty";
    /** Nopeimman tähän asti löydetyn reitin pituus*/
    private long nopeimmanReitinPituus;

    /**
     * NopeimmanReitinEtsijan konstruktori
     *
     * @param lista = kaikki maaliin johtavat tavat poimia avaimia
     * merkkijonomuodossa
     * @param l = labyrintti, jota tutkitaan
     */
    public NopeimmanReitinEtsija(String[] lista, Labyrintti l) {
        this.kaikkiReitit = lista;
        this.laby = l;
        v = new Verkko(laby);
        //this.huoneMatriisi = new int[laby.getHuoneidenMaara() + 1][laby.getHuoneidenMaara() + 1];
    }

    /**
     * Metodi käy for-loopissa läpi löytyneitä reittejä ja käynnistää jokaisen
     * kohdalla haun, joka tarkastelee kuinka pitkä tämä reitti on.
     */
    public String laskeNopeinReitti() {

        nopeimmanReitinPituus = Long.MAX_VALUE;
        long temp;

        for (String reitti : kaikkiReitit) {
            temp = tutkiOnkoNopein(reitti);
            if (temp < nopeimmanReitinPituus) {
                nopeinReitti = reitti;
                nopeimmanReitinPituus = temp;
            }
        }
        nopeinReitti = "\"" + nopeinReitti + "\" \npituus = " + nopeimmanReitinPituus;
        return nopeinReitti;
    }

    /**
     * Metodi tutkii parametrina annettavaa reittiä ja jos se on uusi lyhin
     * reitti, palautetaan sen pituus. Mikäli huomataan että reitin kulkeminen
     * vaatii enemmän askelia kuin lyhin reitti tähän asti, keskeytetään reitin
     * kulkeminen ja palautetaan vain luku, joka on nykyistä lyhintä reittiä
     * pitempi
     *
     * @param reitti = tutkittavan reitin avainlista merkkijonomuodossa esim.
     * abcd
     * @return tamanPituus = kuinka pitkä annettu reitti on kulkea. Jos reitti
     * on pitempi kuin lyhin jo löytynyt reitti, palautetaan vain tähän asti
     * kuljetun reitin pituus, jotta "laskeNopeinReitti" osaa unohtaa reitin.
     */
    private long tutkiOnkoNopein(String reitti) {
        //System.out.println("Tutkitaan reitti " + reitti);
        if (reitti == null) {
            return Long.MAX_VALUE;
        }
        for (int i = 0; i < v.getLeveys(); i++) {
            v.suljeYhteys(i);
        }
        v.avaaYhteyksia(0);

        //System.out.println("\ntutkiOnkoNopein käy /// sana: " + reitti +  "\n");
        // tamanPituus = matkan pituus tällä hetkellä
        long tamanPituus = 0;
        // matkanAlku = mistä kirjaimesta alkavaa matkaa tutkitaan
        // (1 = a, 2 = b jne)
        int matkanAlku;
        // matkanLopunIndeksi = mihin kirjaimeen päättyvää matkaa tutkitaan 
        // (0 = sanan ensimmäinen kirjain, 1 = sanan toinen kirjain)
        int matkanLopunIndeksi = 0;
        // matkanLoppu = mistä kirjaimesta alkavaa matkaa tutkitaan
        // (1 = a, 2 = b jne)
        int matkanLoppu;

        // KUN AVATAAN UUSI OVI, AVATAAN SEURAAVAT REITIT:
        // 1) KAIKKI reitit jotka alkavat uuden huoneen avaimista
        // 2) KAIKKI reitit jotka alkavat juuri avatusta ovesta
        tamanPituus = v.etsiReitti(0, (int) reitti.charAt(0) - 96);
        //System.out.println(tamanPituus);

        v.avaaYhteyksia((int) reitti.charAt(matkanLopunIndeksi) - 96);
        v.avaaYhteyksia((int) reitti.charAt(matkanLopunIndeksi) - 96 + laby.getAvaintenMaara());

        while (tamanPituus < nopeimmanReitinPituus) {
            //System.out.println("tutkionkonopein while");

            matkanAlku = (int) reitti.charAt(matkanLopunIndeksi) - 96;
            matkanLopunIndeksi++;
            if (matkanLopunIndeksi >= reitti.length()) {
                tamanPituus += v.etsiReitti(matkanAlku, (1 + laby.getAvaintenMaara() + laby.getAvaintenMaara()));
                //System.out.println("+ " + v.etsiReitti(matkanAlku, (1 + laby.getAvaintenMaara() + laby.getAvaintenMaara())));
                break;
            }
            matkanLoppu = (int) reitti.charAt(matkanLopunIndeksi) - 96;

            //Etsi lyhin etäisyys nykysijainnista seuraavaan kirjaimeen
            //System.out.println("");
            //System.out.println("+ " +v.etsiReitti(matkanAlku, matkanLoppu));
            tamanPituus += v.etsiReitti(matkanAlku, matkanLoppu);
            v.avaaYhteyksia(matkanLoppu);
            v.avaaYhteyksia(matkanLoppu + laby.getAvaintenMaara());

        }

        /*System.out.print("REITTI " + reitti + " KÄSITELTY! ");
        if(tamanPituus < nopeimmanReitinPituus){
            System.out.println(" Uusi nopein reitti, pituus: " + tamanPituus +"\n");
        } else {
            System.out.println(" Reitti oli pitempi kuin " + nopeimmanReitinPituus + "\n");
        }
         */
        return tamanPituus;
    }
}
