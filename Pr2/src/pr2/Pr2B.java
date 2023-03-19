/*
 * Manuel Ortega Salvador
 */
package pr2;

/**
 *
 * @author Usuario
 */
public class Pr2B {
    int N;
    //vvvvv Cambiar por el Lock que se quiera usar vvvvv
    volatile LockTicketMax turno;
    volatile varModificable var = new varModificable(0);
    
    Pr2B(int N) {
        this.N = N;
        //El primer parámetro es el nº de procesos, el segundo el nº MAX de procesos
        turno = new LockTicketMax(2*N, 2*N);
    }
    
    void run() {
        MiThreadIncDec th[] = new MiThreadIncDec[2*N];
        
        for(int i=0; i<N; ++i) {
            th[i] = new MiThreadIncDec(i, 0, 1, var, turno);
        }
        for(int i=N; i<2*N; ++i) {
            th[i] = new MiThreadIncDec(i, 1, 1, var, turno);
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
