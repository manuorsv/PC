//Manuel Ortega Salvador

package pr5;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Mensaje_confirmacion_conexion extends Mensaje implements Serializable{

    @Override
    public TipoMensaje getTipo() {
       return TipoMensaje.MENSAJE_CONFIRMACION_CONEXION;
    }
    
}
