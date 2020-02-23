package pakohuone.algoritmit;

import pakohuone.Main.Labyrintti;
import pakohuone.tyokalut.Verkko;

public class NopeimmanReitinEtsija {
    private String[] kaikkiReitit;
    private Labyrintti laby;
    private String nopeinReitti = "Nopeinta reittiä ei ole vielä selvitetty"; 
    private int nopeimmanReitinPituus = Integer.MAX_VALUE;
    
    public NopeimmanReitinEtsija(String[] lista, Labyrintti l){
        this.kaikkiReitit = lista;
        this.laby = l;
        Verkko v = new Verkko(laby);
    }
    
    public String laskeNopeinReitti(){
        for(String reitti : kaikkiReitit){
            if(tutkiOnkoNopein(reitti) < nopeimmanReitinPituus){
                nopeinReitti = reitti;
            }
        }
        return nopeinReitti;
    }
    
    private int tutkiOnkoNopein(String reitti){
        int tamanPituus = 0;
        
        
        
        
        return tamanPituus;
    }
}
