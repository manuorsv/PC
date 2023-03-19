//Manuel Ortega Salvador

package pr5;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Mensaje_emitir_fichero extends Mensaje implements Serializable{
    
    //Usuario que pide fichero: user_id1
    //Usuario que manda fichero: user_id2
    
    private volatile String user_id1;
    private volatile String user_id2;
    private volatile String fich_name;
    private volatile int port;
    
    public Mensaje_emitir_fichero(String user_id1, String user_id2, String fich_name, int port) {
        this.user_id1 = user_id1;
        this.user_id2 = user_id2;
        this.fich_name = fich_name;
        this.port = port;
    }
    
    @Override
    public TipoMensaje getTipo() {
        return TipoMensaje.MENSAJE_EMITIR_FICHERO;
    }
    
    public String getUserId1() {
        return user_id1;
    }
    
    public String getUserId2() {
        return user_id2;
    }
    
    public String getFichName() {
        return fich_name;
    }
    
    public int getPort() {
        return port;
    }
    
}
