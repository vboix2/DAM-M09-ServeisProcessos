
package fils;

public class ProductorConsumidor_Pila {

    public static void main(String[] args) {
        
        Pila pila = new Pila(6);

        Productor_pila p = new Productor_pila(pila);
        Consumidor_pila c = new Consumidor_pila(pila);
        
        Thread productor = new Thread(p);
        Thread consumidor = new Thread(c);

        productor.start();
        consumidor.start();
    }

}

class Pila {
    private int mida;
    private volatile int pila[];
    private volatile int segNum;
    
    public Pila(int mida){
        this.mida = mida;
        this.segNum = 0;
        this.pila = new int[mida];
    }

    public synchronized int afagar() {

        while (segNum==0) 
        {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Problema bloquejant el fil");
            }
        }

        segNum--;
        notify();
        return (pila[segNum]);

    }

    public synchronized void deixar(int num) {

        while (segNum==mida)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Problema bloquejant el fil");
            }
        }

        pila[segNum] = num;
        segNum++;
        notify();
    }
}

class Productor_pila implements Runnable {

    private Pila pila;

    public Productor_pila(Pila p) {
        pila = p;
    }
       
    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {;
            }
            int numero = (int) (10 * Math.random());
            pila.deixar(numero);
            System.out.println("Produït el número " + numero);
        }
    }
}

class Consumidor_pila implements Runnable {

    private Pila pila;

    public Consumidor_pila(Pila p) {
        pila = p;
    }
    
    @Override
    public void run() {
        int num;

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep((int) (Math.random() * 2000));
            } catch (InterruptedException e) {
                System.out.println("Problema adormint el fil");
            }
            num = pila.afagar();
            System.out.println("Número agafat: " + num);
        }
    }
}