Viikon 4 aikana toteutin sovellukseen ehkä sen monimutkaisimman algoritmin, joka selvittää kaikki mahdolliset järjestykset, joissa avaimet voi ruudukosta hakea. Koko prosessin aloittaa metodi pakohuone.tyokalut.ReittienEtsinta.etsiReitteja() Luokan laskenta tapahtuu rekursiivisesti kolmen sisäkkäisen toisiaan kutsuvan metodin avulla luokassa pakohuone.tyokalut.ReittienEtsinta.  Nämä kolme metodia ovat hajaannu(), yhidstaHuoneet(int a, int b) sekä vaihtoehtoisesti joko OnJaEi(int a, int b) tai EiJaEi(int a, int b). 

Lopulta prosessi tuottaa listan merkkijonoja, jotka kuvaavat mahdollisia tapoja poimia avaimia tavalla joka johtaa maaliin pääsemiseen, esimerkiksi "ABCD" sekä "BD". Merkkijonon muodostamista varten toteutin läheisesti pinoa muodpostavan tietorakenteen pakohuone.tyokalut.KirjainPino. 

### Miten rekursio tapahtuu?

1. Luodaan pakohuone.tyokalut.ReittienEtsija
2. Kutsutaan metodia ReittienEtsija.Etsi()
3. Etsi() tekee avainListasta riittävän pituisen (pituuden on oltava avaintenMaara! pituinen, koska se on pahin mahdollinen tilanne, jossa kaikki kuviteltavissa olevien avainten järjestykset ovat mahdollisia
4. Etsi() kutsuu metodia Etsireitteja()
5. EtsiReitteja() merkitsee alkutilanteessa saatavilla olevat avaimet listaan int[] avaimetSaatavilla.
6. Etsireitteja() kutsuu metodia hajaannu(), ja ALKAA # REKURSIO #

7. Hajaannu() poimii KirjainPinoon vapaita avaimia ja kutsuu jokaisen poiminnan jälkeen yhdistaHuoneet(int a, int b). (a = tarkasteltua avainta vastaavan oven toisella puolella olevan huoneen numero, b toista puolta vastaava numero) Kun yhdistaHuoneet() on tehty, poistetaan KirjainPinosta tuorein kirjain ja lisätään toinen vapaa kirjain sen tilalle ja kutsutaan taas yhdistaHuoneet(int a, int b) jne
8. YhdistaHuoneet(int a, int b) tarkastelee mitkä huoneista a ja b on jo vierailtu ja sen mukaan joko
A) Jos molemmat on jo vierailtu, ei tee mitään.
  Kutsutaan metodia Hajaannu() uudelleen;
B) Jos toinen on vierailtu, kutsutaan metodia OnJaEi(int a, int b) (a = vieraillun huoneen numero, b = uuden huoneen numero)
  Lisätään uudet saavutettavat avaimet boolean[] avaimetTarjolla -taulukkoon;
C) Jos kumpaakaan ei ole vierailtu, kutsutaan metodia EiJaEi(int a, int b) (a = vieraillun huoneen numero, b = uuden huoneen numero)
  
  
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

> numero/boolean -------------------------- avaimetTarjolla[2] (montako yhteyttä C:hen tähän asti on luotu) // onko avainta c vielä > lisätty kirjainpinoon


![Osa1](https://github.com/Hipsterisiili/Pakohuone/blob/master/Dokumentointikansio/Kuvat/vko4Syote1.jpg)

![Osa2](https://raw.githubusercontent.com/Hipsterisiili/Pakohuone/master/Dokumentointikansio/Kuvat/vko4syote.jpg)

