//Manuel Ortega Salvador

package pr4;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Usuario
 */
public class Pr4A2 {
    private volatile int N, M;                           //M prod, N cons
    private volatile AlmacenN almacen;
    
    Pr4A2(int N, int M, int MAX) {
        this.N = N;
        this.M = M;
        almacen = new AlmacenN(MAX);
    }
    
    void run() {
        ThreadCons th_cons[] = new ThreadCons[N];
        ThreadProd th_prod[] = new ThreadProd[M];
        
        Lock lock = new ReentrantLock();
        Condition puedeCons = lock.newCondition();
        Condition puedeProd = lock.newCondition();
        Contador_consumidores cont_cons = new Contador_consumidores(0);
        Contador_productores cont_prod = new Contador_productores(0);
        MonitorAlmacen monitor = new MonitorAlmacen(cont_cons, cont_prod, lock, puedeCons, puedeProd);
        
        for(int i=0; i<N; ++i) {
            th_cons[i] = new ThreadCons(i, almacen, monitor);
        }
        for(int i=0; i<M; ++i) {
            th_prod[i] = new ThreadProd(i, almacen, monitor);
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
