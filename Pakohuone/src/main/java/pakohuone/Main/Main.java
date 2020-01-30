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
        
        Huone h = new Huone();
    Avain a = new Avain(1,2);
    Avain aa = new Avain(3,4);
    Ovi o = new Ovi(1,2,3,4);
    Ovi oo = new Ovi(5,6,7,8);
        a.setOvi(o);
        System.out.println(a.getOvi() == o);
        h.LisaaAvain(a);
        h.LisaaAvain(aa);
        aa.setOvi(oo);
        System.out.println(h.getAvaimet()[0].getOvi() == o);
        System.out.println(h.getAvaimet()[1].getOvi() == oo);
    }
}
