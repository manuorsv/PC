
/*
 * Manuel Ortega Salvador
 */
package pr3;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Usuario
 */
public class Pr3B {
    private volatile int N, M;                           //M prod, N cons
    private volatile Semaphore full;
    private volatile Semaphore empty;
    private volatile Almacen almacen;
    
    Pr3B(int N, int M) {
        this.N = N;
        this.M = M;
        full = new Semaphore(0);
        empty = new Semaphore(1);
        almacen = new Almacen();
    }
    
    void run() {
        ThreadConsB th_cons[] = new ThreadConsB[N];
        ThreadProdB th_prod[] = new ThreadProdB[M];
        
        for(int i=0; i<N; ++i) {
            th_cons[i] = new ThreadConsB(i, almacen, full, empty);
        }
        for(int i=0; i<M; ++i) {
            th_prod[i] = new ThreadProdB(i, almacen, full, empty);
        }
        
        for(int i=0; i<N; ++i) {
            th_cons[i].start();
        }
        
        for(int i=0; i<M; ++i) {
            th_prod[i].start();
        }
        
        for(int i=0; i<N; ++i) {
            try{
                th_cons[i].join();
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i=0; i<M; ++i) {
            try{
                th_prod[i].join();
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }   
}
