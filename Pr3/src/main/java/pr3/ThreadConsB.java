/*
 * Manuel Ortega Salvador
 */
package pr3;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import pr3.AlmacenN;
import pr3.AlmacenN;

/**
 *
 * @author Usuario
 */

//Comentando las partes de mutexC y mutexP funciona (permite almacenar varios items de seguido)
public class ThreadConsB extends Thread{
    private volatile int id;
    private volatile Almacen almacen;
    private volatile Semaphore full;
    private volatile Semaphore empty;
    
   public ThreadConsB(int id, Almacen almacen, Semaphore full, Semaphore empty) {
        this.id = id;
        this.almacen = almacen;
        this.empty = empty;
        this.full = full;
    }
    
    public void run() {
        try {
            full.acquire();
            almacen.extraer(); 
            empty.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadConsB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
