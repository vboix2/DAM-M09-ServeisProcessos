
package criptografia;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Xifrat_Simetric {
    
    public static void main(String[] args) {
        // Generem la clau de xifrat
        SecretKey clau = generarClau("password",192);
        // Xifrem el missatge
        byte[] dades_enc = encriptar(clau,"Missatge");
        String text_enc = DatatypeConverter.printHexBinary(dades_enc);
        System.out.println(text_enc);
        
        // Desxifrem les dades encriptades
        String text_clar = desencriptar(clau, dades_enc);
        System.out.println(text_clar);
    }
    
    public static SecretKey generarClau(String text, int mida){
        // Genera una clau de xifrat utilitzant una contrasenya
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
    
    public static SecretKey generarClauAleatoria(){
        // Genera una clau de xifrat aleatòria
        KeyGenerator kgen = null;
        try {
            kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
        } catch (Exception ex){
            System.err.println("Error generant la clau " + ex);
        }
        return kgen.generateKey();
    }
    
    public static byte[] encriptar (SecretKey clau, String text) {
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
    
    public static String desencriptar (SecretKey clau, byte[] dades) {
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