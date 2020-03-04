package pakohuone.sovelluslogiikka;

    /**
     * Huone on tietorakenne, joka sisältää tiedot siihen kuuluvista
     * avaimista ja ovista olioina.
     */
public class Huone {
    /**Huoneen sisältämät ovet olioina.*/
    private Ovi[] ovet;
    /**huoneen sisältämät avaimet olioina*/
    private Avain[] avaimet;
    /**huoneen sisältämien avainten määrä*/
    private int avaintenMaara = 0;
    /**huoneen sisältämien ovien määrä*/
    private int ovienMaara = 0;
    
    /**
     * Huoneen konstruktori.
     * Annetaan huoneelle vielä placeholder-arvot.
     */
    public Huone(){
        ovet = new Ovi[30];
        avaimet = new Avain[30];
        for(int i = 0; i < 30 ; i++){
            ovet[i] = new Ovi(0,0,0,0);
            avaimet[i] = new Avain(0, 0, '@');
        }
    }
    /**
     * Metodi lisää huoneeseen tiedon uudesta avaimesta.
     *
     * @param a Lisättävä avain
     */
    public void lisaaAvain(Avain a) {
        if (avaintenMaara >= 30) {
            return;
        }
        avaimet[avaintenMaara] = a;
        avaintenMaara++;
    }
    /**
     * Metodi poistaa huoneelta viimeksi lisätyn avaimen. Tätä metodia kutsutaan
     * ainoastaan tutkiessa avainten mahdollista järjestystä luokassa
     * pakohuone.tyokalut.HuoneidenEtsija ja kun on jossakin vaiheessa
     * avattu ovi, joka ei vielä auetessaan avannut uusia reittejä.
     *
     */
    public void poistaAvain() {
        if (avaintenMaara <= 0) {
            return;
        }
        //System.out.println("POISTETAAN AVAIN " + avaimet[avaintenMaara - 1].toString());
        avaintenMaara--;
        avaimet[avaintenMaara] = new Avain(0,0,'@');
    }
    /**
     * Metodi lisää huoneeseen tiedon uudesta ovesta.
     *
     * @param o Lisättävä avain
     */
    public void lisaaOvi(Ovi o) {
        if (ovienMaara >= 30) {
            return;
        }
        //System.out.println("LAITETAAN UUSI OVI KOHTAAN " + ovienMaara);
        ovet[ovienMaara] = o;
        ovienMaara++;
    }
    /**
     * Metodi poistaa huoneelta viimeksi lisätyn oven. Tätä metodia kutsutaan
     * ainoastaan tutkiessa avainten mahdollista järjestystä luokassa
     * pakohuone.tyokalut.HuoneidenEtsija ja kun on jossakin vaiheessa
     * avattu ovi, joka ei vielä auetessaan avannut uusia reittejä.
     *
     */
    public void poistaOvi() {
        if (ovienMaara <= 0) {
            return;
        }
        //System.out.println("POISTETAAN OVI " + (ovet[ovienMaara - 1].toString()));
        ovienMaara--;
        ovet[ovienMaara] = new Ovi(0,0,0,0);
    }

    public Ovi[] getOvet() {
        return ovet;
    }
    public Avain[] getAvaimet() {
        return avaimet;
    }
    public int getAvaintenMaara() {
        return avaintenMaara;
    }
    public int getOvienMaara() {
        return ovienMaara;
    }
}
