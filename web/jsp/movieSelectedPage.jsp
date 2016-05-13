<%-- 
    Document   : movieSelectedPage
    Created on : 18.04.2016, 08:43:46
    Author     : Dino Patarcec
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="database.DBAccess"%>
<%@page import="java.util.LinkedList"%>
<%@page import="BL.Movie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="BL.Root"%>
<!DOCTYPE html>
<html>
    <head>
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
        <style>

            /* jssor slider bullet navigator skin 03 css */
            /*
            .jssorb03 div           (normal)
            .jssorb03 div:hover     (normal mouseover)
            .jssorb03 .av           (active)
            .jssorb03 .av:hover     (active mouseover)
            .jssorb03 .dn           (mousedown)
            */
            .jssorb03 {
                position: absolute;
            }
            .jssorb03 div, .jssorb03 div:hover, .jssorb03 .av {
                position: absolute;
                /* size of bullet elment */
                width: 21px;
                height: 21px;
                text-align: center;
                line-height: 21px;
                color: white;
                font-size: 12px;
                background: url('img/b03.png') no-repeat;
                overflow: hidden;
                cursor: pointer;
            }
            .jssorb03 div { background-position: -5px -4px; }
            .jssorb03 div:hover, .jssorb03 .av:hover { background-position: -35px -4px; }
            .jssorb03 .av { background-position: -65px -4px; }
            .jssorb03 .dn, .jssorb03 .dn:hover { background-position: -95px -4px; }

            /* jssor slider arrow navigator skin 03 css */
            /*
            .jssora03l                  (normal)
            .jssora03r                  (normal)
            .jssora03l:hover            (normal mouseover)
            .jssora03r:hover            (normal mouseover)
            .jssora03l.jssora03ldn      (mousedown)
            .jssora03r.jssora03rdn      (mousedown)
            */
            .jssora03l, .jssora03r {
                display: block;
                position: absolute;
                /* size of arrow element */
                width: 55px;
                height: 55px;
                cursor: pointer;
                background: url('img/a03.png') no-repeat;
                overflow: hidden;
            }
            .jssora03l { background-position: -3px -33px; }
            .jssora03r { background-position: -63px -33px; }
            .jssora03l:hover { background-position: -123px -33px; }
            .jssora03r:hover { background-position: -183px -33px; }
            .jssora03l.jssora03ldn { background-position: -243px -33px; }
            .jssora03r.jssora03rdn { background-position: -303px -33px; }
        </style>

        <title>JSP Page</title>


    </head>

    <style>
        tr, td {
            padding: 15px;
        }

        table { 
            table-layout:fixed; width:100px;
        }
        table tr {
            height: 20px;
        }

        tr:nth-child(even){background-color: #f2f2f2}
    </style>


    <body>
        <%
            Root r = (Root) request.getAttribute("movie");
            out.println("<h1>" + r.getMovie().getTitle() + "</h1>");
            out.println("<img src='" + r.getMovie().getPoster() + "' alt='MoviePoster' style='width:240px;height:300px;'>");
            out.println("<table style='width:100%'>");
            out.println("<tr>");
            out.println("<td>Released: </td>");
            out.println("<td>" + r.getMovie().getRelease() + "</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Runtime: </td>");
            out.println("<td>" + r.getMovie().getRuntime() + "</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Director:</td>");
            out.println("<td>" + r.getMovie().getDirector() + "</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>IMdB Rating:  </td>");
            out.println("<td>" + r.getMovie().getImdbRating() + "</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Plot: </td>");
            out.println("<td>" + r.getMovie().getPlot() + "</td>");
            out.println("</tr>");
            out.println("</table>");

            LinkedList<Movie> posters = DBAccess.getInstance().getRandomMoviePics();

            request.setAttribute("itemList", posters);

            for (Movie m : posters)
            {
                System.out.println(m);
            }
        %>
         <form name="HomeButton" action="MovieServlet">
            <input type="submit" name="home" value="Home" />
            <%
            request.setAttribute("homescreen", true);
            %>
        </form>
        <div id="jssor_1" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 809px; height: 150px; overflow: hidden; visibility: hidden;">
            <!-- Loading Screen -->
            <div data-u="loading" style="position: absolute; top: 0px; left: 0px;">
                <div style="filter: alpha(opacity=70); opacity: 0.7; position: absolute; display: block; top: 0px; left: 0px; width: 100%; height: 100%;"></div>
                <div style="position:absolute;display:block;background:url('../img/loading.gif') no-repeat center center;top:0px;left:0px;width:100%;height:100%;"></div>
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

    </body>
</html>
