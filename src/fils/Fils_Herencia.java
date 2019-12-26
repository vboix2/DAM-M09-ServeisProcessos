
package fils;

public class Fils_Herencia {
    
    // Creació de fils heretant la classe Thread

    public static void main(String[] args) throws InterruptedException {
        
        // Creem els fils
        Fil_Thread primer = new Fil_Thread("Fil 1");
        Fil_Thread segon = new Fil_Thread("Fil 2");
        
        // Execució independent dels fils
        primer.start();
        segon.start();
        
        // Sincronització dels fils
        primer.join();
        segon.join();
        System.out.println("Final de l'execució dels fils");
        
    }
    
}

class Fil_Thread extends Thread{

    String nom;
    
    public Fil_Thread(String nom){
        this.nom = nom;
    }
   
    @Override
    public void run(){
        // Mètode que conté les instruccions del fil
        for (int i=1; i<=10; i++){
            System.out.println(nom + " - " + i);
        }
    }
}
