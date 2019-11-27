package criptografia.activitats;

import org.junit.Test;
import static org.junit.Assert.*;


public class Practica1_HashTest {
    
    public Practica1_HashTest() {
    }

    @Test
    public void testResumFitxer() {
        System.out.println("resumFitxer");
        String fitxer = "fitxers/practica1.txt";
        Practica1_Hash instance = new Practica1_Hash();
        String expResult = "03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4";
        String result = instance.resumFitxer(fitxer);
        assertEquals(expResult, result);
    }

    @Test
    public void testTrobaCombinacio() {
        System.out.println("trobaCombinacio");
        String hash = "03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4";
        Practica1_Hash instance = new Practica1_Hash();
        String expResult = "1234";
        String result = instance.trobaCombinacio(hash);
        assertEquals(expResult, result);
    }

   
    
}
