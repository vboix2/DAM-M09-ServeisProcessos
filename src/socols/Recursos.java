package socols;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Recursos {

    public static void main(String[] args) {
        // Exemples d'utilització de les classes URL i URLConnection

        // Classe URL
        try {
            // Crear una instància URL
            URL url = new URL("https://docs.oracle.com/javase/8/docs/api/java/net/URL.html");
            
            // Obtenir informació de la URL
            System.out.println("File: " + url.getFile());
            System.out.println("Host: " + url.getHost());
            System.out.println("Protocol: " + url.getProtocol());
            System.out.println("Port: " + url.getDefaultPort());
            
            // Descarregar el contingut d'una URL
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            String linia;
            String html="";
            while ((linia = in.readLine()) != null) {
                html += linia;
            }
            in.close();
            System.out.println(html.substring(0, 100));
            
        } catch (Exception ex) {
            System.out.println("Host no trobat");
        }
       

        // Classe URLConnection
        try {
            // Crear una instància URLConnection
            URL url = new URL("https://docs.oracle.com/javase/8/docs/api/java/net/URL.html");
            URLConnection con = url.openConnection();
            
            // Obtenir informació del contingut
            System.out.println("ContentType: " + con.getContentType());
            System.out.println("Encoding: " + con.getContentEncoding());
            System.out.println("Length: " + con.getContentLength());
            System.out.println("Date: " + con.getDate());
            
            // Obtenir el contingut
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String html="";
            String linia;
            while((linia = br.readLine())!= null){
                html += linia;
            }
            in.close();
            System.out.println(html.substring(0, 100));
            
        } catch (Exception ex) {
            System.out.println("Problema accedint a la URL");
        }
    }

}
