/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package syahira.controller;


import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import syahira.db.DbHelper;
import syahira.model.*;
import syahira.dao.*;
import syahira.view.FormPeminjaman;


/**
 *
 * @author LAB-MM
 */
public class PeminjamanController {
    FormPeminjaman formPeminjaman;
    Peminjaman peminjaman;
    PeminjamanDao peminjamanDao;
    AnggotaDao anggotaDao;
    BukuDao bukuDao;
    
    public PeminjamanController(FormPeminjaman formPeminjaman){
        try {
            this.formPeminjaman = formPeminjaman;
            peminjamanDao = new PeminjamanDaoImpl(DbHelper.getConnection());
            anggotaDao = new AnggotaDaoImpl(DbHelper.getConnection());
            bukuDao = new BukuDaoImpl(DbHelper.getConnection());
        } catch (SQLException ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
          
    public void clearForm(){
        formPeminjaman.getTxttglPinjam().setText("");
        formPeminjaman.getTxttglKembali().setText("");
    }
    
    public void isiComboAnggota(){
        try {
            formPeminjaman.getCbokodeAnggota().removeAllItems();
            List<Anggota> list = anggotaDao.getAll();
            for(Anggota anggota : list){
                formPeminjaman.getCbokodeAnggota()
                        .addItem(anggota.getKodeanggota()+"-"+anggota.getNamaanggota());
            }
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void isiComboBuku(){
        try {
            formPeminjaman.getCbokodeBuku().removeAllItems();
            List<Buku> list = bukuDao.getAll();
            for(Buku buku : list){
                formPeminjaman.getCbokodeBuku()
                        .addItem(buku.getKodebuku()+"-"+buku.getJudulbuku());
            }
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insert(){
        try {
            peminjaman = new Peminjaman();
            Anggota anggota = anggotaDao.getAnggota(formPeminjaman.getCbokodeAnggota()
                    .getSelectedItem().toString().split("-")[0]);
            peminjaman.setAnggota(anggota);
            Buku buku = bukuDao.getBuku(formPeminjaman.getCbokodeBuku()
                    .getSelectedItem().toString().split("-")[0]);
            peminjaman.setBuku(buku);
            peminjaman.setTglpinjam(formPeminjaman.getTxttglPinjam().getText());
            peminjaman.setTglkembali(formPeminjaman.getTxttglKembali().getText());
            peminjamanDao.insert(peminjaman);
            JOptionPane.showMessageDialog(formPeminjaman, "Entri OK");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(formPeminjaman, ex.getMessage());
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(){
        try {
            peminjaman.setAnggota(
                    anggotaDao.getAnggota(formPeminjaman.getCbokodeAnggota().getSelectedItem().toString().split("-")[0]));
            peminjaman.setBuku(
                    bukuDao.getBuku(formPeminjaman.getCbokodeBuku().getSelectedItem().toString().split("-")[0]));
            peminjaman.setTglpinjam(formPeminjaman.getTxttglPinjam().getText());
            peminjaman.setTglkembali(formPeminjaman.getTxttglKembali().getText());
            peminjamanDao.update(peminjaman);
            JOptionPane.showMessageDialog(formPeminjaman, "Update OK");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(formPeminjaman, ex.getMessage());
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        try {
            peminjamanDao.delete(peminjaman);
            JOptionPane.showMessageDialog(formPeminjaman, "Delete OK");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(formPeminjaman, ex.getMessage());
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getPeminjaman(){
        try {
            String kodeanggota = formPeminjaman.getTabelPeminjaman()
                    .getValueAt(formPeminjaman.getTabelPeminjaman()
                            .getSelectedRow(), 0).toString();
            String kodebuku = formPeminjaman.getTabelPeminjaman()
                    .getValueAt(formPeminjaman.getTabelPeminjaman()
                            .getSelectedRow(), 2).toString();
            String tglpinjam = formPeminjaman.getTabelPeminjaman()
                    .getValueAt(formPeminjaman.getTabelPeminjaman()
                            .getSelectedRow(), 4).toString();
            
            peminjaman = peminjamanDao.getPeminjaman(kodeanggota, kodebuku, tglpinjam);
            Anggota anggota = anggotaDao.getAnggota(peminjaman.getAnggota().getKodeanggota());
            formPeminjaman.getCbokodeAnggota()
                    .setSelectedItem(anggota.getKodeanggota()+"-"+anggota.getNamaanggota());
            
            Buku buku = bukuDao.getBuku(peminjaman.getBuku().getKodebuku());
            formPeminjaman.getCbokodeBuku()
                    .setSelectedItem(buku.getKodebuku()+"-"+buku.getJudulbuku());
            
            formPeminjaman.getTxttglPinjam().setText(peminjaman.getTglpinjam());
            formPeminjaman.getTxttglKembali().setText(peminjaman.getTglkembali());
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tampil(){
        try {
            DefaultTableModel tabelModel =
                    (DefaultTableModel) formPeminjaman.getTabelPeminjaman().getModel();
            tabelModel.setRowCount(0);
            List<Peminjaman> list = peminjamanDao.getAll();
            for(Peminjaman p : list){
                Anggota anggota = anggotaDao.getAnggota(p.getAnggota().getKodeanggota());
                Buku buku = bukuDao.getBuku(p.getBuku().getKodebuku());
                Object[] data = {
                    anggota.getKodeanggota(),
                    anggota.getNamaanggota(),
                    buku.getKodebuku(),
                    buku.getJudulbuku(),
                    p.getTglpinjam(),
                    p.getTglkembali()
                };
                tabelModel.addRow(data);
            }
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
}
