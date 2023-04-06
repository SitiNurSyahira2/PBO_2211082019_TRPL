/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adit160323;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
/**
 *
 * @author LABP1KOMP
 */
public class Latihan61 {
     public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Masukkan nilai ujian ke-1: ");
            double nilai1 = Double.parseDouble(reader.readLine());

            System.out.print("Masukkan nilai ujian ke-2: ");
            double nilai2 = Double.parseDouble(reader.readLine());

            System.out.print("Masukkan nilai ujian ke-3: ");
            double nilai3 = Double.parseDouble(reader.readLine());

            double rataRata = (nilai1 + nilai2 + nilai3) / 3.0;

            System.out.println("Rata-rata nilai ujian adalah: " + rataRata);

            if (rataRata >= 60) {
                System.out.println(":)");
            } else {
                System.out.println(":(");
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing input: " + e.getMessage());
        }
    }
}
        
        
        
        
   
           
    
    

