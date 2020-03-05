package socols.activitats.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        Socket socket;
        try {
            ServerSocket servidor = new ServerSocket(9090);
            socket = servidor.accept();
            System.out.println("Connection started");
            
            Listen input = new Listen(socket);
            Talk output = new Talk(socket);
            input.start();
            output.start();
            
        } catch (IOException ex) {
            System.out.println("Connection error");
        }
    }
}
