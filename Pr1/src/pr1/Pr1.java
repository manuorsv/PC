/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1;
import java.util.Scanner;

/**
 *
 * @author usuario_local
 */
public class Pr1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Primer apartado
        int N = 5;
        Pr1A pr1a = new Pr1A(N);
        pr1a.run();
        
        //Segundo apartado
        N = 1000;
        int M = 1000;
        Pr1B pr1b = new Pr1B(N, M);
        pr1b.run();
        
        //Tercer apartado
        Pr1C pr1c = new Pr1C();
        pr1c.run();
        
    }
    
}
