package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ConnectionSQL {
    
    private static ConnectionSQL instance;
    
    public static ConnectionSQL getInstance(){
        if(instance==null){
            instance=new ConnectionSQL();
        }
        return instance;
    }
    
    public Connection openConnection(){
        Connection connection=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3307/redsocial","root","123123123");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "1 "+e);
        }
        return connection;
    }
    
    public void closeConnection(){
        try {
            if(!openConnection().isClosed()){
                openConnection().close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
