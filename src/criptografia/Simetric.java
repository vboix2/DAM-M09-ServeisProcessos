
package criptografia;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Simetric {
    // Xifrat per blocs
    
    public SecretKey generarClau(String text, int mida){
        // Genera una clau de xifrat
        SecretKey sKey = null;
        if ((mida == 128)||(mida == 192)||(mida == 256)) {
            try {
                byte[] data = text.getBytes("ISO-8859-1");
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hash = md.digest(data);
                byte[] key = Arrays.copyOf(hash, mida/8);
                sKey = new SecretKeySpec(key, "AES");
            } catch (Exception ex) {
                System.err.println("Error generant la clau:" + ex);
                }
            }
        return sKey;
    }
    
    public byte[] encriptar (SecretKey clau, String text) {
        // Encripta les dades
        byte[] dades = null;
        byte[] dadesEncriptades = null;
        try {
            dades = text.getBytes("ISO-8859-1");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, clau);
            dadesEncriptades = cipher.doFinal(dades);
        } catch (Exception ex) {
            System.err.println("Error xifrant les dades: " + ex);
        }
        return dadesEncriptades;
    }
    
    public String desencriptar (SecretKey clau, byte[] dades) {
        // Desencripta les dades
        String text = null;
        byte[] dadesDesencriptades = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, clau);
            dadesDesencriptades = cipher.doFinal(dades);
            text = new String(dadesDesencriptades, "ISO-8859-1");
        } catch (Exception ex) {
            System.err.println("Error xifrant les dades: " + ex);
        }
        return text;
    } 
}