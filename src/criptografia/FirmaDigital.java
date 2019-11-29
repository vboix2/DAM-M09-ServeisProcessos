package criptografia;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class FirmaDigital {
    
    public static void main(String[] args) {
        // Generem una parella de claus
        KeyPair claus = Xifrat_Assimetric.generarClaus();
        // Signem un missatge
        byte[] dades = "Missatge".getBytes();
        byte[] firma = signar(dades, claus.getPrivate());
        // Validem la firma
        boolean esValida = validar(dades, firma, claus.getPublic());
        System.out.println((esValida)? "Firma vàlida":"Firma invàlida");
    }

    public static byte[] signar(byte[] data, PrivateKey priv) {
        // Signa digitalment dades binàries
        byte[] signature = null;
        try {
            Signature signer = Signature.getInstance("SHA1withRSA");
            signer.initSign(priv);
            signer.update(data);
            signature = signer.sign();

        } catch (Exception ex) {
            System.err.println("Error signant les dades: " + ex);
        }
        return signature;
    }

    public static boolean validar(byte[] data, byte[] signature, PublicKey pub) {
        // Valida una signatura digital
        boolean isValid = false;
        try {
            Signature signer = Signature.getInstance("SHA1withRSA");
            signer.initVerify(pub);
            signer.update(data);
            isValid = signer.verify(signature);
            
        } catch (Exception ex) {
            System.err.println("Error validant les dades: " + ex);
        }
        return isValid;
    }

}
