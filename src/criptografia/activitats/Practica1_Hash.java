package criptografia.activitats;

import criptografia.Hash;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Practica1_Hash {
    
    public String resumFitxer(String fitxer){
        /*
        Agafa la ruta d'un fitxer i torna un String
        amb el resum (SHA-256) de tot el seu contingut
        */
        
        // Llegim el contingut del fitxer
        String contingut="";
        try (FileReader fr = new FileReader(fitxer);
                BufferedReader bf = new BufferedReader(fr);){
            
            String buffer;
            while((buffer=bf.readLine())!=null){
                contingut+=buffer;
            }
            
        } catch(FileNotFoundException ex){
            System.out.println("Fitxer inexistent");
        } catch(IOException ex){
            System.out.println("Error de lectura");
        }
        
        // Calculem el resum del contingut
        byte[] resum = Hash.generar(contingut);
        
        // Tornem el resum en hexadecimal
        String resultat="";
        for (byte b:resum){
            resultat += String.format("%02X", b);
        }
        return resultat;
        
    }   
    public String trobaCombinacio(String hash){
        /*
        Agafa un String amb un hash i torna la cadena de text que
        l'ha generat. 
        Busca combinacions numèriques de 4 dígits.
        */
        
        String codi="";
        for (int i=0; i<10000; i++){
            // Generem una combinació de 4 dígits
            codi = Integer.toString(i);
            while(codi.length()<4){
                codi = "0"+codi;
            }
               
            // Calculem el resum
            byte[] resum = Hash.generar(codi);
               
            // Convertim el resum a hexadecimal
            String resultat = "";
            for (byte b : resum) {
                resultat += String.format("%02X", b);
            }
            
            // Comparem el resum amb l'entrada
            if (resultat.equals(hash)){
                break;
            }

        }
        return codi;
    }
}
