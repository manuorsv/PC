/*
 * Manuel Ortega Salvador
 */
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
public class MonitorAlmacen {
    
    final Lock lock;
    final Condition puedeCons;
    final Condition puedeProd;
    private Contador_consumidores cont_cons;
    private Contador_productores cont_prod;
    private AlmacenN almacen;
    
    public MonitorAlmacen(Contador_consumidores cont_cons, Contador_productores cont_prod, Lock lock, Condition puedeCons, Condition puedeProd) {
        this.cont_cons = cont_cons;
        this.cont_prod = cont_prod;
        this.lock = lock;
        this.puedeCons = puedeCons;    
        this.puedeProd = puedeProd;
    }
    
    public void request_produce() {
        lock.lock();
        while(cont_prod.get_num() > 0 || cont_cons.get_num()>0) {
            try {
                puedeProd.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(MonitorAlmacen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cont_prod.increment();
    }  
    
    public void release_produce() {
        cont_prod.decrement();
        puedeProd.signalAll();
        puedeCons.signalAll();
        lock.unlock();
    }
    
    public void request_consume() {
        lock.lock();
        while(cont_prod.get_num() > 0) {
            try {
            puedeCons.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(MonitorAlmacen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cont_cons.increment();       
    }
    
    public void release_consume() {
        cont_cons.decrement();
        puedeProd.signalAll();
        lock.unlock();
    }
    
    
    
}
