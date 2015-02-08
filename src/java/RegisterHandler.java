
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import p1.ServiceInvoker;
import simplestblog.IOException_Exception;
import simplestblog.JSONException_Exception;
import simplestblog.Users;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Windy Amelia
 */

@ManagedBean(name = "Register", eager = true)
@SessionScoped
public class RegisterHandler {
    private String usernameReg;
    private String passwordReg;
    private String roleReg;
    private String namaReg, emailReg;
    private Connection con;
    boolean register;
    boolean registerFailed;
    
    public RegisterHandler() {
        usernameReg = "";
        namaReg = "";
        emailReg = "";
        register = false;
        registerFailed = false;
    }
    
    public String getUsernameReg() {
        return usernameReg;
    }
    
    public String getPasswordReg() {
        return passwordReg;
    }
    
    public String getRoleReg() {
        return roleReg;
    }
    
    public String getNameReg() {
        return namaReg;
    }
    
    public String getEmailReg() {
        return emailReg;
    }
    
    public void setUsernameReg(String user) {
        usernameReg=user;
    }
    
    public void setPasswordReg(String pass) {
        passwordReg=pass;
    }
    
    public void setRoleReg(String role1) {
        roleReg = role1;
    }
    
    public void setNameReg(String name) {
        namaReg = name;
    }
    
    public void setEmailReg(String email1) {
        emailReg = email1;
    }
    
    public void setRegister() throws IOException {
        setUsernameReg(" ");
        setPasswordReg(" ");
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.sendRedirect("register.xhtml");
    }
    
    public void register(String username1, String pass, String menu, String nama, String email) throws ClassNotFoundException, SQLException, IOException, JSONException_Exception, IOException_Exception {
        /*String host = "jdbc:mysql://localhost:3305/blog?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String pwd = "asdasd123";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        con = DriverManager.getConnection(host, user, pwd);
        
        PreparedStatement ps = null;
        String select;
        select = "SELECT * FROM akun WHERE (username ='" + username1 + "')";
        ps= con.prepareStatement(select); 
        ResultSet rs = ps.executeQuery(select);
        String userWeb=null;
        while (rs.next()) {
            userWeb = rs.getString("username");
            setUsernameReg(userWeb);
            String passUser = rs.getString("password");
            setPasswordReg(passUser);            
        }*/
        List<Users> list = new ArrayList<Users>();
        list = ServiceInvoker.listUser();
        
        int size = list.size();
        int i = 0;
        boolean found = false;
        while (i < size && !found) {
            if (list.get(i).getUsername().equals(username1)) {
                found = true;
            } else {
                i++;
            }
        }
        
        if (!found) {
            register = true;
            registerFailed=false;
        } else {
            register = false;
            registerFailed = true;
        }
        
        FacesContext context2 = FacesContext.getCurrentInstance();
        HttpServletResponse response2 = (HttpServletResponse) context2.getExternalContext().getResponse();
        
        if (register) {
            /*Statement stmt = null;
            stmt = con.createStatement();
            String insert = "INSERT INTO akun (username, password, role, nama, email) VALUES ('" + username1 + "' , '" + pass + "' , '" + menu + "' , '" + nama + "' , '" + email + "')";
            stmt.executeUpdate(insert);*/
            ServiceInvoker.addUser(username1, pass, nama, email, menu);
            response2.sendRedirect("page2.xhtml");
        } else {
            response2.sendRedirect("register.jsp");
        }
        
    }
    
    public void registerValidation() throws SQLException {
        PreparedStatement ps = null;
        String select;
        select = "SELECT * FROM akun WHERE (username ='" + usernameReg + "' AND password='" + passwordReg + "')";
        ps= con.prepareStatement(select); 
        ResultSet rs = ps.executeQuery(select);
        String userWeb="";
        while (rs.next()) {
            userWeb = rs.getString("username");
            setUsernameReg(userWeb);
            String passUser = rs.getString("password");
            setPasswordReg(passUser);            
        }
        
        if (userWeb == "") {
            register = true;
        } else {
            register = false;
        }
    }
    
    public boolean isFailedRegistration() throws SQLException {
        return registerFailed;
    }
}
