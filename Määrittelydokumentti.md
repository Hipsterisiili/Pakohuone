# PAKOHUONE MÄÄRITTELYDOKUMENTTI<br>


### Ongelma:
1.) Voinko ratkaista kuvan 1. mukaisen labyrintin?<br>
2.) Jos voin, minkälaista polkua pitkin?<br>
3.) Mikä on nopein mahdollinen polku, joka ratkaisee labyrintin?<br>

![Kuva 1](https://raw.githubusercontent.com/Hipsterisiili/Pakohuone/master/pakohuone_esimerkkihuone.jpg)

Kuva 1. esimerkki ratkaistavasta labyrintistä

### Säännöt:
<p>  Labyrintissa voi edetä kerralla yhden askeleen johonkin neljästä ilmansuunasta, mikäli seinä (kuvassa mustaksi väritetty ruutu) ei ole tiellä. jotta "Ovesta" voi kulkea, on sitä ennen haettava oven kirjainta vastaava avain saapumalla sen sisältämään ruutuun.<p>
 
### Labyrintit
<p>  Tarkoitukseni on luoda käsin pieni joukko mielekkäitä testilabyrintteja, joita algoritmi pyrkii ratkaisemaan ja myöhemmmin sovellus, joka luo sattuanvaraisesti lisää testattavia labyrintteja. Labyrintin tallennusmuoto on kaksiuloitteinen taulukko char[x][y], jossa x on labyrintin leveys ja y on sen korkeus. Taulukon arvot kertovat mitä kyseisessä "ruudussa" on: <p>
  
  > \# = Seinä<br>
  > . = Tyhjä ruutu<br>
  > 0 = Lähtöpiste<br>
  > 1 = Maali<br>
  > a,b,c = Avaimia<br>
  > A,B,C = Ovia<br>
  
<p>  Alustavasti labyrintin huoneiden on tarkoitus olla nelikulmaisia huoneita, mutta jos loppukurssista jää aikaa sovelluksen täydentämiseen, on mahdollista että täydennän algoritmin toimintaa siten, että se osaa etsiä reittejä myös vaikeammissa huoneissa. <p>
  
  **Ohjelman on tulkittava labyrinttia suuntaamattomana painotettuna verkkona, jotta algoritmien toteuttaminen on mahdollista.**
 
  ### Algoritmit
  
<p>  **Vaiheessa 1** ohjelma selvittää huoneiden määrän, mitä ruutuja ja avaimia kukin huone sisältä2ä, käyttäen **Union-find-rakennetta** ja tallentaen tieton labyrintin union-find-rakenteesta myöhempää käyttöä ja muokkaamista varten. <p>
  
<p>   Huoneita alkutilanteessa voi pahimmillaan olla standardini mukaisesa labyrintissä korkeintaan (x/2)\*(y/2), missä x = labyrintin leveys ja y = labyrintin korkeus.<p>
  
<p>  **Vaiheessa 2** ohjelma selvittää kaikki mahdolliset järjestykset, joissa avaimet voi noutaa niin että lopulta saavutaan maaliin. Tämä tapahtuu **syvyyshakua** (DFS) käyttämällä. Haku perustuu joka askeleella päivittyvään listaan tavoitettavissa olevia avaimia, siten että aina mentäessä astel syvemmälle, seurava vaihe saa uuden listan sillä hetkellä saatavilla olevista avaimista ja maaleista. **Kunkin avaimen on sisällettävä tieto siitä mnkä kahden ruudun välille se muodostaa yhteyden** <p>
  
<p>  Mahdollisia järjestyksiä voi olla 0 - n!, missä n = avainten määrä. Kukin mahdollinen järjestys tallennetaan erikseen listaksi, jotta niitä voidaan vertailla vaiheessa 3. Mikäli järjestyksiä on vain 0, ohjelma keskeytyy ja tiedämme että labyrintti ei ole ratkaistavissa. <p>
  
<p>  **Vaiheessa 3** Tutkitaan mikä vaiheessa 2 muodostetuista avainten järjestykistä on nopeinta toteuttaa. Tätä varten verkkoa tehostetaan luomalla yhteyksiä avainten ja ovien väleille huoneiden sisällä. Tämä on lähes välttämätöntä, jotta ohjelman ei tarvitse kuluttäa paljon aikaa kaikkien mahdollisten polkujen läpikäymiseen huoneen sisällä. Kuvassa 2 näkyy minkälaiset reitit huoneen sisällä ovat optimaalisia. <p>

![Kuva 2](https://raw.githubusercontent.com/Hipsterisiili/Pakohuone/master/pakohuone_lyhimmatreitit.jpg)

Kuva 2 Lyhimmät ratkaisut olennaisiin reitteihin erään huoneen sisällä.

<p>  Käytännössä kuvan 2 mukaiset lyhimmät reitit löytää nopeasti vertailemalla niiden alku-ja loppupisteiden koordinaatteja, joten tämä reitinhakualgoritmi on melko yksinkertainen. Lyhin etäisyys pisteiden (x1,y1) ja (x2,y2) välillä on |x1-x2| + |y1-y2|. <p>

<p>  Lopuksi käydään läpi kaikki vaiheessa 2 muodostetut järjestykset ja erotellaan niistä lyhin. Kutakin järjestystä seuratessa on luotava uusi lähes kaareton verkko, johon syntyy kaaria sitä mukaa kun avaimissa vieraillaan. <p>

<p>  **Esimerkki algoritmin etenemisestä vaiheessa 3 kuvan 1 mukaisessa labytintissä:** Aluksi verkossa on vain kaaret lähtöpisteestä sen huoneessa oleviin kiinnostaviin kohteisiin. Kiinnostavia kohteita ovat poimimattomat avaimet sekä avoimet oviaukot. Täten verkossa on ainoastaan kaari (L-a). Ensimmäiseksi siis missä tahansa avainjärjestyksessä saavutaan avaimeen a. Tällöin lisätään verkkoon uudet kaaret (A-a), (A-b), (A-c). Jos seuraavaksi poimitaan avain b, lisätään kaaret (B-A), (B-e). (Huom kaarta B-a ei tarvitse enää muodostaa, sillä ei ole tarvetta vierailla avaimessa a enää.) Lisäksi voidaan myös poistaa kaari (A-a). (Kun avain on kerran poimittu ja siitä on poistuttu, kaikki sen kautta kulkevat kaaret voi poistaa verkosta, koska sen kautta ei tarvitse kulkea enää.) <p>

<p>  Jos reittiä jäljitellessä ohjelma huomaa jo kulkeneensa pitemmän matkan kuin mitä jokin aikaisempi ratkaisu on vaatinut, voidaan siirtyä seuraavaan polkuun, sillä muodostettava reitti ei voi olla enää lyhin.  <p>
