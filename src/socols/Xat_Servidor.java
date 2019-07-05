package socols;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Xat_Servidor {
    public static void main(String[] args) {
        TcpSocketServer servidor = new TcpSocketServer();
        servidor.listen();
    }
    
}

class TcpSocketServer {

    static final int PORT = 9090;

    public void listen() {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            clientSocket = serverSocket.accept();
            System.out.println("Connexió iniciada");
            proccesClientRequest(clientSocket);
            closeClient(clientSocket);
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }

        } catch (IOException ex) {
            System.out.println("Error de connexió. " + ex);
        }
    }

    public void proccesClientRequest(Socket clientSocket) {
        boolean missatgeFinal = false;
        String missatgeClient = "";
        BufferedReader in = null;
        PrintStream out = null;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintStream(clientSocket.getOutputStream());
            
            do {
                String respostaServidor = processData(missatgeClient);
                out.println(respostaServidor);
                out.flush();
                missatgeClient = in.readLine();
                missatgeFinal = esMissatgeFinal(missatgeClient);
                
            } while ((missatgeClient) != null && !missatgeFinal);
        } catch (IOException ex) {
             System.out.println("Error de connexió. " + ex);
        }
    }
    
    private String processData(String missatgeClient){
        System.out.println(missatgeClient);
        String dades = JOptionPane.showInputDialog(null,
                "Introdueix les dades a enviar",
                "Servidor",
                JOptionPane.QUESTION_MESSAGE);  
        System.out.println(dades);
        return dades;
    }
    
    private boolean esMissatgeFinal(String missatgeClient){
        if (missatgeClient.equals("fi")) {
            return true;
        }
        else{
            return false;
        }
    }

    private void closeClient(Socket clientSocket) {

        try {
            if (clientSocket != null && !clientSocket.isClosed()) {
                if (!clientSocket.isInputShutdown()) {
                    clientSocket.shutdownInput();
                }
                if (!clientSocket.isOutputShutdown()) {
                    clientSocket.shutdownOutput();
                }
                clientSocket.close();
            }
        } catch (IOException ex) {
            System.out.println("Error de connexió indefinit. " + ex);
        }
    }
}