package pakohuone.Main;
import pakohuone.sovelluslogiikka.Avain;
import pakohuone.sovelluslogiikka.Ovi;
import pakohuone.sovelluslogiikka.Huone;
import pakohuone.tyokalut.EtaisyydenEtsija;
import pakohuone.tyokalut.HuoneidenEtsinta;

public class Main {
    public static void main(String[] args) {

        //Tällä hetkellä main-oliossa luodaan labyrintti ,jolla ohjelmaa
        //testataan ja sen jälkeen tulostetaan testattu labyrintti 
        //kahdessa eri muodossa
        
        int korkeus = 100;
        int leveys = 10;
        
        char[][] labyrintti = new char[korkeus+1][leveys+1];

        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                labyrintti[i][j] = '.';
            }
        }
        for (int i = 0; i < leveys; i++) {
            labyrintti[0][i] = '#';
            labyrintti[korkeus][i] = '#';
            labyrintti[5][i] = '#';
        }

        for (int i = 0; i < korkeus; i++) {
            labyrintti[i][0] = '#';
            labyrintti[i][leveys] = '#';
            labyrintti[i][6] = '#';
        }
        labyrintti[5][2] = 'A';
        labyrintti[3][6] = 'B';
        labyrintti[5][8] = 'C';
        labyrintti[2][2] = 'a';
        labyrintti[7][4] = 'b';
        labyrintti[2][8] = 'c';

        Labyrintti laby = new Labyrintti(labyrintti);
        System.out.println("\nTULOSTETAAN HUONEET:\n");
        laby.tulostaHuoneet();
        System.out.println("\nTULOSTETAAN LABYRINTTI:\n");
        laby.tulostaLabyrintti();
        

        labyrintti = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                labyrintti[i][j] = '.';
            }
        }
        for (int i = 0; i < 10; i++) {
            labyrintti[0][i] = '#';
            labyrintti[9][i] = '#';
            labyrintti[i][0] = '#';
            labyrintti[i][9] = '#';
            labyrintti[5][i] = '#';
            labyrintti[i][7] = '#';
        }
        HuoneidenEtsinta h = new HuoneidenEtsinta(labyrintti);
        
        int[][] huoneet = h.tulkitse();

        System.out.println(huoneet[0][0] == 0);
        System.out.println(huoneet[1][1] == 1);
        System.out.println(huoneet[5][5] == 0);
        System.out.println(huoneet[8][8] == 4);
    }
}
