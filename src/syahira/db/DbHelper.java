/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package syahira.db;
import com.mysql.cj.jdbc.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author LAB-MM
 */
public class DbHelper {
    private static Connection connection;
    
    public static Connection getConnection() throws SQLException{
    if(connection==null){
    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setURL( url: "jdbc:mysql://pbo_2211082019");
    dataSource.setUser(userID:"root");
    dataSource.setPassword(pass: "");
    connection = dataSource.getConnection();
    }    
    return connection;
}
    
public static void main(String[]args)(

