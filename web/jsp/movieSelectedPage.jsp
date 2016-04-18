<%-- 
    Document   : movieSelectedPage
    Created on : 18.04.2016, 08:43:46
    Author     : Dino Patarcec
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="BL.Root"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        Root r = (Root)request.getAttribute("movie");
         out.println("<h1>"+r.getMovie().getTitle()+"</h1>");
        %>
        
        <span>
             <img src="" alt="MoviePoster" style="width:240px;height:300px;">
             <h2>Released</h2>
             <p></p>
        </span>
        <h2>Plot</h2>
        <p></p>
        
    </body>
</html>
