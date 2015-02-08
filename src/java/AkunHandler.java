
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import p1.ServiceInvoker;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Windy Amelia
 */

@ManagedBean(name = "akun", eager = true)
@SessionScoped
public class AkunHandler {
    private String usernameAkun;
    private String passwordAkun;
    private String roleAkun;
    private String namaAkun, emailAkun;
    private String id_akun;
    private Connection con;
    
    public AkunHandler() {
        usernameAkun = "";
        namaAkun = "";
        emailAkun = "";
    }
    
    public String getIdAkun() {
        return id_akun;
    }
    
    public String getUsernameAkun() {
        return usernameAkun;
    }
    
    public String getPasswordAkun() {
        return passwordAkun;
    }
    
    public String getRoleAkun() {
        return roleAkun;
    }
    
    public String getNameAkun() {
        return namaAkun;
    }
    
    public String getEmailAkun() {
        return emailAkun;
    }
    
    public void setIdAkun(String id) {
        id_akun = id;
    }
    
    public void setUsernameAkun(String user) {
        usernameAkun=user;
    }
    
    public void setPasswordAkun(String pass) {
        passwordAkun=pass;
    }
    
    public void setRoleAkun(String role1) {
        roleAkun = role1;
    }
    
    public void setNameAkun(String name) {
        namaAkun = name;
    }
    
    public void setEmailAkun(String email1) {
        emailAkun = email1;
    }
    
    public void repost(String id, String username, String password, String Role, String name, String email) throws IOException {
        setIdAkun(id);
        setUsernameAkun(username);
        setPasswordAkun(password);
        setRoleAkun(Role);
        setNameAkun(name);
        setEmailAkun(email);
        
        FacesContext context3 = FacesContext.getCurrentInstance();
        HttpServletResponse response3 = (HttpServletResponse) context3.getExternalContext().getResponse();
        response3.sendRedirect("Edit_Akun.xhtml");
    }
    
    public void updateAkun(String id) throws ClassNotFoundException, SQLException, IOException {
        /*String host = "jdbc:mysql://localhost:3305/blog?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String pwd = "asdasd123";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        con = DriverManager.getConnection(host, user, pwd);
        
        String query = "UPDATE akun SET username= '" + usernameAkun + "', password= '" + passwordAkun + "', role= '" + roleAkun + "', nama= '" + namaAkun + "', email= '" + emailAkun + "' WHERE id_akun=" + id + "";
        PreparedStatement ps = con.prepareStatement(query);
        ps.executeUpdate();*/
        ServiceInvoker.editUser(id, usernameAkun, passwordAkun, namaAkun, emailAkun, roleAkun);
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.sendRedirect("page2.xhtml");
    }
    
    public void deleteAkun(String id) throws ClassNotFoundException, SQLException {
        /*String host = "jdbc:mysql://localhost:3305/blog?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String pwd = "asdasd123";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        con = DriverManager.getConnection(host, user, pwd);
        
        String query = "DELETE FROM akun WHERE id_akun=" + id + "";
        PreparedStatement ps = con.prepareStatement(query);
        ps.executeUpdate();*/
        ServiceInvoker.deleteUser(id);
    }
}
