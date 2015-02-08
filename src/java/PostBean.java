import static com.sun.faces.facelets.util.Path.context;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vidiaanindhita
*/

@ManagedBean(name = "postBean", eager = true)
@SessionScoped
public class PostBean {
    public List<Post> getPostList() throws ClassNotFoundException, SQLException {
        List<Post> list = new ArrayList<Post>();
            String host = "jdbc:mysql://localhost:3305/blog?zeroDateTimeBehavior=convertToNull";
            String user = "root";
            String pwd = "asdasd123";
            PreparedStatement ps = null;
            Connection con = null;
            ResultSet rs = null;

            try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(host, user, pwd);
                String sql = "SELECT * FROM post";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Post post = new Post();
                    post.setJudul(rs.getString("judul"));
                    post.setTanggal (rs.getDate("tanggal"));
                    post.setContent(rs.getString("content"));
                    post.setIdPost(rs.getString("id_post"));
                    list.add(post);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    con.close();
                    ps.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return list;
    }
            
}
