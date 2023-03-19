//Manuel Ortega Salvador

package pr5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ProcesoReceptor extends Thread{
    
    private volatile int port;
    private volatile String ip;
    
    public ProcesoReceptor(int port, String ip) {
        this.port = port;
        this.ip = ip;
    }
    
    public void run() {
        try(Socket socket = new Socket(ip, port)){
          
            ObjectInputStream inputC = new ObjectInputStream(socket.getInputStream());
            
            System.out.println("Los contenidos del fichero son:\n");
            
            String fichero_recibido = (String) inputC.readObject();
            System.out.println(fichero_recibido);

            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClienteP1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
