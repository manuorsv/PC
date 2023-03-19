//Manuel Ortega Salvador

package pr4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class MultiBufferB1 {
    private volatile int productos[];
    private volatile int INI;
    private volatile int FIN;
    private volatile int MAX;
    private volatile int count;
    
    public MultiBufferB1(int N) {
        productos = new int[N];
        INI = 0;
        FIN = 0;
        MAX = N;
        count = 0;
    }
    
    synchronized void almacenar(int p, int n){
        try {
            while(count + n > MAX) wait();
            System.out.println("Se almacenaran " + n + " unidades del elemento " + p + ".");
            for(int i=0; i<n; ++i) {
                productos[FIN] = p;
                productos=productos;
                System.out.println("Se ha introducido el producto " + productos[FIN] + '.');
                FIN = (FIN+1)%MAX;
                count++;
            }
            notifyAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(AlmacenN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    synchronized void extraer(int n) {  
        try {
            while(count < n) wait();
            System.out.println("Se extraeran " + n + " unidades.");
            for(int i=0; i<n; ++i) {
                System.out.println("Se ha extraido el producto " + productos[INI] + '.');
                INI = (INI+1)%MAX;
                count--;
            }
            notifyAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(AlmacenN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
