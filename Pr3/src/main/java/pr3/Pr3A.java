/*
 * Manuel Ortega Salvador
 */
package pr3;

import java.lang.Object;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Usuario
 */
public class Pr3A {
    private volatile int N;
    private volatile Semaphore turno;
    private volatile varModificable var = new varModificable(0);
    
    Pr3A(int N) {
        this.N = N;
        turno = new Semaphore(1);
    }
    
    void run() {
        MiThreadIncDec th[] = new MiThreadIncDec[2*N];
        
        for(int i=0; i<N; ++i) {
            th[i] = new MiThreadIncDec(i, 0, 1, var, turno);
        }
        for(int i=N; i<2*N; ++i) {
            th[i] = new MiThreadIncDec(i, 1, 1, var, turno);
        }
        
        for(int i=0; i<2*N; ++i) {
            th[i].start();
        }
        
        for(int i=0; i<2*N; ++i) {
            try{
                th[i].join();
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("Valor de la var: " + var.getVar());
    }   
    
}
