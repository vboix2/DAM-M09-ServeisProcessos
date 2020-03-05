package socols.activitats.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Listen extends Thread {

    BufferedReader in;
    Socket socket;

    public Listen(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        while (true) {
            try {
                String missatge = in.readLine();
                System.out.println(missatge);
                
                if (missatge.equals("end")) {
                    socket.close();
                    System.exit(0);
                }
            } catch (IOException ex) {
                System.err.println("I/O error");
            }
        }
    }
}
