package pakohuone.sovelluslogiikka;
     /**
    * Ovet ovat olioita, jotka tuntevat niiden alkupisteen ja loppupisteen 
    * koordinaatit. ovi ei tunne sen avaavaa avainta, mutta avain tuntee ovensa.
    */
public class Ovi {
    /**Oven alku. ja loppupään koordinaatit*/
    private int alkuX, alkuY, loppuX, loppuY;
    
    /**
    * Oven konstruktori
    * @param x1 = oven alkupuolen sijainti x-akselilla
    * @param y1 = oven alkupuolen sijainti y-akselilla
    * @param x2 = oven loppupuolen sijainti x-akselilla
    * @param y2 = oven loppupuolen sijainti y-akselilla
    */
    public Ovi(int x1, int y1, int x2, int y2) {
        this.alkuX = x1;
        this.alkuY = y1;
        this.loppuX = x2;
        this.loppuY = y2;
    }

    public int getAlkuX() {
        return alkuX;
    }

    public int getAlkuY() {
        return alkuY;
    }

    public int getLoppuX() {
        return loppuX;
    }

    public int getLoppuY() {
        return loppuY;
    }
    
    public String toString() {
        return "Oven sijainti"
                + " x: " + (alkuX + loppuX) / 2 
                + " y: " + (alkuY + loppuY) / 2  ;
    }
}

