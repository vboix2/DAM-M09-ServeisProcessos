
package fils;

public class Fils_Interficie {
    
    // Creació de fils implementant la  interfície Runnable
    
    public static void main(String[] args) throws InterruptedException {
        // Creem els objectes
        Fil_Runnable fr1 = new Fil_Runnable("Fil 1");
        Fil_Runnable fr2 = new Fil_Runnable("Fil 2");
        
        // Creem els fils
        Thread primer = new Thread(fr1);
        Thread segon = new Thread(fr2);
        
        // Execució seqüencial dels fils
        primer.start();
        primer.join();
        
        segon.start();
        segon.join();
        
        System.out.println("Final de l'execució dels fils");
        
    }
}

class Fil_Runnable implements Runnable{
    
    String nom;
    
    public Fil_Runnable(String nom){
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