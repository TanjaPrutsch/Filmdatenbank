<%-- 
    Document   : test
    Created on : 11.04.2016, 08:21:53
    Author     : Dino Patarcec
--%>

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
        <h1>Hello World!</h1>
        <%
            Movie m = JAXB.unmarshal("http://www.omdbapi.com/?t=casino+royale&y=&plot=short&r=xml", Movie.class);
            out.println("<p>"+m.getTitle()+"</p>");
        %>
    </body>
</html>
