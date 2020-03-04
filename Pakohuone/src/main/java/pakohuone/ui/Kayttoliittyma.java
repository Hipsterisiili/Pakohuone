package pakohuone.ui;

import java.util.Scanner;
import pakohuone.sovelluslogiikka.Labyrintti;

public class Kayttoliittyma {

    // laby = Tällä hetkellä tarkasteltavissa oleva labyrintti
    private Labyrintti laby;
    // automaatti = olio joka luo ennaltamuotoiltuja labyrintteja
    private LabyrintinLuoja automaatti = new LabyrintinLuoja();
    // rakennus = olio jonka avulla käyttäjä voi luoda omia labyrintteja
    private LabyrintinRakennus rakennus = new LabyrintinRakennus();
    // reititEtsitty = onko labyrintista vielä etsitty kaikki mahdolliset reitit
    private boolean reititEtsitty = false;
    // keskeytys = pyöritetäänkö käyttöliittymää vielä vai onko keskeytys käsketty
    private boolean keskeytys = false;
    // halutaankoAikaIlmoituksia = tulostetaanko algoritmin suorituksen jälkeen kulunut aika
    private boolean halutaanAikaIlmoituksia = false;
    // loppu, alku ovat arvoja joiden avulla aikavaativuuksia arvioidaan
    private long alku;
    private long loppu;
    // lukija = Scanner, jolla luetaan käyttäjältä merkkijonosyötteitä.
    private Scanner lukija = new Scanner(System.in);

    /**
     * Kayttoliittyma on luokka, joka toimii rajapintana sovelluksen laskennan
     * ja käyttäjän välillä. Käyttäjä kontrolloi sovelluksen toimintaa antamalla
     * näppäimistöllä numeroita Käyttoliittyman lukija-Scannerille
     */
    public Kayttoliittyma() {

    }

    /**
     * Ajetaan käyttöliittymää. Luokka tarkastelee käyttäjän antamia komentoja
     * ja aloittaa niitä vastaavat toimenpiteet.
     */
    public void aja() {

        uusiLabyrintti();

        String komento;
        while (!keskeytys) {
            System.out.print("1 = luo\n"
                    + "2 = tulosta\n"
                    + "3 = etsi reitit\n"
                    + "4 = etsi paras reitti\n"
                    + "5 = aikavaativuusilmoitukset päälle/pois\n"
                    + "6 = keskeytä\n > ");
            komento = lukija.nextLine();
            if (komento.equals("1")) {
                uusiLabyrintti();
            } else if (komento.equals("2")) {
                labyrintinTulostus();
            } else if (komento.equals("3")) {
                reittienEtsinta();
            } else if (komento.equals("4")) {
                lyhimmanReitinEtsinta();
            } else if (komento.equals("5")) {
                flipHalutaanAikailmoituksia();
            } else if (komento.equals("6")) {
                lopetus();
            } else {
                vaaranlainenKomento("1, 2, 3, 4, 5, 6");
            }
        }
    }

    /**
     * Kun luokassa aja() annettiin komento 1: Kysytään käyttäjältä minkälainen
     * labyrintti luodaan. Vaihtoehdot 1-5 ovat valmiita
     * "LabyrintinLuoja"-luokassa rakennettavia labyrintteja. Vaihtoehto 6
     * aloittaa labyrintin rakentamisen käsin käyttäjän toimesta
     */
    private void uusiLabyrintti() {
        System.out.print("Minkälainen labyrintti luodaan?\n"
                + "1 - 5 = valmiita labyrinttejä\n"
                + "6 = luo oma labyrintti\n > ");
        reititEtsitty = false;

        String komento;
        while (true) {
            komento = lukija.nextLine();
            if (onkoNumero(komento)) {
                if (komento.equals("1")) {
                    alku = System.nanoTime();
                    laby = automaatti.LuoLabyrintti1();
                    loppu = System.nanoTime();
                    break;
                } else if (komento.equals("2")) {
                    alku = System.nanoTime();
                    laby = automaatti.LuoLabyrintti2();
                    loppu = System.nanoTime();
                    break;
                } else if (komento.equals("3")) {
                    alku = System.nanoTime();
                    laby = automaatti.LuoLabyrintti3();
                    loppu = System.nanoTime();
                    break;
                } else if (komento.equals("4")) {
                    alku = System.nanoTime();
                    laby = automaatti.LuoLabyrintti4();
                    loppu = System.nanoTime();
                    break;
                } else if (komento.equals("5")) {
                    alku = System.nanoTime();
                    laby = automaatti.LuoLabyrintti5();
                    loppu = System.nanoTime();
                    break;
                } else if (komento.equals("6")) {
                    alku = System.nanoTime();
                    laby = rakennus.aja();
                    loppu = System.nanoTime();
                    break;
                } else {
                    vaaranlainenKomento("1, 2, 3, 4, 5, 6");
                }
            }
        }
        aikavaativuus();
    }

    /**
     * Kun metodissa aja() annettiin komento 2: Kysytään käyttäjältä
     * tulostetaanko koko labyrintti vai annetaanko raportti huoneiden
     * nimeämisestä (periaatteessa huondien nimet on käyttäjälle turhaa tietoa,
     * mutta tulostus toimii todisteena sille, että HuoneidenEtsinta.etsi() on
     * tehty labyrinttiä luodessa.
     */
    private void labyrintinTulostus() {
        System.out.print("Tulostetaanko labyrintti (1) vai huoneet (2)?\n > ");
        String komento;
        while (true) {
            komento = lukija.nextLine();
            if (onkoNumero(komento)) {
                if (komento.equals("1")) {
                    System.out.println(laby.tulostaLabyrintti());
                    return;
                } else if (komento.equals("2")) {
                    System.out.println(laby.tulostaHuoneet());
                    return;
                } else {
                    vaaranlainenKomento("1, 2");
                }
            }
        }
    }

    /**
     * Kun metodissa aja() annettiin komento 3: Kysytään käyttäjältä
     * tulostetaanko maaliin johtavien reittien määrä vai tulostetaanko reitit
     * maaliin ja tämän jälkeen etsitään ja tulostetaan mitä käyttäjä pyysi
     */
    private void reittienEtsinta() {
        reititEtsitty = true;
        System.out.print("Reittien määrä (1) vai kaikki reitit (2)? "
                + "\n(huom. 2 vaatii enemmän aikaa)\n > ");

        String teksti;
        String komento;
        while (true) {
            komento = lukija.nextLine();
            if (onkoNumero(komento)) {
                if (komento.equals("1")) {
                    alku = System.nanoTime();
                    teksti = ("Labyrintistä löytyy " + laby.etsiReitit() + " reittiä");
                    loppu = System.nanoTime();
                    System.out.println(teksti);
                    aikavaativuus();
                    return;
                } else if (komento.equals("2")) {
                    alku = System.nanoTime();
                    teksti = ("Labyrintistä löytyy seuraavat reitit:\n" + laby.etsiJaTulostaReitit());
                    loppu = System.nanoTime();
                    System.out.println(teksti);
                    aikavaativuus();
                    return;
                } else {
                    vaaranlainenKomento("1, 2");
                }
            }
        }
    }

    /**
     * Kun metodissa aja() annettiin komento 4: Etsitään ja tulostetaan lyhin
     * reitti labyrintista. Jos reittejä ei ole vielä etsitty alkuunkaan,
     * selvitetään ensin kaikki reitit. Tämä ei vaikuta aikavaativuusraporttiin.
     */
    private void lyhimmanReitinEtsinta() {
        if (!reititEtsitty) {
            laby.etsiReitit();
        }
        System.out.println("Lyhin reitti on:");
        alku = System.nanoTime();
        System.out.println(laby.etsiParasReitti());
        loppu = System.nanoTime();
        aikavaativuus();
    }

    /**
     * Kun metodissa aja() annettiin komento 5: Jos aikavaativuusraportit olivat
     * päällä, laitetaan ne toisin päin Jos ne olivat pois päältä, laitetaan ne
     * päälle.
     */
    private void flipHalutaanAikailmoituksia() {
        if (halutaanAikaIlmoituksia) {
            System.out.println("Aikavaativuusraportit pois päältä");
        } else {
            System.out.println("Aikavaativuusraportit päällä");
        }
        halutaanAikaIlmoituksia = !halutaanAikaIlmoituksia;
    }

    /**
     * Kun metodissa aja() annettiin komento 6: Lopetetaan käyttöliittymän
     * ajaminen.
     */
    private void lopetus() {
        System.out.println("Lopetetaan ohjelman suoritus");
        keskeytys = true;
    }

    /**
     * Kun jossakin metodissa on annettu kelvoton komento. Tulostetaan mitkä
     * komennot ovat sallittuja tässä vaiheessa
     *
     * @param String komennot = sallitut komennot täsmennettynä merkkijonona.
     */
    private void vaaranlainenKomento(String komennot) {
        System.out.println("Sallitut komennot: " + komennot + "\n > ");
    }

    /**
     * Kutsutaan kunkin algoritmin toeutuksen jälkeen Jos
     * aikavaativuusilmoitukset on päällä, tulostetaan viimeisimpään operaatioon
     * kulunut aika.
     */
    private void aikavaativuus() {
        if (halutaanAikaIlmoituksia) {
            System.out.println("AIKAA OPERAATIOON KULUI: " + (((double) (loppu - alku)) / 1000000000) + "sekuntia");
        }
    }

    /**
     * Kun käyttäjä on antanut jonkin komennon
     * Tarkastetaan onko annettu komento tulkittavissa (eli onko se numero)
     *
     * @param String sana = tarkasteltava sana.
     * @ret Boolean totuus = oliko sana muutettavissa lukuarvoksi
     */
    private static boolean onkoNumero(String sana) {
        if (sana == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(sana);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
