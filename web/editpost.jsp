<%-- 
    Document   : editpost
    Created on : Nov 25, 2014, 8:51:25 PM
    Author     : Windy Amelia
--%>

<%@page import="p1.ServiceInvoker"%>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<!DOCTYPE htmlPUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<%@ page import="java.sql.Connection, javax.sql.*, java.io.*, javax.naming.*" 
%>

<f:view>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="description" content="Deskripsi Blog">
    <meta name="author" content="Judul Blog">

    <!-- Twitter Card -->
    <meta name="twitter:card" content="summary">
    <meta name="twitter:site" content="omfgitsasalmon">
    <meta name="twitter:title" content="Simple Blog">
    <meta name="twitter:description" content="Deskripsi Blog">
    <meta name="twitter:creator" content="Simple Blog">
    <meta name="twitter:image:src" content="{{! TODO: ADD GRAVATAR URL HERE }}">

    <meta property="og:type" content="article">
    <meta property="og:title" content="Simple Blog">
    <meta property="og:description" content="Deskripsi Blog">
    <meta property="og:image" content="{{! TODO: ADD GRAVATAR URL HERE }}">
    <meta property="og:site_name" content="Simple Blog">

    <link rel="stylesheet" type="text/css" href="assets/style.css" />
    <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">

    <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <title>The Simplest Blog | Edit Post</title>
    <script type="text/javascript" src="assets/js/validasi.js"></script>
</head>
<body class="default">
    <div class="wrapper">

    <nav class="nav">
        <div id="login">
            <h:form rendered="#{!Login.isLogin()}">
                Username: <h:inputText value="#{Login.username}" style="outline: none; border: none; border-style: none; background-color: #b9ebe8; font-family: 'Quicksand', sans-serif; height: 16px;"></h:inputText>
                Password: <h:inputSecret value="#{Login.password}" style="outline: none; border: none; border-style: none; background-color: #b9ebe8; font-family: 'Quicksand', sans-serif; height: 16px;"></h:inputSecret> 
                <h:commandButton action="#{Login.Login()}" value="submit" style="margin-left: 6px; border: none; border-style: none; outline: none; background-color: #acaba9; font-family: 'Quicksand', sans-serif; height: 16px; font-weight: 700; font-color: #fff; cursor: pointer; color:#fff;"></h:commandButton>
            </h:form>

            <h:outputLabel rendered="#{Login.isLoginFailed()}">Invalid username or password</h:outputLabel>
        </div>

        <div id="loginsc">
            <h:form rendered="#{Login.isLogin()}">
                <h:outputLabel value="Halo #{Login.username}"></h:outputLabel>
                <h:commandButton action="#{Login.logout()}" value="logout" style="margin-left: 6px; border: none; border-style: none; outline: none; background-color: #acaba9; font-family: 'Quicksand', sans-serif; height: 16px; font-weight: 700; font-color: #fff; cursor: pointer; color:#ffffff;"></h:commandButton>
            </h:form>
        </div>

        <div id="nav-logo">
            <a id="logo" href="index.jsp"><h1>The Simplest Blog</h1></a>
        </div>
        <div id="menu">
            <ul class="nav-primary">
                <li><a href="index.jsp">Home</a></li>
                <li><a href="about.jsp">About</a></li>
                <li><a href="contact.jsp">Contact</a></li>
            <f:subview id="manajemen-user" rendered="#{Login.isAdmin()}"><li><a href="page2.xhtml">User-Management</a></li></f:subview>
            <f:subview id="draft" rendered="#{Login.isEditor() or Login.isAdmin()}"><li><a href="unpublished.jsp">Draft</a></li></f:subview>
            <f:subview id="tambah-post" rendered="#{Login.isOwner() or Login.isAdmin()}"><li><a href="new.jsp">Create Post</a></li></f:subview>
            </ul>
        </div>
    </nav>

    <article class="art simple post">


        <header class="art-header">
            <div class="art-header-inner">
                <h2 class="art-title">Edit Post</h2>
                    <%
                        String pid="";
                        String judul="";
                        String tanggal="";
                        String konten="";
                        List<simplestblog.Post> listpost = new ArrayList<simplestblog.Post>();
                        listpost = ServiceInvoker.listPost();
                        for(simplestblog.Post p : listpost){
                            if(p.getIdPost().compareTo(request.getParameter("id_post"))==0){
                                pid = p.getIdPost();
                                judul = p.getJudul();
                                tanggal = p.getTanggal();
                                konten = p.getKonten();
                            }
                        }
                    %>
            </div>
        </header>

        <div class="art-body">
            <div class="art-body-inner">

                <div id="contact-area">
                    <form method="post" action="submitedit.jsp" onSubmit="return PostValidation()">
                        <label for="Judul">Title:</label>
                        <input type="text" name="Judul" id="Judul" value="<% out.print(judul);%>">

                        <label for="Tanggal">Date:</label>
                        <input type="Date" name="Tanggal" id="Tanggal" value="<% out.print(tanggal); %>">

                        <label for="Konten">Content:</label><br>
                            <textarea name="Konten" rows="20" cols="20" id="Konten"><% out.print(konten);%></textarea>
                            <input type=hidden name="id_post" value="<%out.print(pid); %>">
                        <input type="submit" name="submit" value="Simpan" class="submit-button">
                    </form>
                </div>
            </div>
        </div>
    </article>
    </div>
    <footer id="footerSlideContainer">
        <div id="footerSlideLink">
            <div id="footerSlideContent">
                <div id="footerSlideText"><a href="">Back to top</a></div>
            </div>
        </div>
    </footer>
</body>
</html>
</f:view>
