# PAKOHUONE MÄÄRITTELYDOKUMENTTI<br>


### Ongelma:
1.) Voinko ratkaista kuvan 1.1. mukaisen labyrintin?<br>
2.) Jos voin, minkälaista polkua pitkin?<br>
3.) Mikä on nopein mahdollinen polku, joka ratkaisee labyrintin?<br>

![Kuva 1.1.](https://raw.githubusercontent.com/Hipsterisiili/Pakohuone/master/pakohuone_esimerkkihuone.jpg)

Kuva 1.1. esimerkki ratkaistavasta labyrintistä

### Säännöt:
<p>Labyrintissa voi edetä kerralla yhden askeleen johonkin neljästä ilmansuunasta, mikäli seinä (kuvassa mustaksi väritetty ruutu) ei ole tiellä. jotta "Ovesta" voi kulkea, on sitä ennen haettava oven kirjainta vastaava avain saapumalla sen sisältämään ruutuun.<p>
  
 <p>Tarkoitukseni on luoda käsin pieni joukko mielekkäitä testilabyrintteja, joita algoritmi pyrkii ratkaisemaan ja myöhemmmin sovellus, joka luo sattuanvaraisesti lisää testattavia labyrintteja. Labyrintin tallennusmuoto on kaksiuloitteinen taulukko char[x][y], jossa x on labyrintin leveys ja y on sen korkeus. Taulukon arvot kertovat mitä kyseisessä "ruudussa" on:
  
  > \#_______= Seinä<br>
  > . _______= Tyhjä ruutu<br>
  > a,b,c____= Avaimia<br>
  > A,B,C____= Ovia<br>
  > 0________= Lähtöpiste<br>
  > 1________= Maali<br>
  
  Alustavasti labyrintin huoneiden on tarkoitus olla nelikulmaisia huoneita, mutta jos loppukurssista jää aikaa sovelluksen täydentämiseen, on mahdollista että täydennän algoritmin toimintaa siten, että se osaa etsiä reittejä myös vaikeammissa huoneissa.
  <p>


