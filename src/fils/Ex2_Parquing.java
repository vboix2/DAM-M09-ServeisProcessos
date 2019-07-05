
package fils;

import static java.lang.Thread.sleep;

public class Ex2_Parquing {

    public static void main(String[] args) {
        // Creem el pàrqing
        Parking parking = new Parking(10);
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
        try {
            // Espera abans d'entrar al pàrquing
            sleep((int) (Math.random() * 10000));
        } catch (InterruptedException e) {
        }

        parking.entra();
        System.out.println("Matrícula " + getName() + " entra al pàrquing");

        try {
            // Simular estada esperant un temps aleatori
            sleep((int) (Math.random() * 20000));
        } catch (InterruptedException e) {
        }

        parking.surt();
        System.out.println("Matrícula " + getName() + " surt del pàrquing");
    }
}

class Parking {

    private int places;

    public Parking(int places) {
        if (places < 0) {
            places = 0;
        }

        this.places = places;
    }

    public synchronized void entra() { // cotxe entra al pàrquing
        try {
            System.out.println("Esperant, pàrquing ple...");
            wait();
        } catch (InterruptedException e) {
        }
        places--;
    }

    public synchronized void surt() { // el coche deixa el pàrquing
        System.out.println("Plaça alliberada.");
        places++;
        notify(); // Notifiquem a la resta de fils
    }
}