/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1;

/**
 *
 * @author usuario_local
 */
public class MiThread extends Thread{
    int id, tmp_sleep;
    
    MiThread(int id, int tmp_sleep) {
        this.id = id;
        this.tmp_sleep = tmp_sleep;
    }
    
    public void run() {
        System.out.println("Mi id es " + id + ". Durmiendo...");
        try {
           sleep(tmp_sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Despierto. Mi id es " + id);
    }
}
