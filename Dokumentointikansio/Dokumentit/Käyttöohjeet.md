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

##### Luodaan labyrintti
    Antamalla komennoksi jotakin valmista labyrinttia vastaavan luvun, luot valmiin labyrintin
    Voit myös luoda itse käsin labyrintin (tarkemmin kohdassa "Labyrintin rakentaminen käsin")
    
##### Tarkastellaan tällä hetkellä käytössä olevaa labyrinttia:

        1 = Luo uusi labyrintti jota tutkitaan. Tutkittavana voi olla yksi labyrintti kerrallaan, eli se joka on viimeksi luotu.
        1 = 12\*11 labyrintti, jossa 4 huonetta ja 4 avainta1
        2 = 14\*19 labyrintti jossa 6 huonetta 7 avainta
        3 = 14\*14 labyrintti jossa 9 huonetta ja 10 avainta (vähän ratkaisuja)
        4 = 14\*14 labyrintti jossa 9 huonetta ja 10 avainta (suuri määrä ratkaisuja)
        5 = 19\*24 labyrintti jossa 20 huonetta ja 19 avainta (vähän ratkaisuja, paljon vaadittavaa muistitilaa)
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
    5 = Keskeytä
        Ohjelman suoritus päättyy
        
##### Labyrintin rakentaminen käsin:

