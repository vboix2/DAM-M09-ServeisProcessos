// Exemples d'aplicació dels mètodes criptogràfics

package criptografia;

import javax.crypto.SecretKey;
import java.security.KeyPair;

public class Main {

    public static void main(String[] args) {
        
        // Funció de hash
        
        byte[] hash = Hash.generar("Exemple");
        imprimir(hash);
        
        // Xifrat simètric
        
        Xifrat_Simetric xs = new Xifrat_Simetric();
        SecretKey clau = xs.generarClau("password",192);
        byte[] text_xifrat = xs.encriptar(clau,"Text clar");
        imprimir(text_xifrat);
        
        // Xifrat assimètric
        
        Xifrat_Assimetric xas = new Xifrat_Assimetric();
        KeyPair claus = xas.generarClaus();
        byte[] text = "Exemple".getBytes();
        byte[] xifrat = xas.encriptar(text, claus.getPublic());
        imprimir(xifrat);
        
        byte[] desxifrat = xas.desencriptar(xifrat, claus.getPrivate());
        System.out.println(new String(desxifrat));
        
    }
    
    public static void imprimir(byte[] text){
        for (byte t:text){
            System.out.print(String.format("%02X", t));
        }
        System.out.println();
    }
}
