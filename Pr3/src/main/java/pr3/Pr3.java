/*
 * Manuel Ortega Salvador
 */
package pr3;

/**
 *
 * @author Usuario
 */
public class Pr3 {
     public static void main(String[] args) {
        /*Pr3A pr3a = new Pr3A(10000);
        pr3a.run();*/
        
        /*Pr3B pr3b = new Pr3B(10000, 10000);
        pr3b.run();
        */
        
        //Por orden, num consumidores, productores y tamaño alamcen
        Pr3C pr3c = new Pr3C(500, 500, 50);
        pr3c.run();
    }
}
