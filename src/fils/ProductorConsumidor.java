package fils;

public class ProductorConsumidor {

    public static void main(String[] args) {
        Magatzem m = new Magatzem();

        Productor p = new Productor(m);
        Consumidor c = new Consumidor(m);
        Thread productor = new Thread(p);
        Thread consumidor = new Thread(c);

        productor.start();
        consumidor.start();
    }
}

class Magatzem {

    private volatile int numero;
    public volatile boolean buit;

    void Magatzem() {
        numero = 0;
        buit = true;
    }

    public synchronized void afagar() {
        // Mentre el magatzem estigui buit esperem
        while (buit) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Problema bloquejant el fil");
            }
        }
        // Quan s'omple el magatzem:
        // Agafem el número
        System.out.println("Agafat el número: " + numero);
        // El marquem com a buit
        buit = true;
    }

    public synchronized void deixar(int num) {
        while (!buit) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Problema bloquejant el fil");
            }
        }
        numero = num;
        System.out.println("Produït el número " + numero);
        buit = false;
        notify();

    }
}

class Productor implements Runnable {

    private Magatzem magatzem;

    public Productor(Magatzem m) {
        magatzem = m;
    }

    public void run() {

        for (int i = 0; i < 5; i++) {
            // Esperem un temps aleatori
            try {
                Thread.sleep((int) (Math.random() * 2000));
            } catch (InterruptedException e) {
                System.out.println("Problema adormint el fil");
            }
            // Generem un número i el deixem al magatzem
            int numero = (int) (10 * Math.random());
            magatzem.deixar(numero);
        }
    }
}

class Consumidor implements Runnable {

    private Magatzem magatzem;

    public Consumidor(Magatzem m) {
        magatzem = m;
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            // Esperem un temps aleatori
            try {
                Thread.sleep((int) (Math.random() * 2000));
            } catch (InterruptedException e) {
                System.out.println("Problema adormint el fil");
            }
            // Agafem un valor del magatzem
            magatzem.afagar();
        }
    }
}
