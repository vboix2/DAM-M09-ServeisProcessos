
package criptografia;

import java.security.MessageDigest;

public class Hash {
    // Funció hash per generar claus de xifrat
    
    public static byte[] generar(String text){
        // Aplica l'algorisme SHA-256 sobre el text
        byte[] hash = null;
        try {
            byte[] dades = text.getBytes();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            hash = md.digest(dades);
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return hash;
    }
}
