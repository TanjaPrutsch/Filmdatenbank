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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author Dino Patarcec
 */
public class DBAccess
{

    // class as singleton
    private DBConnectionPool dbp = null;
    private static DBAccess theInstance = null;
    private Connection con;
    private PreparedStatement insertMovieStmt = null;
    private static final String insertMovieStr = "INSERT INTO movieList(title, released, type, poster, runtime, director, plot, imdbRating) \n" +
                                                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    private final HashMap<Connection, PreparedStatement> map = new HashMap<>();

    public static DBAccess getInstance() throws IOException, ClassNotFoundException, SQLException, Exception
    {
        if (theInstance == null)
        {
            theInstance = new DBAccess();
        }
        return theInstance;
    }

    private DBAccess() throws IOException, ClassNotFoundException, SQLException, Exception
    {
        this.dbp = DBConnectionPool.getInstance();

    }

    
    
    public void insertMovie(Movie m) throws SQLException
    {
        //title, released, type, poster, runtime, director, plot, imdbRating
        String title = m.getTitle();
        String released = m.getRelease();
        String type = m.getType();
        String runtime = m.getRuntime();
        String plot = m.getPlot(); 
        String imdbRating = m.getImdbRating() + ""; 


        if (insertMovieStmt == null) {
            insertMovieStmt = con.prepareStatement(insertMovieStr);
        }
        insertMovieStmt.setString(1, title);
        insertMovieStmt.setString(2, released);
        insertMovieStmt.setString(3, type);
        insertMovieStmt.setString(4, runtime);
        insertMovieStmt.setString(5, plot);
        insertMovieStmt.setString(6, imdbRating);
        insertMovieStmt.executeUpdate();
    }
    
    
   

}
