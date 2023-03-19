//Manuel Ortega Salvador

package pr5;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Mensaje_cerrar_conexion extends Mensaje implements Serializable{

    private volatile String user_id;
    
    public Mensaje_cerrar_conexion(String user_id) {
        this.user_id = user_id;
    }
    
    @Override
    public TipoMensaje getTipo() {
        return TipoMensaje.MENSAJE_CERRAR_CONEXION;
    }
    
    public String getUserId() {
        return user_id;
    }
    
}
