
package fils;

public class Compartida_Herencia {
    
    // Dades compartides entre fils heretant de Thread

    public static void main(String[] args) {
        
        // Creem l'objecte comptador
        Comptador m = new Comptador(20);
        
        // Creem els fils
        for (int i=0; i <5; i++){
            new Comptador_Fil(m, "Fil " + i).start();
        }
    }
}

class Comptador_Fil extends Thread{
    Comptador comptador;
    String nom;
    
    public Comptador_Fil(Comptador c, String n){
        comptador = c;
        nom = n;
    }
    
    @Override
    public void run(){
        while (!comptador.getFi()){
            comptador.incrementa(nom);
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Excepció " + ex);
            }
        }
        
    }
}

class Comptador {
    private volatile boolean fi;
    private volatile int num;
    private int ultim;
    
    public Comptador(int u){
        num = 0;
        fi = false;
        ultim = u;
    }
    
    public synchronized void incrementa(String nom){
        // Mètode bloquejat que incrementa el comptador
        if (num<=ultim){
            System.out.println(nom + " - " + num);
            num++;
        }
        if (num==ultim+1){
            fi = true;
        }
    }
    
    public boolean getFi(){
        return fi;
    }
}
