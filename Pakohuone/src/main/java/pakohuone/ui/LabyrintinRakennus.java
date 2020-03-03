package pakohuone.ui;

import java.util.Scanner;
import pakohuone.sovelluslogiikka.Labyrintti;

public class LabyrintinRakennus {

    private char[][] taul;
    private int korkeus;
    private int leveys;
    private int avaintenMaara;
    private int seinaRuutujenMaara;
    private Scanner lukija;
    private boolean keskeytaAvaimenJaOvenLuonti;
    private int avainX, avainY, oviX, oviY;
    private char avainKirjain, oviKirjain;
    //private long alku;
    //private long loppu;

    public LabyrintinRakennus() {
        lukija = new Scanner(System.in);
    }

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

    private void rakennaVaakaseina(int luku) {
        for (int i = 1; i < leveys; i++) {
            if (taul[luku][i] != '#') {
                seinaRuutujenMaara++;
            }
            taul[luku][i] = '#';
        }
    }

    private void rakennaPystyseina(int luku) {
        for (int i = 1; i < korkeus; i++) {
            if (taul[i][luku] != '#') {
                seinaRuutujenMaara++;
            }
            taul[i][luku] = '#';
        }
    }

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
            if (taul[y][x] == '.' && 
                    !((y == 1) && (x == 1)) && 
                    !((y == korkeus) && x == leveys)) {
                break;
            }
        }
        avainY = y;
        avainX = x;
        avainKirjain = kirjain;
    }

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
                System.out.print(taul[i][j] + " ");
            }
            if (i != 0) {
                System.out.println(" " + i);
            } else if ( i != korkeus) {
                System.out.println(" y");
            }
        }
    }

    private void vaaranlainenKomento(String komennot) {
        System.out.println("Sallitut komennot: " + komennot + "\n > ");
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
