//Manuel Ortega Salvador

package pr4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Almacen {
    private volatile int producto;
    
    public Almacen()
    {
        producto = -1;
    }
    
    synchronized void almacenar(int p) {
        try {
            while(producto != -1)
                wait();           
            producto = p;
            System.out.println("Se ha introducido el producto " + producto + '.');
            notifyAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    synchronized void extraer() {
        try {
            while(producto == -1) wait();
            System.out.println("Se ha extraido el producto " + producto + '.');
            producto = -1;
            notifyAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}