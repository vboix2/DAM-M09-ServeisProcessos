
package socols;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDP_Receptor {

    public static void main(String[] args) {

        try {
            DatagramSocket socket = new DatagramSocket(5555);

            while (true) {
                byte[] missatge = new byte[1024];
                DatagramPacket packet = new DatagramPacket(missatge, missatge.length);
                socket.receive(packet);

                String text = new String(packet.getData()).trim();
                if (text.equals("q")) break;
                System.out.println("IP: " + packet.getAddress()
                        + " Text: " + text);
            }
        } catch (Exception ex) {
            System.out.println("Problema amb la recepció");
        }

    }

}
