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
        char[][] labyrintti = new char[11][11];

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 10; j++) {
                labyrintti[i][j] = '.';
            }
        }
        for (int i = 0; i < 11; i++) {
            labyrintti[0][i] = '#';
            labyrintti[10][i] = '#';
            labyrintti[5][i] = '#';
        }

        for (int i = 0; i < 11; i++) {
            labyrintti[i][0] = '#';
            labyrintti[i][10] = '#';
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

        System.out.println("\nAVAINTEN SIJAINNIT");
        System.out.println(laby.getAvaimet()[0].getSijaintiX() == 2);
        System.out.println(laby.getAvaimet()[0].getSijaintiY() == 2);
        System.out.println(laby.getAvaimet()[1].getSijaintiX() == 7);
        System.out.println(laby.getAvaimet()[1].getSijaintiY() == 4);



    }
}
