
package criptografia;

import java.security.MessageDigest;

public class Hash {
    
    public static void main(String[] args) {
        byte[] hash = Hash.generar("Exemple");
        imprimir(hash);
    }
    
    public static byte[] generar(String text){
        // Aplica l'algorisme SHA-256 sobre el text
        byte[] hash = null;
        try {
            byte[] dades = text.getBytes();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            hash = md.digest(dades);
        } catch (Exception ex) {
            System.err.println("Error");
        }
        return hash;
    }
    
    public static void imprimir(byte[] text){
        // Imprimeix un byte[] en hexadecimal
        for (byte t:text){
            System.out.print(String.format("%02X", t));
        }
        System.out.println();
    }
}
