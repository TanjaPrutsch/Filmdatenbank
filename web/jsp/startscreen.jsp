<%-- 
    Document   : startscreen
    Created on : 18.04.2016, 08:22:53
    Author     : Dino Patarcec
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="BL.Movie"%>
<%@page import="java.util.LinkedList"%>
<%@page import="database.DBAccess"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    LinkedList<Movie> posters = DBAccess.getInstance().getRandomMoviePics();

    request.setAttribute("itemList", posters);

    for (Movie m : posters)
    {
        System.out.println(m);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/StyleCSS.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="js/jssor.slider.mini.js"></script>

        <script>
            jQuery(document).ready(function ($) {

                var jssor_1_options = {
                    $AutoPlay: true,
                    $AutoPlaySteps: 4,
                    $SlideDuration: 160,
                    $SlideWidth: 200,
                    $SlideSpacing: 3,
                    $Cols: 4,
                    $ArrowNavigatorOptions: {
                        $Class: $JssorArrowNavigator$,
                        $Steps: 4
                    },
                    $BulletNavigatorOptions: {
                        $Class: $JssorBulletNavigator$,
                        $SpacingX: 1,
                        $SpacingY: 1
                    }
                };

                var jssor_1_slider = new $JssorSlider$("jssor_1", jssor_1_options);

                //responsive code begin
                //you can remove responsive code if you don't want the slider scales while window resizing
                function ScaleSlider() {
                    var refSize = jssor_1_slider.$Elmt.parentNode.clientWidth;
                    if (refSize) {
                        refSize = Math.min(refSize, 809);
                        jssor_1_slider.$ScaleWidth(refSize);
                    }
                    else {
                        window.setTimeout(ScaleSlider, 30);
                    }
                }
                ScaleSlider();
                $(window).bind("load", ScaleSlider);
                $(window).bind("resize", ScaleSlider);
                $(window).bind("orientationchange", ScaleSlider);
                //responsive code end
            });
        </script>


        <title>JSP Page</title>


    </head>

    <body>
    <center>
        <div class="headDiv">
            <form name="LogIn" action="MovieServlet">
                <input type="submit" class="mainButton" name="login" value="Login" />
            </form>
            <form name="SignUp" action="MovieServlet">
                <input type="submit" name="signup" value="SignUp" class="mainButton"/>
            </form>
            <form id="movieSearch" name="movieSearch" action="MovieServlet" method="GET">
                <span>
                    <input type="text" name="movieName" value="" />
                    <input type="submit" value="Suche" name="btSearchMovie" class="mainButton"/>
                </span>
            </form>
        </div>

        <div class="container">
            <br>
            <div id="jssor_1" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 809px; height: 150px; overflow: hidden; visibility: hidden;">
                <!-- Loading Screen -->
                <div data-u="loading" style="position: absolute; top: 0px; left: 0px;">
                    <div style="filter: alpha(opacity=70); opacity: 0.7; position: absolute; display: block; top: 0px; left: 0px; width: 100%; height: 100%;"></div>
                    <div style="position:absolute;display:block;background:url('img/loading.gif') no-repeat center center;top:0px;left:0px;width:100%;height:100%;"></div>
                </div>
                <div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 809px; height: 150px; overflow: hidden;">
                    <c:forEach begin="0" end="19" step="1" var="i">
                        <div style="display: none;">
                            <a href="MovieServlet?movieName=${itemList[i].getTitle()}&btSearchMovie=Suche"><img data-u="image" src="${itemList[i].getPoster()}"></a>
                        </div>
                    </c:forEach>
                    <a data-u="ad" href="http://www.jssor.com" style="display:none">jQuery Slider</a>

                </div>
                <!-- Bullet Navigator -->
                <div data-u="navigator" class="jssorb03" style="bottom:10px;right:10px;">
                    <!-- bullet navigator item prototype -->
                    <div data-u="prototype" style="width:21px;height:21px;">
                        <div data-u="numbertemplate"></div>
                    </div>
                </div>
                <!-- Arrow Navigator -->
                <span data-u="arrowleft" class="jssora03l" style="top:0px;left:8px;width:55px;height:55px;" data-autocenter="2"></span>
                <span data-u="arrowright" class="jssora03r" style="top:0px;right:8px;width:55px;height:55px;" data-autocenter="2"></span>
            </div>
        </div>
    </center>
</body>
</html>
