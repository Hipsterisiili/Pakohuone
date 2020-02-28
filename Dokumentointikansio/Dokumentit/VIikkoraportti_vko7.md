### Vko7

Reittien etsinnän toiminnallisuus on käytännössä valmis. Koodi vaatii enää täydennystä pääosin käyttöliittymän sekä testauksen osa-alueilla. Aion vielä tehdä runsaasti testausta käsin erilaisilla labyrinteillä löytääkseni koodista mahdollisia korjauksen tarpeessa olevia bugeja.

Labyrintin toiminnassa on jo havaittu yksi bugi: Jos mahdollisia reittejä ei ole, ohjelma ei suoriudu loppuun asti. Ongelman paikallistaminen ei liene kovin tuskallinen prosessi, mutta nopealla vilkaisulla en sitä saanut paikannettua.

Viikon aikana täydensin projektin yksikkötestejä merkittävästi ja nyt jacoco-raportti antaa koko projektin testikattavuudeksi 96%. Testit eivät ole kuitenkaan vielä täysin valmiit, aion tarkentaa niitä vielä ja todennäköisesti tulen vielä kirjoittamaan lisää testausta vaativaa koodia.

Olen huomannut että ohjelman toiminta alkaa hidastua jo merkittävästi kun labyrintissä on aikaisessa vaiheessa saatavilla useita avaimia, sillä tällöin käsittelyä vaativia avainpermutaatioita tulee paljon, pahimmassa tilanteessa avainten määrän (a) kertoman (a!) verran. Koska kutakin reittiä käsitellään yhä enemmän rekursioita hyödyntävillä työkaluilla, kasvaa ohjelman suoritusaika jo merkittävästi. 

Seuraava labyrintti (joka on suunniteltu erittäin kuormittavaksi osoittautui liian raskaaksi suorittaa, tapahtui keon ylivuoto java.lang.OutOfMemoryError: Java heap space

    x 1 2 3 4 5 6 7 8 9 101112131415
    # # # # # # # # # # # # # # # #   y
    # + a f k # . . . . . . # . . #   1
    # . b g l # . . . . . . # . . #   2
    # . c h m # . . . . . . # . . #   3
    # . d i n # . . . . . . # . . #   4
    # . e j o # . . . . . . # . . #   5
    # A B C D # # # # # # # # # # #   6
    # . . . . # . . . . . . # . . #   7
    # . . . . # . . . . . . # . . #   8
    # . . . . # . . . . . . # . . #   9
    # E F G H # # # # # # # # # # #   10
    # . . . . I . . . . . . M . . #   11
    # . . . . J . . . . . . N . . #   12
    # . . . . K . . . . . . O . . #   13
    # . . . . L . . . . . . # . * #   14
    # # # # # # # # # # # # # # # #   15 
    
Olen myös testannut viikolla ohjelman suorituskykyä kolmella testilabyrintilla, tästä tarkemmin toteutusdokumentissa.
