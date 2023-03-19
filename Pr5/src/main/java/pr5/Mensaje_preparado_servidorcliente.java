//Manuel Ortega Salvador

package pr5;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Mensaje_preparado_servidorcliente extends Mensaje implements Serializable{
    
    private volatile int port;
    private volatile String ip;
    
    public Mensaje_preparado_servidorcliente(int port, String ip) {
        this.port = port;
        this.ip = ip;
    }

    @Override
    public TipoMensaje getTipo() {
        return TipoMensaje.MENSAJE_PREPARADO_SERVIDORCLIENTE;
    }
    
    public int getPort() {
        return port;
    }
            
    public String getIp() {
        return ip;
    }
    
}
