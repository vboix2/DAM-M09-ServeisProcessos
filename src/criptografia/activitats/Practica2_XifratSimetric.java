package criptografia.activitats;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Practica2_XifratSimetric {
    
    private static final String NOM_FITXER = "practica2";

    public static void main(String[] args) {
        SecretKey clau = generarClau("password");
        
        String text_clar = llegirFitxer(NOM_FITXER + ".txt");
        String text_xif = encriptar(clau, text_clar);
        escriureFitxer(text_xif, NOM_FITXER + ".enc");
        
        String text_xif2 = llegirFitxer(NOM_FITXER + ".enc");
        String text_clar2 = desencriptar(clau, text_xif2);
        System.out.println(text_clar2);
        

    }

    public static SecretKey generarClau(String text) {
        SecretKey sKey = null;
        try {
            byte[] data = text.getBytes("ISO-8859-1");
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(data);
            byte[] key = Arrays.copyOf(hash, 16);
            sKey = new SecretKeySpec(key, "AES");
        } catch (Exception ex) {
            System.err.println("Error generant la clau:" + ex);
        }
        return sKey;
    }

    public static String llegirFitxer(String nom) {
        String txt = "";
        try (FileReader fr = new FileReader("fitxers/" + nom);
                BufferedReader bf = new BufferedReader(fr);) {
            String line;
            while ((line = bf.readLine()) != null) {
                txt += line;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Fitxer inexistent");
        } catch (IOException ex) {
            System.out.println("Error de lectura");
        }
        return txt;
    }

    public static void escriureFitxer(String text, String nom) {
        try (PrintStream ps2
                = new PrintStream("fitxers/" + nom)) {
            ps2.println(text);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getClass().getName());
        }
    }

    public static String encriptar(SecretKey clau, String text_clar) {
        String text_enc = null;
        try {
            byte[] dades_clar = text_clar.getBytes("ISO-8859-1");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, clau);
            byte[] dades_enc = cipher.doFinal(dades_clar);
            text_enc = DatatypeConverter.printHexBinary(dades_enc);
            
        } catch (Exception ex) {
            System.err.println("Error xifrant les dades: " + ex);
        }
        return text_enc;
    }
    
    public static String desencriptar (SecretKey clau, String text_enc) {
        String text_clar = null;
        try {
            byte[] dades_enc = DatatypeConverter.parseHexBinary(text_enc);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, clau);
            byte[] dades_clar = cipher.doFinal(dades_enc);
            text_clar = new String(dades_clar);
        } catch (Exception ex) {
            System.err.println("Error xifrant les dades: " + ex);
        }
        return text_clar;
    } 

}
