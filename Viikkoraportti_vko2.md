### Ohjelman rakenne

<c> Viikon aikana viimeistelin algoritmien alustavan suunnittelun. Aion pitäytyä määrittelydokumentin mukaisessa arkkitehtuurissa. Ohjelman rakentamisessa sain "ensimmäisen algoritmin" rakennettua todennäköisesti lähes viimeiseen muotoonsa. Nyt sovellus kykenee erittelemään annetusta labyrintistä huoneet seuraavia vaiheita varten. Eritellyistä huoneista luodaan olioita. <c>
  
<c> Ohjelma sisältä jo joitakin toisen algoritmin toteuttamiseen liittyviä toiminnallisuuksia. Samassa luokassa, jossa huoneita eritellään, tunnistetaan myös avaimien sijainnit sekä niiden yhteydet vastaaviin oviin. Lisäksi ovet ja avaimet luodaan olioiksi. Olen koodannut myös työkalun, joka laskee etäisyyksiä ruutujen välillä. Tämä tulee hyödylliseksi myöhempien algoritmien rakentamisessa. <c>

<c>Tämänhetkisessä palautuksessa ohjelman main-luokassa sijaitsee koodi, joka luo testilabyrintin ja sen jälkeen suoraan tarkastelee sitä. Ohjelma luo olion Labyrintti, joka saa parametrikseen luodun labyrintin. Laskenta tapahtuu pääosin Labyrintti-luokan sisällä. <c>

![Kuva 1](https://raw.githubusercontent.com/Hipsterisiili/Pakohuone/master/Ohjelman_syote_viikko2.png)

<c>Kuvassa näkyy tulostus, jonka nykyinen ohjelma antaa main-luokassa olevalle testilabyrintille. Ylemmässä ruudukossa on eritelty kukin ruutu huoneen mukaan (numeroitu 1-4) ja alemmassa ruudukossa on kuva koko labyrintista.<c>

### Haasteita

<c>Jostakin syystä en saa netbeansissa itse ajettua projektitiedostoa, kun yritän sitä, ohjelma palauttaa seuraavan 
virheraportin:

>Plugin org.apache.maven.plugins:maven-compiler-plugin:3.3 or one of its dependencies could not be resolved: 
>Failed to read artifact descriptor for org.apache.maven.plugins:maven-compiler-plugin:jar:3.3: Could not 
>transfer artifact org.apache.maven.plugins:maven-compiler-plugin:pom:3.3 from/to central 
>(http://repo.maven.apache.org/maven2): Failed to transfer file: 
>http://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-compiler-plugin/3.3/maven-compiler-plugin-3.3.pom. 
>Return code is: 501 , ReasonPhrase:HTTPS Required. -> [Help 1]

Arvelen että minulla on joko jotakin vialla projektitiedostossa, tai omalle koneelle asennetussa mavenissa. Tätä täytynee tarkastella esimerkiksi ensi viikon laskareissa. Rakensin kuitenkin ohjelman viikkopalautuksen mukaiseen muotoonsa toisessa tiedostossa ja refaktoroin sen palautusrepositoriooni.<c>

### Testaus

<c> Aloitin viikon 2 aikana ohjelman testaamisen JUNIT-kirjaston työkalujen avulla. Johtuen yllä mainituista vaikeuksista, en ole tehnyt testikattavuusraporttia palautusrepositorioon, sillä ohjelman ajaminen palautusrepositoriossa ei ole vielä onnistunut. Mielestäni testikattavuus on jopa melko hyvä näin varhaisessa vaiheessa ohjelman kehitystä. Lähes kaikkia toiminnallisuuksia testataan jollakin tapaa, mutta mielestäni en ole vielä eritellyt riittävästi haastavia erikoistapauksia. Testejä voisi toteuttaa myös keskittyen pienempiin ykisttäisiin toiminallisuuksiin, jotta testien tulokset olisivat yksiselitteisempiä. <c>
  
### Mitä on jäänyt käteen?

<c>Viikon aikana on koitunut hieman haasteita ohjelman järkevän rakenteen hahmottelun kanssa, mutta koen kehittyneeni, erityisesti muutamien virheiden kautta skaalautuvan, muokattavan ja helppolukuisen koodin rakentamisessa. Koska suunnittelemani ohjelma sisältää suuren määrän toisistaan riippuvaisia toiminnallisuuksia, on eirtyisen tärkeää, että jo kerran tehtyyn koodiin olisi helpompi kajota uudelleen myöhemmillä viikoilla, ilman suurempaa päänvaivaa.<c>

<c>Projektitiedostojen hallinta on osoittautunut aikaisemminkin minulle haastavaksi aiheeksi aiemminkin, joten on todennäköistä että ohjelman ajaminen ei onnistu johtuen jostakin virheestä esimerkiksi pom.xml-tiedostossa. Tietysti tämankaltaisten projektien parissa työskentely tekee jatkivasti siitäkin tutumpaa.<c>
