/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package syahira.dao;


import java.util.List;
import syahira.model.Pengembalian;

/**
 *
 * @author LAB-MM
 */
public interface PengembalianDao {
    void insert (Pengembalian pengembalian) throws Exception;
    
    void update (Pengembalian pengembalian) throws Exception;
    
    void delete (Pengembalian pengembalian) throws Exception;
    
    Pengembalian getPengembalian(String kodeanggota, String kodebuku, String tglpinjam) throws Exception;
    
    List<Pengembalian> getAll() throws Exception;
    
    int selisihTgl(String tgl1, String tgl2) throws Exception;
    
    List<Pengembalian> cari(String kode, String cari) throws Exception;
}
    

