
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import pakohuone.tyokalut.Syvyyshaku;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rasmus
 */
public class SyvyyshakuTest {

    Syvyyshaku l;
    boolean[][] matriisi = new boolean[5][5];

    @Before
    public void SetUp() {
        l = new Syvyyshaku();
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
    public void SyvyshaunPerustoimintaKunReittiLoytyy() {
        assertTrue(l.hae(matriisi));
    }
    @Test
    public void SyvyshakuMuihinHuoneisiinKunReittiLoytyy() {
        assertTrue(l.haeArvolla(matriisi,1));
        assertTrue(l.haeArvolla(matriisi,2));
        assertTrue(l.haeArvolla(matriisi,3));
        assertTrue(l.haeArvolla(matriisi,4));
    }
    
    @Test
    public void SyvyshaunPerustoimintaKunReittiErilainen() {
        matriisi[2][4] = false;
        matriisi[4][2] = false;
        assertFalse(l.hae(matriisi));
    }
    @Test
    public void SyvyshakuMuihinHuoneisiin() {
        matriisi[2][4] = false;
        matriisi[4][2] = false;
        matriisi[3][4] = false;
        matriisi[4][3] = false;
        assertTrue(l.haeArvolla(matriisi,2));
        assertTrue(l.haeArvolla(matriisi,3));
        assertFalse(l.haeArvolla(matriisi,4));
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
        assertTrue(l.hae(mat));
        mat[1][2] = false;
        mat[2][2] = false;
        assertFalse(l.hae(mat));
    }
    
    @Test
    public void PieninLabyrintti() {
        boolean[][] mat = new boolean[2][2];
        mat[1][1] = true;
        assertTrue(l.hae(mat));
        mat[1][1] = false;
        assertTrue(l.hae(mat));
    }
}
