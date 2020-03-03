package pakohuone.ui;

import java.util.Scanner;
import pakohuone.sovelluslogiikka.Labyrintti;

public class LabyrintinRakennus {

    //taul = taulukko jota lopulta käytetään palautettavan labyrintin parametrina
    private char[][] taul;
    //korkeus, leveys = taul:n mitat
    private int korkeus;
    private int leveys;
    // avaintenMaara, seinaRuutujenMaara = labyrintin avainten ja seinien määrä
    private int avaintenMaara;
    private int seinaRuutujenMaara;
    // lukija = Scanner joka lukee käyttäjän merkkijonosyötteitä
    private Scanner lukija;
    // keskeytaAvaimenJaOvenLuonti = haluaako käyttäjä keskeyttää avaimen ja oven luonnin
    private boolean keskeytaAvaimenJaOvenLuonti;
    // Arvot, joiden avulla uusia avaimia ja ovia lisätään taul:n
    private int avainX, avainY, oviX, oviY;
    private char avainKirjain, oviKirjain;

    /**
     * LabyrintinRakennus on käyttöliittymäolio, joka ottaa käyttäjältä vastaan
     * merkkijonokomentoja ja rakentaa niiden perusteella labyrintin.
     */
    public LabyrintinRakennus() {
        lukija = new Scanner(System.in);
    }

    /**
     * Metodi pitää labyrinttia rakenavan käyttöliittymän käynnissä kunnes
     * käyttäjä tappaa sen komennolla 3. Komennolla 1 aloitetaan seinän
     * luominen. Komennolla w aloitetaan avaimen ja oven luominen. kunkin
     * komennon toteuttamisen jälkeen tulostetaan miltä labyrintti näyttäisi,
     * jos se luotaisiin nyt.
     */
    public Labyrintti aja() {
        korkeusJaLeveys();
        alustaLabyrintti();
        String komento;
        avaintenMaara = 0;
        seinaRuutujenMaara = 0;

        while (true) {
            System.out.print("Luo seinä(1)\n"
                    + "Luo avain-ovi-pari (2)\n"
                    + "Valmista labyrintti (3)\n > ");
            komento = lukija.nextLine();
            if (onkoNumero(komento)) {
                switch (komento) {
                    case "1":
                        luoSeina();
                        break;
                    case "2":
                        luoAvainJaOvi();
                        break;
                    case "3":
                        return new Labyrintti(taul);
                    default:
                        vaaranlainenKomento("1, 2, 3");
                        break;
                }
                tulosta();
            }
        }
    }

    /**
     * Metodi määrittelee luotavan labyrintin mitat.
     */
    private void korkeusJaLeveys() {
        String komento;
        System.out.print("Mikä on halutun labyrintin korkeus? (min 2, max 50)\n > ");
        while (true) {
            komento = lukija.nextLine();
            if (onkoNumero(komento)) {
                if (Integer.parseInt(komento) >= 2 && Integer.parseInt(komento) <= 50) {
                    korkeus = Integer.parseInt(komento) + 1;
                    break;
                } else {
                    vaaranlainenKomento("2, 3, 4, ..., 49, 50");
                }
            }
        }
        System.out.print("Mikä on halutun labyrintin leveys (min 2, max 50)\n > ");
        while (true) {
            komento = lukija.nextLine();
            if (onkoNumero(komento)) {
                if (Integer.parseInt(komento) >= 2 && Integer.parseInt(komento) <= 50) {
                    leveys = Integer.parseInt(komento) + 1;
                    break;
                } else {
                    vaaranlainenKomento("2, 3, 4, ..., 49, 50");
                }
            }
        }
    }

    /**
     * Luokka luo taulukon char annettujen korkeuden ja leveyden.
     */
    private void alustaLabyrintti() {
        taul = new char[korkeus + 1][leveys + 1];
        for (int i = 1; i < korkeus; i++) {
            for (int j = 1; j < leveys; j++) {
                taul[i][j] = '.';
            }
        }
        for (int i = 0; i < korkeus; i++) {
            taul[i][0] = '#';
            taul[i][leveys] = '#';
        }
        for (int j = 0; j < leveys; j++) {
            taul[0][j] = '#';
            taul[korkeus][j] = '#';
        }
        taul[korkeus][leveys] = '#';
        tulosta();
    }

    /**
     * Kun metodissa aja() annetaan komento 1: Luokka aloittaa seinän luomisen
     * taulukkoon taul. Käyttäjä saa päättää luodaanko pysty- (1) vai vaakasuora
     * seinä (2). Komennolla 99 keskeytetään seinän luominen
     */
    private void luoSeina() {
        System.out.print("Seinän rakentamisen voi keskeyttää komennolla (99)\n");
        System.out.print("Haluatko rakentaa pysty- (1) vai vaakaseinän (2)?\n > ");
        String komento;
        int luku;
        while (true) {
            komento = lukija.nextLine();
            if (onkoNumero(komento)) {
                luku = Integer.parseInt(komento);
                switch (luku) {
                    case 1:
                        luoPystyseina();
                        return;
                    case 2:
                        luoVaakaseina();
                        return;
                    case 99:
                        return;
                    default:
                        vaaranlainenKomento("1, 2, 99");
                        break;
                }
            }
        }
    }

    /**
     * Kun metodissa luoSeina() annetaan komento 1: Luokka aloittaa pystyseinän
     * luomisen taulukkoon taul. Käyttäjä saa päättää mihin seinä luodaan.
     * Tarkistetaan saako tähän rakentaa seinän. Jos saa, aloitetaan
     * rakentaminen, Komennolla 99 keskeytetään seinän luominen
     */
    private void luoPystyseina() {
        String komento;
        int luku;
        System.out.print("Anna pystyseinän sijainti x-akselilla\n > ");
        while (true) {
            komento = lukija.nextLine();
            if (onkoNumero(komento)) {
                luku = Integer.parseInt(komento);
                if (luku > 0 && luku < leveys) {
                    if (voikoPystySeinanRakentaa(luku)) {
                        rakennaPystyseina(luku);
                    }
                    return;
                } else if (luku == 99) {
                    return;
                } else {
                    vaaranlainenKomento("2 - " + (leveys - 1) + ", 99");
                }
            }
        }
    }

    /**
     * Kun metodissa luoSeina() annetaan komento 2: Luokka aloittaa vaakaseinän
     * luomisen taulukkoon taul. Käyttäjä saa päättää mihin seinä luodaan.
     * Tarkistetaan saako tähän rakentaa seinän. Jos saa, aloitetaan
     * rakentaminen, Komennolla 99 keskeytetään seinän luominen
     */
    private void luoVaakaseina() {
        String komento;
        int luku;
        System.out.print("Anna vaakaseinän sijainti y-akselilla\n > ");
        while (true) {
            komento = lukija.nextLine();
            if (onkoNumero(komento)) {
                luku = Integer.parseInt(komento);
                if (luku > 0 && luku < korkeus) {
                    if (voikoVaakaSeinanRakentaa(luku)) {
                        rakennaVaakaseina(luku);
                    }
                    return;
                } else if (luku == 99) {
                    return;
                } else {
                    vaaranlainenKomento("2 - " + (korkeus - 1) + ", 99");
                }
            }
        }
    }

    /**
     * Lisätään merkit '#' labyrinttiin siten että halutussa kohdassa on seinä.
     *
     * @param Integer luku = mille korkeudelle seinä rakennetaan labyrinttiin
     */
    private void rakennaVaakaseina(int luku) {
        for (int i = 1; i < leveys; i++) {
            if (taul[luku][i] != '#') {
                seinaRuutujenMaara++;
            }
            taul[luku][i] = '#';
        }
    }

    /**
     * Lisätään merkit '#' labyrinttiin siten että halutussa kohdassa on seinä.
     *
     * @param Integer luku = Mille leveydelle seinä rakennetaan labyrinttiin.
     */
    private void rakennaPystyseina(int luku) {
        for (int i = 1; i < korkeus; i++) {
            if (taul[i][luku] != '#') {
                seinaRuutujenMaara++;
            }
            taul[i][luku] = '#';
        }
    }

    /**
     * Tarkistetaan onko halutun seinän rakentaminen mahdollista. Jos seinä on
     * toisen samansuuntaisen seinän vieressä, ei voi Jos seinän tiellä on ovi
     * tai avain, ei voi. Muuten voi.
     *
     * @param Integer luku = Mille leveydelle seinä halutaan rakentaa?
     * @return boolean Voiko tämän seinän rakentaa?
     */
    private boolean voikoPystySeinanRakentaa(int luku) {
        if (taul[1][luku - 1] == '#' || taul[1][luku + 1] == '#') {
            System.out.print("Seinän viereen ei voi rakentaa toista seinää\n > ");
            return false;
        }
        for (int i = 1; i < korkeus; i++) {
            if (taul[i][luku] != '.' && taul[i][luku] != '#') {
                System.out.print("Seinää ei voi rakentaa avaimen tai oven päälle\n > ");
                return false;
            }
        }
        return true;
    }

    /**
     * Tarkistetaan onko halutun seinän rakentaminen mahdollista. Jos seinä on
     * toisen samansuuntaisen seinän vieressä, ei voi Jos seinän tiellä on ovi
     * tai avain, ei voi Muuten voi.
     *
     * @param Integer luku = mille korkeudelle seinä halutaan rakentaa.
     * @return boolean voiko tämän seinän rakentaa.
     */
    private boolean voikoVaakaSeinanRakentaa(int luku) {
        if (taul[luku - 1][1] == '#' || taul[luku + 1][1] == '#') {
            System.out.print("Seinän viereen ei voi rakentaa toista seinää\n > ");
            return false;
        }
        for (int i = 1; i < leveys; i++) {
            if (taul[luku][i] != '.' && taul[luku][i] != '#') {
                System.out.print("Seinää ei voi rakentaa avaimen tai oven päälle\n > ");
                return false;
            }
        }
        return true;
    }

    /**
     * Kun metodissa aja() annetaan komento 2: Jos seinien ovien rakentaminen on
     * mahdotonta tai avaimia on jo liikaa, ei tehdä mitään. Luokka aloittaa
     * avaimen ja oven luomisen taulukkoon taul. Lopuksi kutsutaan luoAvain() ja
     * luoOvi(). Jos on annettu sopivat arvot, avain ja ovi luodaan. Tämän voi
     * keskeyttää milloin vain komennolla 99.
     */
    private void luoAvainJaOvi() {
        if (seinaRuutujenMaara <= 0) {
            System.out.println("Labyrintissä ei ole seinää, johon oven voisi sijoittaa");
            return;
        }
        if (avaintenMaara >= 25) {
            System.out.println("Avaimia on jo liikaa. (max 25)");
            return;
        }
        keskeytaAvaimenJaOvenLuonti = false;
        avainY = 0;
        avainX = 0;
        oviY = 0;
        oviX = 0;
        avainKirjain = '@';
        oviKirjain = '@';
        System.out.println("Avaimen ja oven luomisen voi keskeyttää komennolla (99)");
        luoAvain();
        luoOvi();
        if (!keskeytaAvaimenJaOvenLuonti) {
            taul[avainY][avainX] = avainKirjain;
            taul[oviY][oviX] = oviKirjain;
        }
        avaintenMaara++;

        tulosta();
    }

    /**
     * Metodi kysyy käyttäjältä mihin koordinaatteihin avain halutaan luoda. Jos
     * ovea ei voi luoda näihin koordinaatteihin, pyydetään uudet koordinaatit.
     * Prosessin voi keskeyttää milloin vain komennolla 99.
     */
    private void luoAvain() {
        char kirjain = (char) (avaintenMaara + 97);
        String komento;
        int y;
        int x;
        System.out.print("Anna avaimen [" + kirjain + "] sijainti korkeussuunnassa\n > ");
        while (true) {
            while (true) {
                if (keskeytaAvaimenJaOvenLuonti) {
                    return;
                }
                komento = lukija.nextLine();
                if (onkoNumero(komento)) {
                    y = Integer.parseInt(komento);
                    if (y < korkeus && y > 0) {
                        break;
                    } else if (y == 99) {
                        keskeytaAvaimenJaOvenLuonti = true;
                    } else {
                        vaaranlainenKomento("2 - " + (korkeus - 1) + ", 99");
                    }
                }
            }

            System.out.print("Anna avaimen [" + kirjain + "] sijainti leveyssuunnassa\n > ");
            while (true) {
                komento = lukija.nextLine();
                if (onkoNumero(komento)) {
                    x = Integer.parseInt(komento);
                    if (x < leveys - 1 && x > 0) {
                        break;
                    } else if (x == 99) {
                        keskeytaAvaimenJaOvenLuonti = true;
                    } else {
                        vaaranlainenKomento("2 - " + (leveys - 1) + ", 99");
                    }
                }
            }
            if (taul[y][x] == '.'
                    && !((y == 1) && (x == 1))
                    && !((y == korkeus) && x == leveys)) {
                break;
            }
        }
        avainY = y;
        avainX = x;
        avainKirjain = kirjain;
    }

    /**
     * Metodi kysyy käyttäjältä mihin koordinaatteihin ovi halutaan luoda. Jos
     * ovea ei voi rakentaa näihin koordinaatteihin pyydetään uudet
     * koordinaatit. Prosessin voi keskeyttää milloin vain komennolla 99.
     */
    private void luoOvi() {
        char kirjain = (char) (avaintenMaara + 65);
        String komento;
        int y;
        int x;
        System.out.print("Anna oven [" + kirjain + "] sijainti korkeussuunnassa\n > ");
        while (true) {
            while (true) {
                if (keskeytaAvaimenJaOvenLuonti) {
                    return;
                }
                komento = lukija.nextLine();
                if (onkoNumero(komento)) {
                    y = Integer.parseInt(komento);
                    if (y < korkeus && y > 0) {
                        break;
                    } else if (y == 99) {
                        keskeytaAvaimenJaOvenLuonti = true;
                    } else {
                        vaaranlainenKomento("2 - " + (korkeus - 1) + ", 99");
                    }
                }
            }
            System.out.print("Anna oven [" + kirjain + "] sijainti leveyssuunnassa\n > ");
            while (true) {
                if (keskeytaAvaimenJaOvenLuonti) {
                    return;
                }
                komento = lukija.nextLine();
                if (onkoNumero(komento)) {
                    x = Integer.parseInt(komento);
                    if (x < leveys && x > 0) {
                        break;
                    } else if (x == 99) {
                        keskeytaAvaimenJaOvenLuonti = true;
                    } else {
                        vaaranlainenKomento("2 - " + (leveys - 1) + ", 99");
                    }
                }
            }
            if (voikoTahanTehdaOven(y, x)) {
                break;
            }
        }
        oviY = y;
        oviX = x;
        oviKirjain = kirjain;
    }

    /**
     * Metodi selvittää onko parametreinä annettuihin koordinaatteihin sellittua
     * rakentaa ovi (eli onko annettu ruutu seinässä ja sen molemmilla puolilla
     * on eri huone.
     *
     * @param Integer y = halutun oven sijainti korkeussuunnassa.
     * @param Integer x = halutun oven sijainti leveyssuunnassa.
     * @return boolean onko haluttu ovi mahdollista rakentaa.
     */
    private boolean voikoTahanTehdaOven(int y, int x) {
        if (taul[y][x] != '#') {
            System.out.print("Ovi täytyy rakentaa seinään\n > ");
            return false;
        }
        if (y < 0 || y > korkeus || x < 0 || x > leveys) {
            System.out.print("Ovi ei saa olla labyrintin reunalla\n > ");
            return false;
        }
        if (taul[y - 1][x] == '#' && taul[y][x - 1] == '#') {
            System.out.print("Ovi ei saa olla kaden seinän risteyksessä\n > ");
            return false;
        }
        return true;
    }

    /**
     * Metodi tulostaa sen minkälainen labyrintti syntyisi, jos se luotaisiin
     * nyt.
     */
    private void tulosta() {
        System.out.println("Labyrintti näyttää nyt tältä:");
        System.out.print("x ");
        for (int i = 1; i < leveys; i++) {
            if (i < 10) {
                System.out.print(i + " ");
            } else {
                System.out.print(i);
            }
        }
        System.out.println("");

        for (int i = 0; i <= korkeus; i++) {
            for (int j = 0; j <= leveys; j++) {
                if (i == 1 & j == 1) {
                    System.out.println("+ ");
                } else if (i == korkeus && j == leveys) {
                    System.out.println("* ");
                } else {
                    System.out.print(taul[i][j] + " ");
                }
            }
            if (i != 0) {
                System.out.println(" " + i);
            } else if (i != korkeus) {
                System.out.println(" y");
            }
        }
    }

    /**
     * Kun jossakin vaiheessa käyttäjä on antanut vääränlaisen komennon
     * Tulostetaan lista oikeista komennoista
     * @param String komennot = sallitut komennot listattuna merkkijonoon.
     */
    private void vaaranlainenKomento(String komennot) {
        System.out.println("Sallitut komennot: " + komennot + "\n > ");
    }
    
    /**
     * Kun käyttäjä on antanut jonkin komennon
     * Tarkastetaan onko annettu komento tulkittavissa (eli onko se numero)
     *
     * @param String sana = tarkasteltava sana.
     * @ret Boolean totuus = Oliko sana muutettavissa lukuarvoksi.
     */
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
