
package fils.activitats;

public class Parquing {

    public static void main(String[] args) {
        // Creem el pàrqing
        Parking parking = new Parking(10);
        // Creem els cotxes (fils)
        for (int i = 1; i <= 20; i++) {
            Cotxe c = new Cotxe(Integer.toString(i), parking);
        }
    }
}

class Cotxe extends Thread {

    private Parking parking;

    public Cotxe(String matricula, Parking p) {
        super(matricula);
        this.parking = p;
        start();
    }

    public void run() {
        espera(10);
        parking.entra();
        System.out.println("Matrícula " + getName() + " entra al pàrquing");
        espera(20);       
        parking.surt();
        System.out.println("Matrícula " + getName() + " surt del pàrquing");
    }
    
    public void espera(int s){
        try {
            sleep((int) (Math.random() * s * 1000));
        } catch (InterruptedException e) {
            System.out.println("Problema bloquejant el fil");
        }
    }
}

class Parking {

    private int places_ocupades;
    private int n_places;

    public Parking(int places) {
        this.n_places = places;
        places_ocupades = 0;
    }

    public synchronized void entra() {
        // Mentre el pàrquing estigui ple esperem
        while (places_ocupades == n_places) {
            try {
                System.out.println("Esperant, pàrquing ple...");
                wait();
            } catch (InterruptedException e) {
                System.out.println("Error bloquejant el fil");
            }
        }
        // El cotxe entra i ocupa una plaça del pàrquing
        places_ocupades++;
    }

    public synchronized void surt() {
        // El coche surt del pàrquing
        System.out.println("Plaça alliberada.");
        places_ocupades--;
        // Notifiquem als cotxes que esperen
        notify();
    }
}