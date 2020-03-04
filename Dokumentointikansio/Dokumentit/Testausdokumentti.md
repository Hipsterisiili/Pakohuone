# Testausdokumentti

Kaikki alla esitellyt komennot voi ajaa projektin juurikansiossa (sama kansio, jossa sijaitsee tiedosto pom.xml) Esimerkkikomennoissa avataan .html-tiedostot firefoxilla, mutta luonnollisesti muutkin selaimet ajavat asian.


## Ohjeet testaamiseen

### Yksikkötestaus
Testaaminen toteutetaan JUNIT-kirjaston työkalujen avulla lukuisien yksikkötestien muodossa. Testikattavuutta voi seurata projektitiedostoon lisätyn jacoco-työkalun avulla. 

Projektin testikattavuuden saa selvittettyä komentorivillä projektin juurikansiossa. Jotta ohjelmaa voi testata, se tulee ensin rakentaa komennolla **mvn compile**. Kun ohjelma on rakennettu, sen testit voi ajaa komennolla **mvn test** ja tämän jälkeen testikatavuusraportti sijaitsee html-tiedostona sijainnissa **target/site/jacoco/index.html**. Raportin voi avata esimerkiksi firefox-verkkoselaimella komennolla **firefox target/site/index.html**. 

### Testikattavuus:

Koko projektin testikattavuus:
![Kaikki](https://raw.githubusercontent.com/Hipsterisiili/Pakohuone/master/Dokumentointikansio/Kuvat/Testikattavuus1.jpg)

Käyttöliittymän testaus:
![ui](https://github.com/Hipsterisiili/Pakohuone/blob/master/Dokumentointikansio/Kuvat/Testikattavuus2.png?raw=true)
Labyrinttien luominen on testattu. Main-luokan ja käyttöliittymäluokkien testaaminen ei ole mielekästä, sillä main:ssa ei ole testattavaa koodia ja käyttöliittymän suoritus riippuu käyttäjän komennoista ja niiden generoiminen testejä varten ei ole mielekästä.

Algoritmien testaus:
![algoritmit](https://raw.githubusercontent.com/Hipsterisiili/Pakohuone/master/Dokumentointikansio/Kuvat/testikattavuus3.png)
Kaikki algoritmien laskennalliset toiminnallisuudet on testattu. Ainoastaan verkon tulostava metodi (joka ei palauta mitään vaan ainoastaan tulostaa) on testaamatta, samoin kaksi tuhoisaa virheitä tarkistavaa if-looppia, jotka keskeyttävät toiminnan. Niihin ei ole tarkoituskaan päästä sisään ohjelmaa käyttäessä enkä usko että sellaista virhettä on mahdollista myöskään saada aikaan. Ne on silti hyvä tarkistaa koodissa.
99% testikattavuus

Työkalujen testaus:
![työkalut](https://raw.githubusercontent.com/Hipsterisiili/Pakohuone/master/Dokumentointikansio/Kuvat/Testikattavuus4.png)
Kaikki toiminnallisuudet on testattu. Ainoastaan verkko-luokan debuggaamiseen käytetty tulosta()-metodi on testaamatta, samoin yksi virheentarkistus (samanlainen kuin algoritmipaketissa) on testaamatta, sillä niitä ei voi testata.
99% testikattavuus

Sovelluslogiikka testaus:
![sovelluslogiikka](https://raw.githubusercontent.com/Hipsterisiili/Pakohuone/master/Dokumentointikansio/Kuvat/Testikattavuus5.png)
100% testikattavuus


### Aikatestaus

Algoritmien ajankäyttöä voi seurata käyttöjärjestelmässä pyytämällä aikavaativuusraportteja komennolla 5

Lopullisen toteutuksen aikavaativuudet poikkeavat jonkin verran alunperin määrittelydokumentissa lasketuista (Alla ne laskettuna tarkemmin)

**Labyrintin luomisen** aikavaativus riippuu lähes pelkästään labyrintin koosta. Suuri avainten ja ovien määrä kasvattaa sitä.
Aikavaativuus on siis *O(x\*y)*, missä x ja y ovat labyrintin mitat

**Reittien etsinnän** aikavaativuus riippuu pääosin mahdollisten kuljettavien (ei välttämättä maaliin johtavien) reittien määrästä. Jos paljon avaimia on saatavilla aikaisessa vaiheessa, mahdollisia reittejä on paljon
Aikavaativuus on siis *O ((a a) + (a a-1) + (a a-2) ... + (a 1))* (missä a = avainten määrä ja (x y) kuvaa "montako y kokoista permutaatiota x alkiosta voi muodostaa) 

**Parhaan reitin etsinnän** aikavaativuus riippuu maaliin johtavien reittien määrästä sekä niiden pituuksista (Suuri kuljettavien ovien määrä kasvattaa reittien pituuksia.) Dijkstran aikavaativuus on O ( n + (n^2 \* log(n^2) ) missä n = avainten määrä. (kaaria on pahimmassa tapauksessa n^2 eli jokaisesta avaimesta ja ovesta jokaiseen avaimeen ja oveen) Jokaiselle reitille toteutetaan dijkstran algoritmi niin monta kertaa kuin reitillä on avaimia + 1 (matka viimeisestä avaimesta maaliin) 
Aikavaativuus on siis *O( X \* (a+1) (a + (a^2 \* log(a^2) ) )* (missä a kuvaa avainten määrää, X kuvaa reittien etsinnän aikavativuutta)

Tosiasiassa mikään tämän algoritmin todellinen toteutus ei yllä edes tämän worst case scenarion murto-osaan, sillä se worst case scenario on laskettu käytännössä mahdottomasta tilanteesta, jossa jokainen avainpermutaatio johtaa maaliin ja jokainen avainpermutaatio sisältää kaikki avaimet sekä jokaisen avaimen ja oven välisen matkan kulkemiseksi täytyy kulkea jokaisen avaimen ja oven kautta. 

### Siisteys

Kun projekti on ensin rakennettu komennolla **mvn compile package**, javadocin voi generoida komennolla **mvn javadoc:javadoc** ja tämän jälkeen sitä voi tarkastella komennolla **firefox target/site/apidocs/index.html**.

Projektin checkstyle-muotoilu mahdollistetaan komennolla **mvn jxr:jxr checkstyle:checkstyle**. Tämän jälkeen checkstyleä voi tarkastella komennolla **firefox target/site/checkstyle.html**.
Checkstyle-virheitä on koodissa paljon, sillä en ole vaivautunut perehtymään kovin syvällisesti checkstylen configurointiin kurssin aikana. Yritin muuttaa checkstyleä vähemmän vaativaksi, jotta se karsisi vain virheet, jotka olen aikeissa korjata, mutta en tahtonut saada ominaisuutta toimimaan. Tämän vuoksi checkstyle-raportti sisältää paljon mielestäni turhia virheitä, kuten puutteellista javadoc-dokumentaatiota joissakin muuttujissa tai magic numberien käyttö.
