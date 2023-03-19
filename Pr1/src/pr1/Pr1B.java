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
public class Pr1B {
    int N, M;
    
   Pr1B(int N, int M) {
       this.N = N;
       this.M = M;
   }
    
    public void run() {
        varModificable var = new varModificable(0);
        MiThread2 threads[];
        threads = new MiThread2[2*M];
        
        //M threads incrementan
        for(int i=0; i<M; ++i) {
            threads[i] = new MiThread2(0, N, var);
        }
        //M threads decrementan
        for(int i=M; i<2*M; ++i) {
            threads[i] = new MiThread2(1, N, var);
        }
        for(int i=0; i<2*M; ++i) {
            threads[i].start();
        }
        for(int i=0; i<2*M; ++i) {
            try {   
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Valor de la variable despuúes de modificaciones: " + var.getVar());
    }
}
