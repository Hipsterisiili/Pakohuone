package juuri.sovelluslogiikka;

public class HuoneidenEtsinta {

    private char[][] labyrintti;
    private int[][] huoneet;
    private int korkeus;
    private int leveys;
    private int huoneidenMaara = 0;
    private char seina = '#';
    private char tyhja = '.';

    public HuoneidenEtsinta(char[][] laby) {
        this.labyrintti = laby;
        this.korkeus = laby.length;
        this.leveys = laby[0].length;
        this.huoneet = new int[korkeus][leveys];
        
        for (int i = 1; i < korkeus - 1; i++) {
            for (int j = 1; j < leveys - 1; j++) {
                huoneet[i][j] = 0;
            }
        }
    }

    public int[][] tulkitse() {
        for (int i = 1; i < korkeus - 1; i++) {
            for (int j = 1; j < leveys - 1; j++) {
                if (this.labyrintti[i][j - 1] == seina && this.labyrintti[i - 1][j] == seina) {
                    luoHuone(i, j);
                }
            }
        }
        return huoneet;
    }

    private void luoHuone(int alkux, int alkuy) {
        int a = alkux;
        int b = alkuy;
        huoneidenMaara++;
        while (labyrintti[alkux][b] != seina) {
            while (labyrintti[a][alkuy] != seina) {
                huoneet[a][b] = huoneidenMaara;
                a++;
            }
            a = alkux;
            b++;
        }
    }
}
