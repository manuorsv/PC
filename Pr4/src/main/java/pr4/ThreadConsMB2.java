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
public class ThreadConsMB2 extends Thread{
    private int id;
    private volatile MultiBufferB2 buffer;
    private volatile int n_bloque;
    
   public ThreadConsMB2(int id, MultiBufferB2 buffer, int n) {
       this.id = id;
       this.buffer = buffer;
       n_bloque = n;
    }
    
    public void run() {
        buffer.extraer(n_bloque); 
    }
    
}
