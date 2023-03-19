/*
 * Manuel Ortega Salvador
 */
package pr4;

//Lleva un contador de los productores en cola para el paso de testigo
public class Contador_consumidores {
    volatile int num_cons;
    
    public Contador_consumidores(int num_cons) {
        this.num_cons = num_cons;
    }
    
    //Para incrementar o decrementar;
    public void increment() {
       ++num_cons;
    }
    
    public void decrement() {
        --num_cons;
    }
    
    public int get_num() {
        return this.num_cons;
    }
}
