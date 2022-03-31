package mvc.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import mvc.model.DBImplements;
import mvc.model.DBInterface;
import mvc.model.NewUser;
import utils.ConnectionSQL;
import utils.SourceCodes;

public class sv_main extends HttpServlet {
    
    private DBInterface dao=new DBImplements();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action=request.getParameter("acjsp");
        if(action.equals("return")){
            SourceCodes.openNewJSP(request, response, "login.jsp");
        }else if(action.equals("login")){
            String user=request.getParameter("txtUser");
            String pass=request.getParameter("txtPass");
            try {
                if(SourceCodes.getAccount(user).containsKey(user)){
                    if(SourceCodes.getAccount(user).get(user).equalsIgnoreCase(pass)){
                        PreparedStatement ps=ConnectionSQL.getInstance().openConnection().prepareStatement
                        ("select u.lastnames,u.names,u.mail from register_users u where u.username=? and u.password=?");
                        ps.setString(1, user);
                        ps.setString(2, pass);
                        ResultSet rs=ps.executeQuery();
                        if(rs.next()){
                            HttpSession sesionOK=request.getSession();
                            sesionOK.setAttribute("lastnames", rs.getString(1));
                            sesionOK.setAttribute("names", rs.getString(2));
                            sesionOK.setAttribute("mail", rs.getString(3));
                            sesionOK.setAttribute("username", user);
                            sesionOK.setAttribute("password", pass);
                            SourceCodes.openNewJSP(request, response, "index.jsp");
                        }
                    }else{
                        request.setAttribute("errorMsg", "Error: Contraseña incorrecta, vuelva a intentarlo.");
                        SourceCodes.openNewJSP(request, response, "login.jsp");
                    }
                }else{
                    request.setAttribute("errorMsg", "Error: Usuario incorrecto o no encontrado, vuelva a intentarlo.");
                    SourceCodes.openNewJSP(request, response, "login.jsp");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }else if(action.equals("log-out")){
            HttpSession sesionOK = request.getSession();
            request.getSession().removeAttribute("lastnames");
            request.getSession().removeAttribute("names");
            request.getSession().removeAttribute("mail");
            request.getSession().removeAttribute("username");
            request.getSession().removeAttribute("password");
            sesionOK.invalidate();
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else if(action.equals("register")){
            SourceCodes.openNewJSP(request, response, "register_user.jsp");
        }else if(action.equals("register_newuser")){
            String lastnames=request.getParameter("txtLastnames");
            String names=request.getParameter("txtNames");
            String mail=request.getParameter("txtMail");
            String username=request.getParameter("txtUser");
            String password1=request.getParameter("txtPass1");
            String password2=request.getParameter("txtPass2");
            if(!password1.contains(" ")){
                if(!password2.contains(" ")){
                    if(password1.equals(password2)){
                        if(mail.contains("gmail.com") || mail.contains("hotmail.com")){
                            try {
                                if(!SourceCodes.getVerifyData(1, lastnames).contains(lastnames)){
                                    if(!SourceCodes.getVerifyData(2, names).contains(names)){
                                        if(!SourceCodes.getVerifyData(3, mail).contains(mail)){
                                            if(!SourceCodes.getVerifyData(4, username).contains(username)){
                                                NewUser user=new NewUser(lastnames, names, mail, username, password1);
                                                boolean status=dao.insertTableRegisterUser(user);
                                                if(status==true){
                                                    SourceCodes.sendMessage(response, "¡Registrado exitosamente!", 1, "login.jsp");
                                                }else{
                                                    request.setAttribute("errorMsg", "Error: Nombre de usuario ya registrado anteriormente, vuelva a intentarlo.");
                                                    SourceCodes.openNewJSP(request, response, "register_user.jsp");
                                                }
                                            }else{
                                                request.setAttribute("errorMsg", "Error: Nombre de usuario ya registrado anteriormente, vuelva a intentarlo.");
                                                SourceCodes.openNewJSP(request, response, "register_user.jsp");
                                            }
                                        }else{
                                            request.setAttribute("errorMsg", "Error: Correo electrónico ya registrado anteriormente, vuelva a intentarlo.");
                                            SourceCodes.openNewJSP(request, response, "register_user.jsp");
                                        }
                                    }else{
                                        request.setAttribute("errorMsg", "Error: Nombre ya registrado anteriormente, vuelva a intentarlo.");
                                        SourceCodes.openNewJSP(request, response, "register_user.jsp");
                                    }
                                }else{
                                    request.setAttribute("errorMsg", "Error: Apellidos ya registrado anteriormente, vuelva a intentarlo.");
                                    SourceCodes.openNewJSP(request, response, "register_user.jsp");
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }
                        }else{
                            request.setAttribute("errorMsg", "Error: Solo se admiten correos con extensiones gmail.com o hotmail.com, vuelva a intentarlo.");
                            SourceCodes.openNewJSP(request, response, "register_user.jsp");
                        }
                    }else{
                        request.setAttribute("errorMsg", "Error: Las contraseñas no coinciden!, vuelva a intentarlo.");
                        SourceCodes.openNewJSP(request, response, "register_user.jsp");
                    }
                }else{
                    request.setAttribute("errorMsg", "Error: La contraseña no puede contener espacios en blanco!, vuelva a intentarlo.");
                    SourceCodes.openNewJSP(request, response, "register_user.jsp");
                }
            }else{
                request.setAttribute("errorMsg", "Error: La contraseña no puede contener espacios en blanco!, vuelva a intentarlo.");
                SourceCodes.openNewJSP(request, response, "register_user.jsp");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
