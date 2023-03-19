/*
 * Manuel Ortega Salvador
 */
package pr2;

/**
 *
 * @author Usuario
 */
public class LockRompeEmpate {
    volatile int N;
    volatile private int in[];
    volatile private int last[];
    
    LockRompeEmpate(int N) {
        this.N = N;
        in = new int[N];
        last = new int[N];
        for(int i=0; i<N; ++i) {
            in[i] = -1 ;
            in = in;
            last[i] = -1;
            last = last;
        }
    }
    
    public void lock(int i) {
        for(int j=0; j<N; ++j) {
            in[i] = j;
            in = in;
            last[j] = i;
            last = last;
            for(int k=0; k<N; ++k) {
                if(k!=i) {
                    while(in[k]>=in[i] && last[j]==i);
                }
            }
        }
    }
    
    public void unlock(int i) {
        in[i] = -1;
        in = in;
    }
}
