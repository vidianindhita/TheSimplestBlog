/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import p1.ServiceInvoker;


@WebServlet(name = "connect", urlPatterns = {"/connect"})
@MultipartConfig(maxFileSize = 16177215)
public class connect extends HttpServlet {
    
    /*private final String host = "jdbc:mysql://localhost:3305/blog?zeroDateTimeBehavior=convertToNull";
    private final String user = "root";
    private final String pwd = "asdasd123";*/
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Connection con = null;
        String site;
        InputStream iS = null;
        try{
        Part filePart = request.getPart("Foto");
        if(filePart!=null){
            iS = filePart.getInputStream();
        }
        
        //Class.forName("com.mysql.jdbc.Driver").newInstance();
        
        /*con = DriverManager.getConnection(host, user, pwd);
        String sql = "INSERT INTO post (judul, tanggal, content, foto) VALUES (?, ?, ?,?)";
        PreparedStatement s = con.prepareStatement(sql);
        s.setString(1, request.getParameter("Judul"));
        s.setString(2, request.getParameter("Tanggal"));
        s.setString(3, request.getParameter("Konten"));
        if(iS!=null){
            s.setBlob(4,iS);
        }
        s.executeUpdate();*/
        ServiceInvoker.addPost(request.getParameter("Judul"), request.getParameter("Tanggal"), request.getParameter("Konten"));
        site="index.jsp";
        response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
        }catch(Exception e){
            out.println("Error"+e);
        }finally{
            /*if(con!=null){
                try{
                    con.close();
                }catch(Exception e){
                    out.println("Error"+e);
                }
            }*/
        }
    }
}
