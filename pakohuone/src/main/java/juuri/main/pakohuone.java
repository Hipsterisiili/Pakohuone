package juuri.main;

import java.util.*;
import juuri.sovelluslogiikka.HuoneidenEtsinta;

public class pakohuone {

    public static void main(String[] args) {

        char[][] labyrintti = new char[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                labyrintti[i][j] = '.';
            }
            System.out.println("");
        }

        for (int i = 0; i < 10; i++) {
            labyrintti[0][i] = '#';
            labyrintti[9][i] = '#';
            labyrintti[i][0] = '#';
            labyrintti[i][9] = '#';
            labyrintti[5][i] = '#';
            labyrintti[i][7] = '#';
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(labyrintti[i][j] + " ");
            }
            System.out.println("");
        }

        System.out.println("");

        HuoneidenEtsinta h = new HuoneidenEtsinta(labyrintti);
        int[][] huoneet = h.tulkitse();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (huoneet[i][j] == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(huoneet[i][j] + " ");
                }
            }
            System.out.println("");
        }

    }

}
