//Manuel Ortega Salvador

package pr5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class OyenteServidor extends Thread{
    
    ObjectInputStream inputC;
    ObjectOutputStream outputC;
    
    public OyenteServidor(ObjectInputStream inputC, ObjectOutputStream outputC) {
        this.inputC = inputC;
        this.outputC = outputC;
    }
    
    public void run() {
        boolean close = false;
        while(!close) {
            try {
                Mensaje m = (Mensaje) inputC.readObject();
                switch(m.getTipo()){
                    case MENSAJE_CONFIRMACION_CONEXION:
                        System.out.println("Se ha establecido la conexión.");
                        break;
                        
                    case MENSAJE_CONFIRMACION_LISTA_USUARIOS:                    
                        System.out.println(((Mensaje_confirmacion_lista_usuarios) m).getListaUsuarios());
                        break;
                        
                    case MENSAJE_EMITIR_FICHERO:
                        Mensaje_emitir_fichero m_ef = (Mensaje_emitir_fichero) m;
                        outputC.writeObject(new Mensaje_preparado_clienteservidor(m_ef.getUserId1(), m_ef.getUserId2()));
                        (new ProcesoEmisor(m_ef.getPort(),m_ef.getFichName())).start();
                        break;
                        
                    case MENSAJE_PREPARADO_SERVIDORCLIENTE:
                        Mensaje_preparado_servidorcliente m_psc = (Mensaje_preparado_servidorcliente) m;
                        (new ProcesoReceptor(m_psc.getPort(), "192.168.56.1")).start();
                        break;
                        
                    case MENSAJE_CONFIRMACION_CERRAR_CONEXION:
                        System.out.println("Se cerrará la conexión. Adiós!");
                        close = true;
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(OyenteServidor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(OyenteServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }
    
}
