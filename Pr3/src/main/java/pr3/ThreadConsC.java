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
public class ThreadConsC extends Thread{
    private int id;
    private volatile AlmacenN almacen;
    private volatile Semaphore s;
    private volatile Semaphore cons;
    private volatile Semaphore prod;
    private volatile Contador_productores num_prod;
     private volatile Contador_consumidores num_cons;
    volatile int pos;
    
   public ThreadConsC(int id, AlmacenN almacen, Semaphore s, Semaphore cons, Semaphore prod, Contador_productores num_prod, Contador_consumidores num_cons, int pos) {
       this.id = id;
        this.almacen = almacen;
        this.s = s;
        this.cons = cons;
        this.prod = prod;
        this.num_prod = num_prod;
        this.num_cons = num_cons;
        this.pos = pos;
    }
    
    public void run() {
        try {
            s.acquire();
            if(num_prod.get_num()>0) {
                s.release();
                cons.acquire();
            }
            num_cons.increment();
            almacen.extraer(pos);
            s.acquire();
            num_cons.decrement();
            if(num_cons.get_num()==0 && num_prod.get_num() > 0) {
                prod.release();
            }
            else s.release();
                    
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadConsC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
