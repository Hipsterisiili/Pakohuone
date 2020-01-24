VIIKKO 2

<c>Tämänhetkisessä palautuksessa ohjelman main-luokassa sijaitsee koodi, joka luo testilabyrintin ja sen jälkeen suoraan
tarkastelee sitä. Määrittlydokumentissa mainituista kolmesta algoritmista ensimmäinen on jo käytännössä toteutettu
vaatimusten mukaiseen muotoon eli ohjelma kykenee erittelemään huoneet ja niiden muodot annetusta taulukosta.<c>


![Kuva 1](https://raw.githubusercontent.com/Hipsterisiili/Pakohuone/master/Ohjelman_syote_viikko2.png)

Kuvassa näkyy tulostus, jonka nykyinen ohjelma antaa main-luokassa olevalle testilabyrintille. Ylemmässä
ruudukossa on eritelty kukin ruutu huoneen mukaan (numeroitu 1-4) ja alemmassa ruudukossa on kuva koko
labyrintista.


Jostakin syystä en saa netbeansissa itse ajettua projektitiedostoa, kun yritän sitä, ohjelma palauttaa seuraavan 
virheraportin:

>Plugin org.apache.maven.plugins:maven-compiler-plugin:3.3 or one of its dependencies could not be resolved: 
>Failed to read artifact descriptor for org.apache.maven.plugins:maven-compiler-plugin:jar:3.3: Could not 
>transfer artifact org.apache.maven.plugins:maven-compiler-plugin:pom:3.3 from/to central 
>(http://repo.maven.apache.org/maven2): Failed to transfer file: 
>http://repo.maven.apache.org/maven2/org/apache/maven/plugins/maven-compiler-plugin/3.3/maven-compiler-plugin-3.3.pom. 
>Return code is: 501 , ReasonPhrase:HTTPS Required. -> [Help 1]

Arvelen että minulla on joko jotakin vialla projektitiedostossa, tai omalle koneelle asennetussa mavenissa.


