## Viikkoraportti (vko3)

Viikolla 3 ohjelman työstämisen painopiste oli hieman enemmän muissa asioissa, kuin ohjelman toiminnallisuuksien kehittämisessä. Paljon aikaa kului ohjelman toimivuuden ja sujuvuuden varmistamiseen. Tämän viikon panoksen ansiosta itse sovelluksen täyspainoinen rakentaminen seuraavilla viikoilla on mahdollista.

### Rakenne

Viikolla toteutin sovellukseen vaadittavat toiminnallisuudet kunkin huoneen sisällön seuraamiseen. Nyt kustakin labyrintissa olevasta huoneesta luodaan automaattisesti oma olionsa, joka sisältää taulukossa kaikki siihen kuuluvat avaimet ja ovet. Samoin kukin avain tuntee nyt oven, jonka se avaa, sekä sen sen minkälaisen yhteyden ovi labyrintissa muodostaa auetessaan. Tämän toteuttaminen vaati useita muutoksia muihin edellisellä viikolla luotuihin luokkiin. Ennalta suunnittelemisen merkitys tuli jälleen huomattua. Kenties tarkemmat viikottaiset ennalta määritetyt tavoitteet ehkäisisivät tällaista "backtrackingia". 

Olen hahmotellut jo ensi viikolla toteutettavaa toiminnallisuutta ja varmistanut että ohjelma nykyisessä tilassaan antaa puitteet totettaa sen ilman että jo tehtyyn koodiin tarvitsisi tehdä suuria muutoksia. Ensi viikolla suunnitelmanani on aloittaa verkon muodostaminen labyrintin ruuduista. Jokaisen ruudun sisältävä verkko ei ole tarkoituksenmukainen, sillä tällöin solmuja olisi kohtuuton määrä ja verkko ei olisi kovin tehokas. Sen vuoksi aikomukseni on toteuttaa verkko, jossa kaaria muodostetaan ainoastaan kriittisten kohteiden, kuten avainten ja ovien välille ja käytetään niiden etäisyyksien määrittämiseen jo luomaani työkalua "pakohuone.tyokalut.EtaisyydenEtsija".

Aloitin jo seuraavan viikon toiminnallisuuden rakentamisen luokassa "pakohuone.tyokalut.ReittienEtsija". Verkko täytynee toteuttaa kaarilistaesityksenä siten, että kukin solmu on oma olionsa, joka sisältää listan solmuista, joihin siinä on yhteys

### Testaus ja muotoilu

Kaikki edellislelä viikolla ilmenneet ongelmat ratkaistiin tällä viikolla. Loin uuden TOIMIVAN sovelluksen repositorioon käyttäen netistä löytämiäni ohjeita. Samojen ohjeiden avulla mahdollistin sovelluksen siisteyden seurannan checkstyle-tyokalun avulla, sekä testikattavuuden seurannan jacoco-tyokalun avulla. Näiden työkalujen avulla edistin merkittävästi ohjelman siisteyttä ja testikattavuutta. (Kattavuus on tällä hetkellä käytännössä lähes 100%, kun paljolti muuttuvia main-paketin luokkia ei lasketa mukaan Täydellisesti testattavia luokkia minun ei ole tarkoitus enää muokata juuri ollenkaan.) 

Checkstyle-virheitä sovelluksessa on runsaasti, johtuen siitä että liittämäni checkstyle-työkalu poimii erittäin ankarasti kaikki pienimmätkin "vääränlaiset" muotoiluseikat, kuten muuttujan arvon antaminen käsin (magic number), sekä muuttujat jotka voisivat saada määritelmän "final". Aikomukseni ei ole karsia näitä kaikkia pois, mutta karsin selkeät virheet, kuten puttuvat/ylimääräiset välilyönnit, ylimääräisen koodin, ylimääräiset importit sekä liian pitkät metodit pois. Tulevilla viikoilla kannattanee etsiä keinoja checkstylen ankaruuden vähentämiseen.

Järjestin repositorion kansiorakenteen myös uudelleen, kuten viime viikon palautuksesta toivottiin.

Olen myös tehostanut koodin kommentointia, jotta koodin tutkiminen on mielekkäämpää. Lähes kaikkien metodien funktio on avattu kommenteissa samoin osa muuttujista on selitetty auki.

### Viime viikon arvioinnista

Viime viikon sanallisessa arviossa arvosteltiin sitä, että repositoriossani ei olisi ollut koodia lainkaan, vaikka koko senhetkinen sovellus sieltä oikeasti löytyikin. Olin epähuomiossa luonut repositorioon kaksi kansiota "pakohuone" sekä "Pakohuone" (iso ja pieni alkukirjain erona). Näistä toisessa oli koko koodi ja toisessa ei ollut mitään ja ilmeisesti tarkastaja oli tarkastellut ainoastaan jälkimmäistä. Johtuiko arvio 2/3 jostakin muusta, vai menetinkö pisteen siksi, että tarkastaja ei löytänyt koodiani?

