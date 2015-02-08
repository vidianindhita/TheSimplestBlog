
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import p1.ServiceInvoker;
import simplestblog.IOException_Exception;
import simplestblog.JSONException_Exception;
import simplestblog.Users;
//import javax.xml.registry.infomodel.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vidiaanindhita
 */
@ManagedBean(name = "Login", eager = true)
@SessionScoped
public class UserHandler {
    private String username;
    private String password;
    private String role;
    private String nama, email;
    private int id_akun;
    private Connection con;
    private boolean login;
    private boolean loginFailed;
    //private boolean register;
    //private boolean registerFailed;
    
    public UserHandler() {
        username="";
        login = false;
        //loginFailed = false;
        //register = false;
        //registerFailed = false;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getRole() {
        return role;
    }
    
    public int getIdAkun() {
        return id_akun;
    }
    
    public String getName() {
        return nama;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setIdAkun(int id) {
        id_akun = id;
    }
    
    public void setUsername(String user) {
        username=user;
    }
    
    public void setPassword(String pass) {
        password=pass;
    }
    
    public void setRole(String role1) {
        role = role1;
    }
    
    public void setName(String name) {
        nama = name;
    }
    
    public void setEmail(String email1) {
        email = email1;
    }
    
    public List<Users> getUserList() throws ClassNotFoundException, SQLException, JSONException_Exception, IOException_Exception
    {
        List<Users> list = new ArrayList<Users>();
        list = ServiceInvoker.listUser();
        /*String host = "jdbc:mysql://localhost:3305/blog?zeroDateTimeBehavior=convertToNull";
        String user = "root";
        String pwd = "asdasd123";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(UserHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        con = DriverManager.getConnection(host, user, pwd);
        
        List<UserHandler> list = new ArrayList<UserHandler>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "select * from akun";
        ps= con.prepareStatement(sql); 
        rs= ps.executeQuery(); 
        while (rs.next())
        {
            UserHandler usr = new UserHandler();
            usr.setIdAkun(rs.getInt("id_akun"));
            usr.setUsername(rs.getString("username"));
            usr.setPassword(rs.getString("password"));
            usr.setRole(rs.getString("role"));
            usr.setName(rs.getString("nama"));
            usr.setEmail(rs.getString("email"));
            list.add(usr);
        } */
        /*UserHandler usr = new UserHandler();
        int size = 
        while (!list.em)
        usr.setIdAkun(list.get(i));
        usr.setUsername(rs.getString("username"));
        usr.setPassword(rs.getString("password"));
        usr.setRole(rs.getString("role"));
        usr.setName(rs.getString("nama"));
        usr.setEmail(rs.getString("email"));*/
        return list;
    }
    
    public boolean isLoginSucceed() {
        return login;
    }
    
    public boolean isLoginFailed() {
        return loginFailed;
    }
    
    public boolean isOwner() {
        String s1 = "owner";
        return (s1.equals(role)) && isLogin();   
    }
    
    public boolean isAdmin() {
        String s1 = "admin";
        return (s1.equals(role)) && isLogin();
    }
    
    public boolean isEditor() {
        String s1 = "editor";
        return (s1.equals(role)) && isLogin();
    }
    
    public boolean isGuest() {
        return !isLogin();
    }
    
    public Cookie getCookie() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        Cookie[] listOfCookie = request.getCookies();
        Cookie cookiename;
        //if (listOfCookie != null) {
            for (int i=0; i<listOfCookie.length; i++)
            {
                if (listOfCookie[i].getName().equals("cookieName")) {
                    cookiename = listOfCookie[i];
                    return cookiename;
                }
            }
        //}
        return null;
    }
    
    public boolean isLogin() {
        Cookie cookie = getCookie();
        if (cookie != null) {
            loginFailed = false;
            login = true;
            return true;
        } else {
            return false;
        }
    }
    
    public void Login() throws SQLException, IOException, ClassNotFoundException, JSONException_Exception, IOException_Exception {
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
        select = "SELECT * FROM akun WHERE (username ='" + username + "' AND password='" + password + "')";
        //String insert = "INSERT INTO akun (username, password, role) VALUES ('" + username + "' , '" + password + "' , '')";
        ps= con.prepareStatement(select); 
        ResultSet rs = ps.executeQuery(select);
        String userWeb="";
        while (rs.next()) {
            userWeb = rs.getString("username");
            setUsername(userWeb);
            String passUser = rs.getString("password");
            setPassword(passUser);
            setRole(rs.getString("role"));
            String nama = rs.getString("nama");
            setName(nama);
            String email = rs.getString("email");
            setEmail(email);
        }*/
        
        List<simplestblog.Users> listUsers = new ArrayList<simplestblog.Users>();
        listUsers = ServiceInvoker.listUser();
        int size = listUsers.size();
        int i = 0;
        boolean found = false;
        while (i < size && !found) {
            if (username.equals(listUsers.get(i).getUsername()) && password.equals(listUsers.get(i).getPassword())) {
                found = true;
                setUsername(listUsers.get(i).getUsername());
                setPassword(listUsers.get(i).getPassword());
                setRole(listUsers.get(i).getRole());
                setName(listUsers.get(i).getName());
                setEmail(listUsers.get(i).getEmail());
            } else {
                i++;
            }
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        if (found) {
            login = true;
            addCookie();
        } else {
            response.sendRedirect("index.jsp");
            loginFailed = true;
        }
    }
    
    public void addCookie() throws IOException {
        Cookie cookie = new Cookie("cookieName", username);
        cookie.setMaxAge(30*60);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.addCookie(cookie);
        response.sendRedirect("index.jsp");
    }
    
    public void logout() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Cookie[] listOfCookie = request.getCookies();
        Cookie cookiename = null;
        boolean found = false;
        for (int i=0; i<listOfCookie.length && !found; i++)
        {
            if (listOfCookie[i].getName().equals("cookieName")) {
                cookiename = listOfCookie[i];
                found = true;
            }
        }
        
        setName(null);
        setEmail(null);
       
        cookiename.setMaxAge(0);
        cookiename.setValue("");
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.addCookie(cookiename);
        response.sendRedirect("index.jsp");
    }
    
    public void updateAkun() {
        
    }

    private boolean strcmp(String role, String s1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
