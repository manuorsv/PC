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
public class ClienteP2 {
    private static String hostname = "192.168.56.1";
    private static int port = 555;
    
    public static void main(String[] args) {
        
        try {
            
            
            System.out.println("Bienvenido. Por favor, introduce tu nombre de usuario:");
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String user_id = br.readLine();
           
            Socket socketC = new Socket(hostname, port);
            ObjectInputStream inputC = new ObjectInputStream(socketC.getInputStream());
            ObjectOutputStream outputC = new ObjectOutputStream(socketC.getOutputStream());
            
            Mensaje m = new Mensaje_conexion(user_id);
            outputC.writeObject(m);
            
            OyenteServidor oyenteServidor = new OyenteServidor(inputC, outputC);
            oyenteServidor.start();          
            
            boolean salir = false;
            while(!salir) {
                System.out.println("¿Que operación quieres realizar?");
                System.out.println("--------------------------------");
                System.out.println("> 1.  Consultar la lista de usuarios.");
                System.out.println("> 2.  Solicitar un fichero.");
                System.out.println("> 3.  Salir de la aplicación.");
                System.out.println("--------------------------------");
                System.out.println("Por favor, introduce una de las opciones anteriores (1,2 o 3):");
                int op = Integer.parseInt(br.readLine());
                switch(op) {
                    case 1:
                        Mensaje m_lu = new Mensaje_lista_usuarios();
                        outputC.writeObject(m_lu);
                        break;
                    case 2:                       
                        System.out.println("Introduce el nombre del fichero que quieres pedir solicitar:");
                        String file_name = br.readLine();
                        Mensaje m_pf = new Mensaje_pedir_fichero(user_id,file_name);
                        outputC.writeObject(m_pf);
                        break;
                        
                    case 3:
                        Mensaje m_cc = new Mensaje_cerrar_conexion(user_id);
                        salir = true;
                        outputC.writeObject(m_cc);
                        break;
                        
                    default:
                        System.out.println("Operación no reconocida.");
                }
                
            }            
            
        } catch (IOException ex) {
            Logger.getLogger(ClienteP2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
