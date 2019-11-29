
package criptografia;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;

public class Xifrat_Assimetric {
    // Criptografia de clau pública
    
    public static void main(String[] args) {
        // Exemples d'utilització de les funcions
        
        // Generem una parella de clau
        KeyPair claus = generarClaus();
        
        //Encriptem un text amb la clau pública
        byte[] dades_enc = encriptar("missatge secret", claus.getPublic());
        String text_enc = DatatypeConverter.printHexBinary(dades_enc);
        System.out.println(text_enc);
        
        // Desencriptem el text amb la clau privada
        String missatge = desencriptar(dades_enc, claus.getPrivate());
        System.out.println(missatge);
        
    }
    
    public static KeyPair generarClaus() {
        // Genera un parell de claus: pública i privada
        KeyPair keys = null;
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            keys = keyGen.genKeyPair();
        } catch (Exception ex) {
            System.err.println("Generador no disponible.");
        }
        return keys;
    }
    
    public static byte[] encriptar(String text_clar, PublicKey pub) {
        // Encripta dades binàries utilitzant la clau pública
        byte[] dades_clar = text_clar.getBytes();
        byte[] dades_enc = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding","SunJCE");
            cipher.init(Cipher.ENCRYPT_MODE, pub);
            dades_enc = cipher.doFinal(dades_clar);
        } catch (Exception ex) {
            System.err.println("Error xifrant: " + ex);
        }
        return dades_enc;
    }
    
    public static String desencriptar(byte[] data, PrivateKey priv) {
        // Desencripta dades binàries utilitzant la clau privada
        byte[] dades = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding","SunJCE");
            cipher.init(Cipher.DECRYPT_MODE, priv);
            dades = cipher.doFinal(data);
        } catch (Exception ex) {
            System.err.println("Error xifrant: " + ex);
        }
        return new String(dades);
    }
}