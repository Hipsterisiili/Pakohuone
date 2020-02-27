## Toteutusdokumentti

### Pakkausrakenne

##### Pakohuone.Main
Sisältää kaikki ohjelman ajamiseen liittyvät luokat, joita on tarkoitus muokata ohjelman käyttötarkoituksen mukaan

        Main.java<br>
        Labyrintinluoja.java<br>

##### Pakohuone.Sovelluslogiikka
Sisältää täsmälleen kaiken tiedon labyrintin sisällöstä itse luominani tietorakenteina.

        Labyrintti.java<br>
        Huone.java<br>
        Avain.java<br>
        Ovi.java<br>

##### Pakohuone.algoritmit
Sisältää suurimmat labyrintin tarkasteluun käytettävät algoritmit

        HuoneidenEtsija.java<br>
        ReittienEtsija.java<br>
        NopeimmanReitinEtsija.java<br>

##### Pakohuone.tyokalut
Sisältää pienet algoritmit joita muut oliot saattavat tarvita usein.

        EtaisyydenEtsija.java<br>
        Kirjainpino.java<br>
        Syvyyshaku.java<br>
        Verkko.java<br>

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

Aikavaativuuden määrittely on mielekästä vasta kun koko ohjelman kaikki algoritminen laskenta on toteutettu valmiiksi. Ohjelma tekee paljon työtä, joten aikavaativuuden tulee olla melko löyhä. Nykyisessä tilassaan koodi on niin täynnä debuggauskoodia sekä muuta ylimääräistä laskentaa, että aikavaativuudet hipovat pilviä


### Parannusajatukset

Vaikka koodi on melko käytännöllistä, kaikkia tietorakenteita ei ole toteutettu optimaalisimmalla tavalla. Mielessäni on kaksi esimerkkiä tästä:

1: Huone-olion taulukot Ovi[] ovet sekä Avain[] avaimet. Kumpikin luodaan suoraan 30 alkion kokoiseksi, jotta niiden kokoa ei tarvitsisi jälkikäteen enää muuttaa. Tämä aiheuttaa ylimääräistä työtä ohjelmalle, kun huoneissa ei ole maksimimäärää avaimia, mutta perustelen ratkaisuni sillä että on pakko varautua tilanteeseen, jossa esimerkiksi kaikki avaimet ovat yhden huoneen sisällä, jotta ohjelma ei ajautuisi virheeseen ennen kuin se kykenee suorittamaan tehtäväänsä loppuun.

2: Useat algoritmit-pakkauksen oliot sisältävät paljon taulukoita ja toteuttamani ratkaisu niiden täyttämiseksi niitä alustaessa ei ole optimaalinen. Olisi mahdollista että loisin luokkaan Pakohuone.tyokalut uuden olion TaulukonTayttaja, joka täyttää taulukoita halutuilla arvoilla. Tämä tosin vaatisi ylimääräistä työtä ja lisää importteja moniin luokkiin, mikä ei ole toivottua luotaessa algoritmeja, joiden tehokkuuteen panostetaan.
