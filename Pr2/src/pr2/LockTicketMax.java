/*
 Manuel Ortega Salvador
 */
package pr2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Usuario
 */
public class LockTicketMax {
    volatile private int N;
    volatile private int next;
    volatile private AtomicInteger number;
    volatile private int[] turno;
    volatile int MAX;
    
    LockTicketMax(int N, int MAX) {
        next = 0;
        number = new AtomicInteger(0);
        this.N = N;
        turno = new int[N];
        for(int i=0; i<N; ++i) {
            turno[i] = -1;
            turno = turno;
        }
        this.MAX = MAX;
    }
    
    public void takeLock(int i) {
        turno[i] = number.getAndIncrement();
        turno=turno;
        if(turno[i]==MAX) {
            number.getAndAdd(-MAX);
        }
        if(turno[i]>=MAX) {
            turno[i] -= MAX;
            turno=turno;
        }
        
        while(turno[i]!=next);    
    }
    
    public void releaseLock(int i) {
        next = (next + 1)%MAX;
    }
}
