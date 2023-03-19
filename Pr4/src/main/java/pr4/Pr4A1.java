//Manuel Ortega Salvador

package pr4;

/**
 *
 * @author Usuario
 */
public class Pr4A1 {
    private volatile int N;
    private volatile varModificable var = new varModificable(0);
    
    Pr4A1(int N) {
        this.N = N;
    }
    
    void run() {
        MiThreadIncDec th[] = new MiThreadIncDec[2*N];
        
        for(int i=0; i<N; ++i) {
            th[i] = new MiThreadIncDec(i, 0, 1, var);
        }
        for(int i=N; i<2*N; ++i) {
            th[i] = new MiThreadIncDec(i, 1, 1, var);
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
