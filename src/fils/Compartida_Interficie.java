
package fils;

public class Compartida_Interficie {
    
    // Dades compartides entre fils implementant Runnable

    public static void main(String[] args) throws InterruptedException {
        
        // Creem els fils
        Compartida_Fil fr = new Compartida_Fil(20);
        Thread primer = new Thread(fr);
        Thread segon = new Thread(fr);
        Thread tercer = new Thread(fr);
        
        // Numerem els fils
        primer.setName("Fil 1");
        segon.setName("Fil 2");
        tercer.setName("Fil 3");
        
        // Executem els fils
        primer.start();
        segon.start();
        tercer.start();
        
        // Sincronització
        primer.join();
        segon.join();
        tercer.join();
        System.out.println("Final de l'execució dels fils");
    }
    
}

class Compartida_Fil implements Runnable{
    
    public volatile int comptador;
    public volatile boolean fi;
    private int ultim;

    public Compartida_Fil(int ultim) {
        this.comptador = 0;
        this.fi = false;
        this.ultim = ultim;
    }
    
    @Override
    public void run(){
        // Mètode que conté les instruccions del fil
        while (!fi){
            incrementa();
            espera();
        }
    }
    
    public synchronized void incrementa(){
        // Mètode bloquejat
        if (comptador<=ultim){
            System.out.println(Thread.currentThread().getName() + " " + comptador);
            comptador++;
        }
        if (comptador==ultim+1){
            fi = true;
        }   
    }
    
    public static void espera(){
        // Bloqueja un fil durant 100 ms
        try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
    }
}
