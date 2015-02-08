<%-- 
    Document   : softdelete
    Created on : Nov 30, 2014, 11:57:46 AM
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
        <title>The Simplest Bog | Soft Delete</title>
    </head>
    <body>
        <f:view>
            <%
                ServiceInvoker.softDelete(request.getParameter("id_post"));
                /*String pid="";
                String judul="";
                String tanggal="";
                String konten="";
                String status="";
                simplestblog.Post found = new simplestblog.Post();
                List<simplestblog.Post> listpost = new ArrayList<simplestblog.Post>();
                listpost = ServiceInvoker.listPost();
                for(simplestblog.Post p : listpost){
                    if(p.getIdPost().compareTo(request.getParameter("id_post"))==0){
                        pid = p.getIdPost();
                        judul = p.getJudul();
                        tanggal = p.getTanggal();
                        konten = p.getKonten();
                        status = p.getStatus();
                        System.out.println("masuk");
                        found = p;
                    }
                }
                ServiceInvoker.editPost(pid, judul, tanggal, konten, status, "deleted");
                System.out.println(found.getStatusDelete());*/
                response.sendRedirect("index.jsp");
            %>
        </f:view>
    </body>
</html>

