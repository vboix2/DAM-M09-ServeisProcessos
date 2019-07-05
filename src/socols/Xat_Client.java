package socols;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class Xat_Client {

    public static void main(String[] args) {
        int port = 9090;
        String ip = "localhost";
        TcpSocketClient client = new TcpSocketClient();
        client.connect(ip, port);
    }
    
}

class TcpSocketClient {

    public void connect(String address, int port) {
        String missatgeServidor;
        String respostaClient;
        boolean finalTransmissio = false;
        Socket socket;
        BufferedReader in;
        PrintStream out;
        try {
            socket = new Socket(InetAddress.getByName(address), port);
            System.out.println("Connexió iniciada");
            
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());
            
            while (!finalTransmissio) {
                missatgeServidor = in.readLine();
                respostaClient = getRequest(missatgeServidor);
                out.println(respostaClient);
                out.flush();
                finalTransmissio = esFinal(respostaClient);
            }
            close(socket);
            
        } catch (UnknownHostException ex) {
            System.out.println("Error de connexió. No existeix el host." + ex);
        } catch (IOException ex) {
            System.out.println("Error de connexió indefinit. " + ex);
        }
    }
    
    private boolean esFinal(String respostaClient){
        if (respostaClient.equals("fi")){
            return true;
        } else {
            return false;
        }
    }
    
    private String getRequest(String missatgeServidor){
        System.out.println(missatgeServidor);
        String dades = JOptionPane.showInputDialog(null,
                "Introdueix les dades a enviar",
                "Client",
                JOptionPane.QUESTION_MESSAGE);
        System.out.println(dades);        
        return dades;
    }

    private void close(Socket socket) {

        try {
            if (socket != null && !socket.isClosed()) {
                if (!socket.isInputShutdown()) {
                    socket.shutdownInput();
                }
                if (!socket.isOutputShutdown()) {
                    socket.shutdownOutput();
                }
                socket.close();
            }
        } catch (IOException ex) {
            System.out.println("Error de connexió indefinit. " + ex);
        }
    }
}
