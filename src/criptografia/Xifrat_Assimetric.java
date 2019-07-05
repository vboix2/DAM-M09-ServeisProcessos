
package criptografia;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

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
        // Desencripta dades binàries utiltizant la clau privada
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
    
    public byte[][] encryptWrappedData(byte[] data, PublicKey pub) {
        // Xifrat amb clau embolcallada
        // Torna un array amb les dades i la clau simètrica xifrada
        
        byte[][] encWrappedData = new byte[2][];
        try {
            // Genera una clau de xifrat simètric
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            SecretKey sKey = kgen.generateKey();
            
            // Xifra les dades binàries utilitzant la clau simètrica
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, sKey);
            byte[] encMsg = cipher.doFinal(data);
            
            // Xifra la clau simètrica amb la clau pública del receptor
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.WRAP_MODE, pub);
            byte[] encKey = cipher.wrap(sKey);
            
            // Dades a enviar
            encWrappedData[0] = encMsg;  // Missatge
            encWrappedData[1] = encKey;  // Clau simètrica xifrada
            
        } catch (Exception ex) {
            System.err.println("Ha succeït un error xifrant: " + ex);
           }
        
        return encWrappedData;
    }
}