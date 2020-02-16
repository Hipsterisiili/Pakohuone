package pakohuone.tyokalut;

import java.util.Arrays;

public class Leveyshaku {

    boolean onkoTieLoytynyt;
    boolean[][] taul;
    boolean[] vierailtu;

    public Leveyshaku() {
        
    }

    public boolean hae(boolean[][] huoneMatriisi) {
        this.taul = huoneMatriisi;
        this.vierailtu = new boolean[huoneMatriisi[0].length];
        this.onkoTieLoytynyt = false;
        Arrays.fill(vierailtu, false);
        vierailtu[1] = true;
        haku(1);
        
        return onkoTieLoytynyt;
    }
    
    private void haku(int luku){
        if(luku == taul[0].length - 1){
            onkoTieLoytynyt = true;
        }
        if(onkoTieLoytynyt){
            return;
        }
        for(int i = 0 ; i < taul[0].length ; i++){
            if(taul[luku][i] == true && vierailtu[i] == false){
                vierailtu[i] = true;
                haku(i);
                vierailtu[i] = false;
            }
        }
    }
}
