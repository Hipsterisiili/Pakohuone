package pakohuone.tyokalut;


public class Kirjainpino {
    private char[] pino;
    int pinta;
    
    public Kirjainpino(){
        this.pinta = 0;
        this.pino = new char[30];
    }
    
    public void add(char c){
        if(pinta >= 30){
            return;
        }
        pino[pinta] = c;
        pinta++;
    }
    
    public char pop(){
        if(pinta <= 0){
            return '@';
        }
        char palautus = pino[pinta - 1];
        pinta--;
        return palautus;
    }
    
    public char peek(){
        if(pinta <= 0){
            return '@';
        }
        return pino[pinta - 1];
    }
    
    @Override
    public String toString(){
        String palautus = "";
        for(int i = 0; i < pinta ; i++){
            palautus = palautus + pino[i];
        }
        return palautus;
    }
}
