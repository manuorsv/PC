//Manuel Ortega Salvador

package pr5;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Mensaje_preparado_clienteservidor extends Mensaje implements Serializable{
    
    private volatile String user_id1;
    private volatile String user_id2;
    
    //Usuario que pide fichero: user_id1
    //Usuario que manda fichero: user_id2
    public Mensaje_preparado_clienteservidor(String user_id1, String user_id2) {
        this.user_id1 = user_id1;
        this.user_id2 = user_id2;       
    }

    @Override
    public TipoMensaje getTipo() {
        return TipoMensaje.MENSAJE_PREPARADO_CLIENTESERVIDOR;
    }
    
    public String getUserId1() {
        return user_id1;
    }
    
    public String getUserId2() {
        return user_id2;
    }
    
}
