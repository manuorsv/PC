//Manuel Ortega Salvador

package pr4;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */

//Comentando las partes de mutexC y mutexP funciona (permite almacenar varios items de seguido)
public class ThreadCons extends Thread{
    private int id;
    private volatile AlmacenN almacen;
    private volatile MonitorAlmacen monitor;
    
   public ThreadCons(int id, AlmacenN almacen, MonitorAlmacen monitor) {
       this.id = id;
       this.almacen = almacen;
       this.monitor = monitor;
    }
    
    public void run() {
        monitor.request_consume();
        almacen.extraer(); 
        monitor.release_consume();
    }
    
}
