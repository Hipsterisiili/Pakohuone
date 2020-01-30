package pakohuone.sovelluslogiikka;

public class Huone {
    private Ovi[] ovet;
    private Avain[] avaimet;
    private int avaintenMaara = 0;
    private int ovienMaara = 0;
    
    public Huone(){
        ovet = new Ovi[100];
        avaimet = new Avain[100];
    }
    /**
     * Metodi lisää huoneeseen tiedon uudesta avaimesta.
     *
     * @param a Lisättävä avain
     */
    public void LisaaAvain(Avain a){
        if(avaintenMaara >= 99){
            return;
        }
        avaimet[avaintenMaara] = a;
        avaintenMaara++;
    }
    /**
     * Metodi lisää huoneeseen tiedon uudesta ovesta.
     *
     * @param a Lisättävä avain
     */
    public void LisaaOvi(Ovi o){
        if(ovienMaara >= 99){
            return;
        }
        ovet[ovienMaara] = o;
        ovienMaara++;
    }

    public Ovi[] getOvet() {
        return ovet;
    }

    public Avain[] getAvaimet() {
        return avaimet;
    }
}
