### Vko7

Reittien etsinnän toiminnallisuus on käytännössä valmis. Koodi vaatii enää täydennystä pääosin käyttöliittymän sekä testauksen osa-alueilla. Aion vielä tehdä runsaasti testausta käsin erilaisilla labyrinteillä löytääkseni koodista mahdollisia korjauksen tarpeessa olevia bugeja.

Labyrintin toiminnassa on jo havaittu yksi bugi: Jos mahdollisia reittejä ei ole, ohjelma ei suoriudu loppuun asti. Ongelman paikallistaminen ei liene kovin tuskallinen prosessi, mutta nopealla vilkaisulla en sitä saanut paikannettua.

Viikon aikana täydensin projektin yksikkötestejä merkittävästi ja nyt jacoco-raportti antaa koko projektin testikattavuudeksi 96%. Testit eivät ole kuitenkaan vielä täysin valmiit, aion tarkentaa niitä vielä ja todennäköisesti tulen vielä kirjoittamaan lisää testausta vaativaa koodia.

Olen huomannut että ohjelman toiminta alkaa hidastua jo merkittävästi kun labyrintissä on aikaisessa vaiheessa saatavilla useita avaimia, sillä tällöin käsittelyä vaativia avainpermutaatioita tulee paljon, pahimmassa tilanteessa avainten määrän (a) kertoman (a!) verran. Koska kutakin reittiä käsitellään yhä enemmän rekursioita hyödyntävillä työkaluilla, kasvaa ohjelman suoritusaika jo merkittävästi. 
