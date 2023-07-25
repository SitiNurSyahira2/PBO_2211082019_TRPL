/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package syahira.controller;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import syahira.db.DbHelper;
import syahira.model.*;
import syahira.dao.*;
import syahira.view.*;
/**
/**
 *
 * @author LAB-MM
 */
public class PengembalikanController {
      Pengembalian pengembalian;
    PengembalianDao pengembalianDao;
    FormPengembalian formPengembalian;
    
    Peminjaman peminjaman;
    PeminjamanDao peminjamanDao;
    FormPeminjaman formPeminjaman;
    
    AnggotaDao anggotaDao;
    BukuDao bukuDao;
    
    Connection connection;
    
    public PengembalianController(FormPengembalian formPengembalian) {
        this.formPengembalian = formPengembalian;
        try {
            connection = DbHelper.getConnection();
            pengembalianDao = new PengembalianDaoImpl(connection);
            peminjamanDao = new PeminjamanDaoImpl(connection);
            anggotaDao = new AnggotaDaoImpl(connection);
            bukuDao = new BukuDaoImpl(connection);
        } catch (SQLException ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clearForm(){
        formPengembalian.getTxtDenda().setText("");
        formPengembalian.getTxtTerlambat().setText("");
        formPengembalian.getTxttglDikembalikan().setText("");
        formPengembalian.getTxttglPinjam().setText("");
    }
    
    public void isiComboAnggota(){
        try {
            formPengembalian.getCbokodeAnggota().removeAllItems();
            List<Anggota> list = anggotaDao.getAll();
            for(Anggota anggota : list){
                formPengembalian.getCbokodeAnggota()
                        .addItem(anggota.getKodeanggota()+"-"+anggota.getNamaanggota());
            }
        } catch (Exception ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void isiComboBuku(){
        try {
            formPengembalian.getCbokodeBuku().removeAllItems();
            List<Buku> list = bukuDao.getAll();
            for(Buku buku : list){
                formPengembalian.getCbokodeBuku()
                        .addItem(buku.getKodebuku()+"-"+buku.getJudulbuku());
            }
        } catch (Exception ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insert(){
        try {
            pengembalian = new Pengembalian();
            pengembalian.setKodeanggota(formPengembalian.getCbokodeAnggota().getSelectedItem().toString().split("-")[0]);
            pengembalian.setKodebuku(formPengembalian.getCbokodeBuku().getSelectedItem().toString().split("-")[0]);
            pengembalian.setTglpinjam(formPengembalian.getTxttglPinjam().getText());
            pengembalian.setTgldikembalikan(formPengembalian.getTxttglDikembalikan().getText());
            pengembalian.setTerlambat(Integer.parseInt(formPengembalian
                    .getTxtTerlambat().getText()));
            pengembalian.setDenda(Double.parseDouble(formPengembalian.getTxtDenda().getText()));
            pengembalianDao.insert(pengembalian);
            JOptionPane.showMessageDialog(formPengembalian, "Pengembalian OK");
        } catch (Exception ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(){
        try {
            pengembalian.setKodeanggota(formPengembalian.getCbokodeAnggota().getSelectedItem().toString());
            pengembalian.setKodebuku(formPengembalian.getCbokodeBuku().getSelectedItem().toString());
            pengembalian.setTglpinjam(formPengembalian.getTxttglPinjam().getText());
            pengembalian.setTgldikembalikan(formPengembalian.getTxttglDikembalikan().getText());
            pengembalian.setTerlambat(Integer.parseInt(formPengembalian.getTxtTerlambat().getText()));
            pengembalian.setDenda(Double.parseDouble(formPengembalian.getTxtDenda().getText()));
            pengembalianDao.update(pengembalian);
            JOptionPane.showMessageDialog(formPengembalian, "Data Pengembalian telah dirubah!", null, 2);
        } catch (Exception ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        try {
            pengembalian.setKodeanggota(formPengembalian.getTabelPengembalian().getValueAt(formPengembalian.getTabelPengembalian().getSelectedRow(), 0)
                    .toString());
            pengembalian.setKodebuku(formPengembalian.getTabelPengembalian().getValueAt(formPengembalian.getTabelPengembalian().getSelectedRow(), 2)
                    .toString());
            pengembalian.setTglpinjam(formPengembalian.getTabelPengembalian().getValueAt(formPengembalian.getTabelPengembalian().getSelectedRow(), 4)
                    .toString());
            pengembalianDao.delete(pengembalian);
            JOptionPane.showMessageDialog(formPengembalian, "Data Pengembalian berhasil dihapus!", null, 2);
        } catch (Exception ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getPengembalian(){
        try {
            pengembalian = new Pengembalian();
            String kodeanggota = formPengembalian.getTabelPengembalian()
                    .getValueAt(formPengembalian.getTabelPengembalian()
                            .getSelectedRow(), 0).toString();
            String kodebuku = formPengembalian.getTabelPengembalian()
                    .getValueAt(formPengembalian.getTabelPengembalian()
                            .getSelectedRow(), 2).toString();
            String tglpinjam = formPengembalian.getTabelPengembalian()
                    .getValueAt(formPengembalian.getTabelPengembalian()
                            .getSelectedRow(), 4).toString();
            
            Peminjaman peminjaman = peminjamanDao.getPeminjaman(kodeanggota, kodebuku, tglpinjam);
            Anggota anggota = anggotaDao.getAnggota(peminjaman.getAnggota().getKodeanggota());
            formPengembalian.getCbokodeAnggota()
                    .setSelectedItem(anggota.getKodeanggota()+"-"+anggota.getNamaanggota());
            
            Buku buku = bukuDao.getBuku(peminjaman.getBuku().getKodebuku());
            formPengembalian.getCbokodeBuku()
                    .setSelectedItem(buku.getKodebuku()+"-"+buku.getJudulbuku());
            
            formPengembalian.getTxttglPinjam().setText(peminjaman.getTglpinjam());
            formPengembalian.getTxttglDikembalikan().setText(pengembalian.getTgldikembalikan());
            int terlambat = pengembalianDao
                    .selisihTgl(pengembalian.getTgldikembalikan(), peminjaman.getTglkembali());
            pengembalian.setTerlambat(terlambat);
            formPengembalian.getTxtTerlambat().setText(""+terlambat);
            formPengembalian.getTxtDenda().setText(""+pengembalian.getDenda());
        } catch (Exception ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void cari() {
        try {
            String kode = formPengembalian.getCboCari().getSelectedItem().toString();
            String cari = formPengembalian.getTxtCari().getText();
            DefaultTableModel tableModel = (DefaultTableModel) formPengembalian.getTabelPengembalian().getModel();
            tableModel.setRowCount(0);
            if (kode == "Kode Anggota") {
                kode = "anggota.kodeAnggota";
            } else if (kode == "Kode Buku") {
                kode = "daftarBuku.kodeBuku";
            } else {
                kode = "anggota.namaAnggota";
            }
            List<Pengembalian> List = pengembalianDao.cari(kode, cari);
            if (List.isEmpty()) {
                if (kode == "anggota.kodeAnggota") {
                    JOptionPane.showMessageDialog(formPengembalian, "Kode Anggota '" + cari + "' Tidak dapat ditemukan");
                } else if (kode == "daftarBuku.kodeBuku") {
                    JOptionPane.showMessageDialog(formPengembalian, "Kode Buku '" + cari + "' Tidak dapat ditemukan");
                } else {
                    JOptionPane.showMessageDialog(formPengembalian, "Nama '" + cari + "' Tidak dapat ditemukan");
                }
            } else {
                for (Pengembalian pm : List) {
                    Object[] data = {
                            pm.getKodeanggota(),
                            pm.getKodebuku(),
                            pm.getTglpinjam(),
                            pm.getTgldikembalikan(),
                            pm.getTerlambat(),
                            pm.getDenda()
                    };
                    tableModel.addRow(data);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(formPengembalian, e, null, 0);
        }
    }
        
    public void tampil(){
        try {
            DefaultTableModel tabelModel =
                    (DefaultTableModel) formPengembalian.getTabelPengembalian().getModel();
            tabelModel.setRowCount(0);
            List<Pengembalian> list = pengembalianDao.getAll();
            for(Pengembalian p : list){
                Anggota anggota = anggotaDao.getAnggota(p.getKodeanggota());
                Buku buku = bukuDao.getBuku(p.getKodebuku());
                Peminjaman peminjaman = peminjamanDao.getPeminjaman(p.getKodeanggota(),
                        p.getKodebuku(), p.getTglpinjam());
                Object[] data = {
                    anggota.getKodeanggota(),
                    anggota.getNamaanggota(),
                    buku.getKodebuku(),
                    buku.getJudulbuku(),
                    p.getTglpinjam(),
                    peminjaman.getTglkembali(),
                    p.getTgldikembalikan(),
                    p.getTerlambat(),
                    p.getDenda()
                };
                tabelModel.addRow(data);
            }
        } catch (Exception ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    
}
