
package fils;

public class ProductorConsumidor_Cua {

    public static void main(String[] args) {
        
        Cua cua = new Cua(6);

        Productor_cua p = new Productor_cua(cua);
        Consumidor_cua c = new Consumidor_cua(cua);
        
        Thread productor = new Thread(p);
        Thread consumidor = new Thread(c);

        productor.start();
        consumidor.start();
    }

}

class Cua {
    private int mida;
    private volatile int pila[];
    private volatile int primer;
    private volatile int ultim;
    
    public Cua(int mida){
        this.mida = mida;
        this.primer = 0;
        this.ultim = 0;
        this.pila = new int[mida];
    }

    public synchronized int afagar() {

        while (primer==ultim) 
        {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Problema bloquejant el fil");
            }
        }

        primer++;
        if (primer==mida){
            primer = 0;
        }
        notify();
        return (pila[primer-1]);

    }

    public synchronized void deixar(int num) {

        while (primer==ultim+1)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Problema bloquejant el fil");
            }
        }

        pila[ultim] = num;
        ultim++;
        if (ultim==mida){
            ultim = 0;
        }
        notify();
    }
}

class Productor_cua implements Runnable {

    private Cua cua;

    public Productor_cua(Cua c) {
        cua = c;
    }

    public void run() {

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {;
            }
            
            int numero = (int) (10 * Math.random());
            cua.deixar(numero);
            System.out.println("Produït el número " + numero);
        }
    }
}

class Consumidor_cua implements Runnable {

    private Cua cua;

    public Consumidor_cua(Cua c) {
        cua = c;
    }

    public void run() {
        int num;

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep((int) (Math.random() * 2000));
            } catch (InterruptedException e) {
                System.out.println("Problema adormint el fil");
            }
            
            num = cua.afagar();
            System.out.println("Número agafat: " + num);
        }
    }
}