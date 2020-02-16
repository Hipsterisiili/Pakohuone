
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import pakohuone.tyokalut.Leveyshaku;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rasmus
 */
public class LeveyshakuTest {

    Leveyshaku l;
    boolean[][] matriisi = new boolean[5][5];

    @Before
    public void SetUp() {
        l = new Leveyshaku();
        matriisi[1][1] = false;
        matriisi[1][2] = true;
        matriisi[1][3] = false;
        matriisi[1][4] = false;

        matriisi[2][1] = true;
        matriisi[2][2] = false;
        matriisi[2][3] = true;
        matriisi[2][4] = true;

        matriisi[3][1] = false;
        matriisi[3][2] = true;
        matriisi[3][3] = false;
        matriisi[3][4] = true;

        matriisi[4][1] = false;
        matriisi[4][2] = true;
        matriisi[4][3] = true;
        matriisi[4][4] = false;
    }

    @Test
    public void LeveyshaunPerustoimintaKunReittiLoytyy() {
        assertTrue(l.hae(matriisi));
    }
    
    @Test
    public void LeveyshaunPerustoimintaKunReittiErilainen() {
        matriisi[2][4] = false;
        matriisi[4][2] = false;
        assertFalse(l.hae(matriisi));
    }

    @Test
    public void EiMahdollistaReittia() {
        matriisi[2][4] = false;
        matriisi[4][2] = false;
        matriisi[3][4] = false;
        matriisi[4][3] = false;
        assertFalse(l.hae(matriisi));
    }
    
    @Test
    public void pieniLabyrintti() {
        boolean[][] mat = new boolean[3][3];
        mat[1][1] = false;
        mat[1][2] = true;
        mat[2][1] = true;
        mat[2][2] = false;
        assertTrue(l.hae(matriisi));
        mat[1][2] = false;
        mat[2][2] = false;
        assertFalse(l.hae(matriisi));
    }
    
    @Test
    public void PieninLabyrintti() {
        boolean[][] mat = new boolean[2][2];
        mat[1][1] = true;
        assertTrue(l.hae(matriisi));
        mat[1][1] = false;
        assertFalse(l.hae(matriisi));
    }
}
