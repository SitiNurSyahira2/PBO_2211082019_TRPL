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
public class CastingExplicit {
    public static void main(String[] args){
        char valChar = 'a';
        int valInt = valChar;
        //casting explisit: keluaran 65
        
        double valDouble = 10.12;
         valInt = (int)valDouble; //men-convert valDouble ke tipe int
        double x = 10.2;
        int y = 2;
        int result = (int)(x/y); 
         System.out.println( valInt );
        System.out.println( result );//hasil typecast operasi ke int
    }
    
}
