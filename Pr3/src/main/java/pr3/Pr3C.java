/*
 * Manuel Ortega Salvador
 */
package pr3;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Usuario
 */
public class Pr3C {
    private volatile int N, M;                           //M prod, N cons
    private volatile Semaphore full;
    private volatile Semaphore empty;
    private volatile AlmacenN almacen;
    private volatile Semaphore mutexP, mutexC;
    
    //MAX tamaño almacen
    Pr3C(int N, int M, int MAX) {
        this.N = N;
        this.M = M;
        full = new Semaphore(0);
        empty = new Semaphore(MAX);
        mutexC = new Semaphore(1);
        mutexP = new Semaphore(1);
        almacen = new AlmacenN(MAX);
    }
    
    void run() {
        ThreadConsC th_cons[] = new ThreadConsC[N];
        ThreadProdC th_prod[] = new ThreadProdC[M];
        
        for(int i=0; i<N; ++i) {
            th_cons[i] = new ThreadConsC(i, almacen, full, empty, mutexC);
        }
        for(int i=0; i<M; ++i) {
            th_prod[i] = new ThreadProdC(i, almacen, full, empty, mutexP);
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
