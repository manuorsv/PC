//Manuel Ortega Salvador

package pr5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class OyenteCliente extends Thread {
    
    private volatile Socket socketS;
    private volatile HashMap<String, ObjectOutputStream> tabla_users;
    private volatile HashMap<String, Usuario> tabla_info;
    private volatile Semaphore sem;
    private volatile Monitor_tabla_info monitor_info;
    
    
    public OyenteCliente(Socket s, HashMap<String, ObjectOutputStream> tabla_users, HashMap<String, Usuario> tabla_info, Semaphore sem) {
        this.socketS = s;
        this.tabla_users = tabla_users;
        this.tabla_info = tabla_info;
        this.sem = sem;
        monitor_info = new Monitor_tabla_info(this.tabla_info);
    }
    
    public void run() {
        boolean close = false;
         ObjectOutputStream outputS = null;
        try {
            outputS = new ObjectOutputStream(socketS.getOutputStream());
            ObjectInputStream inputS = new ObjectInputStream(socketS.getInputStream());
           
            while(!close) {
                Mensaje m = (Mensaje) inputS.readObject();
                switch(m.getTipo()){
                    case MENSAJE_CONEXION:
                        Mensaje_conexion m_c = (Mensaje_conexion) m; 
                        
                        sem.acquire();
                        tabla_users.put(m_c.getUserId(), outputS); 
                        sem.release();
                        
                        monitor_info.addUser(m_c.getUserId());
                                                
                        outputS.writeObject(new Mensaje_confirmacion_conexion());
                        break;
                    case MENSAJE_LISTA_USUARIOS:
                         Mensaje_lista_usuarios m_lu = (Mensaje_lista_usuarios) m;
                        
                         sem.acquire();
                         String lista_usuarios = "==================================\n";
                         lista_usuarios += "Lista de usuarios conectados:\n\n";
                         for(String id : tabla_users.keySet()){
                             lista_usuarios += monitor_info.write_user_list(id);
                         }
                         lista_usuarios += "==================================\n";
                         sem.release();
                         
                         outputS.writeObject(new Mensaje_confirmacion_lista_usuarios(lista_usuarios));
                         break;
                        
                    case MENSAJE_CERRAR_CONEXION:
                        Mensaje_cerrar_conexion m_cc = (Mensaje_cerrar_conexion) m;
                        
                        sem.acquire();
                        tabla_users.remove(m_cc.getUserId());
                        sem.release();
                        
                        outputS.writeObject(new Mensaje_confirmacion_cerrar_conexion());
                        
                        close = true;
                        break;
                        
                    case MENSAJE_PEDIR_FICHERO:
                        Mensaje_pedir_fichero m_pf = (Mensaje_pedir_fichero) m;
                        sem.acquire();
                        String user_has_file = null;
                        for(String user_id : tabla_users.keySet()) {
                            user_has_file = monitor_info.getUserwithFile(user_id, m_pf.getFileName());
                            if(user_has_file != null)
                                break;
                        }
                        if(user_has_file != null) {
                            monitor_info.getUser(m_pf.getUserId()).addFile(m_pf.getFileName());
                            tabla_users.get(user_has_file).writeObject(new Mensaje_emitir_fichero(m_pf.getUserId(), user_has_file, m_pf.getFileName(), monitor_info.getUser(user_has_file).getPort()));                  
                        }
                        else {
                            System.out.println("Ningún usuario posee el archivo especificado.");
                        }
                        sem.release();
                        break;
                        
                    case MENSAJE_PREPARADO_CLIENTESERVIDOR:
                        Mensaje_preparado_clienteservidor m_pcs = (Mensaje_preparado_clienteservidor) m;
                        sem.acquire();
                        tabla_users.get(m_pcs.getUserId1()).writeObject(new Mensaje_preparado_servidorcliente(monitor_info.getUser(m_pcs.getUserId2()).getPort(), monitor_info.getUser(m_pcs.getUserId1()).getIp()));
                        sem.release();
                        break;
                }
                
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(OyenteCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OyenteCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(OyenteCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                outputS.close();
            } catch (IOException ex) {
                Logger.getLogger(OyenteCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
               
    }
    
}
