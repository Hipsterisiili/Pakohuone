package pakohuone.pakohuone_rasmus;



public class pakohuone {

    public static void main(String[] args) {
        char[][] labyrintti = new char[10][10];

        for (int i = 0; i < 11; i++) {
            labyrintti[0][i] = '#';
            labyrintti[10][i] = '#';
        }

    }
}
