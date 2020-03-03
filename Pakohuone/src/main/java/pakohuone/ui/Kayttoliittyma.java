package pakohuone.ui;

import java.util.Scanner;
import pakohuone.sovelluslogiikka.Labyrintti;

public class Kayttoliittyma {

    private Labyrintti laby;
    private LabyrintinLuoja automaatti = new LabyrintinLuoja();
    private LabyrintinRakennus rakennus = new LabyrintinRakennus();
    private boolean reititEtsitty = false;
    private boolean keskeytys = false;
    private boolean halutaanAikaIlmoituksia = false;
    private long alku;
    private long loppu;
    private Scanner lukija = new Scanner(System.in);

    public Kayttoliittyma() {

    }

    /* 
    1 = Luo uusi labyrintti jota tutkitaan. Tutkittavana voi olla yksi 
    labyrintti kerrallaan, eli se joka on viimeksi luotu.
        1 = valmis1
        2 = valmis2
        3 = valmis3
        4 = valmis4
        5 = rakenna itse
            -> Käynnistä rakennus
    2 = Tulosta labyrintti
        1 = Tulostetaan labyrintin sisältö
        2 = Tulostetaan labyrintin huoneet
    3 = Etsi reitit 
        1 = reitien määrä
        2 = reittien tulostus
    4 = Etsi lyhin reitti
        Tulostetaan lyhin reitti
        Tulostetaan lyhimmän reitin pituus
    5 = Keskeytä
        Ohjelman suoritus päättyy
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
                    teksti = ("Labyrintistä löytyy " + laby.etsiReitit() + "reittiä");
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

    private void flipHalutaanAikailmoituksia() {
        if (halutaanAikaIlmoituksia) {
            System.out.println("Aikavaativuusraportit pois päältä");
        } else {
            System.out.println("Aikavaativuusraportit päällä");
        }
        halutaanAikaIlmoituksia = !halutaanAikaIlmoituksia;
    }

    private void lopetus() {
        System.out.println("Lopetetaan ohjelman suoritus");
        keskeytys = true;
    }

    private void vaaranlainenKomento(String komennot) {
        System.out.println("Sallitut komennot: "+ komennot + "\n > ");
    }

    private void aikavaativuus() {
        if (halutaanAikaIlmoituksia) {
            System.out.println("AIKAA OPERAATIOON KULUI: " + (((double) (loppu - alku)) / 1000000000) + "sekuntia");
        }
    }

    private static boolean onkoNumero(String sana) {
        if (sana == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(sana);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
