package Algoritmitestit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import pakohuone.Main.LabyrintinLuoja;
import pakohuone.sovelluslogiikka.Labyrintti;

public class ReittienEtsijaTest {

    LabyrintinLuoja ll = new LabyrintinLuoja();
    Labyrintti laby1 = ll.LuoLabyrintti1();
    Labyrintti laby2 = ll.LuoLabyrintti2();
    Labyrintti laby3 = ll.LuoLabyrintti3();

    @Test
    public void LoytyykoReitteja() {
        String reittilista
                = "Löydetyt reitit: / aebcd / aebcdf / aebcdfg / aebcdg / aebcdgf / aebcf / aebcfd / aebcfdg / aebcfg / aebcfgd\n"
                + " / aebcg / aebcgd / aebcgdf / aebcgf / aebcgfd / aebdc / aebdcf / aebdcfg / aebdcg / aebdcgf\n"
                + " / aebdf / aebdfc / aebdfcg / aebdfg / aebdfgc / aebdgc / aebdgcf / aebdgf / aebdgfc / aebf\n"
                + " / aebfc / aebfcd / aebfcdg / aebfcg / aebfcgd / aebfd / aebfdc / aebfdcg / aebfdg / aebfdgc\n"
                + " / aebfg / aebfgc / aebfgcd / aebfgd / aebfgdc / aebgc / aebgcd / aebgcdf / aebgcf / aebgcfd\n"
                + " / aebgdc / aebgdcf / aebgdf / aebgdfc / aebgf / aebgfc / aebgfcd / aebgfd / aebgfdc / aecbd\n"
                + " / aecbdf / aecbdfg / aecbdg / aecbdgf / aecbf / aecbfd / aecbfdg / aecbfg / aecbfgd / aecbg\n"
                + " / aecbgd / aecbgdf / aecbgf / aecbgfd / aecd / aecdb / aecdbf / aecdbfg / aecdbg / aecdbgf\n"
                + " / aecdf / aecdfb / aecdfbg / aecdfg / aecdfgb / aecdg / aecdgb / aecdgbf / aecdgf / aecdgfb\n"
                + " / aecfb / aecfbd / aecfbdg / aecfbg / aecfbgd / aecfd / aecfdb / aecfdbg / aecfdg / aecfdgb\n"
                + " / aecfg / aecfgb / aecfgbd / aecfgd / aecfgdb / aecg / aecgb / aecgbd / aecgbdf / aecgbf\n"
                + " / aecgbfd / aecgd / aecgdb / aecgdbf / aecgdf / aecgdfb / aecgf / aecgfb / aecgfbd / aecgfd\n"
                + " / aecgfdb / aedbc / aedbcf / aedbcfg / aedbcg / aedbcgf / aedbf / aedbfc / aedbfcg / aedbfg\n"
                + " / aedbfgc / aedbgc / aedbgcf / aedbgf / aedbgfc / aedc / aedcb / aedcbf / aedcbfg / aedcbg\n"
                + " / aedcbgf / aedcf / aedcfb / aedcfbg / aedcfg / aedcfgb / aedcg / aedcgb / aedcgbf / aedcgf\n"
                + " / aedcgfb / aedfb / aedfbc / aedfbcg / aedfbg / aedfbgc / aedfc / aedfcb / aedfcbg / aedfcg\n"
                + " / aedfcgb / aedfgb / aedfgbc / aedfgc / aedfgcb / aedgbc / aedgbcf / aedgbf / aedgbfc / aedgc\n"
                + " / aedgcb / aedgcbf / aedgcf / aedgcfb / aedgfb / aedgfbc / aedgfc / aedgfcb / aefb / aefbc\n"
                + " / aefbcd / aefbcdg / aefbcg / aefbcgd / aefbd / aefbdc / aefbdcg / aefbdg / aefbdgc / aefbg\n"
                + " / aefbgc / aefbgcd / aefbgd / aefbgdc / aefcb / aefcbd / aefcbdg / aefcbg / aefcbgd / aefcd\n"
                + " / aefcdb / aefcdbg / aefcdg / aefcdgb / aefcg / aefcgb / aefcgbd / aefcgd / aefcgdb / aefdb\n"
                + " / aefdbc / aefdbcg / aefdbg / aefdbgc / aefdc / aefdcb / aefdcbg / aefdcg / aefdcgb / aefdgb\n"
                + " / aefdgbc / aefdgc / aefdgcb / aefgb / aefgbc / aefgbcd / aefgbd / aefgbdc / aefgc / aefgcb\n"
                + " / aefgcbd / aefgcd / aefgcdb / aefgdb / aefgdbc / aefgdc / aefgdcb / aegbc / aegbcd / aegbcdf\n"
                + " / aegbcf / aegbcfd / aegbdc / aegbdcf / aegbdf / aegbdfc / aegbf / aegbfc / aegbfcd / aegbfd\n"
                + " / aegbfdc / aegc / aegcb / aegcbd / aegcbdf / aegcbf / aegcbfd / aegcd / aegcdb / aegcdbf\n"
                + " / aegcdf / aegcdfb / aegcf / aegcfb / aegcfbd / aegcfd / aegcfdb / aegdbc / aegdbcf / aegdbf\n"
                + " / aegdbfc / aegdc / aegdcb / aegdcbf / aegdcf / aegdcfb / aegdfb / aegdfbc / aegdfc / aegdfcb\n"
                + " / aegfb / aegfbc / aegfbcd / aegfbd / aegfbdc / aegfc / aegfcb / aegfcbd / aegfcd / aegfcdb\n"
                + " / aegfdb / aegfdbc / aegfdc / aegfdcb / afeb / afebc / afebcd / afebcdg / afebcg / afebcgd\n"
                + " / afebd / afebdc / afebdcg / afebdg / afebdgc / afebg / afebgc / afebgcd / afebgd / afebgdc\n"
                + " / afecb / afecbd / afecbdg / afecbg / afecbgd / afecd / afecdb / afecdbg / afecdg / afecdgb\n"
                + " / afecg / afecgb / afecgbd / afecgd / afecgdb / afedb / afedbc / afedbcg / afedbg / afedbgc\n"
                + " / afedc / afedcb / afedcbg / afedcg / afedcgb / afedgb / afedgbc / afedgc / afedgcb / afegb\n"
                + " / afegbc / afegbcd / afegbd / afegbdc / afegc / afegcb / afegcbd / afegcd / afegcdb / afegdb\n"
                + " / afegdbc / afegdc / afegdcb / afgeb / afgebc / afgebcd / afgebd / afgebdc / afgec / afgecb\n"
                + " / afgecbd / afgecd / afgecdb / afgedb / afgedbc / afgedc / afgedcb / agebc / agebcd / agebcdf\n"
                + " / agebcf / agebcfd / agebdc / agebdcf / agebdf / agebdfc / agebf / agebfc / agebfcd / agebfd\n"
                + " / agebfdc / agec / agecb / agecbd / agecbdf / agecbf / agecbfd / agecd / agecdb / agecdbf\n"
                + " / agecdf / agecdfb / agecf / agecfb / agecfbd / agecfd / agecfdb / agedbc / agedbcf / agedbf\n"
                + " / agedbfc / agedc / agedcb / agedcbf / agedcf / agedcfb / agedfb / agedfbc / agedfc / agedfcb\n"
                + " / agefb / agefbc / agefbcd / agefbd / agefbdc / agefc / agefcb / agefcbd / agefcd / agefcdb\n"
                + " / agefdb / agefdbc / agefdc / agefdcb / agfeb / agfebc / agfebcd / agfebd / agfebdc / agfec\n"
                + " / agfecb / agfecbd / agfecd / agfecdb / agfedb / agfedbc / agfedc / agfedcb / eabcd / eabcdf\n"
                + " / eabcdfg / eabcdg / eabcdgf / eabcf / eabcfd / eabcfdg / eabcfg / eabcfgd / eabcg / eabcgd\n"
                + " / eabcgdf / eabcgf / eabcgfd / eabdc / eabdcf / eabdcfg / eabdcg / eabdcgf / eabdf / eabdfc\n"
                + " / eabdfcg / eabdfg / eabdfgc / eabdgc / eabdgcf / eabdgf / eabdgfc / eabf / eabfc / eabfcd\n"
                + " / eabfcdg / eabfcg / eabfcgd / eabfd / eabfdc / eabfdcg / eabfdg / eabfdgc / eabfg / eabfgc\n"
                + " / eabfgcd / eabfgd / eabfgdc / eabgc / eabgcd / eabgcdf / eabgcf / eabgcfd / eabgdc / eabgdcf\n"
                + " / eabgdf / eabgdfc / eabgf / eabgfc / eabgfcd / eabgfd / eabgfdc / eacbd / eacbdf / eacbdfg\n"
                + " / eacbdg / eacbdgf / eacbf / eacbfd / eacbfdg / eacbfg / eacbfgd / eacbg / eacbgd / eacbgdf\n"
                + " / eacbgf / eacbgfd / eacd / eacdb / eacdbf / eacdbfg / eacdbg / eacdbgf / eacdf / eacdfb\n"
                + " / eacdfbg / eacdfg / eacdfgb / eacdg / eacdgb / eacdgbf / eacdgf / eacdgfb / eacfb / eacfbd\n"
                + " / eacfbdg / eacfbg / eacfbgd / eacfd / eacfdb / eacfdbg / eacfdg / eacfdgb / eacfg / eacfgb\n"
                + " / eacfgbd / eacfgd / eacfgdb / eacg / eacgb / eacgbd / eacgbdf / eacgbf / eacgbfd / eacgd\n"
                + " / eacgdb / eacgdbf / eacgdf / eacgdfb / eacgf / eacgfb / eacgfbd / eacgfd / eacgfdb / eadbc\n"
                + " / eadbcf / eadbcfg / eadbcg / eadbcgf / eadbf / eadbfc / eadbfcg / eadbfg / eadbfgc / eadbgc\n"
                + " / eadbgcf / eadbgf / eadbgfc / eadc / eadcb / eadcbf / eadcbfg / eadcbg / eadcbgf / eadcf\n"
                + " / eadcfb / eadcfbg / eadcfg / eadcfgb / eadcg / eadcgb / eadcgbf / eadcgf / eadcgfb / eadfb\n"
                + " / eadfbc / eadfbcg / eadfbg / eadfbgc / eadfc / eadfcb / eadfcbg / eadfcg / eadfcgb / eadfgb\n"
                + " / eadfgbc / eadfgc / eadfgcb / eadgbc / eadgbcf / eadgbf / eadgbfc / eadgc / eadgcb / eadgcbf\n"
                + " / eadgcf / eadgcfb / eadgfb / eadgfbc / eadgfc / eadgfcb / eafb / eafbc / eafbcd / eafbcdg\n"
                + " / eafbcg / eafbcgd / eafbd / eafbdc / eafbdcg / eafbdg / eafbdgc / eafbg / eafbgc / eafbgcd\n"
                + " / eafbgd / eafbgdc / eafcb / eafcbd / eafcbdg / eafcbg / eafcbgd / eafcd / eafcdb / eafcdbg\n"
                + " / eafcdg / eafcdgb / eafcg / eafcgb / eafcgbd / eafcgd / eafcgdb / eafdb / eafdbc / eafdbcg\n"
                + " / eafdbg / eafdbgc / eafdc / eafdcb / eafdcbg / eafdcg / eafdcgb / eafdgb / eafdgbc / eafdgc\n"
                + " / eafdgcb / eafgb / eafgbc / eafgbcd / eafgbd / eafgbdc / eafgc / eafgcb / eafgcbd / eafgcd\n"
                + " / eafgcdb / eafgdb / eafgdbc / eafgdc / eafgdcb / eagbc / eagbcd / eagbcdf / eagbcf / eagbcfd\n"
                + " / eagbdc / eagbdcf / eagbdf / eagbdfc / eagbf / eagbfc / eagbfcd / eagbfd / eagbfdc / eagc\n"
                + " / eagcb / eagcbd / eagcbdf / eagcbf / eagcbfd / eagcd / eagcdb / eagcdbf / eagcdf / eagcdfb\n"
                + " / eagcf / eagcfb / eagcfbd / eagcfd / eagcfdb / eagdbc / eagdbcf / eagdbf / eagdbfc / eagdc\n"
                + " / eagdcb / eagdcbf / eagdcf / eagdcfb / eagdfb / eagdfbc / eagdfc / eagdfcb / eagfb / eagfbc\n"
                + " / eagfbcd / eagfbd / eagfbdc / eagfc / eagfcb / eagfcbd / eagfcd / eagfcdb / eagfdb / eagfdbc\n"
                + " / eagfdc / eagfdcb / ebacd / ebacdf / ebacdfg / ebacdg / ebacdgf / ebacf / ebacfd / ebacfdg\n"
                + " / ebacfg / ebacfgd / ebacg / ebacgd / ebacgdf / ebacgf / ebacgfd / ebadc / ebadcf / ebadcfg\n"
                + " / ebadcg / ebadcgf / ebadf / ebadfc / ebadfcg / ebadfg / ebadfgc / ebadgc / ebadgcf / ebadgf\n"
                + " / ebadgfc / ebaf / ebafc / ebafcd / ebafcdg / ebafcg / ebafcgd / ebafd / ebafdc / ebafdcg\n"
                + " / ebafdg / ebafdgc / ebafg / ebafgc / ebafgcd / ebafgd / ebafgdc / ebagc / ebagcd / ebagcdf\n"
                + " / ebagcf / ebagcfd / ebagdc / ebagdcf / ebagdf / ebagdfc / ebagf / ebagfc / ebagfcd / ebagfd\n"
                + " / ebagfdc / ebcad / ebcadf / ebcadfg / ebcadg / ebcadgf / ebcaf / ebcafd / ebcafdg / ebcafg\n"
                + " / ebcafgd / ebcag / ebcagd / ebcagdf / ebcagf / ebcagfd / ebcda / ebcdaf / ebcdafg / ebcdag\n"
                + " / ebcdagf / ebdac / ebdacf / ebdacfg / ebdacg / ebdacgf / ebdaf / ebdafc / ebdafcg / ebdafg\n"
                + " / ebdafgc / ebdagc / ebdagcf / ebdagf / ebdagfc / ebdca / ebdcaf / ebdcafg / ebdcag / ebdcagf\n"
                + " / ecabd / ecabdf / ecabdfg / ecabdg / ecabdgf / ecabf / ecabfd / ecabfdg / ecabfg / ecabfgd\n"
                + " / ecabg / ecabgd / ecabgdf / ecabgf / ecabgfd / ecad / ecadb / ecadbf / ecadbfg / ecadbg\n"
                + " / ecadbgf / ecadf / ecadfb / ecadfbg / ecadfg / ecadfgb / ecadg / ecadgb / ecadgbf / ecadgf\n"
                + " / ecadgfb / ecafb / ecafbd / ecafbdg / ecafbg / ecafbgd / ecafd / ecafdb / ecafdbg / ecafdg\n"
                + " / ecafdgb / ecafg / ecafgb / ecafgbd / ecafgd / ecafgdb / ecag / ecagb / ecagbd / ecagbdf\n"
                + " / ecagbf / ecagbfd / ecagd / ecagdb / ecagdbf / ecagdf / ecagdfb / ecagf / ecagfb / ecagfbd\n"
                + " / ecagfd / ecagfdb / ecbad / ecbadf / ecbadfg / ecbadg / ecbadgf / ecbaf / ecbafd / ecbafdg\n"
                + " / ecbafg / ecbafgd / ecbag / ecbagd / ecbagdf / ecbagf / ecbagfd / ecbda / ecbdaf / ecbdafg\n"
                + " / ecbdag / ecbdagf / ecda / ecdab / ecdabf / ecdabfg / ecdabg / ecdabgf / ecdaf / ecdafb\n"
                + " / ecdafbg / ecdafg / ecdafgb / ecdag / ecdagb / ecdagbf / ecdagf / ecdagfb / ecdba / ecdbaf\n"
                + " / ecdbafg / ecdbag / ecdbagf / edabc / edabcf / edabcfg / edabcg / edabcgf / edabf / edabfc\n"
                + " / edabfcg / edabfg / edabfgc / edabgc / edabgcf / edabgf / edabgfc / edac / edacb / edacbf\n"
                + " / edacbfg / edacbg / edacbgf / edacf / edacfb / edacfbg / edacfg / edacfgb / edacg / edacgb\n"
                + " / edacgbf / edacgf / edacgfb / edafb / edafbc / edafbcg / edafbg / edafbgc / edafc / edafcb\n"
                + " / edafcbg / edafcg / edafcgb / edafgb / edafgbc / edafgc / edafgcb / edagbc / edagbcf / edagbf\n"
                + " / edagbfc / edagc / edagcb / edagcbf / edagcf / edagcfb / edagfb / edagfbc / edagfc / edagfcb\n"
                + " / edbac / edbacf / edbacfg / edbacg / edbacgf / edbaf / edbafc / edbafcg / edbafg / edbafgc\n"
                + " / edbagc / edbagcf / edbagf / edbagfc / edbca / edbcaf / edbcafg / edbcag / edbcagf / edca\n"
                + " / edcab / edcabf / edcabfg / edcabg / edcabgf / edcaf / edcafb / edcafbg / edcafg / edcafgb\n"
                + " / edcag / edcagb / edcagbf / edcagf / edcagfb / edcba / edcbaf / edcbafg / edcbag / edcbagf\n"
                + " / null";
        assertTrue(laby2.etsiJaTulostaReitit().equals(reittilista));
    }
    
    public void ReittienEtsintaIlmanTulostusta(){
        assertEquals(laby1.etsiReitit(), 11);
        assertEquals(laby2.etsiReitit(), 1031);
        assertEquals(laby3.etsiReitit(), 195);
    }
}
