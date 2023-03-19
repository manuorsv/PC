//Manuel Ortega Salvador

package pr4;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ThreadProd extends Thread{
    private int id;
    private volatile AlmacenN almacen;
    private volatile MonitorAlmacen monitor; 
    
   public ThreadProd(int id, AlmacenN almacen, MonitorAlmacen monitor) {
        this.id = id;
        this.almacen = almacen;
        this.monitor = monitor;
    }
    
    public void run() {
        monitor.request_produce();
        almacen.almacenar(id); 
        monitor.release_produce();
    }
}
