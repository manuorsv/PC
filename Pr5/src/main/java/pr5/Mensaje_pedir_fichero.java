//Manuel Ortega Salvador

package pr5;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Mensaje_pedir_fichero extends Mensaje implements Serializable{
    
    private volatile String user_id;
    private volatile String fich_name;
    
    public Mensaje_pedir_fichero(String user_id, String fich_name) {
        this.user_id = user_id;
        this.fich_name = fich_name;
    }
    
    @Override
    public TipoMensaje getTipo() {
        return TipoMensaje.MENSAJE_PEDIR_FICHERO;
    }
    
    public String getUserId() {
        return user_id;
    }
    
    public String getFileName() {
        return fich_name;
    }
}
