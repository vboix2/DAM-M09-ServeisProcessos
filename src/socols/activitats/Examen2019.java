
package socols.activitats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;


public class Examen2019 {
    
    private static BufferedReader in;
    private static PrintStream out;

    public static void main(String[] args) {
        Socket socket;
        
        try {
            ServerSocket servidor = new ServerSocket(6543);
            socket = servidor.accept();
            System.out.println("Connection started");
            
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());
            
            while (true) {
                String missatge = in.readLine();
                if (missatge.equals("/end")) break;
                String resposta = respostaBot(missatge);
                out.println(resposta);
                out.flush();
            }
            
            socket.close();
            
        } catch (IOException ex) {
            System.out.println("Connection error");
        }
    }
    
    public static String respostaBot(String missatge) throws IOException{
        String resposta = "";
        switch (missatge){
            case "/start":
                resposta = "Benvingut al bot de M09!";
                break;
            case "/hora":
                resposta = funcio_hora();
                break;
            case "/dia":
                resposta = funcio_dia();
                break;
            case "/suma":
                resposta = funcio_suma();
                break;
            default:
                resposta = missatge;
        }
        
        return resposta;
    }
    
    public static String funcio_hora(){
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minutes = now.get(Calendar.MINUTE);
        return "Són les " + hour + ":" + minutes;
    }
    
    public static String funcio_dia(){
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_MONTH);
        int month = now.get(Calendar.MONTH);
        int year = now.get(Calendar.YEAR);
        return "Avui és "+day+"/"+month+"/"+year;
    }
    
    public static String funcio_suma() throws IOException{
        out.println("Indica els nombres a sumar separats per espais");
        out.flush();
        String valors = in.readLine();
        String[] nombres = valors.split(" ");
        int total = 0;
        for (String s:nombres){
            total += Integer.parseInt(s);
        }
        return "El resultat és " + Integer.toString(total);
    }
    
}

