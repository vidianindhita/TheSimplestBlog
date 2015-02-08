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
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vidiaanindhita
 */
@WebServlet (name ="AddCommentServlet", urlPatterns = {"/AddCommentServlet"})
public class AddCommentServlet extends HttpServlet {

    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String JDBC_DRIVER="com.mysql.jdbc.Driver";  
            String DB_URL="jdbc:mysql://localhost:3305/blog?zeroDateTimeBehavior=convertToNull";
            String USER = "root";
            String PASS = "asdasd123";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = null;
            Statement stmt = null;

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql;
            java.util.Date utilDate1 = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate1.getTime());
            sql = "INSERT INTO comment (id_post, nama, email, komentar, waktu) VALUES ("
                     + request.getParameter("id_post")
                     + ","
                     + "\'"
                     + request.getParameter("Nama")
                     + "\'"
                     + ","
                     + "\'"
                     + request.getParameter("Email")
                     + "\'"
                     + ","
                     + "\'"
                     + request.getParameter("Komentar")
                     + "\'"
                     + ","
                     + "\'"
                     + request.getParameter("waktu")
                     + "\')" 
                     ;
            System.out.println(sql);
            stmt.executeUpdate(sql);
            conn.close();
            //RequestDispatcher req = request.getRequestDispatcher("post.jsp?id_post=" + request.getParameter("id_post"));
            String site = new String("post.jsp?id_post="
                        + request.getParameter("id_post")
                        + "");
            //response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);
            //System.out.println(site);
            
            String output = "<li class=\"art-list-item\">"+
                    "<div class=\"art-list-item-title-and-time\">"+
                    "<h2 class=\"art-list-title\"><a href=\"post.jsp\">"+ request.getParameter("Nama")+"</a></h2>"+
                    "<div class=\"art-list-time\">"+request.getParameter("waktu")+
                    "</div></div>"+
                    "<p>"+request.getParameter("Komentar")+"</p></li>";
            out.println(output);
            
            /*if (request.getParameter("id_akun").isEmpty())
            {
                String site = new String("post.jsp?id_post="
                        + request.getParameter("id_post")
                        + "");
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", site);
            }
            else
            {
                String site = new String("post.jsp?"
                        + "id_akun="
                        + request.getParameter("id_akun")
                        + "&"
                        + "id_post="
                        + request.getParameter("id_post")
                        + "");
                System.out.println(site);            
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", site);
            }*/
            
        } catch (SQLException ex) {
            out.println("SQLEX : " + ex.getMessage());
            Logger.getLogger(AddCommentServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            out.println("CNFEX : " + ex.getMessage());
            Logger.getLogger(AddCommentServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
            out.close();
        }
    }

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
    }
}
