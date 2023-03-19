//Manuel Ortega Salvador

package pr5;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Mensaje_confirmacion_lista_usuarios extends Mensaje implements Serializable{
    
    private volatile String lista_usuarios;
    
    public Mensaje_confirmacion_lista_usuarios(String lista) {
        this.lista_usuarios = lista;
    }
    
    @Override
    public TipoMensaje getTipo() {
       return TipoMensaje.MENSAJE_CONFIRMACION_LISTA_USUARIOS;
    }
    
    public String getListaUsuarios() {
        return lista_usuarios;
    }
    
}
