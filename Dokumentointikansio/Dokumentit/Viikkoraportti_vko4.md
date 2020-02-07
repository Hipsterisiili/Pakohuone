Viikon 4 aikana toteutin sovellukseen ehkä sen monimutkaisimman algoritmin, joka selvittää kaikki mahdolliset järjestykset, joissa avaimet voi ruudukosta hakea


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
