## Toteutusdokumentti

### Pakkausrakenne

#### Pakohuone.Main
Sisältää kaikki ohjelman ajamiseen liittyvät luokat, joita on tarkoitus muokata ohjelman käyttötarkoituksen mukaan

        Main.java
        Labyrintinluoja.java

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
(huom. 4 avain/ovi -paria, 4 huonetta, labyrintin koko 11\*11)

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
(huom. 7 avain/ovi -paria, 6 huonetta, labyrintin koko 14\*19)

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

              x 1 2 3 4 5 6 7 8 91011121314 15
              # # # # # # # # # # # # # # # #   y
              # + . . . # . i . . . . H . . #   1
              # . c . . # . j . . . . # . . #   2
              # . . . . C . k e . . . # . . #   3
              # d . . . # . l . . . . # . . #   4
              # . . . a # . . . . . . # . . #   5
              # # # A # # # # # # F # # # K #   6
              # . . . . D . b . . . . # . . #   7
              # . . . . # . . . . . . # . . #   8
              # . . . . # . . . . . . I . . #   9
              # # B # # # # G # # # # # L # #   10
              # . . . . # . . . . . . # . . #   11
              # . . . . # . . . . . . J . . #   12
              # g . . f E . . . h . . # . . #   13
              # . . . . # . . . . . . # . * #   14
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
    # . c h m # . . . . . . # . . #   3
    # . d i . # . . . . . . # . . #   4
    # . e . . # . . . . . . # . . #   5
    # A B C D # # # # # # # # # # #   6
    # . . . . # . . . . . . # . . #   7
    # . . . . # . . . . . . # . . #   8
    # . . . . # . . . . . . # . . #   9
    # E F G H # # # # # # # # # # #   10
    # . . . . I . . . . . . M . . #   11
    # . . . . # . . . . . . # . . #   12
    # . . . . # . . . . . . # . . #   13
    # . . . . # . . . . . . # . * #   14
    # # # # # # # # # # # # # # # #   15   
            
Labyrintin luomiseen kuluu aikaa: 0.000098996 sekuntia

Reittien etsimiseen kuluu aikaa: 0.988639369 sekuntia

Reittien vertailuun kuluu aikaa: STACK OVERFLOW


RAJOITTEET OHJELMAN SUORITUKSESSA EIVÄT OLE SUORAAN AVAINTEN MÄÄRÄN SEURAUSTA, SILLÄ USEIN SUURIN OSA KAIKISTA AVAINPERMUTAATIOISTA EI OLE MAHDOLLISIA. LABYRINTTI 4 ON RAKENNETTU JUURI SILLÄ AJATUKSELLA ETTÄ LÖYDÄN RAJAN MISSÄ AVASINPERMUTAATIOIDEN MÄÄRÄ ON LIIAN SUURI

### Parannusajatukset

Vaikka koodi on melko käytännöllistä, kaikkia tietorakenteita ei ole toteutettu optimaalisimmalla tavalla. Mielessäni on kaksi esimerkkiä tästä:

1: Huone-olion taulukot Ovi[] ovet sekä Avain[] avaimet. Kumpikin luodaan suoraan 30 alkion kokoiseksi, jotta niiden kokoa ei tarvitsisi jälkikäteen enää muuttaa. Tämä aiheuttaa ylimääräistä työtä ohjelmalle, kun huoneissa ei ole maksimimäärää avaimia, mutta perustelen ratkaisuni sillä että on pakko varautua tilanteeseen, jossa esimerkiksi kaikki avaimet ovat yhden huoneen sisällä, jotta ohjelma ei ajautuisi virheeseen ennen kuin se kykenee suorittamaan tehtäväänsä loppuun.

2: Useat algoritmit-pakkauksen oliot sisältävät paljon taulukoita ja toteuttamani ratkaisu niiden täyttämiseksi niitä alustaessa ei ole optimaalinen. Olisi mahdollista että loisin luokkaan Pakohuone.tyokalut uuden olion TaulukonTayttaja, joka täyttää taulukoita halutuilla arvoilla. Tämä tosin vaatisi ylimääräistä työtä ja lisää importteja moniin luokkiin, mikä ei ole toivottua luotaessa algoritmeja, joiden tehokkuuteen panostetaan.

3: Useissa luokissa suoritetaan toistuvia laskutoimituksia joissa char-muuttujia muutetaan integereiksi ja toisin päin. Tätä muutosta varten voisi luoda uusia metodeja koodin siisteyden ylläpitämiseksi, mutta se myös hidastaisi koodin ajamista, sillä se sisältäisi enemmän kutsuja metodeihin, joka sisältää enemmän koodia kuin vain yhden laskutoimituksen kussakin vaiheessa, kuten koodissa tällä hetkellä on tehty.
