/*
 * Manuel Ortega Salvador
 */
package pr3;
import java.util.Arrays; 
import java.util.Collections; 
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author usuario_local
 */
public class MiThreadIncDec extends Thread{
    int id;
    int type; // 0 incrementa 1 decrementa
    int num_op;
    private volatile varModificable var;
    private volatile Semaphore turno;
    
    MiThreadIncDec(int id, int type, int n, varModificable var, Semaphore turno) {
        this.id = id;
        this.type = type;
        this.num_op = n;
        this.var = var;
        this.turno = turno;
    }
    
    public void run() {
        if (this.type == 0) {
            try {
                turno.acquire();
                var.incrementar();
                turno.release();
            } catch (InterruptedException ex) {
                Logger.getLogger(MiThreadIncDec.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        else if (this.type == 1) {
            try {
                turno.acquire();
                var.decrementar();
                turno.release();
            } catch (InterruptedException ex) {
                Logger.getLogger(MiThreadIncDec.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
        
    }
    
   
}
