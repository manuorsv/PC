//Manuel Ortega Salvador

package pr5;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author Usuario
 */
public class Mensaje_conexion extends Mensaje implements Serializable{

    private volatile String user_id;
    
    public Mensaje_conexion(String user_id) {
        this.user_id = user_id;
    }
    
    @Override
    public TipoMensaje getTipo() {
        return TipoMensaje.MENSAJE_CONEXION;
    }
    
    public String getUserId() {
        return user_id;
    }
    
}
