/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package syahira.db;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import syahira.sql.SQLException;
import syahira.swing.JOptionPane;
import syahira.dao.AnggotaDao;
import syahira.dao.AnggotaDaoImpl;
import syahira.model.Anggota;

/**
 *
 * @author User
 */
public class DbHelper {
    private static Connection connection;
    public static Connection getConnection() throws SQLException {
        if(connection==null){
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setURL("jdbc:mysql://localhost/pbo_2211082019");
            dataSource.setUser("root");
            dataSource.setPassword("");
            connection = dataSource.getConnection();
        }
        return connection;
    }
    
    public static void main(String[] args){
        try {
            connection = DbHelper.getConnection();
            AnggotaDao dao = new AnggotaDaoImpl(connection);
            Anggota anggota = new Anggota("B001","Bumi","Tere Liye","Fajar Harapan");
            dao.insert(anggota);
            JOptionPane.showMessageDialog(null, "Entri data OK");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}