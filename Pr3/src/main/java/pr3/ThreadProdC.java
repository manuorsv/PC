/*
 * Manuel Ortega Salvador
 */
package pr3;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import pr3.AlmacenN;

/**
 *
 * @author Usuario
 */
public class ThreadProdC extends Thread{
    private int id;
    private volatile AlmacenN almacen;
    private volatile Semaphore s;
    private volatile Semaphore empty;
    private volatile Semaphore cons;
    private volatile Semaphore prod;
    private volatile Contador_productores num_prod;
    private volatile Contador_consumidores num_cons;
    
   public ThreadProdC(int id, AlmacenN almacen, Semaphore s, Semaphore cons, Semaphore prod, Contador_productores num_prod, Contador_consumidores num_cons) {
        this.id = id;
        this.almacen = almacen;
        this.s = s;
        this.cons = cons;
        this.prod = prod;
        this.num_prod = num_prod;
        this.num_cons = num_cons;
    }
    
    public void run() {
        try {
            s.acquire();
            if(num_prod.get_num()>0 || num_cons.get_num()>0) {
                s.release();
                prod.acquire();
            }
            num_prod.increment();
            s.release();
            almacen.almacenar(id);
            s.acquire();
            num_prod.decrement();
            
            if(num_prod.get_num() > 0) {
                num_prod.decrement();
                prod.release();
            }
            else if (num_cons.get_num()>0){
                cons.release();
            }
            else s.release();
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadProdC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
