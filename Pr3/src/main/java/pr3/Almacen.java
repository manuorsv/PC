/*
 Manuel Ortega Salvador
 */
package pr3;

/**
 *
 * @author Usuario
 */
public class Almacen {
    private volatile int producto;
    
    public Almacen()
    {
        producto = -1;
    }
    
    public void almacenar(int p){
        producto = p;
        System.out.println("Se ha introducido el producto " + producto + '.');
        
    }
    
    public void extraer() {
        System.out.println("Se ha extraido el producto " + producto + '.');
        producto = -1;
    }

}