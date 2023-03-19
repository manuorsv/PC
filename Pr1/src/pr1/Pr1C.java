/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pr1;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author usuario_local
 */
public class Pr1C {
    
    Random rand = new Random();
    
    public void run() {
        int N=3;
        int a;
        int[][] mt1 = new int[N][N];
        int[][] mt2 = new int[N][N];    
        for(int i=0; i<N; ++i){
            for(int j=0; j<N; ++j){
                a = rand.nextInt(2);
                mt1[i][j] = a;
            }
        }
        for(int i=0; i<N; ++i){
            for(int j=0; j<N; ++j){
                a = rand.nextInt(2);
                mt2[i][j] = a;
            }
        }
        
        int[][] sol = new int[N][N];
        for(int i=0; i<N; ++i){
            for(int j=0; j<N; ++j){
                sol[i][j] = 0;
            }
        }
        MiThread3 th[] = new MiThread3[N];
        for(int i=0; i<N; ++i) {
            th[i] = new MiThread3(i,mt1,mt2,sol);
        }
        for(int i=0; i<N; ++i) {
            th[i].start();
        }
        for(int i=0; i<N; ++i) {
            try {
                th[i].join();
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        for(int i=0; i<N; ++i){
            for(int j=0; j<N; ++j){
                System.out.print(sol[i][j]);
                 System.out.print(' ');
            }
            System.out.print('\n');
        }
     
    }
}
