package pakohuone.tyokalut;

import java.util.Arrays;

public class Syvyyshaku {

    boolean onkoTieLoytynyt;
    boolean[][] taul;
    boolean[] vierailtu;

    /**
     * Leveyshaku kykenee suorittamaan syvyyshaun kaksiuloitteisessa neliön 
     * muotoisessa boolean[][] matriisissa. Metodi hae palauttaa tiedon onko
     * reittiä olemassa. Lyhintä reittiä ei tällä metodilla selvitetä
     */
    public Syvyyshaku() {
    }

    /**
     * Metodi hae aloittaa rekusion kutsumalla metodia haku. Etsittävä huone
     * on se, jota edustaa suurin lukuarvo matriisissa
     * @param huoneMatriisi matriisi, josta reittejä selvitetään
     * @return onko mahdollista löytää reitti annetussa labyrintissa maaliin
     */
    public boolean hae(boolean[][] huoneMatriisi) {
        this.taul = huoneMatriisi;
        this.vierailtu = new boolean[huoneMatriisi[0].length];
        this.onkoTieLoytynyt = false;
        Arrays.fill(vierailtu, false);
        vierailtu[1] = true;
        rekursio(1);
        
        return onkoTieLoytynyt;
    }
    /**
     * Haku rekursoi reittejä syvyyshaulle tyypilliseen tapaan. Etsitään sitä
     * huonetta, jota matriisissa edustaa suurin numero
     * @param luku Huoneen, jota tällä hetkellä tarkastellaan numero
     */
    private void rekursio(int luku){
        if(luku == taul[0].length - 1){
            onkoTieLoytynyt = true;
        }
        if(onkoTieLoytynyt){
            return;
        }
        for(int i = 0 ; i < taul[0].length ; i++){
            if(taul[luku][i] == true && vierailtu[i] == false){
                vierailtu[i] = true;
                rekursio(i);
                vierailtu[i] = false;
            }
        }
    }
    /**
     * metodi aloittaa syvyyshaun matriisissa, etsittävänä on
     * tietty parametrina annettava huone
     * @param luku Huoneen, jota tällä hetkellä tarkastellaan numero
     * @param i Etsittävän huoneen numero
     */
    public boolean haeArvolla(boolean[][] huoneMatriisi, int i) {
        this.taul = huoneMatriisi;
        this.vierailtu = new boolean[huoneMatriisi[0].length];
        this.onkoTieLoytynyt = false;
        Arrays.fill(vierailtu, false);
        vierailtu[1] = true;
        rekursioArvolla(1, i);
        
        return onkoTieLoytynyt;
    }
    /**
     * metodi rekursoi reittejä syvyyshaulle tyypilliseen tapaan, etsittävänä on
     * tietty parametrina annettava huone
     * @param luku Huoneen, jota tällä hetkellä tarkastellaan numero
     * @param arvo Etsittävän huoneen numero
     */
    private void rekursioArvolla(int luku, int arvo){
        if(luku == arvo){
            onkoTieLoytynyt = true;
        }
        if(onkoTieLoytynyt){
            return;
        }
        for(int i = 0 ; i < taul[0].length - 1 ; i++){
            if(taul[luku][i] == true && vierailtu[i] == false){
                vierailtu[i] = true;
                rekursio(i);
                vierailtu[i] = false;
            }
        }
    }
}
