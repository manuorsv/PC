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
public class MiThread3 extends Thread{
    int fila;
    int matriz1[][];
    int matriz2[][];
    int fila_sol[][];
    
    MiThread3(int fila, int matriz1[][], int matriz2[][], int sol[][]) {
        this.fila = fila;
        this.matriz1 = matriz1;
        this.matriz2 = matriz2;
        this.fila_sol = sol;
    }
    
    public void run() {
        for(int i=0; i<matriz1.length; ++i) {
            for(int j=0; j<matriz1.length; ++j) {
                fila_sol[fila][i] += matriz1[fila][j]*matriz2[j][i];
            }
        }
    }
}

