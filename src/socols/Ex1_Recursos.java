/*
Exemples d'utilització de les classes InetAddress i URL
 */

package socols;

import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Ex1_Recursos {

    public static void main(String[] args) {
        try {
            
            // Classe InetAddress
            
            InetAddress ipLocal = InetAddress.getLocalHost();
            System.out.println(ipLocal.getHostAddress());
            System.out.println(ipLocal.getHostName());
            System.out.println(ipLocal.toString());

            InetAddress ipGoogle = InetAddress.getByName("www.google.com");
            System.out.println(ipGoogle.toString());

            InetAddress ipOpenDNS = InetAddress.getByName("208.67.222.222");
            System.out.println(ipOpenDNS.toString());
            
            InetAddress[] google = InetAddress.getAllByName("google.com");

            for (InetAddress addr : google) {
                System.out.println(addr.getHostAddress());
            }
            
            // Classe URL
            
            URL a1 = new URL("https://docs.oracle.com/javase/8/docs/api/java/net/URL.html");
            System.out.println("File: "+a1.getFile());
            System.out.println("Host: "+a1.getHost());
            System.out.println("Protocol: "+a1.getProtocol());
            System.out.println("Port: "+a1.getDefaultPort());
            
            // Descarregar el contingut d'una URL
            BufferedReader in = new BufferedReader(
            new InputStreamReader(a1.openStream()));
            String linia;
            while((linia=in.readLine())!=null){
                System.out.println(linia);
            }
            
        } catch (Exception ex) {
            System.out.println("Host no trobat");
        }

    }

}
