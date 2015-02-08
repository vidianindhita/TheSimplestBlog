<%-- 
    Document   : restore
    Created on : Nov 30, 2014, 12:23:51 PM
    Author     : Windy Amelia
--%>

<%@page import="p1.ServiceInvoker"%>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<!DOCTYPE htmlPUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<%@ page import="java.sql.Connection, javax.sql.*, java.io.*, javax.naming.*" 
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Simplest Blog | Restore Post</title>
    </head>
    <body>
        <f:view>
            <%
                /*String host = "jdbc:mysql://localhost:3305/blog?zeroDateTimeBehavior=convertToNull";
                String user = "root";
                String pwd = "asdasd123";
                Connection con;
                PreparedStatement ps;
                Random random = new Random();
                
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    int updateQuery = 0;
                    con = DriverManager.getConnection(host, user, pwd);
                    String sql = "UPDATE post SET status_delete='undeleted' WHERE id_post = ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, String.valueOf(request.getParameter("id_post")));
                    updateQuery = ps.executeUpdate();
                }
                catch (Exception e){
                    out.println("Error" + e);
                }*/
                String pid="";
                String judul="";
                String tanggal="";
                String konten="";
                String status="";
                //simplestblog.Post found = new simplestblog.Post();
                List<simplestblog.Post> listpost = new ArrayList<simplestblog.Post>();
                listpost = ServiceInvoker.listPost();
                boolean found = false;
                //for(simplestblog.Post p : listpost) {
                int size = listpost.size();
                int i = 0;
                while (i < size && !found) {
                    if(listpost.get(i).getIdPost().compareTo(request.getParameter("id_post"))==0){
                        pid = listpost.get(i).getIdPost();
                        judul = listpost.get(i).getJudul();
                        tanggal = listpost.get(i).getTanggal();
                        konten = listpost.get(i).getKonten();
                        status = listpost.get(i).getStatus();
                        found = true;
                        //System.out.println("masuk");
                        //found = p;
                    } else {
                        i++;
                    }
                }
                System.out.println(pid);
                String s = "undeleted";
                ServiceInvoker.editPost(pid, judul, tanggal, konten, status, s);
                //System.out.println(found.getStatusDelete());
                response.sendRedirect("unpublished.jsp");
            %>
        </f:view>
    </body>
</html>