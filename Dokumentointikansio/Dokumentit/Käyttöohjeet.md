## Pakohuoneen käyttöohjeet

### Ohjelman ajaminen:
1. Kloonaa tiedosto<br>
2. Navigoi kansioon Pakohuone-master/Pakohuone/<br>
3. "puhdista" tiedosto tässä kansiossa komennolla 
    mvn clean package<br>
4a. aja tiedosto samassa kansiossa komennolla 
    java -jar target/Pakohuone-1.0-SNAPSHOT-jar-with-dependencies.jar 
4b. Jos tarvitset enemmän muistitilaa ohjelman suoritukselle, ala tiedosto esim komennolla: 
    java -Xmx4096m -jar target/Pakohuone-1.0-SNAPSHOT-jar-with-dependencies.jar 
 
### Käyttöliittymän tulkinta:

#### Luodaan labyrintti
    Antamalla komennoksi jotakin valmista labyrinttia vastaavan luvun 1 - 5, luot valmiin labyrintin
    komennolla 6 luot itse käsin labyrintin (tarkemmin kohdassa "Labyrintin rakentaminen käsin")
    
#### Tarkastellaan tällä hetkellä käytössä olevaa labyrinttia:

    1 = Luo uusi labyrintti jota tutkitaan. Tutkittavana voi olla yksi labyrintti kerrallaan, eli se joka on viimeksi luotu.
        1 = 12\*11 labyrintti, jossa 4 huonetta ja 4 avainta1
        2 = 14\*19 labyrintti jossa 6 huonetta 7 avainta
        3 = 14\*14 labyrintti jossa 9 huonetta ja 10 avainta (vähän ratkaisuja)
        4 = 14\*14 labyrintti jossa 9 huonetta ja 10 avainta (suuri määrä ratkaisuja, suuri aikavaativuus)
        5 = 19\*24 labyrintti jossa 20 huonetta ja 20 avainta (vähän ratkaisuja, suuri muistivaativuus)
        6 = rakenna itse
            -> Käynnistä labyrintin rakenaminen käsin (ks. "Labyrintin rakentaminen käsin")
            
    2 = Tulosta labyrintti
        1 = Tulostetaan labyrintin sisältö
        2 = Tulostetaan labyrintin huoneet
        
    3 = Etsi reitit 
        1 = reitien määrä
        2 = reittien tulostus
        
    4 = Etsi lyhin reitti
        Tulostetaan lyhin reitti
        Tulostetaan lyhimmän reitin pituus
        
    5 = Aikavaativuusilmoitukset päälle/pois
        Sovelluksen algoritmien aikavaativuuksia on mahdollista seurata tämän työkalun avulla.
        Kun aikavaativuusilmoitukset on päällä, jokaisen pääkomennon (paitsi tulostusen) jälkeen kerrotaan kuinka kauan sen toteuttamisessa kesti.
        Raportoitu kesto ei ota huomioon tulostukissa kuluvaa aikaa. (huom. reitienetsinnässä komento 2 onn aikaavievämpi, koska siinä luodaan pitkä merkkijono, jota komennolla 1 ei luoda.
        Aikavaativuusilmoitukset ovat lähtötilanteessa pois päältä.
        
    6 = Keskeytä
        Ohjelman suoritus päättyy
        
#### Labyrintin rakentaminen käsin:

    Anna halutun labyrintin korkeus (2 - 50)
    Anna halutun labyrintin leveys (2 - 50)
    
    1 = Luo seinä
    (koko operaation ajan komento 99 keskeyttää seinän rakentamisen)
        1 = Luo pystysuora seinä
            Anna halutun seinän sijainti x-akselilla (ei saa olla reunassa eikä toisen pystysienän vieressä)
        2 = Luo vaakasuora seinä
            Anna halutun seinän sijainti y-akselilla (ei saa olla reunassa eikä toisen vaakasienän vieressä)
            
    2 = Luo avain ja ovi (huom ei voi luoda ellei labyrintissa ole seinäruutuja joihin voia voi rakentaa 
    (koko operaation ajan komento 99 keskeyttää seinän rakentamisen)
        Anna halutun avaimen sijainti x-akselilla
        Anna halutun avaimen sijainti y-akselilla 
        (Avain ei saa olla seinän päällä, eikä labyrintin lähdössä tai maalissa)
        Anna luotua avainta vastaavan oven sijainti x-akselilla 
        Anna luotua avainta vastaavan oven sijainti y-akselilla 
        (Oven tulee olla seinäruudun päällä, mutta ei toisen oven päällä)
        
    3 = Labyrintti on valmis. Annetaan se käyttöliittymälle tutkittavaksi.
    
##### Huomioita:
**Muistivaatimukset:**<br>
Jos sovellukselle vaatii liikaa muistia, se kaatuu virheeseen (stackoverflow). Helppo keino toteuttaa tämä on etsiä labyrintistä useita kertoja peräkkäin kaikkia reittejä. Algoritmin on varauduttava siihen että kaikki mahdolliset ja kaiken pituiset avainpermutaatiot ovat mahdollisia ratkaisuja, mutta kun avaimia on esim. 20, niistä voi muodostaa lähes 21! erilaista avainyhdistelmää.<br>
Tämän ongelman helpottamiseksi ohjelma valmistautuu käsittelemään korkeintaan 650 000 000 erilaista ratkaisua jotta muistitila riittäisi. Suurimmassa osassa labyrinteistä oikea ratkaisujen määrä on häviävän pieni verrattuna worst case scenarioon jossa ratkaisuja on lähes (a+1)!, missä a = avainten määrä. Esimerkkilabyrintti 4, jossa on 10 heti saavutettavissa olevaa avainta, on luotu sillä ajatuksella että se vielä selviää juuri ja juuri muistivaatimuksista.<br>
Sovelluksella käytössä olevan muistin määrää voi säätää vivulla kun ohjelma käynnistetään komentoriviltä. Esim jos tahdotaan antaa sovellukselle 4 gigatavua muistia, lisätään komentoon vipu "-Xmx4096m". <br>

**Aikavaativuus:** <br>
 **Labyrintin luomisen aikavaativus** riippuu lähes pelkästään labyrintin koosta. Suuri avainten ja ovien määrä kasvattaa sitä.<br>
 **Reittien etsinnän aikavaativuus** riippuu pääosin mahdollisten kuljettavien (ei välttämättä maaliin johtavien) reittien määrästä. Jos paljon avaimia on saatavilla aikaisessa vaiheessa, mahdollisia reittejä on paljon<br>
 **Parhaan reitin etsinnän aikavaativuus** riippuu maaliin johtavien reittien määrästä sekä niiden pituuksista (Suuri kuljettavien ovien määrä kasvattaa reittien pituuksia.)<br>
