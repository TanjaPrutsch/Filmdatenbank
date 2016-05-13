<%--
    Document   : login
    Created on : 03.05.2016, 08:18:28
    Author     : pruta_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name="LogInInfos" action="../MovieServlet" method="POST">
            <center>
                <h1>Login</h1>
                <label id="error" style="color: red"><%=request.getAttribute("loginError")!= null ? request.getAttribute("loginError"): ""%></label> <br>
                Username: <input type="text" name="username"/> <br>
                Password: <input type="password" name="password"/>
                <input type="submit" value="Login" name="btLogin"/>
                <input type="hidden" name="LogInInfos" />
                       
            </center>
        </form>
    </body>
</html>