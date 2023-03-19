//Manuel Ortega Salvador

package pr4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class MultiBufferB2 {
    private volatile int productos[];
    private volatile int INI;
    private volatile int FIN;
    private volatile int MAX;
    private volatile int count;
    final Lock lock = new ReentrantLock();
    final Condition full = lock.newCondition();
    final Condition empty = lock.newCondition();
    
    public MultiBufferB2(int N) {
        productos = new int[N];
        INI = 0;
        FIN = 0;
        MAX = N;
        count = 0;
    }
    
    public void almacenar(int p, int n){
        lock.lock();
        try {
            while(count + n > MAX) empty.await();
            System.out.println("Se almacenaran " + n + " unidades del elemento " + p + ".");
            for(int i=0; i<n; ++i) {                
                productos[FIN] = p;
                productos = productos;
                System.out.println("Se ha introducido el producto " + productos[FIN] + '.');
                FIN = (FIN+1)%MAX;
                count++;
            }
            full.signalAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(AlmacenN.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            lock.unlock();
        }
    }
    
    public void extraer(int n) {  
        lock.lock();
        try {
            while(count < n) full.await();
            System.out.println("Se extraeran " + n + " unidades.");
            for(int i=0; i<n; ++i) {
                System.out.println("Se ha extraido el producto " + productos[INI] + '.');
                INI = (INI+1)%MAX;
                count--;
            }          
            empty.signalAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(AlmacenN.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            lock.unlock();
        }
    }
    
}
