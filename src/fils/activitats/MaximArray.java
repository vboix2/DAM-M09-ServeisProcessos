
package fils.activitats;

import java.util.Random;

public class MaximArray {

    public static void main(String[] args) {
        try {
            // Longitud de l'array
            int X = 1000;
            // Generem l'array de nombres aleatoris
            int[] nombres = new int[X];
            Random r = new Random();
            for (int i=0; i<X; i++){
                nombres[i] = r.nextInt(100*X);
            }
            // Nombre de fils
            int N=10;
            // Generem els fils
            FilMax[] fils = new FilMax[N];
            for (int i=0;i<N; i++){
                fils[i] = new FilMax(i*X/N, (i+1)*X/N-1, nombres);
                fils[i].start();
            }
            // Array amb els màxims de cada fil
            int[] maxims = new int[N];
            for (int i=0;i<N; i++){
                fils[i].join();
                maxims[i]=fils[i].getMaxim();
            }
            // Trobem el màxim dels màxims
            FilMax ultim = new FilMax(0,N-1, maxims);
            ultim.start();
            ultim.join();
            System.out.println(ultim.getMaxim());   

        } catch (InterruptedException ex) {
            
        }
        
    }
    
}

class FilMax extends Thread{
    private int maxim; // Màxim del fil
    private int inici;  // Posició inicial de la cerca
    private int fi;  // Posició final de la cerca
    private int[] nums;  // Array de valors
    
    public FilMax(int i, int f, int[] nums){
        this.inici=i;
        this.fi=f;
        this.nums = nums;
        this.maxim=nums[inici];
    }
    
    public int getMaxim(){
        return maxim;
    }    
    
    public void run(){
        // Cerquem el màxim entre inici i fi
        for (int x=inici+1; x<=fi; x++){
            if (nums[x]>maxim) maxim=nums[x];
        }
    }    
}
