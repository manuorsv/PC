//Manuel Ortega Salvador

package pr4;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ThreadProdMB1 extends Thread{
    private volatile int id;
    private volatile MultiBufferB1 buffer;
    private volatile int n_bloque;
    
   public ThreadProdMB1(int id, MultiBufferB1 buffer, int n) {
        this.id = id;
        this.buffer = buffer;
        n_bloque = n;
    }
    
    public void run() {
        buffer.almacenar(id, n_bloque); 
    }
}
