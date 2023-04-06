/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adit160323;
import javax.swing.JOptionPane;
/**
 *
 * @author LABP1KOMP
 */
public class Latihan62 {
    public static void main(String[] args) {
        // Meminta input dari user
        String input1 = JOptionPane.showInputDialog("Masukkan nilai ujian ke-1:");
        String input2 = JOptionPane.showInputDialog("Masukkan nilai ujian ke-2:");
        String input3 = JOptionPane.showInputDialog("Masukkan nilai ujian ke-3:");

        // Mengubah input menjadi tipe data numerik
        double nilai1 = Double.parseDouble(input1);
        double nilai2 = Double.parseDouble(input2);
        double nilai3 = Double.parseDouble(input3);

        // Menghitung rata-rata
        double rataRata = (nilai1 + nilai2 + nilai3) / 3.0;

        // Menampilkan hasil
        if (rataRata >= 60.0) {
            JOptionPane.showMessageDialog(null, "Rata-rata nilai adalah: " + rataRata + " :)");
        } else {
            JOptionPane.showMessageDialog(null, "Rata-rata nilai adalah: " + rataRata + " :(");
        }
    }
}
