import static com.sun.faces.facelets.util.Path.context;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vidiaanindhita
 */

@ManagedBean(name = "post", eager = true)
@SessionScoped
public class Post {
    private String judul;
    private Date tanggal;
    private String content;
    private String id_post;
    
    public Post(){
        
    }
    
    public String getJudul() {
        return judul;
    }
    
    public Date getTanggal() {
        return tanggal;
    }
    
    public String getContent() {
        return content;
    }
    
    public String getIdPost() {
        return id_post;
    }
    
    public void setJudul(String judul1) {
        this.judul = judul1;
    }
    
    public void setTanggal (Date tanggal1) {
        this.tanggal = tanggal1;
    }
    
    public void setContent (String content1) {
        this.content = content1;
    }
    
    public void setIdPost (String id_post1) {
        this.id_post = id_post1;
    }
}
