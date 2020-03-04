# Testausdokumentti

Kaikki alla esitellyt komennot voi ajaa projektin juurikansiossa (sama kansio, jossa sijaitsee tiedosto pom.xml) Esimerkkikomennoissa avataan .html-tiedostot firefoxilla, mutta luonnollisesti muutkin selaimet ajavat asian.


## Ohjeet testaamiseen

### Yksikkötestaus
Testaaminen toteutetaan JUNIT-kirjaston työkalujen avulla lukuisien yksikkötestien muodossa. Testikattavuutta voi seurata projektitiedostoon lisätyn jacoco-työkalun avulla. 

Projektin testikattavuuden saa selvittettyä komentorivillä projektin juurikansiossa. Jotta ohjelmaa voi testata, se tulee ensin rakentaa komennolla *mvn compile*. Kun ohjelma on rakennettu, sen testit voi ajaa komennolla *mvn test* ja tämän jälkeen testikatavuusraportti sijaitsee html-tiedostona sijainnissa *target/site/jacoco/index.html*. Raportin voi avata esimerkiksi firefox-verkkoselaimella komennolla *firefox index.html*. 

### Aikatestaus

Algoritmien ajankäyttöä voi seurata käyttöjärjestelmässä pyytämällä aikavaativuusraportteja komennolla 5

### Siisteys

Kun projekti on ensin rakennettu komennolla *mcn compile package*, javadocin voi generoida komennolla *mvn javadoc:javadoc* ja tämän jälkeen sitä voi tarkastella komennolla *firefox target/site/apidocs/index.html*.

Projektin checkstyle-muotoilu mahdollistetaan komennolla *mvn jxr:jxr checkstyle:checkstyle*. Tämän jälkeen checkstyleä voi tarkastella komennolla *firefox target/site/checkstyle.html*.
Checkstyle-virheitä on koodissa paljon, sillä en ole vaivautunut perehtymään kovin syvällisesti checkstylen configurointiin kurssin aikana. Yritin muuttaa checkstyleä vähemmän vaativaksi, jotta se karsisi vain virheet, jotka olen aikeissa korjata, mutta en tahtonut saada ominaisuutta toimimaan. Tämän vuoksi checkstyle-raportti sisältää paljon mielestäni turhia virheitä, kuten puutteellista javadoc-dokumentaatiota joissakin muuttujissa tai magic numberien käyttö.


## Testien nykytilanne

Tällä hetkellä projektin testikattavuus on kokonaisuudessaan 96%. Tämä luku kuulostaa todellista tilannetta huonommalta, sillä siihen lasketaan mukaan myös main-pakkauksen tiedostojen testikattavuus. Esim. kaikkia Main-paketin luokkia ei ole tarkoituksenmukaista testata kovin syvällisesti, sillä sen sisäiset toiminnallisuudet muuttuvat todella paljon eivätkä ne ole ohjelman kasassa pysymisen kannalta tärkeimpiä 

    pakohuone.Main: testikattavuus: 92%

    pakohuone.algoritmit: testikattavuus: 99%

    pakohuone.tyokalut: testikattavuus 96%

    pakohuone.sovelluslogiikka: testikattavuus 99%

Tyokalut-paketin testaus on näyttävästä testikattavuusraportista huolimatta mielestäni vielä erittäin vaiheessa, sillä suurten luokkien "verkko" ja "dijkstra" testaamiseen ei ole toteutettu pieniä ykisttäistestejä vaan niiden testaus taoahtuu testatessa isompia algoritmeja, jotka käyttävät niitä. 
