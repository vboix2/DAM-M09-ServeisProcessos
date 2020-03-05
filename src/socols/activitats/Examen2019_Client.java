
package socols.activitats;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Examen2019_Client {

    public static void main(String[] args) {
        InetAddress ip;
        Socket socket;
        BufferedReader in = null;
        PrintStream out = null;

        try {
            // Petició de connexió
            ip = InetAddress.getByName("localhost");
            socket = new Socket(ip, 6543);
            System.out.println("Connexió iniciada");
            
            // Fluxos de dades
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());
            
            // Transmissió
            Scanner s = new Scanner(System.in);
            String missatge = "";
            
            while (true) {
                System.out.print("Client: ");
                missatge = s.nextLine();
                out.println(missatge);
                out.flush();
                if (missatge.equals("/end")) break;
                String resposta = in.readLine();
                System.out.println("\t\tServidor: " + resposta);
            }            
            socket.close();

        } catch (Exception ex) {
            System.out.println("Problema establint la connexió");            
        }
    }
    
}
