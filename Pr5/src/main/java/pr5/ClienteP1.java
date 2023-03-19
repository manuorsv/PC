//Manuel Ortega Salvador

package pr5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ClienteP1 {
    
    public static void main(String[] args) {  
        String hostname = "192.168.56.1";
        int port = Integer.parseInt("555");
        
        try(Socket socket = new Socket(hostname, 555)){
            
            ObjectOutputStream outputC = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputC = new ObjectInputStream(socket.getInputStream());
            
            System.out.println("Por favor, introduce el fichero a consultar: ");
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String file = br.readLine();
            outputC.writeObject(file);
            System.out.println("Enviando petición al servidor...");
            
            String contentsFile = (String) inputC.readObject();
            System.out.println("Respuesta del servidor recibida.");
            System.out.println("Los contenidos del fichero son:");
            System.out.print('\n');   
            System.out.println(contentsFile);

            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClienteP1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}