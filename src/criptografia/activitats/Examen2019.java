package criptografia.activitats;

import criptografia.Xifrat_Assimetric;
import criptografia.Xifrat_Simetric;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.SecretKey;

public class Examen2019 {

    public static void main(String[] args) {
        // Generem la parella de claus
        KeyPair claus = Xifrat_Assimetric.generarClaus();
        // L'emissor xifra el missatge i envia les dade
        byte[][] xifrat = emissor("Missatge secret!","contrasenya",claus.getPublic());
        // El receptor desxifra el missatge
        String clar = receptor(xifrat, claus.getPrivate());
        System.out.println(clar);
        
    }
    
    public static byte[][] emissor(String missatge, String pw, PublicKey pb){
        byte[][] dades = new byte[2][];
        // Xifrem la contrasenya amb la clau pública
        dades[0] = Xifrat_Assimetric.encriptar(pw, pb);
        // Generem la clau simètrica
        SecretKey clau = Xifrat_Simetric.generarClau(pw, 192);
        // Xifrem el missatge
        dades[1] = Xifrat_Simetric.encriptar(clau, missatge);
        
        return dades;
    }
    
    public static String receptor(byte[][] dades, PrivateKey pv){
        // Desxifrem la contrasenya
        String pw = Xifrat_Assimetric.desencriptar(dades[0], pv);
        // Generem la clau simètrica
        SecretKey clau = Xifrat_Simetric.generarClau(pw, 192);
        // Desxifrem el missatge
        String missatge = Xifrat_Simetric.desencriptar(clau, dades[1]);
        
        return missatge;
    }
    
}
