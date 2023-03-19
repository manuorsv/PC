//Manuel Ortega Salvador

package pr5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ProcesoEmisor extends Thread{
    private volatile int port;
    private volatile String fich;
    
    public ProcesoEmisor(int port, String fich) {
        this.port = port;
        this.fich = fich;
    }
    
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(port)){            
            Socket socket = serverSocket.accept(); 
            ObjectOutputStream outputS = new ObjectOutputStream(socket.getOutputStream());
            String file_path = "src/main/java/pr5/Ficheros/" + fich + ".txt";
            String contentsFile = readFile(file_path);
            outputS.writeObject(contentsFile);           
        } catch (IOException ex) {
                Logger.getLogger(ServidorP1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String readFile(String file_name) {
        FileReader fr = null;
        String contentsFile = "";
        try {
            fr = new FileReader(file_name);
            BufferedReader bf = new BufferedReader(fr);
            String line;
            while((line = bf.readLine()) != null) {        
                contentsFile += line+'\n';
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProcesoEmisor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProcesoEmisor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(ProcesoEmisor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return contentsFile;
    }
    
}
