package pakohuone.sovelluslogiikka;
    /**
     * Avaimet ovat olioita, jotka tuntevat oman sijaintinsa koordinaatin, 
     * avainta kuvastavan kirjaimen sekä oven, joka avaimen on tarkoitus avata.
     * Ennen kuin avaimelle määritetään ovea, se käyttää väliaikaista ovea,
     * jonka parametrit ovat 0,0,0,0. Näistä arvoista on helppo tunnistaa 
     * avain, jolle ei ole vielä määritetty ovea.
     * Avain tuntee oven, jonka se avaa, mutta oven ei ole tarpeellista tuntea
     * sen avaavaa avainta.
     */
public class Avain {
    /**Avaimen sijainti x-akselilla*/
    private int sijaintiX;
    /**Avaimen sijainti y-akselilla*/
    private int sijaintiY;
    /**Avaimen kirjain*/
    private char kirjain;
    /**Ovi, jonka avain avaa*/
    private Ovi ovi;

    /**
     * Avaimen konstruktori.
     * @param sijaintiX = avaimen sijainti x-akselilla.
     * @param sijaintiY = avaimen sijainti y-akselilla.
     * @param c = avaimen kirjain.
     */
    public Avain(int sijaintiX, int sijaintiY, char c) {
        this.sijaintiX = sijaintiX;
        this.sijaintiY = sijaintiY;
        //Ovea ei ole vielä määritetty
        this.ovi = new Ovi(0, 0, 0, 0);
        this.kirjain = c;
    }

    public int getSijaintiX() {
        return sijaintiX;
    }

    public int getSijaintiY() {
        return sijaintiY;
    }

    public Ovi getOvi() {
        return ovi;
    }
    /**
   * Sijoitetaan avaimelle ovi.
   * @param ovi on avaimelle annettava uusi ovi
   */
    public void setOvi(Ovi ovi) {
        this.ovi = ovi;
    }

    public char getKirjain() {
        return this.kirjain;
    }

    public String toString() {
        return "x: " + sijaintiX + " y: " + sijaintiY + "\n" + ovi.toString();
    }
}
