## Toteutusdokumentti

### Pakkausrakenne

#### Pakohuone.Main
Sisältää vähimmät toiminnallisudet ohjelman käynnistämiseen

        Main.java
        Labyrintinluoja.java
        
#### Pakohuone.ui
Sisältää käyttöliittymän eli rajapinnan käyttäjän komentojen ja ohjelman toiminnan välille sekä siihen liittyvät oliot.

        Kayttoliittyma.java
        Labyrintinluoja.java
        LabyrintinRakennus.java
        
#### Pakohuone.Sovelluslogiikka
Sisältää täsmälleen kaiken tiedon labyrintin sisällöstä itse luominani tietorakenteina.

        Labyrintti.java
        Huone.java
        Avain.java
        Ovi.java

#### Pakohuone.algoritmit
Sisältää suurimmat labyrintin tarkasteluun käytettävät algoritmit

        HuoneidenEtsija.java
        ReittienEtsija.java
        NopeimmanReitinEtsija.java

#### Pakohuone.tyokalut
Sisältää pienet algoritmit joita muut oliot saattavat tarvita usein.

        EtaisyydenEtsija.java
        Kirjainpino.java
        Syvyyshaku.java
        Verkko.java
        Dijkstra.java

### Ohjelman toiminta:

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

### Aikavaativuus

Luokka LabyrintinLuoja sisältää metodit neljän erilaisen valmiiksi määritellyn labyrintin luomiseen. Näistä neljäs on tarkoituksella tehty niin työlääksi, että javan heap space ei riitä sen työsytämiseen. Alla ensin kuva kustakin testilabyrintista ja tämän jälkeen täsmennys niiden aikavaativuuksista.

#### Labyrintti 1: 
(4 avain/ovi -paria, 4 huonetta, labyrintin koko 11\*11)

            x 1 2 3 4 5 6 7 8 9 10 11 12
            # # # # # # # # # # # # # #   y
            # + . . . . # . . . . . . #   1
            # . a . . . # . c . . . . #   2
            # . . . d . B . . . . . . #   3
            # . . . . . # . . . . . . #   4
            # # A # # # # # C # # # # #   5
            # . . . . . # . . . . . . #   6
            # . . . b . # . . . . . . #   7
            # . . . . . D . . . . . . #   8
            # . . . . . # . . . . . . #   9
            # . . . . . # . . . . . . #   10
            # . . . . . # . . . . . * #   11
            # # # # # # # # # # # # # #   12
            
Labyrintin luomiseen kuluu aikaa: 0.004124888 sekuntia

Reittien etsimiseen kuluu aikaa: 0.000332165 sekuntia

Reittien vertailuun kuluu aikaa: 0.002564198


#### Labyrintti 2: 
(7 avain/ovi -paria, 6 huonetta, labyrintin koko 14\*19)

    x 1 2 3 4 5 6 7 8 9 1011121314151617181920
    # # # # # # # # # # # # # # # # # # # # #   y
    # + . . . # . . . . . . # . . . . . . . #   1
    # . a . . # . c . . . . # . . . . . . . #   2
    # . . . . # . . . . . . # . . . . . . . #   3
    # . . . . # . . . . . . # . . . . . . . #   4
    # . . . . # . . . d . . B . . . . . . . #   5
    # . . . . # . . . . . . # . . . . . . . #   6
    # . . . . E . b . . . . # . . . . . . . #   7
    # . . . e # . . . . . . # . . . . . . . #   8
    # . . . . # . . . . . . # . . . . . . . #   9
    # # A # # # # # # G # # # # # F # # # # #   10
    # . . . . # . . . . . . C . . . . . . . #   11
    # . . . . # . . . . . . # . . . . . . . #   12
    # g . . f # . . . . . . # . . . . . . . #   13
    # . . . . D . . . . . . # . . . . . . * #   14
    # # # # # # # # # # # # # # # # # # # # #   15 
            
Labyrintin luomiseen kuluu aikaa: 0.000208749E sekuntia

Reittien etsimiseen kuluu aikaa: 0.005819768 sekuntia

Reittien vertailuun kuluu aikaa: 0.014021167 sekuntia


#### Labyrintti 3: 
(huom. 12 avain/ovi -paria, 9 huonetta, labyrintin koko 14\*14)

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
            
Labyrintin luomiseen kuluu aikaa: 0.000242574 sekuntia

Reittien etsimiseen kuluu aikaa: 0.768935346 sekuntia

Reittien vertailuun kuluu aikaa: 0.001724548 sekuntia


#### Labyrintti 4: 
(huom. 10 avain/ovi -paria, 9 huonetta, labyrintin koko 14\*14)

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
            
Labyrintin luomiseen kuluu aikaa: 0.000098996 sekuntia

Reittien etsimiseen kuluu aikaa: 9.167623216 sekuntia

Reittien vertailuun kuluu aikaa: 49.395056998 sekuntia

#### Labyrintti 5: 
(huom. 25 avain/ovi -paria, 25 huonetta, labyrintin koko 19\*24)

    x 1 2 3 4 5 6 7 8 9 101112131415161718192021222324
    # # # # # # # # # # # # # # # # # # # # # # # # # #  y
    # + . . . # . . . . # . . . . # . . . . # . . . . #   1
    # . . . . # . . . . # . . . . # . . . . # . . . . #   2
    # . . a . A . . b . B . . c . C . . d . D . . e . #   3
    # . . . . # . . . . # . . . . # . . . . # . . . . #   4
    # # # E # # # # # # # # # # # # # # # # # # # # # #   5
    # . . . . # . . . . # . . . . # . . . . # . . . . #   6
    # . . . . # . . . . # . . . . # . . . . # . . . . #   7
    # . . f . F . . g . G . . h . H . . i . I . . j . #   8
    # . . . . # . . . . # . . . . # . . . . # . . . . #   9
    # # # J # # # # # # # # # # # # # # # # # # # # # #   10
    # . . . . # . . . . # . . . . # . . . . # . . . . #   11
    # . . . . # . . . . # . . . . # . . . . # . . . . #   12
    # . . k . K . . l . L . . m . M . . n . N . . o . #   13
    # . . . . # . . . . # . . . . # . . . . # . . . . #   14
    # # # O # # # # # # # # # # # # # # # # # # # T # #   15
    # . . . . # . . . . # . . . . # . . . . # . . . . #   16
    # . . . . # . . . . # . . . . # . . . . # . . . . #   17
    # . . p . P . . q . Q . . r . R . . s . S . . t . #   18
    # . . . . # . . . . # . . . . # . . . . # . . . * #   19
    # # # # # # # # # # # # # # # # # # # # # # # # # #   20    
            
Labyrintin luomiseen kuluu aikaa: 0.001078193 sekuntia

Reittien etsimiseen kuluu aikaa: 1.258620104 sekuntia

Reittien vertailuun kuluu aikaa: 0.014833125 sekuntia
