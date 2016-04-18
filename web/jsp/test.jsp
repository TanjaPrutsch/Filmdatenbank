<%-- 
    Document   : test
    Created on : 11.04.2016, 08:21:53
    Author     : Dino Patarcec
--%>

<%@page import="BL.Root"%>
<%@page import="BL.Movie"%>
<%@page import="javax.xml.bind.JAXB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Dafuq - Filmdatenbank</h1>
        <%
            Root r = JAXB.unmarshal("http://www.omdbapi.com/?t=Divergent&y=&plot=full&r=xml", Root.class);
            out.println("<p>"+r.getMovie().getTitle()+"</p>");
            out.println("<p>"+r.getMovie().getPlot()+"</p>");
            out.println("<img src='"+r.getMovie().getPoster()+"' alt='Mountain View'>");
        %>
    </body>
</html>
