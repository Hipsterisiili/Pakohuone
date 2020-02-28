# Testausdokumentti

Testaaminen toteutetaan JUNIT-kirjaston työkalujen avulla lukuisien yksikkötestien muodossa. Testikattavuutta voi seurata projektitiedostoon lisätynn jacoco-työkalun avulla. 

## Ohjeet testaamiseen

Projektin testikattavuuden saa selvittettyä komentorivillä projektin juurikansiossa (sama kansio, jossa sijaitsee mm. tiedosto pom.xml) Jotta ohjelmaa voi testata, se tulee ensin rakentaa komennolla *mvn compile*. Kun ohjelma on rakennettu, sen testit voi ajaa komennolla *mvn test* ja tämän jälkeen testikatavuusraportti sijaitsee html-tiedostona sijainnissa *target/site/jacoco/index.html*. Raportin voi avata esimerkiksi firefox-verkkoselaimella komennolla *firefox index.html*

## Testien nykytilanne

Tällä hetkellä projektin testikattavuus on kokonaisuudessaan 66%. Tämä luku kuulostaa todellista tilannetta huonommalta, sillä siihen lasketaan mukaan myös main-pakkauksen tiedostojen testikattavuus. Eism kaikkia Main-paketin luokkia ei ole tarkoituksenmukaista testata kovin syvällisesti, sillä sen sisäiset toiminnallisuudet muuttuvat todella paljon eivätkä ne ole ohjelman kasassa pysymisen kannalta tärkeimpiä 

    pakohuone.Main: testikattavuus: 92%

    pakohuone.algoritmit: testikattavuus: 90%

    pakohuone.tyokalut: testikattavuus 22%

    pakohuone.sovelluslogiikka: testikattavuus 90%
