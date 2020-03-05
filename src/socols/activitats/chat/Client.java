package socols.activitats.chat;

import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getByName("localhost");
            Socket socket = new Socket(ip, 9090);
            System.out.println("Connection started");
            
            Listen input = new Listen(socket);
            Talk output = new Talk(socket);
            input.start();
            output.start();
            
        } catch (Exception ex) {
            System.out.println("Connection error");
        }
    }

}
