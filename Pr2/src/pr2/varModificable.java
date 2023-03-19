/*
 * Manuel Ortega Salvador
 */
package pr2;

/**
 *
 * @author usuario_local
 */
public class varModificable {
    int var;
    
    varModificable(int var) {
        this.var = var;
    }
    
    public void incrementar() {
        ++var;
    }
    
    public void decrementar() {
        --var;
    }
    
    public int getVar() {
        return var;
    }
}
