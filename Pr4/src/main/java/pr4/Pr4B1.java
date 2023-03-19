//Manuel Ortega Salvador

package pr4;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Usuario
 */
public class Pr4B1 {
    //M prod, N cons
    private volatile int N, M;                           
    private volatile MultiBufferB1 buffer;
    private volatile int MAX;
    //Longitud maxima de secuencia a introducir o extraer
    private volatile int max_length;                     
    
    Pr4B1(int N, int M, int MAX, int max_length) {
        this.N = N;
        this.M = M;
        this.MAX = MAX;
        buffer = new MultiBufferB1(MAX);
        this.max_length = max_length;
    }
    
    void run() {
        ThreadConsMB1 th_cons[] = new ThreadConsMB1[N];
        ThreadProdMB1 th_prod[] = new ThreadProdMB1[M];
        
        for(int i=0; i<N; ++i) {
            int n_bloque = new Random().nextInt(max_length)+1;
            th_cons[i] = new ThreadConsMB1(i, buffer, n_bloque);
        }
        for(int i=0; i<M; ++i) {
            int n_bloque = new Random().nextInt(max_length)+1;
            th_prod[i] = new ThreadProdMB1(i, buffer, n_bloque);
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
