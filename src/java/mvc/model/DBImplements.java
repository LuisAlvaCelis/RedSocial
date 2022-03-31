package mvc.model;

import java.sql.CallableStatement;
import javax.swing.JOptionPane;
import utils.ConnectionSQL;

public class DBImplements implements DBInterface{

    @Override
    public boolean insertTableRegisterUser(NewUser user) {
        boolean status=false;
        try {
            CallableStatement cs=ConnectionSQL.getInstance().openConnection().prepareCall("{call newUser(?,?,?,?,?)}");
            cs.setString(1, user.getLastnames());
            cs.setString(2, user.getNames());
            cs.setString(3, user.getMail());
            cs.setString(4, user.getUsername());
            cs.setString(5, user.getPassword());
            int rpta=cs.executeUpdate();
            if(rpta==1){
                status=true;
            }else{
                status=false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            status=false;
        }
        return status;
    }
    
}
