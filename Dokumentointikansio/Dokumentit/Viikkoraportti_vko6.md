### VKO6

Viikon aikana olen työstänyt ohjelman algoritmien toiminnan oikeellisuutta ja tehokkuutta. Kaikkien reittien etsintä onnistuu nyt täydellisesti ja olen kovassa vauhdissa rakentamassa järjestelmää, joka selvittää reiteistä lyhimmän. 

Viikon aikana merkittävin uusi asia oli tietorakenne Verkko, joka ylläpitää tietoa siitä kuinka pitkä masta kustakin avaimesta ja ovesta on kuhunkin avaimeen ja oveen sekä mitä näistä poluista on sallittua kulkea. Myös lyhintä reittiä etsivä olio NopeimmanReitinEtsija on pitkälti toteutettu. Ainoastaan polkujen tehokas kulkeminen on enää toteuttamatta sovelluksen perustoiminnoista. 

Tässä esimerkki matriisista, jota olio Verkko pitää yllä:

* a b c d A B C D +

0 2 0 0 5 5 7 0 0 0   *

2 0 0 0 3 3 5 0 0 0   a

0 0 0 0 0 4 0 0 3 0   b

0 0 0 0 0 0 3 3 0 0   c

5 3 0 0 0 4 2 0 0 0   d

5 3 4 0 4 0 6 0 7 0   A

7 5 0 3 2 6 0 4 0 0   B

0 0 0 3 0 0 4 0 5 10  C

0 0 3 0 0 7 0 5 0 9   D

0 0 0 0 0 0 0 10 9 0  +


Tässä matriisi, joka kuvaa labyrinttia, johon yllä kuvattu verkko perustuu:
x 1 2 3 4 5 6 7 8 9 10 11 12

@ @ @ @ @ @ @ @ @ @ @ @ @ @   y
@ + . . . . @ . . . . . . @    1
@ . a . . . @ . c . . . . @   2
@ . . . d . B . . . . . . @   3
@ . . . . . @ . . . . . . @   4
@ @ A @ @ @ @ @ C @ @ @ @ @   5
@ . . . . . @ . . . . . . @   6
@ . . . b . @ . . . . . . @   7
@ . . . . . D . . . . . . @   8
@ . . . . . @ . . . . . . @   9
@ . . . . . @ . . . . . . @   10
@ . . . . . @ . . . . . * @   11
@ @ @ @ @ @ @ @ @ @ @ @ @ @   12

(Pienet kirjaimet a-d ovat avaimia, suuret kirjaimet A-D niitä vastaavia ovia. * kuvaa lähtöruutua, + kuvaa maalia.)

Kun toiminnallisuudet ovat valmiit aion keskittyä käyttöliittymän parantamiseen sekä uusien labyrinttien generoimisen helpottamiseen. Myös testaaminen sekä koodin siisteyden seuranta (esim. checkstyle) vaativat vielä tekemistä.
