package utils;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

public class SourceCodes {
    
    private static SourceCodes instance=new SourceCodes();
    
    public static SourceCodes sendMessage(HttpServletResponse response,String msg, int num,String jsp){
        try {
            if(num==0){
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter pw=response.getWriter();
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('"+msg+"');");
                pw.println("</script>");
            }else if(num==1){
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter pw=response.getWriter();
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('"+msg+"');");
                pw.println("location='"+jsp+"';");
                pw.println("</script>");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return instance;
    }
    
    public static void openNewJSP(HttpServletRequest request,HttpServletResponse response,String name){
        try {
            request.getRequestDispatcher(name).forward(request, response);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static List<String> getListUsers(String search){
        List<String> list=new ArrayList<>();
        try {
            String sql="select * from register_users where lastnames like '%"+search+"%'";
            PreparedStatement ps=ConnectionSQL.getInstance().openConnection().prepareCall(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                list.add(rs.getString("lastnames")+ " "+rs.getString("names"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
    
    public static List<String> getVerifyData(int num,String search){
        List<String> list=new ArrayList<>();
        try {
            if(num==1){
                String sql="select * from register_users where lastnames like '%"+search+"%'";
                PreparedStatement ps=ConnectionSQL.getInstance().openConnection().prepareCall(sql);
                
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    if(rs.getString("lastnames").equalsIgnoreCase(search)){
                        list.add(rs.getString("lastnames"));
                    }
                }
            }else if(num==2){
                String sql="select * from register_users where names like '%"+search+"%'";
                PreparedStatement ps=ConnectionSQL.getInstance().openConnection().prepareCall(sql);
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    if(rs.getString("names").equalsIgnoreCase(search)){
                        list.add(rs.getString("names"));
                    }
                }
                
            }else if(num==3){
                String sql="select * from register_users where mail like '%"+search+"%'";
                PreparedStatement ps=ConnectionSQL.getInstance().openConnection().prepareCall(sql);
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    if(rs.getString("mail").equalsIgnoreCase(search)){
                        list.add(rs.getString("mail"));
                    }
                }
            }else if(num==4){
                String sql="select * from register_users where username like '%"+search+"%'";
                PreparedStatement ps=ConnectionSQL.getInstance().openConnection().prepareCall(sql);
                ResultSet rs=ps.executeQuery();
                while(rs.next()){
                    if(rs.getString("username").equalsIgnoreCase(search)){
                        list.add(rs.getString("username"));
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
    
    public static HashMap<String, String> getAccount(String account) {
        HashMap<String,String> hm=new HashMap<>();
        try {
            String url="SELECT * FROM register_users WHERE CONCAT (username,'',mail) LIKE '%"+account+"%'";
            Statement s=ConnectionSQL.getInstance().openConnection().createStatement();
            ResultSet rs=s.executeQuery(url);
            while(rs.next()){
                String username=rs.getString("username");
                String mail=rs.getString("mail");
                String password=rs.getString("password");
                if(username.equalsIgnoreCase(account)){
                    hm.put(username, password);
                }else if(mail.equalsIgnoreCase(account)){
                    hm.put(mail, password);
                }
            }
            rs.close();
            ConnectionSQL.getInstance().closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hm;
    }
    
}
