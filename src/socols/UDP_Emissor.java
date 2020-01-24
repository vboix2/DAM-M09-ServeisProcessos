
package socols;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDP_Emissor {

    public static void main(String[] args) {

        try {
            DatagramSocket socket = new DatagramSocket();

            int portDest = 5555;
            InetAddress adrecaDest = InetAddress.getByName("localhost");

            System.out.println("Escriu les dades a enviar ('q' finalitza la transmissió).");
            Scanner lector = new Scanner(System.in);
            while (true) {
                // Dades
                String text = lector.nextLine();
                
                //Paquet emissor
                byte[] missatge = text.getBytes();
                DatagramPacket packet = new DatagramPacket(missatge,
                        missatge.length, adrecaDest, portDest);

                //Enviament del missatge
                socket.send(packet);
                
                if (text.equals("q")) break;
            }
        } catch (Exception ex) {
            System.out.println("Problema amb la transmissió");
        }
    }

}
