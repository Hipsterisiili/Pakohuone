package pakohuone.Main;
//import pakohuone.sovelluslogiikka.Avain;
//import pakohuone.sovelluslogiikka.Ovi;

import pakohuone.sovelluslogiikka.Avain;
import pakohuone.sovelluslogiikka.Huone;
import pakohuone.sovelluslogiikka.Labyrintti;
import pakohuone.sovelluslogiikka.Ovi;
import pakohuone.tyokalut.Syvyyshaku;

//import pakohuone.sovelluslogiikka.Huone;
//import pakohuone.tyokalut.EtaisyydenEtsija;
//import pakohuone.tyokalut.HuoneidenEtsinta;
public class Main {

    public static void main(String[] args) {

        LabyrintinLuoja labyrintinLuoja = new LabyrintinLuoja();
        /*
        Tällä hetkellä ohjelman suorituksessa tehdään seuraavat vaiheet kahdella 
        eri labyrintilla:
        1) Luodaan uusi Labyrintti-olio
        
            Ensin labyrintin rakenne määritellään oliolla 
            pakohuone.main.LabyrintinLuoja, sitten labyrintistä luodaan olio
        
            Kun Labyrintti luodaan, se selvittää sen huoneiden rakenteen sekä
            avainten ja ovien sijainnit. Jokaisesta avaimesta, jokaisesta
            ovesta ja jokaisesta huoneesta luodaan oma olionsa. Labyrintti 
            käyttää tähän algoritmia pakohuone.algoritmit.HuoneidenEtsija.java
        
        2) Tulostetaan labyrintti
        
            Kaksiuloitteisena matriisina char[][], jossa 
            # = seinä
            . = lattia
            abc = avaimia
            ABC = ovia
            + = Lähtöruutu
            * = Maaliruutu
        
        3) Selvitetään kaikki mahdolliset ratkaisut luotuun labyrinttiin ja 
        tulostetaan ne merkkijonoina (1 ratkaisu on 1 järjestys jossa avaimet 
        voi poimia siten että maali saavutetaan, esimerkkimerkkijono 'adef')
        
            Labyrintti käyttää reittien etsimiseen algoritmia 
            pakohuone.algoritmit.ReittienEtsija.java. Reitit etsitään 
            syvyyshakua muodostavalla menetelmällä jossa joka vaiheessa 
            kokeillaan seuraavaksi poimittavaksi kaikkia avaimia ja mikäli 
            avain on poimimatta ja vapaana, se poimitaan, uudet yhteydet avataan
            ja astutaan syvemmälle rekursioon. 
        
            Kun tutkitaan mihin kohteisiin milloinkin on mahdollista päästä,
            toteutetaan ReittienEtsijan huonematriisille syvyyshaku
            pakohuone.tyokalut.Syvyyshaku.java. (huonematriisi on taulukko 
            int[][] joka sisältää tiedon siitä mihin huoneeseen mistäkin 
            huoneesta on mahdollista päästä.)
            
        4) Selvitetään näistä ratkaisuista kaikkein nopein ja tulostetaan se 
        samanlaisena merkkijonona kuin vaiheessa 3.
            
            Labyrintti käyttää nopeimman reitin etsimiseen algoritmia
            pakohuone.algoritmit.NopeimmanReitinEtsija.java. Se käy läpi kaikki
            edellisessä vaiheessa selvitetyt ratkaisut ja ilmoittaa mikä niistä
            on nopein
        
            Nopein reitti selvitetään toteuttamalla joukko peräkkäisiä hakuja
            labyrinttiä yksinkertaistaen kuvaavassa verkossa 
            pakohuone.tyokalut.Verkko.java. haut toteutetaan hyödyntäen 
            Dijkstran algoritmia pakohuone.tyokalut.Dijkstra.java. Dijkstran 
            algoritmilla etsitään aina lyhin reitti kustakin ratkaisun avaimesta
            seuraavaan ja nämä pituudet summataa yhteen. Lyhin löytynyt ratkaisu
            palautetaan (ja tulostetaan).

        
        HUOM: Labyrintin huoneiden tulostus on merkitty kommenteiksi, koska se 
        tuottaa melko turhia tulostuksia ohjelman normaalissa käytössä
         */
        Labyrintti laby;
        
        laby = labyrintinLuoja.LuoLabyrintti1();
        //System.out.println("\nTULOSTETAAN HUONEET:\n");
        //laby.tulostaHuoneet();
        System.out.println("\nTULOSTETAAN LABYRINTTI:\n");
        laby.tulostaLabyrintti();
        System.out.println("\nETSITÄÄN REITIT");
        System.out.println(laby.etsiReitit());
        System.out.println("\nETSITÄÄN PARAS REITTI");
        System.out.println(laby.etsiParasReitti());
         

        laby = labyrintinLuoja.LuoLabyrintti2();
        //System.out.println("\nTULOSTETAAN HUONEET:\n");
        //laby.tulostaHuoneet();
        //System.out.println("\nTULOSTETAAN LABYRINTTI:\n");
        //System.out.println(laby.tulostaLabyrintti());
        System.out.println("\nETSITÄÄN REITIT:\n");
        System.out.println(laby.etsiReitit());
        //System.out.println("\nTULOSTETAAN REITIT:\n");
        //System.out.println(laby.etsiJaTulostaReitit());
        System.out.println("\nETSITÄÄN PARAS REITTI");
        System.out.println(laby.etsiParasReitti());
        
        
        laby = labyrintinLuoja.LuoLabyrintti3();

        laby.tulostaLabyrintti();
        System.out.println("\nTULOSTETAAN REITIT:\n");
        System.out.println(laby.etsiReitit());
        System.out.println("\nETSITÄÄN PARAS REITTI");
        System.out.println(laby.etsiParasReitti());
         
    }
}
