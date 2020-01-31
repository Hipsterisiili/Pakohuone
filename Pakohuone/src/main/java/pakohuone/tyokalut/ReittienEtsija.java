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
    /**
   * Luokka etsii annetusta labyrintistä kaikki mahdolliset järjestykset, joissa
   * avaimia voi poimia, siten että päädytään maaliin. 
   */
    public String[] etsi(Labyrintti l){
        this.mahdollisetReitit = 0;
        this.laby = l.getKuva();
        this.huoneet = l.getHuoneet();
        this.avaintenMaara = l.getAvaintenMaara();
        //Taulukko, joka sisältää mahdolliset avainjärjestykset merkkijonona
        //muodossa abcdefg. Taulukossa on oltava tilaa n! missä n = avainten 
        //määrä, sillä pahimmillaan palautettavia avainlistoja voi olla 
        //juuri niin monta
        this.avainLista = new String[kertoma(avaintenMaara)];
        
        
        
        return avainLista;
    }
    /**
   * Luokka palauttaa parametrina annetun luvun kertoman luku!
   * @param luku Luku, jonka kertoma palautetaan
   * @return parametrin luku kertoma
   */
    private int kertoma(int luku) {
        for(int i = luku-1; i > 0; i--){
            luku = luku*i;
        }
        return luku;
    }
    
}
