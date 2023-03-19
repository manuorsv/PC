/*
 * Manuel Ortega Salvador
 */
package pr3;

//Lleva un contador de los productores en cola para el paso de testigo
public class Contador_productores {
    volatile int num_prod;
    
    public Contador_productores(int num_prod) {
        this.num_prod = num_prod;
    }
    
    //Para incrementar o decrementar;
    public void increment() {
       ++num_prod;
    }
    
    public void decrement() {
        --num_prod;
    }
    
    public int get_num() {
        return this.num_prod;
    }
}
