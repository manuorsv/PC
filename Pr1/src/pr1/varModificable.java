/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1;

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
