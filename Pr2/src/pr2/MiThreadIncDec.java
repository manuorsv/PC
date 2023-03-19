/*
 * Manuel Ortega Salvador
 */
package pr2;
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
    //vvvvv Cambiar por el Lock que se quiera usar vvvvv
    private volatile LockTicketMax turno;
    
    MiThreadIncDec(int id, int type, int n, varModificable var, LockTicketMax turno) {
        this.id = id;
        this.type = type;
        this.num_op = n;
        this.var = var;
        this.turno = turno;
    }
    
    public void run() {
        if (this.type == 0) {
                turno.takeLock(id);
                var.incrementar();
                turno.releaseLock(id);
            }
        else if (this.type == 1) {
                turno.takeLock(id);
                var.decrementar();
                turno.releaseLock(id);
        }    
        
    }
    
   
}
