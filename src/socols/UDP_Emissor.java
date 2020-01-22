/*
Implementació de sòcols no orientats a connexió (UDP)
 */
package socols;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDP_Emissor {

    public static void main(String[] args) throws UnknownHostException,
            SocketException, IOException {
        DatagramSocket socket = new DatagramSocket();
        int portDest = 5555;
        InetAddress adrecaDest = InetAddress.getByName("localhost");
                
        Scanner lector = new Scanner(System.in);
        String text = lector.nextLine();
        while (!text.equals("q")) {
            
            //Paquet emissor
            byte[] missatge = text.getBytes();
            DatagramPacket packet = new DatagramPacket(missatge,
                    missatge.length, adrecaDest, portDest);

            //Enviament del missatge
            socket.send(packet);
            text = lector.nextLine();
        }
    }

}