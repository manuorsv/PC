/*
 * Manuel Ortega Salvador
 */
package pr3;

/**
 *
 * @author Usuario
 */
public class AlmacenN {
    private volatile int productos[];
    //private volatile int INI;
    private volatile int FIN;
    private volatile int MAX;
    
    public AlmacenN(int N)
    {
        productos = new int[N];
        //INI = 0;
        FIN = 0;
        //MAX = N;
    }
    
    public void almacenar(int p){       
        productos[FIN] = p;
        System.out.println("Se ha introducido el producto " + productos[FIN] + '.');
        productos = productos;
        FIN = FIN+1;
    }
    
    public void extraer(int i) {
        System.out.println("Se han extraido unidades del producto " + productos[i] + '.');
        
        //Ya no nos hace falta
        //INI = (INI+1)%MAX;
    }
    
}

