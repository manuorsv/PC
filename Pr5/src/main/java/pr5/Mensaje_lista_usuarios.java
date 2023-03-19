//Manuel Ortega Salvador

package pr5;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Mensaje_lista_usuarios extends Mensaje implements Serializable{

    @Override
    public TipoMensaje getTipo() {
        return TipoMensaje.MENSAJE_LISTA_USUARIOS;
    }
    
}
