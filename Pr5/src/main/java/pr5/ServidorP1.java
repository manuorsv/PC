//Manuel Ortega Salvador

package pr5;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ServidorP1 {
    
    public static void main(String[] args) {  
        int port = Integer.parseInt("555");
        try(ServerSocket serverSocket = new ServerSocket(555)){
            System.out.println("El servidor se ha iniciado.");
            while(true) {
                System.out.println("Esperando conexión de cliente...");
                Socket socket = serverSocket.accept(); 
                System.out.println("Conexión establecida.");
                (new FileSender(socket)).start();                
            }  
        } catch (IOException ex) {
                Logger.getLogger(ServidorP1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
