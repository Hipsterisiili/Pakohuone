Viikon 4 aikana toteutin sovellukseen ehkä sen monimutkaisimman algoritmin, joka selvittää kaikki mahdolliset järjestykset, joissa avaimet voi ruudukosta hakea. 

### Nykytilaisen algoritmin tulostus (joka sisältää kaikki sillä hetkellä muodostetut sanat)

Labyrintti, jolla algoritmia testataan main-luokassa: 

![Kuva 1](https://raw.githubusercontent.com/Hipsterisiili/Pakohuone/master/Dokumentointikansio/Kuvat/Ohjelman_syote_viikko2.png)

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

![Osa1](https://github.com/Hipsterisiili/Pakohuone/blob/master/Dokumentointikansio/Kuvat/vko4Syote1.jpg)

![Osa2](https://raw.githubusercontent.com/Hipsterisiili/Pakohuone/master/Dokumentointikansio/Kuvat/vko4syote.jpg)

