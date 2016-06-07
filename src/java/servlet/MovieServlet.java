/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import BL.Root;
import BL.Series;
import BL.User;
import database.DBAccess;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;

/**
 *
 * This is the main controllerservlet that forwards to other servlets.
 */
@WebServlet(name = "MovieServlet", urlPatterns
        =
        {
            "/MovieServlet"
        })

public class MovieServlet extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            System.out.println("LOGININFOS: " + request.getParameter("LogInInfos"));
            System.out.println("LOGIN:" + request.getParameter("login"));
            response.setContentType("text/html;charset=UTF-8");
            if (request.getParameter("movieName") != null)
            {
                System.out.println("HALLO MOVIE");
                try
                {
                    String moviename = request.getParameter("movieName").replace(" ", "+");
                    String url = "http://www.omdbapi.com/?t=" + moviename + "&y=&plot=full&r=xml";
                    Root r = JAXB.unmarshal(url, Root.class);
//                    request.setAttribute("movie", r);
//                    DBAccess.getInstance().movieAlreadyInDatabase(r.getMovie());
//                    if (r.getMovie().getType().equals("movie")) {
                    request.setAttribute("movie", r);
                    DBAccess.getInstance().alreadyInDatabase(r.getMovie());
                    //DBAccess.getInstance().insertUser(new User( "hallo", "tanja"));

//                    }
//                    else if(r.getSeries().getType().equals("series"))
//                    {
//                        System.out.println("SERIESSSSSSSSSSS");
//                        request.setAttribute("series", r);
//                        DBAccess.getInstance().seriesAlreadyInDatabase(r.getSeries());
//                    }
                    RequestDispatcher r1 = request.getRequestDispatcher("/jsp/movieSelectedPage.jsp");
                    r1.forward(request, response);
                } catch (SQLException ex)
                {
                    Logger.getLogger(MovieServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex)
                {
                    Logger.getLogger(MovieServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (request.getParameter("login") != null)
            {
                System.out.println("HALLO LOGIN");
                //RequestDispatcher r1 = request.getRequestDispatcher("/jsp/login.jsp");
                // r1.forward(request, response);
                response.sendRedirect("jsp/login.jsp");
                return;
            } //            else if (request.getParameter("LogInInfos") != null) {
            //                System.out.println("HALLO LOGININFOS");
            //                String username = request.getParameter("username");
            //                String password = request.getParameter("password");
            //                DBAccess.getInstance().loginUser(password, username);
            //            }
            else if (request.getParameter("signup") != null)
            {
                System.out.println("SIGNUPPPPP");
                RequestDispatcher r1 = request.getRequestDispatcher("/jsp/signup.jsp");
                r1.forward(request, response);

//                System.out.println("SIGNUPPPP");
//                String username = request.getParameter("username");
//                String password = request.getParameter("password");
//                DBAccess.getInstance().insertUser(new User(password, username));
            } else
            {
                RequestDispatcher r1 = request.getRequestDispatcher("/jsp/startscreen.jsp");
                response.sendRedirect("jsp/startscreen.jsp");
//                r1.forward(request, response);
            }
            /* TODO output your page here. You may use following sample code. */

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response);
        } catch (Exception ex)
        {
            Logger.getLogger(MovieServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            if (request.getParameter("LogInInfos") != null)
            {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                if (DBAccess.getInstance().loginUser(password, username) == true)
                {
                    User user = new User(password, username);
                    String sessionID = user.getPassword();
                    request.getSession().setAttribute("user", user);
                    request.getSession().setAttribute("sessionID", sessionID);
                    Cookie cookie = new Cookie("sessionID", user.getPassword());
                    response.addCookie(cookie);
                    response.sendRedirect("LoggedinServlet");
                } else
                {
                    request.setAttribute("loginError", "Benutzername oder "
                            + "Passwort falsch!");
                    response.sendRedirect("jsp/login.jsp");
                    //request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
                }
            } else if (request.getParameter("SignUp") != null)
            {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String password2 = request.getParameter("password2");
                if (password.equals(password2))
                {
                    DBAccess.getInstance().insertUser(new User(password, username));
                    User user = new User(password, username);
                    String sessionID = user.getPassword();
                    request.getSession().setAttribute("user", user);
                    request.getSession().setAttribute("sessionID", sessionID);
                    Cookie cookie = new Cookie("sessionID", user.getPassword());
                    response.addCookie(cookie);
                    response.sendRedirect("LoggedinServlet");
                } else
                {
                    request.setAttribute("signupError", "Passwörter stimmen nicht überein!");
                    request.getRequestDispatcher("jsp/signup.jsp").forward(request, response);
                }

            }

            if (request.getAttribute("homescreen") != null)
            {
                request.getRequestDispatcher("jsp/startscreen.jsp").forward(request, response);
            }
            //processRequest(request, response);

            //processRequest(request, response);
        } catch (Exception ex)
        {
            Logger.getLogger(MovieServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}