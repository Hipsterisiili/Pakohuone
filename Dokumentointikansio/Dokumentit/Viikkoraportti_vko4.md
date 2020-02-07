Viikon 4 aikana toteutin sovellukseen ehkä sen monimutkaisimman algoritmin, joka selvittää kaikki mahdolliset järjestykset, joissa avaimet voi ruudukosta hakea. 

### Nykytilaisen algoritmin tulostus (joka sisältää kaikki sillä hetkellä muodostetut sanat)

Labyrintti, jolla algoritmia testataan main-luokassa: 

x 1 2 3 4 5 6 7 8 9 10 11 12
@ @ @ @ @ @ @ @ @ @ @ @ @ @   y
@ + . . . . @ . . . . . . @   1
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
@ @ @ @ @ @ @ @ @ @ @ @ @ @   12

Muodostettavat 4 kirjaimen mittaiset sanat: ABCD, ABDC, ADBC, DABC

(ONGELMA:)
Jostakin syystä toistaiseksi algoritmi ei osaa vielä muodostaa sanaa ADBC, syy lienee se että jostakin syystä tätä aiemmin Luokassa pakohuone.tyokalut.ReittienEtsija olevassa taulukossa int[] avaimetTarjolla avainta c vastaava arvo avaimetTarjolla[2] pienenee jostakimn syystä aikaisemmissa toteutusvaiheissa alle 1:n (Kyseisessä taulukossa jos esim avaimeen a tai b löytyy uusi reitti, niin avaimetTarjolla[0] tai avaimetTarjolla[1] kasvaa yhdellä luvulla. samoin jos yksi reitti avaimeen poistetaan, arvoa pienennetään yhdellä. Jos avaimetTarjolla[n] < 0, sitä vastaavaa avainta ei ole vieläsaavutettavissa algoritmin senhetkisessä suoritusvaiheessa.

#### Merkinnät: 
> HAJAANNU aloitetaan metodi hajaannu ---- metodin hajaannu lopettamisesta ei tehdä erillistä tulostetta
> Ydistahuoneet -------------- ALKU // aloitetaan metodi yhdistähuoneet ja aletaan muodostaa
> Ydistahuoneet -------------- LOPPU // loetetaan metodi yhdistähuoneet
> ONJAEI alku // EIJAEI alki ------------ metodien onjaei ja eijaei aloittaminen
> ONJAEI loppu // EIJAEI loppu ------------ metodien onjaei ja eijaei lopettaminen
> numero/boolean -------------------------- avaimetTarjolla[2] (montako yhteyttä C:hen tähän asti on luotu) // onko avainta c vielä lisätty kirjainpinoon

HAJAANNU

YhdistaHuoneet ------------- ALKU // sana: a
0//false
ONJAEI ALKU (1,3)

HAJAANNU

YhdistaHuoneet ------------- ALKU // sana: ab
0//false
ONJAEI ALKU (1,2)

HAJAANNU

YhdistaHuoneet ------------- ALKU // sana: abc
1//true
ONJAEI ALKU (2,4)

HAJAANNU

YhdistaHuoneet ------------- ALKU // sana: abcd
1//true
YhdistaHuoneet ------------- LOPPU // sana: abcd
ONJAEI LOPPU (2,4)
YhdistaHuoneet ------------- LOPPU // sana: abc
YhdistaHuoneet ------------- ALKU // sana: abd
1//false
ONJAEI ALKU (3,4)

HAJAANNU

YhdistaHuoneet ------------- ALKU // sana: abdc
1//true
YhdistaHuoneet ------------- LOPPU // sana: abdc
ONJAEI LOPPU (3,4)
YhdistaHuoneet ------------- LOPPU // sana: abd
ONJAEI LOPPU (1,2)
YhdistaHuoneet ------------- LOPPU // sana: ab
YhdistaHuoneet ------------- ALKU // sana: ad
0//false
ONJAEI ALKU (3,4)

HAJAANNU

YhdistaHuoneet ------------- ALKU // sana: adb
0//false
YhdistaHuoneet ------------- LOPPU // sana: adb
ONJAEI LOPPU (3,4)
YhdistaHuoneet ------------- LOPPU // sana: ad
ONJAEI LOPPU (1,3)
YhdistaHuoneet ------------- LOPPU // sana: a
YhdistaHuoneet ------------- ALKU // sana: d
0//false
EIJAEI LOPPU (3,4)

HAJAANNU

YhdistaHuoneet ------------- ALKU // sana: da
0//false
ONJAEI ALKU (1,3)

HAJAANNU

YhdistaHuoneet ------------- ALKU // sana: dab
0//false
ONJAEI ALKU (1,2)

HAJAANNU

YhdistaHuoneet ------------- ALKU // sana: dabc
1//true
ONJAEI ALKU (2,4)

HAJAANNU

ONJAEI LOPPU (2,4)
YhdistaHuoneet ------------- LOPPU // sana: dabc
ONJAEI LOPPU (1,2)
YhdistaHuoneet ------------- LOPPU // sana: dab
ONJAEI LOPPU (1,3)
YhdistaHuoneet ------------- LOPPU // sana: da
EIJAEI LOPPU (3,4)
POISTETAAN AVAIN x: 7 y: 4
Oven sijainti x: 3 y: 6
POISTETAAN AVAIN x: 7 y: 4
Oven sijainti x: 3 y: 6
YhdistaHuoneet ------------- LOPPU // sana: d
