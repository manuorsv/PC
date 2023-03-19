//Manuel Ortega Salvador

package pr4;
import java.util.Arrays; 
import java.util.Collections; 
/**
 *
 * @author usuario_local
 */
public class MiThreadIncDec extends Thread{
    int id;
    int type; // 0 incrementa 1 decrementa
    int num_op;
    private volatile varModificable var;
    
    MiThreadIncDec(int id, int type, int n, varModificable var) {
        this.id = id;
        this.type = type;
        this.num_op = n;
        this.var = var;
    }
    
    public void run() {
        if (this.type == 0) {
                var.incrementar();
            }
        else if (this.type == 1) {
                var.decrementar();
        }    
        
    }
    
   
}
