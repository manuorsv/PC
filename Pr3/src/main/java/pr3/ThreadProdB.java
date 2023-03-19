/*
 * Manuel Ortega Salvador
 */
package pr3;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ThreadProdB extends Thread{
    private volatile int id;
    private volatile Almacen almacen;
    private volatile Semaphore full;
    private volatile Semaphore empty;
    
   public ThreadProdB(int id, Almacen almacen, Semaphore full, Semaphore empty) {
        this.id = id;
        this.almacen = almacen;
        this.empty = empty;
        this.full = full;

    }
    
    public void run() {
        try {
            empty.acquire();
            almacen.almacenar(id); 
            full.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadProdB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
