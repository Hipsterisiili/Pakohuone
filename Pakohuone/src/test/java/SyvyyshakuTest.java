
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

    Syvyyshaku SH;
    int[][] matriisi = new int[5][5];

    @Before
    public void SetUp() {
        SH = new Syvyyshaku();
        matriisi[1][1] = 0;
        matriisi[1][2] = 1;
        matriisi[1][3] = 0;
        matriisi[1][4] = 0;

        matriisi[2][1] = 1;
        matriisi[2][2] = 0;
        matriisi[2][3] = 1;
        matriisi[2][4] = 1;

        matriisi[3][1] = 0;
        matriisi[3][2] = 1;
        matriisi[3][3] = 0;
        matriisi[3][4] = 1;

        matriisi[4][1] = 0;
        matriisi[4][2] = 1;
        matriisi[4][3] = 1;
        matriisi[4][4] = 0;
    }

    @Test
    public void SyvyshaunPerustoimintaKunReittiLoytyy() {
        assertTrue(SH.hae(matriisi));
    }
    @Test
    public void SyvyshakuMuihinHuoneisiinKunReittiLoytyy() {
        assertTrue(SH.haeArvolla(matriisi,1));
        assertTrue(SH.haeArvolla(matriisi,2));
        assertTrue(SH.haeArvolla(matriisi,3));
        assertTrue(SH.haeArvolla(matriisi,4));
    }
    
    @Test
    public void SyvyshaunPerustoimintaKunReittiErilainen() {
        matriisi[2][4] = 0;
        matriisi[4][2] = 0;
        assertTrue(SH.hae(matriisi));
    }
    @Test
    public void SyvyshakuMuihinHuoneisiin() {
        matriisi[2][4] = 0;
        matriisi[4][2] = 0;
        matriisi[3][4] = 0;
        matriisi[4][3] = 0;
        assertTrue(SH.haeArvolla(matriisi,2));
        assertTrue(SH.haeArvolla(matriisi,3));
        assertFalse(SH.haeArvolla(matriisi,4));
    }

    @Test
    public void EiMahdollistaReittia() {
        matriisi[2][4] = 0;
        matriisi[4][2] = 0;
        matriisi[3][4] = 0;
        matriisi[4][3] = 0;
        assertFalse(SH.hae(matriisi));
    }
    
    @Test
    public void pieniLabyrintti() {
        int[][] mat = new int[3][3];
        mat[1][1] = 0;
        mat[1][2] = 1;
        mat[2][1] = 1;
        mat[2][2] = 0;
        assertTrue(SH.hae(mat));
        mat[1][2] = 0;
        mat[2][2] = 0;
        assertFalse(SH.hae(mat));
    }
    
    @Test
    public void PieninLabyrintti() {
        int[][] mat = new int[2][2];
        mat[1][1] = 1;
        assertTrue(SH.hae(mat));
        mat[1][1] = 0;
        assertTrue(SH.hae(mat));
    }
}
