
package fils.activitats;

public class Examen2019 {

    public static void main(String[] args) {
        CompteCorrent cc = new CompteCorrent(500);
        Targeta t1 = new Targeta(cc, "A");
        Targeta t2 = new Targeta(cc, "B");
        Targeta t3 = new Targeta(cc, "C");
    }    
}

class CompteCorrent {
    private volatile double saldo;
    private volatile boolean disponible;
    
    public CompteCorrent(double saldoInicial){
        saldo = saldoInicial;
        disponible = true;
    }
    
    public double getSaldo(){
        return saldo;
    }
    
    public synchronized void retirar(double diners){
        try {
            while(!disponible){
                wait();
            }
        } catch (Exception ex){
            System.out.println("Problema bloquejant el compte");
        }
        disponible = false;
        if (diners<=saldo){
            saldo -= diners;
            System.out.print("L'usuari " + Thread.currentThread().getName());
            System.out.printf(" ha retirat %.2f €", diners);
            System.out.printf("\tSaldo: %.2f€\n",saldo);
        } else {
            System.out.println("Quantitat no disponible");
        }
        disponible = true;
        notify();       
    }
}

class Targeta extends Thread{
    
    private CompteCorrent cc;
    
    public Targeta(CompteCorrent cc, String nom){
        this.cc = cc;
        setName(nom);
        start();
    }
    
    @Override
    public void run(){
        while (cc.getSaldo()>5){
            espera();
            double diners = Math.random() * 50;
            cc.retirar(diners);
        }
        
    }
    public void espera(){
        try {
            sleep((int) (Math.random() * 3000));
        } catch (InterruptedException e) {
            System.out.println("Problema bloquejant el fil");
        }
    }
}