//Manuel Ortega Salvador

package pr5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ServidorP2 {
    
    private volatile static HashMap<String, Usuario> tabla_info = new HashMap<String, Usuario>();
    private volatile static HashMap<String, ObjectOutputStream> tabla_users = new HashMap<String, ObjectOutputStream>();
    private volatile static int port = 555;
    private volatile static Semaphore sem = new Semaphore(1);
    
    public static void main(String[] args) {
        
        try(ServerSocket serverSocket = new ServerSocket(port)){
            
            readFile(tabla_info);
            
            System.out.println("El servidor se ha iniciado.");
            while(true) {
                System.out.println("Esperando conexión de cliente...");
                Socket socket = serverSocket.accept(); 
                System.out.println("Conexión establecida.");
                (new OyenteCliente(socket,tabla_users, tabla_info, sem)).start();                
            }  
        } catch (IOException ex) {
                Logger.getLogger(ServidorP1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private static void readFile(HashMap<String, Usuario> tabla_info) {
         FileReader fr = null;
        try {
            File file = new File("src/main/java/pr5/users.txt");
            fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr);
            System.out.println("Leyendo contenido del fichero users.txt...");
            String line;
            String user_id, file_name;
            String[] files;
            Usuario user;                  
            while((line = bf.readLine()) != null) {
                user_id = bf.readLine();
                String num = bf.readLine();
                int num_of_files = Integer.parseInt(num);
                int i=0;
                files = new String[num_of_files];
                while(i<num_of_files) {
                    file_name = bf.readLine();
                    files[i] = file_name;  
                    ++i;
                }
                user = new Usuario(user_id, files);
                tabla_info.put(user_id, user);
            }
                     
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServidorP2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServidorP2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(ServidorP2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
