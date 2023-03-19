//Manuel Ortega Salvador

package pr4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class AlmacenN extends Almacen{
    private volatile int productos[];
    private volatile int INI;
    private volatile int FIN;
    private volatile int MAX;
    private volatile int count;
    
    public AlmacenN(int N)
    {
        productos = new int[N];
        INI = 0;
        FIN = 0;
        MAX = N;
        count = 0;
    }
    
    public void almacenar(int p){
        productos[FIN] = p;
        System.out.println("Se ha introducido el producto " + productos[FIN] + '.');
        FIN = FIN+1;
    }
    
    public void extraer(int j) {  
        System.out.println("Se ha extraido el producto " + productos[j] + '.');       
    }
    
}

