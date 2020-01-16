
package fils.activitats;

import java.util.Random;


public class SumaArray {

    public static void main(String[] args) {
        try {
            // Generem l'array de nombres aleatoris
            double[] nombres = new double[1000];
            Random r = new Random();
            for (int i=0; i<1000; i++){
                nombres[i] = r.nextDouble() * 20 - 10;
            }

            // Generem els fils
            FilSuma[] fils = new FilSuma[10];
            for (int i=0;i<10; i++){
                fils[i] = new FilSuma(i*100, (i+1)*100, nombres);
                fils[i].start();
            }
            // Array amb la suma de cada fil
            double[] maxims = new double[10];
            for (int i=0;i<10; i++){
                fils[i].join();
                maxims[i]=fils[i].getSuma();
            }
            // Calculem la suma de les sumes
            FilSuma ultim = new FilSuma(0,10, maxims);
            ultim.start();
            ultim.join();
            System.out.println(ultim.getSuma());   

        } catch (InterruptedException ex) {
            System.out.println("Excepció " + ex);
        }

    }

}

class FilSuma extends Thread{
    private double suma;
    private int inici;
    private int fi;
    private double[] nums;

    public FilSuma(int i, int f, double[] nums){
        this.inici=i;
        this.fi=f;
        this.nums = nums;
        this.suma=0;
    }

    public double getSuma(){
        return suma;
    }    

    public void run(){
        // Sumem els valors entre inici i fi
        for (int x=inici; x<fi; x++){
            suma +=nums[x];
        }
    }    
}