package pakohuone.tyokalut;

/**
 * Kirjainpino on tietorakenne, joka muistuttaa läheisesti pinoa, jossa arvot
 * ovat char-muuttujia. Kirjainpinolla on metodit add, pop sekä peek.
 */
public class Kirjainpino {
    /** Taulukko, joka sisältää pinoon lisätyt kirjaimet.*/
    private char[] pino;
    /** Pinon pinta (pinnan yläpuolisia arvoja ei käsitellä.*/
    int pinta;

    /**
     * Kirjainpinon konstruktori
     */
    public Kirjainpino() {
        this.pinta = 0;
        this.pino = new char[30];
    }

    /**
     * Lisätään pinon pinnalle kirjain.
     * Kirjaimia ei voi lisätä jos niitä on yli 30
     *
     * @param c = pinoon lisättävä kirjain
     */
    public void add(char c) {
        if (pinta >= 30) {
            return;
        }
        pino[pinta] = c;
        pinta++;
    }

    /**
     * Poistetaan pinon pinnalta kirjain ja palautetaan se.
     * Jos kirjaimia ei ole, palautetaan viheen merkiksi '@'.
     * @return char palautus = pinon päällä ollut kirjain, jos sellainen löytyi.
     */
    public char pop() {
        if (pinta <= 0) {
            return '@';
        }
        char palautus = pino[pinta - 1];
        pinta--;
        return palautus;
    }
    /**
     * Palautetaan pinon pinnalta kirjain poistamatta sitä.
     * Jos kirjaimia ei ole, palautetaan viheen merkiksi '@'.
     * @return char palautus = pinon päällä ollut kirjain, jos sellainen löytyi.
     */
    public char peek() {
        if (pinta <= 0) {
            return '@';
        }
        return pino[pinta - 1];
    }
    /**
     * Palautetaan pinon kirjaimet merkkijonona, esim "abdecf".
     * @return String palautus = pinon kirjaimet merkkijonomuodossa.
     */
    @Override
    public String toString() {
        String palautus = "";
        for (int i = 0; i < pinta; i++) {
            palautus = palautus + pino[i];
        }
        return palautus;
    }
}
