package pakohuone.tyokalut;

public class EtaisyydenEtsija {
    
    public EtaisyydenEtsija(){
        
    }
    
    /**
   * Palauttaa pisteiden (x1,y1) ja (x2,y2) välisen etäisyyden
   * * @param x1 pisteen 1 x-koordinaatti
   * * @param y1 pisteen 1 y-koordinaatti
   * * @param y1 pisteen 2 x-koordinaatti
   * * @param y2 pisteen 2 x-koordinaatti
   * @return pisteiden etäisyys
   */
    public int etsiEtaisyys(int x1, int y1, int x2, int y2){
        return Math.abs(x1-x2) + Math.abs(y1-y2);
        }
}
