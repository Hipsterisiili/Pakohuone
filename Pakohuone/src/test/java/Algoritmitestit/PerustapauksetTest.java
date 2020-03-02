/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmitestit;
// Tässä testiluokassa testataan sovelluksen kaikkien algoritmien yleinen 
//toiminta ykisnkertaisissa tapauksissa, kuten labyrintit joissa ei ole seiniä

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import pakohuone.sovelluslogiikka.Labyrintti;

//sekä labyrintit joissa on vain yksi avain
public class PerustapauksetTest {

    Labyrintti laby;
    int korkeus = 5;
    int leveys = 5;
    char[][] taul = new char[korkeus + 1][leveys + 1];

    @Before
    public void Alustus() {

        /*
        Luodaan seuraavanlainen pohjalabyrintti:
        # # # # # #  
        # + . . . #   
        # . . . . #   
        # # # # # #   
        # . . . * #   
        # # # # # #   
        testeissä lisätään labyrinttiin avaimia, ovia ja seiniä
         */
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                taul[i][j] = '.';
            }
        }
        for (int i = 0; i < korkeus; i++) {
            taul[0][i] = '#';
            taul[korkeus][i] = '#';
            taul[3][i] = '#';
        }
        for (int i = 0; i < leveys; i++) {
            taul[i][0] = '#';
            taul[i][leveys] = '#';
        }
    }

    @Test
    public void YksiAvainEiRatkaisua() {
        // Labyrintissä 2 huonetta ja 1 avain. Avain on saavuttamattomissa joten
        // labyrintissa ei ole ratkaisua.

        taul[4][2] = 'a';
        taul[3][3] = 'A';
        laby = new Labyrintti(taul);

        assertEquals(laby.etsiReitit(), 0);
        assertEquals(laby.etsiJaTulostaReitit(), "Ei reittejä");
        assertEquals(laby.etsiParasReitti(), "Labyrintistä ei löydy reittejä");
    }

    @Test
    public void YksiAvainYksiRatkaisu() {
        // Labyrintissä 2 huonetta ja 1 avain. Avain on alkuhuoneessa.
        // Labyrintissa on ratkaisu.

        taul[2][2] = 'a';
        taul[3][3] = 'A';
        laby = new Labyrintti(taul);

        assertEquals(laby.etsiReitit(), 1);
        assertEquals(laby.etsiJaTulostaReitit(), "Löydetyt reitit: / a");
        assertEquals(laby.etsiParasReitti(), "\"a\" \npituus = 6");
    }

    @Test
    public void EiSeinia() {
        // Labyrintissä ei ole seiniä joten reitti on selvo jo ennen avainten 
        // poimintaa
        // Labyrintissa on ratkaisu.
        for (int i = 1; i < 5; i++) {
            taul[3][i] = '.';
        }
        laby = new Labyrintti(taul);

        assertEquals(laby.etsiReitit(), 1);
        assertEquals(laby.etsiJaTulostaReitit(), "+*");
        assertEquals(laby.etsiParasReitti(),
                "Maaliin pääsee ilman avaimia\nMatka = 6");
    }

    @Test
    public void EiAvaimia() {
        // Labyrintissä on seinä, mutta ei lainkaan avainta sen avaamiseksi
        // labyrintissa ei ole ratkaisua.
        laby = new Labyrintti(taul);

        assertEquals(laby.etsiReitit(), 0);
        assertEquals(laby.etsiJaTulostaReitit(), "Ei reittejä");
        assertEquals(laby.etsiParasReitti(), "Labyrintistä ei löydy reittejä");
    }

    @Test
    public void kaksiAvaintaNeljaRatkaisua() {
        // Labyrintissä on seinä, mutta ei lainkaan avainta sen avaamiseksi
        // labyrintissa ei ole ratkaisua.
        taul[2][3] = 'a';
        taul[1][2] = 'b';
        taul[3][3] = 'A';
        taul[3][1] = 'B';
        laby = new Labyrintti(taul);

        assertEquals(laby.etsiReitit(), 4);
        assertEquals(laby.etsiJaTulostaReitit(), "Löydetyt reitit: / a / ab / b / ba");
        assertEquals(laby.etsiParasReitti(), "\"a\" \npituus = 6");
    }
}
