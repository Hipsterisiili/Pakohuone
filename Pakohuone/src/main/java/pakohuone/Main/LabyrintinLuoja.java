package pakohuone.Main;

import pakohuone.sovelluslogiikka.Labyrintti;

public class LabyrintinLuoja {

    private int korkeus;
    private int leveys;
    private char[][] kuva;

    public LabyrintinLuoja() {

    }

    public Labyrintti LuoLabyrintti1() {
        //luotavan labyrintin korkeus
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

        return new Labyrintti(labyrintti);
    }

    public Labyrintti LuoLabyrintti2() {
        korkeus = 15;
        //Luotavan labyrintin leveys
        leveys = 20;

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
            labyrintti[10][i] = '#';
        }

        for (int i = 0; i < korkeus; i++) {
            labyrintti[i][0] = '#';
            labyrintti[i][leveys] = '#';
            labyrintti[i][12] = '#';
            labyrintti[i][5] = '#';
        }
        //Iso kirjain A = ovi ja pieni kirjain a = avain. avain avaa aina
        //sen kirjainta vastaaman ison kirjaimen omaavan oven
        labyrintti[10][2] = 'A';
        labyrintti[5][12] = 'B';
        labyrintti[11][12] = 'C';
        labyrintti[14][5] = 'D';
        labyrintti[7][5] = 'E';
        labyrintti[10][15] = 'F';
        labyrintti[10][9] = 'G';

        labyrintti[2][2] = 'a';
        labyrintti[7][7] = 'b';
        labyrintti[2][7] = 'c';
        labyrintti[5][9] = 'd';
        labyrintti[8][4] = 'e';
        labyrintti[13][4] = 'f';
        labyrintti[13][1] = 'g';

        labyrintti[korkeus][leveys] = '#'; // ASCIIssa 35

        return new Labyrintti(labyrintti);
    }

    public Labyrintti LuoLabyrintti3() {

        /* Syntyy seuraavanlainen labyrintti:
    x 1 2 3 4 5 6 7 8 9 101112131415
    # # # # # # # # # # # # # # # #   y
    # + . . . # . . . . . . H h . #   1
    # . . . . # . . . . . . # . . #   2
    # . . a . C . . d . . . # j . #   3
    # . . . . # . . . . . . # k . #   4
    # . . . . # . . . . . . # . . #   5
    # # # A # # # # # # F # # # K #   6
    # . . . . D . . . . . . # . . #   7
    # . . . b # . . . e . . # . . #   8
    # . . . . # . . . . . . I . . #   9
    # # B # # # # G # # # # # L # #   10
    # . . . . # . . . . . . # . . #   11
    # . . c . # . . . . . . J . . #   12
    # g . . f E . . . . i . # . . #   13
    # . . . . # . . . l . . # . * #   14
    # # # # # # # # # # # # # # # #   15      

         */
        korkeus = 15;
        //Luotavan labyrintin leveys
        leveys = 15;

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
            labyrintti[10][i] = '#';
            labyrintti[6][i] = '#';
        }

        for (int i = 0; i < korkeus; i++) {
            labyrintti[i][0] = '#';
            labyrintti[i][leveys] = '#';
            labyrintti[i][12] = '#';
            labyrintti[i][5] = '#';
        }
        //Iso kirjain A = ovi ja pieni kirjain a = avain. avain avaa aina
        //sen kirjainta vastaaman ison kirjaimen omaavan oven
        labyrintti[6][3] = 'A';
        labyrintti[10][2] = 'B';
        labyrintti[3][5] = 'C';
        labyrintti[7][5] = 'D';
        labyrintti[13][5] = 'E';
        labyrintti[6][10] = 'F';
        labyrintti[10][13] = 'G';
        labyrintti[1][12] = 'H';
        labyrintti[9][12] = 'I';
        labyrintti[12][12] = 'J';

        labyrintti[3][3] = 'a';
        labyrintti[8][4] = 'b';
        labyrintti[12][3] = 'c';
        labyrintti[3][8] = 'd';
        labyrintti[8][9] = 'e';
        labyrintti[13][4] = 'f';
        labyrintti[13][1] = 'g';
        labyrintti[1][13] = 'h';
        labyrintti[13][10] = 'i';
        labyrintti[3][13] = 'j';

        labyrintti[korkeus][leveys] = '#'; // ASCIIssa 35

        return new Labyrintti(labyrintti);
    }
    
    public Labyrintti LuoLabyrintti4() {

        /* Syntyy seuraavanlainen labyrintti:
    x 1 2 3 4 5 6 7 8 9 101112131415
    # # # # # # # # # # # # # # # #   y
    # + a f . # . . . . . . # . . #   1
    # . b g . # . . . . . . # . . #   2
    # . c h . # . . . . . . # . . #   3
    # . d i . # . . . . . . # . . #   4
    # . e j . # . . . . . . # . . #   5
    # A B C D # # # # # # # # # # #   6
    # . . . . # . . . . . . # . . #   7
    # . . . . # . . . . . . # . . #   8
    # . . . . # . . . . . . # . . #   9
    # E F G H # # # # # # # # # # #   10
    # . . . . I . . . . . . J . . #   11
    # . . . . # . . . . . . # . . #   12
    # . . . . # . . . . . . # . . #   13
    # . . . . # . . . . . . # . * #   14
    # # # # # # # # # # # # # # # #   15      

         */
        korkeus = 15;
        //Luotavan labyrintin leveys
        leveys = 15;
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
            labyrintti[10][i] = '#';
            labyrintti[6][i] = '#';
        }

        for (int i = 0; i < korkeus; i++) {
            labyrintti[i][0] = '#';
            labyrintti[i][leveys] = '#';
            labyrintti[i][12] = '#';
            labyrintti[i][5] = '#';
        }
        //Iso kirjain A = ovi ja pieni kirjain a = avain. avain avaa aina
        //sen kirjainta vastaaman ison kirjaimen omaavan oven
        labyrintti[1][2] = 'a';
        labyrintti[2][2] = 'b';
        labyrintti[3][2] = 'c';
        labyrintti[4][2] = 'd';
        labyrintti[5][2] = 'e';
        labyrintti[1][3] = 'f';
        labyrintti[2][3] = 'g';
        labyrintti[3][3] = 'h';
        labyrintti[4][3] = 'i';
        labyrintti[5][3] = 'j';

        labyrintti[6][1] = 'A';
        labyrintti[6][2] = 'B';
        labyrintti[6][3] = 'C';
        labyrintti[6][4] = 'D';
        labyrintti[10][1] = 'E';
        labyrintti[10][2] = 'F';
        labyrintti[10][3] = 'G';
        labyrintti[10][4] = 'H';
        labyrintti[11][5] = 'I';
        labyrintti[12][12] = 'J';

        labyrintti[korkeus][leveys] = '#'; // ASCIIssa 35

        return new Labyrintti(labyrintti);
    }
}
