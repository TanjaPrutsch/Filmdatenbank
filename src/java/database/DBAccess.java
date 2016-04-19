/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import BL.Movie;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dino Patarcec
 */
public class DBAccess {

    // class as singleton
    private DBConnectionPool dbp = null;
    private static DBAccess theInstance = null;
    private Connection con;
    private PreparedStatement insertMovieStmt = null;
    private static final String insertMovieStr = "INSERT INTO movieList(title, released, type, poster, runtime, director, plot, imdbRating) \n"
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    private final HashMap<Connection, PreparedStatement> map = new HashMap<>();
    private LinkedList<Movie> movieList = new LinkedList<>();

    public static DBAccess getInstance() throws IOException, ClassNotFoundException, SQLException, Exception {
        if (theInstance == null) {
            theInstance = new DBAccess();
        }
        return theInstance;
    }

    private DBAccess() throws IOException, ClassNotFoundException, SQLException, Exception {
        this.dbp = DBConnectionPool.getInstance();

    }

    public void alreadyInDatabase(Movie m) throws Exception {
        Connection conn = dbp.getConnection();
        Statement stat = conn.createStatement();
        String sqlString = "SELECT * "
                + "FROM movielist "
                + "WHERE UPPER(title) LIKE UPPER('" + m.getTitle() + "');";
   
        ResultSet rs = stat.executeQuery(sqlString);

        if (rs.next()) {
            System.out.println(rs.toString());
            while (rs.next()) {
                //String title, String released, String type, String poster, String runtime, String director, String plot, double imdbRating
                String titleM  = rs.getString("title");
                String released = rs.getString("released");
                String typeM = rs.getString("type");
                String poster = rs.getString("poster");
                String runtime = rs.getString("runtime");
                String director = rs.getString("director");
                String plot = rs.getString("plot");
                Double imdbRating = rs.getDouble("imdbRating");

                m = new Movie(titleM, released, typeM, poster, runtime, director, plot, imdbRating);
                System.out.println("Liste");
           
                    returnMovie(m);
                
            }

        }
        else
        {
            System.out.println("halllllooooooo");
            insertMovie(m);
        }

        dbp.releaseConnection(conn);

    }

    public Movie returnMovie(Movie m) {
        return m;
    }

    public void insertMovie(Movie m) throws SQLException {

        try {
            Connection conn = dbp.getConnection();
            PreparedStatement insertMovieStmt = map.get(conn);

            if (insertMovieStmt == null) {
                insertMovieStmt = conn.prepareStatement(insertMovieStr);
                map.put(con, insertMovieStmt);
            }
            String title = m.getTitle();
            String released = m.getRelease();
            String type = m.getType();
            String runtime = m.getRuntime();
            String plot = m.getPlot();
            String imdbRating = m.getImdbRating() + "";
            String poster = m.getPoster();
            String director = m.getDirector();

            insertMovieStmt.setString(1, title);
            insertMovieStmt.setString(2, released);
            insertMovieStmt.setString(3, type);
            insertMovieStmt.setString(4, poster);
            insertMovieStmt.setString(5, runtime);
            insertMovieStmt.setString(6, director);
            insertMovieStmt.setString(7, plot);
            insertMovieStmt.setString(8, imdbRating);
            insertMovieStmt.executeUpdate();

            movieList.add(m);
            dbp.releaseConnection(conn);
        } catch (Exception ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
