package pakohuone.sovelluslogiikka;

public class Avain {
    private int sijaintiX;
    private int sijaintiY;
    private Ovi ovi;

    public Avain(int sijaintiX, int sijaintiY) {
        this.sijaintiX = sijaintiX;
        this.sijaintiY = sijaintiY;
        //Ovea ei ole vielä määritetty
        this.ovi = new Ovi(0, 0, 0, 0);
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

    public void setOvi(Ovi ovi) {
        this.ovi = ovi;
    }
    
    public String toString() {
        return "x: " + sijaintiX + " y: " + sijaintiY + "\n" + ovi.toString(); 
    }
}
