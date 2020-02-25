## Toteutusdokumentti

### Pakkausrakenne

##### Pakohuone.Main
Sisältää kaikki ohjelman ajamiseen liittyvät luokat, joita on tarkoitus muokata ohjelman käyttötarkoituksen mukaan

>Main.java<br>
>Labyrintinluoja.java<br>

##### Pakohuone.Sovelluslogiikka
Sisältää täsmälleen kaiken tiedon labyrintin sisällöstä itse luominani tietorakenteina.

>Labyrintti.java<br>
>Huone.java<br>
>Avain.java<br>
>Ovi.java<br>

##### Pakohuone.algoritmit
Sisältää suurimmat labyrintin tarkasteluun käytettävät algoritmit

>HuoneidenEtsija.java<br>
>ReittienEtsija.java<br>
>NopeimmanReitinEtsija.java<br>

##### Pakohuone.tyokalut
Sisältää pienet algoritmit joita muut oliot saattavat tarvita usein.

>EtaisyydenEtsija.java<br>
>Kirjainpino.java<br>
>Syvyyshaku.java<br>
>Verkko.java<br>

### Aikavaativuus

Aikavaativuuden määrittely on mielekästä vasta kun koko ohjelman kaikki algoritminen laskenta on toteutettu valmiiksi. Ohjelma tekee paljon työtä, joten aikavaativuuden tulee olla melko löyhä. Nykyisessä tilassaan koodi on niin täynnä debuggauskoodia sekä muuta ylimääräistä laskentaa, että aikavaativuudet hipovat pilviä


### Parannusajatukset

Vaikka koodi on melko käytännöllistä, kaikkia tietorakenteita ei ole toteutettu optimaalisimmalla tavalla. Mielessäni on kaksi esimerkkiä tästä:

1: Huone-olion taulukot Ovi[] ovet sekä Avain[] avaimet. Kumpikin luodaan suoraan 30 alkion kokoiseksi, jotta niiden kokoa ei tarvitsisi jälkikäteen enää muuttaa. Tämä aiheuttaa ylimääräistä työtä ohjelmalle, kun huoneissa ei ole maksimimäärää avaimia, mutta perustelen ratkaisuni sillä että on pakko varautua tilanteeseen, jossa esimerkiksi kaikki avaimet ovat yhden huoneen sisällä, jotta ohjelma ei ajautuisi virheeseen ennen kuin se kykenee suorittamaan tehtäväänsä loppuun.

2: Useat algoritmit-pakkauksen oliot sisältävät paljon taulukoita ja toteuttamani ratkaisu niiden täyttämiseksi niitä alustaessa ei ole optimaalinen. Olisi mahdollista että loisin luokkaan Pakohuone.tyokalut uuden olion TaulukonTayttaja, joka täyttää taulukoita halutuilla arvoilla. Tämä tosin vaatisi ylimääräistä työtä ja lisää importteja moniin luokkiin, mikä ei ole toivottua luotaessa algoritmeja, joiden tehokkuuteen panostetaan.
