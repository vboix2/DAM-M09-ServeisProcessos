package socols.activitats.chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Talk extends Thread {

    PrintStream out;
    Socket socket;

    public Talk(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        while (true) {
            String resposta = JOptionPane.showInputDialog("Write your message:");
            out.println(resposta);
            out.flush();
            System.out.println("\t\t" + resposta);
            
            if (resposta.equals("end")) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    System.out.println("I/O error");
                }
                System.exit(0);
            }
        }
    }
}
