//Manuel Ortega Salvador

package pr4;

/**
 *
 * @author usuario_local
 */
public class varModificable {
    private volatile int var;
    
    varModificable(int var) {
        this.var = var;
    }
    
    synchronized void incrementar() {
        ++var;
    }
    
    synchronized void decrementar() {
        --var;
    }
    
    public int getVar() {
        return var;
    }
}
