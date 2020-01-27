
package socols;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


public class TCP_Servidor {


    public static void main(String[] args) {
        BufferedReader in;
        PrintStream out;
        
        try {
            // Espera connexions
            ServerSocket servidor = new ServerSocket(9090);
            Socket socket = servidor.accept();
            System.out.println("Connexió iniciada");
            
            // Fluxos de dades
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());

            // Transmissió
            while (true) {
                String missatge = in.readLine();
                System.out.println("Client: " + missatge);
                if (missatge.equals("adeu")) break;
                String resposta = missatge;
                out.println(resposta);
                out.flush();
                System.out.println("Servidor: " + resposta);
            }
            
            socket.close();
            
        } catch (IOException ex) {
            System.out.println("Problema iniciant el servidor");
        }
        
        
    }
    
}
