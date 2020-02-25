package pakohuone.tyokalut;

import java.util.Arrays;

public class Dijkstra {

    private int alku;
    private int loppu;
    private boolean[] onkoYhteysKaytossa;
    private int[] lyhinMatka;
    private int[][] verkko;
    private boolean[] kasitelty;

    public Dijkstra(int[][] matriisi) {
        this.verkko = matriisi;
        this.lyhinMatka = new int[matriisi[0].length];
        this.kasitelty = new boolean[matriisi[0].length];
    }

    public int hae(int a, int b, boolean[] taul) {
        this.alku = a;
        this.loppu = b;
        this.onkoYhteysKaytossa = taul;
        Arrays.fill(lyhinMatka, Integer.MAX_VALUE);
        Arrays.fill(kasitelty, false);
        lyhinMatka[a] = 0;
        kasitelty[a] = true;

        return rekursio(a, b);
    }

    private int rekursio(int a, int b) {
        System.out.println("Dijkstra käy /// a = " + a + " /// b = " + b);

        int nyky;
        int uusi;
        int missaOllaanNyt = 0;
        int lahimpana = mihinOnPieninMatka();
        while (lahimpana != -1) {
            System.out.println("dijkstra while");
            for (int i = 0; i < verkko[0].length; i++) {
                if (kasitelty[i]) {
                    continue;
                }
                kasitelty[i] = true;

                if (verkko[lahimpana][i] != 0) {
                    System.out.println("Verrataan vanha matka I:hin " + lyhinMatka[i]
                            + " sekä uusi " + (lyhinMatka[i] + verkko[lahimpana][i]));
                    nyky = lyhinMatka[i];
                    uusi = lyhinMatka[i] + verkko[lahimpana][i];
                    if (uusi < nyky) {
                        lyhinMatka[i] = uusi;
                    }
                }
            }
            missaOllaanNyt = lahimpana;
            lahimpana = mihinOnPieninMatka();
        }

        return lyhinMatka[b];
    }

    private int mihinOnPieninMatka() {
        int palautus = -1;
        int pieninMatka = Integer.MAX_VALUE;

        for (int i = 0; i < lyhinMatka.length; i++) {
            System.out.println("i = " + i + " /// " + lyhinMatka[i] + " /// " + onkoYhteysKaytossa[i] + " /// " + kasitelty[i]);
            if (lyhinMatka[i] < pieninMatka
                    && /*onkoYhteysKaytossa[i]*/ true
                    && !kasitelty[i]) {
                
                pieninMatka = lyhinMatka[i];
                palautus = i;
            }
        }
        System.out.println("pienin matka oli " + palautus + ":n");
        return palautus;
    }

}
