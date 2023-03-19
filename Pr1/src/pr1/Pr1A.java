/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1;

import java.util.Scanner;

/**
 *
 * @author usuario_local
 */
public class Pr1A {
    int N;
    
    Pr1A(int N) {
        this.N = N;
    }
    
    public void run() {
        int T;
        Scanner in = new Scanner(System.in);
        
        MiThread threads[];
        threads = new MiThread[N];
        for(int i=0; i<N; ++i) {
           System.out.println("Introduce el tiempo de sleep para el thread: ");
           T = in.nextInt();
           threads[i] = new MiThread(i, T);
        }
        for(int i=0; i<N; ++i) {
            threads[i].start();
        }
        for(int i=0; i<N; ++i) {
            try {   
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
