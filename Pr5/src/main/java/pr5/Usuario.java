//Manuel Ortega Salvador

package pr5;

/**
 *
 * @author Usuario
 */
public class Usuario {
    
    private String user_id;
    private String user_ip;
    private String[] ficheros;
    private int port;
    
    public Usuario(String user_id, String[] ficheros) {
        this.user_id = user_id;
        this.user_ip = "192.168.56.1";
        this.ficheros = ficheros;
        this.port = 666;
    }
    
    public String getUserId(){
        return user_id;
    }
    
    public String[] getFiles() {
        return ficheros;
    }

    public int getPort() {
        return port;
    }  
    
    public String getIp() {
        return this.user_ip;
    }
    
    public void addFile(String file) {
        String[] new_files = new String[ficheros.length+1];
        for(int i=0; i<ficheros.length; ++i) {
            new_files[i] = ficheros[i];
        }
        new_files[ficheros.length] = file;
        ficheros = new_files;
    }
    
}
