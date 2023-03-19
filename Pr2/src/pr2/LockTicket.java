/*
 * Manuel Ortega Salvador
 */
package pr2;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Usuario
 */
public class LockTicket {
    volatile private int N;
    volatile private int next;
    volatile private AtomicInteger number;
    volatile private int[] turno;
    
    LockTicket(int N) {
        next = 0;
        number = new AtomicInteger(0);
        this.N = N;
        turno = new int[N];
        for(int i=0; i<N; ++i) {
            turno[i] = -1;
            turno = turno;
        }
    }
    
    public void lock(int i) {
        turno[i] = number.getAndIncrement();
        turno=turno;
        while(turno[i]!=next);    
    }
    
    public void unlock(int i) {
        next = next + 1;
    }
   
}
