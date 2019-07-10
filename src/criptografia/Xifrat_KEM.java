// Criptografia de clau embolcallada

package criptografia;

import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Xifrat_KEM {
        public byte[][] encryptWrappedData(byte[] data, PublicKey pub) {
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
