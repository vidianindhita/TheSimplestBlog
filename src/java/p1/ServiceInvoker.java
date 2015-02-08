package p1;

import simplestblog.IOException_Exception;
import simplestblog.JSONException_Exception;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vidiaanindhita
 */
public class ServiceInvoker {

    public static Boolean addComment(java.lang.String idPost, java.lang.String nama, java.lang.String email, java.lang.String konten) {
        simplestblog.SimplestBlog_Service service = new simplestblog.SimplestBlog_Service();
        simplestblog.SimplestBlog port = service.getSimplestBlogPort();
        return port.addComment(idPost, nama, email, konten);
    }

    public static Boolean addPost(java.lang.String judul, java.lang.String tanggal, java.lang.String konten) {
        simplestblog.SimplestBlog_Service service = new simplestblog.SimplestBlog_Service();
        simplestblog.SimplestBlog port = service.getSimplestBlogPort();
        return port.addPost(judul, tanggal, konten);
    }

    public static Boolean addUser(java.lang.String username, java.lang.String password, java.lang.String nama, java.lang.String email, java.lang.String role) {
        simplestblog.SimplestBlog_Service service = new simplestblog.SimplestBlog_Service();
        simplestblog.SimplestBlog port = service.getSimplestBlogPort();
        return port.addUser(username, password, nama, email, role);
    }

    public static Boolean deleteUser(String id) {
        simplestblog.SimplestBlog_Service service = new simplestblog.SimplestBlog_Service();
        simplestblog.SimplestBlog port = service.getSimplestBlogPort();
        return port.deleteUser(id);
    }

    public static Boolean editPost(java.lang.String id, java.lang.String judul, java.lang.String tanggal, java.lang.String konten, java.lang.String status, java.lang.String statusDelete) {
        simplestblog.SimplestBlog_Service service = new simplestblog.SimplestBlog_Service();
        simplestblog.SimplestBlog port = service.getSimplestBlogPort();
        return port.editPost(id, judul, tanggal, konten, status, statusDelete);
    }

    public static Boolean editUser(java.lang.String id, java.lang.String username, java.lang.String password, java.lang.String nama, java.lang.String email, java.lang.String role) {
        simplestblog.SimplestBlog_Service service = new simplestblog.SimplestBlog_Service();
        simplestblog.SimplestBlog port = service.getSimplestBlogPort();
        return port.editUser(id, username, password, nama, email, role);
    }

    public static java.util.List<simplestblog.Comment> listComment() throws JSONException_Exception, IOException_Exception {
        simplestblog.SimplestBlog_Service service = new simplestblog.SimplestBlog_Service();
        simplestblog.SimplestBlog port = service.getSimplestBlogPort();
        return port.listComment();
    }

    public static java.util.List<simplestblog.Post> listPost() throws IOException_Exception, JSONException_Exception {
        simplestblog.SimplestBlog_Service service = new simplestblog.SimplestBlog_Service();
        simplestblog.SimplestBlog port = service.getSimplestBlogPort();
        return port.listPost();
    }

    public static java.util.List<simplestblog.Users> listUser() throws JSONException_Exception, IOException_Exception {
        simplestblog.SimplestBlog_Service service = new simplestblog.SimplestBlog_Service();
        simplestblog.SimplestBlog port = service.getSimplestBlogPort();
        return port.listUser();
    }

    public static Boolean publishPost(java.lang.String id) {
        simplestblog.SimplestBlog_Service service = new simplestblog.SimplestBlog_Service();
        simplestblog.SimplestBlog port = service.getSimplestBlogPort();
        return port.publishPost(id);
    }

    public static java.util.List<simplestblog.Post> search(java.lang.String query) throws IOException_Exception, JSONException_Exception {
        simplestblog.SimplestBlog_Service service = new simplestblog.SimplestBlog_Service();
        simplestblog.SimplestBlog port = service.getSimplestBlogPort();
        return port.search(query);
    }

    public static Boolean softDelete(String id) {
        simplestblog.SimplestBlog_Service service = new simplestblog.SimplestBlog_Service();
        simplestblog.SimplestBlog port = service.getSimplestBlogPort();
        return port.softDelete(id);
    }
}
