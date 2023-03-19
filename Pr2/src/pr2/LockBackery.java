/*
 * Manuel Ortega Salvador
 */
package pr2;

/**
 *
 * @author usuario_local
 */
public class LockBackery {
    
    volatile int N;
    volatile int[] turno;
    
    LockBackery(int N) {
        this.N = N;
        turno = new int[N];
        for(int i=0; i<N; ++i){
            turno[i] = -1;
            turno = turno;
        }
    }
    
    public void lock(int i) {
        turno[i] = 0;
        turno[i] = getMax()+1;
        turno = turno;
        for(int j=0; j<turno.length; ++j) {
            while(turno[j] != -1 && (turno[i] > turno[j] || (turno[i] == turno[j] && i > j)));
        }
    }
    
    public void unlock(int i) {
        turno[i] = -1;
        turno = turno;
    }
    
    public int getMax() {
        int max = 0;
        for(int i=0; i<turno.length; ++i) {
            if(turno[i] > max) max = turno[i];
        }
        return max;
    }
    
}
