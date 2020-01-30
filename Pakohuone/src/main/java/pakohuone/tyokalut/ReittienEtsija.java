package pakohuone.tyokalut;
import pakohuone.Main.Labyrintti;
import pakohuone.sovelluslogiikka.Avain;


 /**
   * Luokan tehtävä on selvittää kaikki mahdolliset järjestykset, 
   * jossa avaimet voi poimia ja muodostaa niistä järjestyksistä 
   * merkkijonoja muodossa abcdefg
   */
public class ReittienEtsija {
    private char[][] laby;
    private int[][] huoneet;
    private String[] avainLista;
    private int avaintenMaara;
    private int mahdollisetReitit;
    
    public ReittienEtsija() {
        
    }
    
    public String[] etsi(Labyrintti l){
        this.mahdollisetReitit = 0;
        this.laby = l.getKuva();
        this.huoneet = l.getHuoneet();
        this.avaintenMaara = l.getAvaintenMaara();
        //Taulukko, joka sisältää mahdolliset avainjärjestykset merkkijonona
        //Muodossa abcdefg;
        this.avainLista = new String[kertoma(avaintenMaara)];
        
        
        
        return avainLista;
    }
    
    private int kertoma(int luku) {
        for(int i = luku-1; i > 0; i--){
            luku = luku*i;
        }
        return luku;
    }
    
}