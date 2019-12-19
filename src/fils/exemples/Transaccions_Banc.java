package fils.exemples;


public class Transaccions_Banc {

    public static void main(String[] args) {
        int comptes = 10;
        int euros = 100;
        int clients = 8;
        
        // Creem un banc.
        Banc banc = new Banc(comptes, euros);

        // Iniciem els clients
        Thread[] fils = new Thread[clients];
        for (int i = 0; i < clients; i++) {
            fils[i] = new Thread(new Client(banc));
            fils[i].setName("Client " + i);
            fils[i].start();
        }
        
        for (int i = 0; i < clients; i++) {
            try {
                fils[i].join();
            } catch (InterruptedException ex) {
                System.out.println("Problema " + ex);
            }
        }
        // Mostrem els resultats de tots els comptes
        banc.informe();
    }
}

class Banc {

    private int[] comptes;
    public boolean disponible;
    private int n_comptes;

    public Banc(int n, int inicial) {
        n_comptes = n;
        comptes = new int[n];
        for (int i=0; i<n_comptes;i++){
            comptes[i]=inicial;
        }
        disponible = true;
        
    }
    
    public synchronized void transaccio (int origen, int dest, int quantitat){
        // Esperem que el banc estigui disponible
        while (!disponible) {
            try {
                wait();
            } catch (Exception ex) {
                System.out.println("Problema bloquejant el fil");
            }
        }
        // Marquem el banc com a no disponible
        disponible = false;
        // Fem les transferències
        comptes[origen] -= quantitat;
        comptes[dest] += quantitat;
        System.out.println(Thread.currentThread().getName()
                + " mou " + quantitat + " euros del compte " + origen
                + " al compte " + dest + ".");
        // Deixem el banc disponible i ho notifiquem
        disponible = true;
        notify();
    }
    
    public int getValor(int num) {
        return comptes[num];
    }
    
    public int getNComptes(){
        return n_comptes;
    }
    
    public void informe() {
        int total = 0;
        System.out.println("----------------");
        for (int j = 0; j < n_comptes; j++) {
            int valor = comptes[j];
            System.out.println("Compte " + j + " té " + valor + " €");
            total = total + valor;
        }
        System.out.println("Total " + total + " €");
        System.out.println("----------------");
    }

}

class Client implements Runnable {

    private volatile Banc banc;

    public Client(Banc banc) {
        this.banc = banc;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            espera();
            // Seleccionem dos comptes a l'atzar
            int n = (int) (Math.random() * banc.getNComptes());
            int m;
            do {
                m = (int) (Math.random() * banc.getNComptes());
            } while (n==m);

            // Creem un valor a l'atzar per a la transacció
            int q = (int) (Math.random() * 30 + 1);

            // Realitzem la transacció si hi ha prou diners
            if (banc.getValor(n)>= q) {
                banc.transaccio(n, m, q);
            } else {
                System.out.println("No hi ha prou diners al compte");
            }
        }
    }

    public void espera() {
        // Espera un temps aleatori fins a 5 segons
        int t = (int) (Math.random() * 5);
        try {
            Thread.currentThread().sleep(t * 1000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

}

