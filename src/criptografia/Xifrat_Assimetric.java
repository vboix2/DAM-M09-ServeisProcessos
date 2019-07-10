
package criptografia;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class Xifrat_Assimetric {
    // Criptografia de clau pública RSA
    
    public KeyPair generarClaus() {
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
    
    public byte[] encriptar(byte[] data, PublicKey pub) {
        // Encripta dades binàries utilitzant la clau pública
        byte[] dades = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding","SunJCE");
            cipher.init(Cipher.ENCRYPT_MODE, pub);
            dades = cipher.doFinal(data);
        } catch (Exception ex) {
            System.err.println("Error xifrant: " + ex);
        }
        return dades;
    }
    
    public byte[] desencriptar(byte[] data, PrivateKey priv) {
        // Desencripta dades binàries utilitzant la clau privada
        byte[] dades = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding","SunJCE");
            cipher.init(Cipher.DECRYPT_MODE, priv);
            dades = cipher.doFinal(data);
        } catch (Exception ex) {
            System.err.println("Error xifrant: " + ex);
        }
        return dades;
    }
}