//Manuel Ortega Salvador

package pr4;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ThreadProdMB2 extends Thread{
    private volatile int id;
    private volatile MultiBufferB2 buffer;
    private volatile int n_bloque;
    
   public ThreadProdMB2(int id, MultiBufferB2 buffer, int n) {
        this.id = id;
        this.buffer = buffer;
        n_bloque = n;
    }
    
    public void run() {
        buffer.almacenar(id, n_bloque); 
    }
}
