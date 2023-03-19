/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr5;

import java.util.HashMap;

/**
 *
 * @author Usuario
 */
public class Monitor_tabla_info {
    
    private volatile HashMap<String, Usuario> tabla_info;
    
    public Monitor_tabla_info(HashMap<String, Usuario> tabla_info){
        this.tabla_info = tabla_info;   
    }
    
    public synchronized Usuario getUser(String user_id) {
        return tabla_info.get(user_id);
    }
    
    public synchronized String getUserwithFile(String user_id, String file_name) {
        String[] files = tabla_info.get(user_id).getFiles();
        for(String file : files) {
            if(file.equals(file_name)){
                return user_id;
            }
        }
        return null;                 
    }
    
    public synchronized String write_user_list(String user_id){
        String lista_usuarios = "";
        if(tabla_info.containsKey(user_id)) {
            lista_usuarios += "------------------------------\n";
            lista_usuarios += "Usuario: " + user_id + '\n';
            lista_usuarios += "El usuario posee los siguientes ficheros:\n";
            int tam_lista = tabla_info.get(user_id).getFiles().length;
            for(int i=0; i<tam_lista; ++i){
                lista_usuarios += tabla_info.get(user_id).getFiles()[i] + '\n';
            }                                                            
        }
        return lista_usuarios;
    }
    
    public synchronized void addUser(String user_id) {
        if(!tabla_info.containsKey(user_id)){
            Usuario user = new Usuario(user_id, new String[0]);
            tabla_info.put(user_id, user);
        }
    }
    
    
}
