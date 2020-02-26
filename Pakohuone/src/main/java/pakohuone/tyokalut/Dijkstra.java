package pakohuone.tyokalut;

import java.util.Arrays;

public class Dijkstra {

    private int alku;
    private int loppu;
    private boolean[] onkoYhteysKaytossa;
    private long[] lyhinMatka;
    private int[][] verkko;
    private boolean[] kasitelty;

    public Dijkstra(int[][] matriisi) {
        this.verkko = matriisi;
        this.lyhinMatka = new long[matriisi[0].length];
        this.kasitelty = new boolean[matriisi[0].length];
    }

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

    private long rekursio(int a, int b) {
        System.out.println("\nDijkstra käy /// a = " + a + " /// b = " + b);

        for (int i = 0; i < verkko[0].length; i++) {
            System.out.println("Tutkitaan " + i + ": " + lyhinMatka[i] + " &&& " + (lyhinMatka[a] + verkko[a][i]));
            if (verkko[a][i] > 0) {
                lyhinMatka[i] = verkko[a][i];
                if (i == 2) {
                    System.out.println("b muuttui");
                }
            }
        }
        kasitelty[a] = true;

        long nyky;
        long uusi;
        int lahimpana = mihinOnPieninMatka();
        while (lahimpana != -1) {

            if (lahimpana == b) {
                System.out.println("Keskeytetään while, matka " + b + ":hen on " + lyhinMatka[b]);
                break;
            }

            System.out.println("dijkstra while");
            if (kasitelty[lahimpana]) {
                continue;
            }
            kasitelty[lahimpana] = true;

            for (int i = 0; i < verkko[0].length; i++) {
                if (verkko[i][lahimpana] != 0) {
                    System.out.println("VERRATAAN " + i + ": " + lyhinMatka[i] + " VRT " + (lyhinMatka[lahimpana] + verkko[i][lahimpana]));
                    nyky = lyhinMatka[i];
                    uusi = lyhinMatka[lahimpana] + verkko[i][lahimpana];
                    if (uusi < nyky) {
                        System.out.println("muutos");
                        lyhinMatka[i] = uusi;
                        if (i == 2) {
                            System.out.println("b muuttui");
                        }
                    }
                }
            }
            lahimpana = mihinOnPieninMatka();
        }
        for (int i = 0; i < lyhinMatka.length; i++) {
            System.out.println("i = " + i + " /// " + lyhinMatka[i] + " /// " + onkoYhteysKaytossa[i] + " /// " + kasitelty[i]);
        }

        return lyhinMatka[b];
    }

    private int mihinOnPieninMatka() {
        int palautus = -1;
        long pieninMatka = Long.MAX_VALUE;

        for (int i = 0; i < lyhinMatka.length; i++) {
            //System.out.println("i = " + i + " /// " + lyhinMatka[i] + " /// " + onkoYhteysKaytossa[i] + " /// " + kasitelty[i]);
            if (lyhinMatka[i] < pieninMatka
                    && /*onkoYhteysKaytossa[i]*/ true
                    && !kasitelty[i]) {

                pieninMatka = lyhinMatka[i];
                palautus = i;
            }
        }
        System.out.println("pienin matka oli " + palautus + ":n");
        if (palautus > -1) {
            System.out.println("Matkan pituus oli: " + lyhinMatka[palautus]);
        }
        return palautus;
    }

}
