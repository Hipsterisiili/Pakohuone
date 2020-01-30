# PAKOHUONE MÄÄRITTELYDOKUMENTTI


### Ongelma:
1.) Voinko ratkaista kuvan 1. mukaisen labyrintin?<br>
2.) Jos voin, minkälaista polkua pitkin?<br>
3.) Mikä on nopein mahdollinen polku, joka ratkaisee labyrintin?<br>

![Kuva 1](https://raw.githubusercontent.com/Hipsterisiili/Pakohuone/master/Dokumentointikansio/Kuvat/pakohuone_esimerkkihuone.jpg)

Kuva 1: Esimerkki ratkaistavasta labyrintistä

### Säännöt:
  Labyrintissa voi edetä kerralla yhden askeleen johonkin neljästä ilmansuunasta, mikäli seinä (kuvassa mustaksi väritetty ruutu) ei ole tiellä. Jotta "Ovesta" voi kulkea, on sitä ennen haettava oven kirjainta vastaava avain saapumalla sen sisältämään ruutuun.
 
### Labyrintit
  Tarkoitukseni on luoda käsin pieni joukko mielekkäitä testilabyrintteja, joita algoritmi pyrkii ratkaisemaan ja myöhemmin sovellus, joka luo sattumanvaraisesti lisää testattavia labyrintteja. Labyrintin tallennusmuoto on kaksiulotteinen taulukko char[x][y], jossa x on labyrintin leveys ja y on sen korkeus. Taulukon arvot kertovat mitä kyseisessä "ruudussa" on:
  
  > \# = Seinä<br>
  > . = Tyhjä ruutu<br>
  > 0 = Lähtöpiste<br>
  > 1 = Maali<br>
  > a,b,c = Avaimia<br>
  > A,B,C = Ovia<br>
  
  Alustavasti labyrintin huoneiden on tarkoitus olla nelikulmaisia huoneita, mutta jos loppukurssista jää aikaa sovelluksen täydentämiseen, on mahdollista että täydennän algoritmin toimintaa siten, että se osaa etsiä reittejä myös vaikeammissa huoneissa.
  
  **Ohjelman on tulkittava labyrinttia suuntaamattomana painotettuna verkkona, jotta algoritmien toteuttaminen on mahdollista.**
 
  ### Algoritmit
  
  #### Algoritmi 1
  Ohjelma selvittää huoneiden määrän, mitä ruutuja ja avaimia kukin huone sisältää, käyttäen **Union-find-rakennetta** ja tallentaen tiedon labyrintin union-find-rakenteesta myöhempää käyttöä ja muokkaamista varten. 
  
   Huoneita alkutilanteessa voi pahimmillaan olla standardini mukaisessa labyrintissä korkeintaan (x/2)\*(y/2), missä x = labyrintin leveys ja y = labyrintin korkeus.
   
   Muodostan Union-Find-rakenteesta hieman poikkeavan rakenteen, jossa joukon edustaja on aina se ruutu, joka on huoneen vasemmassa yläkulmassa. (Jos huoneita myöhemmin yhdistetään, pienemmän huoneen edellinen edustaja saa edustajakseen suuremman huoneen vasemman ylänurkan)  
   Tällaisessa Union-Find-rakenteessa yhden alkion edustajan  löytäminen vie aikaa O(x+y), x ja y ovat labyrintin leveys ja korkeus. Vaiheen 1 aikavaativuus on siis kaikkien labyrintin ruutujen edustajan etsimisten aikavaativuuksien summa. Labyrintissä on korkeintaan x\*y ruutua, joten vaiheen 1 aikavaativuus = O((x\*y)\*(x+y)) = **O(xxy + xyy)**. Tämä tarkoittaa että suurten labyrinttien ratkaiseminen on todella aikaavievää, vaikka tämä onkin tehostettu union-find -malli.
   
   Vaihtoehtoinen ratkaisu kunkin ruudun edustajan löytämiseen on etsiä labyrintistä kaikkien huoneiden vasemmat ylänurkat (eli etsiä kaikki ruudut, joiden vasemmalla puolella ja yläpuolella on seinää. Tämän jälkeen siivutetaan huonetta seinien sisällä käyden läpi kaikki sen ruudut ja nimittäen niille edustajaksi vasen ylänurkka. Kuvassa 2 näkyy järjestys, jossa ruutuja käydään läpi.
   
   Vaihtoehtoisen ratkaisun aikavaativuus on ruutujen määrä x\*y + ruutujen määrä x\*y. Täten aikavaativuus = O(2(x\*y)) Tämä on merkittävästi vähemmän, kuin aiemman menetelmän aikavaativuus. Lisäksi jälkimmäinen menetelmä on todennäköisesti helpompi toteuttaa.

![Kuva 2](https://raw.githubusercontent.com/Hipsterisiili/Pakohuone/master/Dokumentointikansio/Kuvat/pakohuone_etsiedustaja.jpg)

Kuva 2: Järjestys jossa labyrintin ruudut käydään läpi kun huoneiden edustajat selvitetään. Kun vasemmassa kuvassa saavutaan johonkin vasempaan ylänurkkaan (ympyröidyt numerot), josta halutaan tehdä huoneen edustaja, voidaan pysähtyä käymään läpi kaikki huoneen ruudut. Kun kohdataan seinä, hypätään takaisin rivin alkuun.
  
  #### Algoritmi 2
   Seuraavaksi ohjelma selvittää kaikki mahdolliset järjestykset, joissa avaimet voi noutaa niin että lopulta saavutaan maaliin. Tämä tapahtuu tarkoituksenmukaisesti muokattua **syvyyshakua** (DFS) käyttämällä. Haku perustuu joka askeleella päivittyvään listaan tavoitettavissa olevia avaimia, siten että aina mentäessä askel syvemmälle, seuraava vaihe saa uuden listan sillä hetkellä saatavilla olevista avaimista ja maaleista. **Kunkin avaimen on sisällettävä tieto siitä minkä kahden ruudun välille se muodostaa yhteyden**
  
  Mahdollisia järjestyksiä voi olla 0 - n!, missä n = avainten määrä. Kukin mahdollinen järjestys tallennetaan erikseen listaksi, jotta niitä voidaan vertailla vaiheessa 3. Mikäli järjestyksiä on vain 0, ohjelma keskeytyy ja tiedämme että labyrintti ei ole ratkaistavissa. 
  
  Kussakin syvyyshaun vaiheessa saatetaan lisätä kaaria korkeintaan yhtä monta kuin avaimia on ja voidaan joutua kulkemaan 1-n huoneen läpi, missä n = huoneiden määrä eli yhden vaiheen aikavaativuus on **O( a + (x\*y)/4 )** (a = avainten määrä). Eri etenemisvaihtoehtoja haulla on a! ja jokaisen etenemisen jokaisessa vaiheessa on 0-(a-1) vaihtoehtoa siitä mihin seuraavaksi voi edetä. Täten aikavaativuus on **O( a! \* a-1 * (a + (x\*y)/4) )**. Normaalikäytössä ei tule tilannetta, jossa ohjelmalla ei ole jatkuvasti maksimimäärää mahdollisimman vaikeita etenemisvaihtoehtoja, joista kaikki johtavat maaliin.
  
  #### Algoritmi 3
  Viimeiseksi Tutkitaan mikä vaiheessa 2 muodostetuista avainten järjestyksistä on nopeinta toteuttaa. Tätä varten verkkoa tehostetaan luomalla yhteyksiä avainten ja ovien väleille huoneiden sisällä. Tämä on lähes välttämätöntä, jotta ohjelman ei tarvitse kuluttaa paljon aikaa kaikkien mahdollisten polkujen läpikäymiseen huoneen sisällä. Kuvassa 3 näkyy minkälaiset reitit huoneen sisällä ovat optimaalisia. 

![Kuva 3](https://raw.githubusercontent.com/Hipsterisiili/Pakohuone/master/Dokumentointikansio/Kuvat/pakohuone_lyhimmatreitit.jpg) 

Kuva 3: Lyhimmät ratkaisut olennaisiin reitteihin erään huoneen sisällä.

  Käytännössä kuvan 2 mukaiset lyhimmät reitit löytää nopeasti vertailemalla niiden alku- ja loppupisteiden koordinaatteja, joten tämä reitinhakualgoritmi on melko yksinkertainen. Lyhin etäisyys pisteiden (x1,y1) ja (x2,y2) välillä on |x1-x2| + |y1-y2|. 

  Lopuksi käydään läpi kaikki vaiheessa 2 muodostetut järjestykset ja erotellaan niistä lyhin. Kutakin järjestystä seuratessa on luotava uusi lähes kaareton verkko, johon syntyy kaaria sitä mukaa kun avaimissa vieraillaan. 

  #### Esimerkki kolmannen algoritmin etenemisestä esimerkkilabyrintissä:
  Käytetään kuvissa 1 ja 4 esiintyvää labyrinttia esimerkkinä. Aluksi verkossa on vain kaaret lähtöpisteestä sen huoneessa oleviin kiinnostaviin kohteisiin. Kiinnostavia kohteita ovat poimimattomat avaimet sekä avoimet oviaukot. Täten verkossa on ainoastaan kaari (L-a). Ensimmäiseksi siis missä tahansa avainjärjestyksessä saavutaan avaimeen a. Tällöin lisätään verkkoon uudet kaaret (A-a), (A-b), (A-c). Jos seuraavaksi poimitaan avain b, lisätään kaaret (B-A), (B-e). (Huom kaarta B-a ei tarvitse enää muodostaa, sillä ei ole tarvetta vierailla avaimessa a enää.) Lisäksi voidaan myös poistaa kaari (A-a). (Kun avain on kerran poimittu ja siitä on poistuttu, kaikki sen kautta kulkevat kaaret voi poistaa verkosta, koska sen kautta ei tarvitse kulkea enää.) Kuvassa 4 on hahmoteltu verkon muodostumista edellä kuvatussa tilanteessa.

  Jos reittiä jäljitellessä ohjelma huomaa jo kulkeneensa pitemmän matkan kuin mitä jokin aikaisempi ratkaisu on vaatinut, voidaan siirtyä seuraavaan polkuun, sillä muodostettava reitti ei voi olla enää lyhin. 
  
  ![Kuva 4](https://raw.githubusercontent.com/Hipsterisiili/Pakohuone/master/Dokumentointikansio/Kuvat/pakohuone_verkonmuodostus.jpg)
  
  Kuva 4: Verkon muodostuminen esimerkkitilanteessa kun on saavuttu avaimen b kohdalle ja täten avattu juuri ovi B. Viivat ruutujen välillä ovat muodostettuja kaaria, katkoviivat ovat jo poistettuja kaaria. Kaarien pituuksia ei ole merkattu kuvaan.

   Aikavaativuus kolmannelle algoritmille on kaikkien reittien määrä \* reitteihin kuuluvien kaarien määrät eli O( n! * ((x\*y) / 4 ) 


## Lähteet

Algoritmien muodostaminen ja niiden tyypillisimpien ilmentymien aikavaativuudet:
tirakirja (Antti Laaksonen 2019) https://www.cs.helsinki.fi/u/ahslaaks/tirakirja/
