/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class UpdatePostServlet
 */
@WebServlet(name ="EditPostServlet", urlPatterns = {"/EditPostServlet"})
public class EditPostServlet extends HttpServlet {
    /**
     * @param request
     * @param response
     * @see HttpServlet#HttpServlet()
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // JDBC driver name and database URL
            String JDBC_DRIVER="com.mysql.jdbc.Driver";  
            String DB_URL="jdbc:mysql://localhost:3305/blog?zeroDateTimeBehavior=convertToNull";
            //  Database credentials
            String USER = "root";
            String PASS = "asdasd123";
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = null;
            Statement stmt = null;
             // Open a connection
             conn = DriverManager.getConnection(DB_URL,USER,PASS);

             // Execute SQL query
             stmt = conn.createStatement();
             String sql = "UPDATE post SET "
                     + "judul=\""
                     + request.getParameter("Judul")
                     + "\",tanggal=\""
                     + request.getParameter("Tanggal")
                     + "\",content=\""
                     + request.getParameter("Konten")
                     + "\" WHERE id_post="
                     + request.getParameter("id_post");
            System.out.println(sql);
            stmt.executeUpdate(sql);
            String site;                
            site = "index.jsp";
            response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);
        } finally {
            out.close();
        }
    }
    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
        {
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(EditPostServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EditPostServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}