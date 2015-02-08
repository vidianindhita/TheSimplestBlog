/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vidiaanindhita
 */

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private String userID;
    private String password;
    private Connection con;
    
    public LoginServlet() throws SQLException {
        String host = "jdbc:mysql://localhost:3305/blog?zeroDateTimeBehavior=convertToNull";
        String username = "root";
        String password = "asdasd123";
        con = DriverManager.getConnection(host, username, password);        
    }
    
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String role = request.getParameter("role");
        
        Statement stmt = null;
        String insert = "INSERT INTO akun (username, password, role) VALUES ('"+
user + "' , '" + pass + "' , '" + role + "')";
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            stmt.executeUpdate(insert);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Cookie loginCookie = new Cookie("user",user);
        //setting cookie to expiry in 30 mins
        loginCookie.setMaxAge(30*60);
        response.addCookie(loginCookie);
        response.sendRedirect("LoginSuccess.jsp");
    }
    
    public static void main(String[] args) throws SQLException, IOException {
        LoginServlet L = new LoginServlet();
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        L.doPost(request, response);
    }
}
