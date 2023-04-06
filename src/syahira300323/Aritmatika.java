/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adit300323;

/**
 *
 * @author LABP1KOMP
 */
public class Aritmatika {
    public int tambah(int a, int b){
        return a + b;
    }
    public boolean cekganjil(int a){
        return a%2!=0;
    }
    public int ganjil(int n){
        int i = 0;
        int j = 1;
        
        while (j <= n){
            if (!cekganjil(j)){
                System.out.print(j + " ");
            }
            j++;
        }
     System.out.println();
        return 0;
    }
}
