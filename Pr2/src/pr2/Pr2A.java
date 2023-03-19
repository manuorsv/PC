/*
 * Manuel Ortega Salvador
 */
package pr2;

/**
 *
 * @author usuario_local
 */
public class Pr2A {
    volatile int N;
    volatile varModificable var = new varModificable(0);
    //vvvvv Cambiar por el Lock que se quiera usar vvvvv
    volatile LockTicketMax turn;
    
    Pr2A(int N) {
        this.N = N;
        turn = new LockTicketMax(2,2);
    }
    
    void run() {
        MiThreadIncDec th_inc = new MiThreadIncDec(0, 0, N, var, turn);
        MiThreadIncDec th_dec = new MiThreadIncDec(1, 1, N, var, turn);
        
        th_inc.start();
        th_dec.start();
        
        try {
            th_inc.join();
            th_dec.join();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Valor de la var: " + var.getVar());
         
    }
}
