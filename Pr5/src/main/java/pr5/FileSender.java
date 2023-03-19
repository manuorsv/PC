//Manuel Ortega Salvador

package pr5;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
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
public class FileSender extends Thread{
    
    private Socket socket;
    
    public FileSender(Socket s) {
        this.socket = s;
    }
    
    public void run(){
            try {
                ObjectInputStream inputS = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outputS = new ObjectOutputStream(socket.getOutputStream());
                while(true) {
                    String fileName = (String )inputS.readObject();
                    FileReader fr = new FileReader(fileName);
                    BufferedReader bf = new BufferedReader(fr);
                    System.out.println("Leyendo contenido del fichero " + fileName + "...");
                    String line, contentsFile = "";;
                    
                    while((line = bf.readLine()) != null) {
                        contentsFile += line+'\n';
                    }        
                    outputS.writeObject(contentsFile);
                }
           
                } catch (EOFException ex) {
                    //
                }
                catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(FileSender.class.getName()).log(Level.SEVERE, null, ex);
                } 
               
        }
    
}
