package pakohuone.Main;
//import pakohuone.sovelluslogiikka.Avain;
//import pakohuone.sovelluslogiikka.Ovi;

import pakohuone.tyokalut.Syvyyshaku;

//import pakohuone.sovelluslogiikka.Huone;
//import pakohuone.tyokalut.EtaisyydenEtsija;
//import pakohuone.tyokalut.HuoneidenEtsinta;
public class Main {

    public static void main(String[] args) {

        //Tällä hetkellä main-oliossa luodaan labyrintti ,jolla ohjelmaa
        //testataan ja sen jälkeen tulostetaan testattu labyrintti 
        //kahdessa eri muodossa
        //Luotavan labyrintin korkeus
        int korkeus = 12;
        //Luotavan labyrintin leveys
        int leveys = 13;

        char[][] labyrintti = new char[korkeus + 1][leveys + 1];

        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                labyrintti[i][j] = '.';
            }
        }
        //Luodaan seiniä labyrinttiin, # = seinä Labyrintin reunat ovat aina seiniä
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
        //Iso kirjain A = ovi ja pieni kirjain a = avain. avain avaa aina
        //sen kirjainta vastaaman ison kirjaimen omaavan oven
        labyrintti[5][2] = 'A';
        labyrintti[3][6] = 'B';
        labyrintti[5][8] = 'C';
        labyrintti[8][6] = 'D';
        labyrintti[2][2] = 'a';
        labyrintti[7][4] = 'b';
        labyrintti[2][8] = 'c';
        labyrintti[3][4] = 'd';
        
        labyrintti[korkeus][leveys] = '#'; // ASCIIssa 35

        Labyrintti laby = new Labyrintti(labyrintti);
        //System.out.println("\nTULOSTETAAN HUONEET:\n");
        //laby.tulostaHuoneet();
        System.out.println("\nTULOSTETAAN LABYRINTTI:\n");
        laby.tulostaLabyrintti();
        System.out.println("\nTULOSTETAAN REITIT");
        System.out.println(laby.etsiReitit());
        
        
        /*
        
        korkeus = 15;
        //Luotavan labyrintin leveys
        leveys = 20;

        labyrintti = new char[korkeus + 1][leveys + 1];

        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                labyrintti[i][j] = '.';
            }
        }
        //Luodaan seiniä labyrinttiin, # = seinä Labyrintin reunat ovat aina seiniä
        for (int i = 0; i < leveys; i++) {
            labyrintti[0][i] = '#';
            labyrintti[korkeus][i] = '#';
            labyrintti[10][i] = '#';
        }

        for (int i = 0; i < korkeus; i++) {
            labyrintti[i][0] = '#';
            labyrintti[i][leveys] = '#';
            labyrintti[i][12] = '#';
        }
        //Iso kirjain A = ovi ja pieni kirjain a = avain. avain avaa aina
        //sen kirjainta vastaaman ison kirjaimen omaavan oven
        labyrintti[10][2] = 'A';
        labyrintti[13][12] = 'B';
        labyrintti[11][12] = 'C';
        labyrintti[10][15] = 'D';
        labyrintti[2][2] = 'b';
        labyrintti[13][4] = 'd';
        labyrintti[2][16] = 'c';
        labyrintti[7][7] = 'a';
        
        labyrintti[korkeus][leveys] = '#'; // ASCIIssa 35

        laby = new Labyrintti(labyrintti);
        System.out.println("\nTULOSTETAAN HUONEET:\n");
        laby.tulostaHuoneet();
        System.out.println("\nTULOSTETAAN LABYRINTTI:\n");
        laby.tulostaLabyrintti();
        System.out.println("\nTULOSTETAAN REITIT:\n");
        System.out.println(laby.etsiReitit());*/
        
        Syvyyshaku l = new Syvyyshaku();
        int[][] matriisi = new int[5][5];
        matriisi[1][1] = 0;
        matriisi[1][2] = 1;
        matriisi[1][3] = 0;
        matriisi[1][4] = 0;
        
        matriisi[2][1] = 1;
        matriisi[2][2] = 0;
        matriisi[2][3] = 1;
        matriisi[2][4] = 1;
        
        matriisi[3][1] = 0;
        matriisi[3][2] = 1;
        matriisi[3][3] = 0;
        matriisi[3][4] = 1;
        
        matriisi[4][1] = 0;
        matriisi[4][2] = 1;
        matriisi[4][3] = 1;
        matriisi[4][4] = 0;
        
        System.out.println(l.hae(matriisi));
        
        System.out.println(l.haeArvolla(matriisi,1));
        System.out.println(l.haeArvolla(matriisi,2));
        System.out.println(l.haeArvolla(matriisi,3));
        System.out.println(l.haeArvolla(matriisi,4));
    }
}
