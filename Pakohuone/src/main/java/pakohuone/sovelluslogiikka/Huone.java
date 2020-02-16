package pakohuone.sovelluslogiikka;

public class Huone {
    private Ovi[] ovet;
    private Avain[] avaimet;
    private int avaintenMaara = 0;
    private int ovienMaara = 0;
    
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
        if (avaintenMaara >= 99) {
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
        System.out.println("POISTETAAN AVAIN " + avaimet[avaintenMaara - 1].toString());
        avaintenMaara--;
        avaimet[avaintenMaara] = new Avain(0,0,'@');
    }
    /**
     * Metodi lisää huoneeseen tiedon uudesta ovesta.
     *
     * @param o Lisättävä avain
     */
    public void lisaaOvi(Ovi o) {
        if (ovienMaara >= 99) {
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
        System.out.println("POISTETAAN OVI " + (ovet[ovienMaara - 1].toString()));
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
