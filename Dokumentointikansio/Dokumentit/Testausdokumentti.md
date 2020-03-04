# Testausdokumentti

Testaaminen toteutetaan JUNIT-kirjaston työkalujen avulla lukuisien yksikkötestien muodossa. Testikattavuutta voi seurata projektitiedostoon lisätynn jacoco-työkalun avulla. 

## Ohjeet testaamiseen

Projektin testikattavuuden saa selvittettyä komentorivillä projektin juurikansiossa (sama kansio, jossa sijaitsee mm. tiedosto pom.xml) Jotta ohjelmaa voi testata, se tulee ensin rakentaa komennolla *mvn compile*. Kun ohjelma on rakennettu, sen testit voi ajaa komennolla *mvn test* ja tämän jälkeen testikatavuusraportti sijaitsee html-tiedostona sijainnissa *target/site/jacoco/index.html*. Raportin voi avata esimerkiksi firefox-verkkoselaimella komennolla *firefox index.html*

## Testien nykytilanne

Tällä hetkellä projektin testikattavuus on kokonaisuudessaan 96%. Tämä luku kuulostaa todellista tilannetta huonommalta, sillä siihen lasketaan mukaan myös main-pakkauksen tiedostojen testikattavuus. Eism kaikkia Main-paketin luokkia ei ole tarkoituksenmukaista testata kovin syvällisesti, sillä sen sisäiset toiminnallisuudet muuttuvat todella paljon eivätkä ne ole ohjelman kasassa pysymisen kannalta tärkeimpiä 

    pakohuone.Main: testikattavuus: 92%

    pakohuone.algoritmit: testikattavuus: 99%

    pakohuone.tyokalut: testikattavuus 96%

    pakohuone.sovelluslogiikka: testikattavuus 99%

Tyokalut-paketin testaus on näyttävästä testikattavuusraportista huolimatta mielestäni vielä erittäin vaiheessa, sillä suurten luokkien "verkko" ja "dijkstra" testaamiseen ei ole toteutettu pieniä ykisttäistestejä vaan niiden testaus taoahtuu testatessa isompia algoritmeja, jotka käyttävät niitä. 
