
package fils.activitats;

public class MitjanaFluxDades {


    public static void main(String[] args) {
        
        Pila pila = new Pila(20);

        Productor_pila p = new Productor_pila(pila);
        Consumidor_pila c = new Consumidor_pila(pila);
        
        Thread productor1 = new Thread(p);
        Thread productor2 = new Thread(p);
        Thread productor3 = new Thread(p);
        Thread consumidor = new Thread(c);

        productor1.start();
        productor2.start();
        productor3.start();
        consumidor.start();
    }

}

class Pila {
    private int mida;
    private volatile double pila[];
    private volatile int segNum;
    private int n;
    private double suma;
    
    public Pila(int mida){
        this.mida = mida;
        this.segNum = 0;
        this.pila = new double[mida];
        n = 0;
        suma = 0;
    }

    public synchronized void afagar() {

        while (segNum==0) 
        {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Problema bloquejant el fil");
            }
        }
        
        for(int i=0; i <segNum; i++){
            suma += pila[i];
            n++;
        }
        segNum = 0;
        notify();
    }

    public synchronized void deixar(double num) {

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

    public int getN() {
        return n;
    }

    public double getSuma() {
        return suma;
    }
}

class Productor_pila implements Runnable {

    private Pila pila;

    public Productor_pila(Pila p) {
        pila = p;
    }

    public void run() {

        while (true) {
            double numero = (10 * Math.random());
            pila.deixar(numero);
            try {
                Thread.sleep((int) (Math.random() * 2000));
            } catch (InterruptedException e) {;
            }
        }
    }
}

class Consumidor_pila implements Runnable {

    private Pila pila;
    private double suma;
    private int n;

    public Consumidor_pila(Pila p) {
        pila = p;
        suma = 0;
        n = 0;
    }

    public void run() {
        double num;

        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Problema adormint el fil");
            }
            pila.afagar();
            
            double mitjana = pila.getSuma()/pila.getN();
            System.out.println("Mitjana: " + Double.toString(mitjana));
            
        }
    }
}