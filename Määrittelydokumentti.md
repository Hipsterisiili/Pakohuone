# PAKOHUONE MÄÄRITTELYDOKUMENTTI<br>


### Ongelma:
1.) Voinko ratkaista kuvan 1.1. mukaisen labyrintin?<br>
2.) Jos voin, minkälaista polkua pitkin?<br>
3.) Mikä on nopein mahdollinen polku, joka ratkaisee labyrintin?<br>

![Kuva 1.1.](https://raw.githubusercontent.com/Hipsterisiili/Pakohuone/master/pakohuone_esimerkkihuone.jpg)

Kuva 1.1. esimerkki ratkaistavasta labyrintistä

### Säännöt:
<p>Labyrintissa voi edetä kerralla yhden askeleen johonkin neljästä ilmansuunasta, mikäli seinä (kuvassa mustaksi väritetty ruutu) ei ole tiellä. jotta "Ovesta" voi kulkea, on sitä ennen haettava oven kirjainta vastaava avain saapumalla sen sisältämään ruutuun.<p>
 
### Labyrintit
Tarkoitukseni on luoda käsin pieni joukko mielekkäitä testilabyrintteja, joita algoritmi pyrkii ratkaisemaan ja myöhemmmin sovellus, joka luo sattuanvaraisesti lisää testattavia labyrintteja. Labyrintin tallennusmuoto on kaksiuloitteinen taulukko char[x][y], jossa x on labyrintin leveys ja y on sen korkeus. Taulukon arvot kertovat mitä kyseisessä "ruudussa" on:
  
  > \# = Seinä<br>
  > . = Tyhjä ruutu<br>
  > 0 = Lähtöpiste<br>
  > 1 = Maali<br>
  > a,b,c = Avaimia<br>
  > A,B,C = Ovia<br>
  
  Alustavasti labyrintin huoneiden on tarkoitus olla nelikulmaisia huoneita, mutta jos loppukurssista jää aikaa sovelluksen täydentämiseen, on mahdollista että täydennän algoritmin toimintaa siten, että se osaa etsiä reittejä myös vaikeammissa huoneissa.
  
  **Ohjelman on tulkittava labyrinttia suuntaamattomana painotettuna verkkona, jotta algoritmien toteuttaminen on mahdollista.**
 
  ### Algoritmit
  
  **Vaiheessa 1** ohjelma selvittää huoneiden määrän, mitä ruutuja ja avaimia kukin huone sisältää, käyttäen **Union-find-rakennetta** ja tallentaen tieton labyrintin union-find-rakenteesta myöhempää käyttöä ja muokkaamista varten.
  
   Huoneita alkutilanteessa voi pahimmillaan olla standardini mukaisesa labyrintissä korkeintaan (x/2)\*(y/2), missä x = labyrintin leveys ja y = labyrintin korkeus.
  
  **Vaiheessa 2** ohjelma selvittää kaikki mahdolliset järjestykset, joissa avaimet voi noutaa niin että lopulta saavutaan maaliin. Tämä tapahtuu **syvyyshakua** (DFS) käyttämällä. Haku perustuu joka askeleella päivittyvään listaan tavoitettavissa olevia avaimia, siten että aina mentäessä astel syvemmälle, seurava vaihe saa uuden listan sillä hetkellä saatavilla olevista avaimista ja maaleista.
  **Kunkin avaimen on sisällettävä tieto siitä mnkä kahden ruudun välille se muodostaa yhteyden**
  
  Mahdollisia järjestyksiä voi olla 0 - n!, missä n = avainten määrä. Kukin mahdollinen järjestys tallennetaan erikseen listaksi, jotta niitä voidaan vertailla vaiheessa 3. Mikäli järjestyksiä on vain 0, ohjelma keskeytyy ja tiedämme että labyrintti ei ole ratkaistavissa.
  
  **Vaiheessa 3** Tutkitaan mikä vaiheessa 2 muodostetuista avainten järjestykistä on nopeinta toteuttaa. Tätä varten verkkoa tehostetaan luomalla yhteyksiä avainten ja ovien väleille huoneiden sisällä. Tämä on lähes välttämätöntä, jotta ohjelman ei tarvitse kuluttäa paljon aikaa kaikkien mahdollisten polkujen läpikäymiseen huoneen sisällä.
  


