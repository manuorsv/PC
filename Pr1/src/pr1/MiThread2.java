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
public class MiThread2 extends Thread{
    int type; // 0 incrementa 1 decrementa
    int num_op;
    varModificable var;
    
    MiThread2(int type, int n, varModificable var) {
        this.type = type;
        this.num_op = n;
        this.var = var;
    }
    
    public void run() {
        if (this.type == 0) {
            for(int i=0; i<num_op; ++i) {
                var.incrementar();
            }
        }
        else if (this.type == 1) {
            for(int i=0; i<num_op; ++i) {
                var.decrementar();
            }
        }
            
        
    }
}
