package socols;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TCP_Client {

    public static void main(String[] args) {
        InetAddress ip;
        Socket socket;
        BufferedReader in = null;
        PrintStream out = null;

        try {
            // Petició de connexió
            ip = InetAddress.getByName("localhost");
            socket = new Socket(ip, 9090);
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
                if (missatge.equals("adeu")) break;
                String resposta = in.readLine();
                System.out.println("Servidor: " + resposta);
            }            
            socket.close();

        } catch (Exception ex) {
            System.out.println("Problema establint la connexió");            
        }

    }

}
