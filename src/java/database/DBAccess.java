/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import BL.Movie;
import BL.Root;
import BL.Series;
import BL.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import javax.xml.bind.JAXB;

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
    private PreparedStatement insertSeriesStmt = null;
    private PreparedStatement insertUserStmt = null;

    private static final String insertMovieStr = "INSERT INTO movielist(title, released, type, poster, runtime, director, plot, imdbrating) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String insertSeriesStr = "INSERT INTO serieslist(title, released, type, poster, runtime, director, plot, imdbrating) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String insertUserStr = "INSERT INTO userlist(username, password)"
            + "VALUES(?, ?);";
    private final HashMap<Connection, PreparedStatement> map = new HashMap<>();
    private LinkedList<Movie> movieList = new LinkedList<>();
    private LinkedList<Movie> seriesList = new LinkedList<>();
    private LinkedList<Movie> showPosters = new LinkedList<>();
    private LinkedList<User> userlist = new LinkedList<>();
    private LinkedList<Movie> newEntererdMovies = new LinkedList<>();

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

    public void alreadyInDatabase(Movie m) throws Exception
    {
        try
        {
            Connection conn = dbp.getConnection();
            Statement stat = conn.createStatement();
            String sqlString = "SELECT * "
                    + "FROM movielist "
                    + "WHERE UPPER(title) LIKE UPPER('" + m.getTitle() + "');";

            ResultSet rs = stat.executeQuery(sqlString);

            Statement statS = conn.createStatement();
            String sqlStringS = "SELECT * "
                    + "FROM serieslist "
                    + "WHERE UPPER(title) LIKE UPPER('" + m.getTitle() + "');";

            ResultSet rsS = statS.executeQuery(sqlStringS);

            if (rs.next())
            {

                System.out.println("DRINNEN");
                while (rs.next())
                {
                    //String title, String released, String type, String poster, String runtime, String director, String plot, double imdbRating
                    String titleM = rs.getString("title");
                    String released = rs.getString("released");
                    String typeM = rs.getString("type");
                    String poster = rs.getString("poster");
                    String runtime = rs.getString("runtime");
                    String director = rs.getString("director");
                    String plot = rs.getString("plot");
                    Double imdbRating = rs.getDouble("imdbrating");

//                if(typeM.equals("series"))
//                {
//                    
//                    int season = 1;
//                    int episode = 1;
//                    Series s = new Series(titleM, released, typeM, poster, runtime, director, plot, imdbRating, season, episode);
//                    insertSeries(s);
//                }
                    m = new Movie(titleM, released, typeM, poster, runtime, director, plot, imdbRating);
                    returnMovie(m);
                }

//        } else if (rsS.next()) {
//            System.out.println(rsS.toString());
//            while (rsS.next()) {
//                //String title, String released, String type, String poster, String runtime, String director, String plot, double imdbRating
//                String titleM = rsS.getString("title");
//                String released = rsS.getString("released");
//                String typeM = rsS.getString("type");
//                String poster = rsS.getString("poster");
//                String runtime = rsS.getString("runtime");
//                String director = rsS.getString("director");
//                String plot = rsS.getString("plot");
//                Double imdbRating = rsS.getDouble("imdbRating");
//
////                if(typeM.equals("series"))
////                {
////                    
////                    int season = 1;
////                    int episode = 1;
////                    Series s = new Series(titleM, released, typeM, poster, runtime, director, plot, imdbRating, season, episode);
////                    insertSeries(s);
////                }
//                m = new Movie(titleM, released, typeM, poster, runtime, director, plot, imdbRating);
//                returnSeries(m);
//            }
            } else
            {
                if (m.getType().equals("series"))
                {
                    //seriesAlreadyInDatabase((Series)m);
                    insertSeries(m);
                } else if (m.getType().equals("movie"))
                {
                    insertMovie(m);
                }

            }

            dbp.releaseConnection(conn);

        } catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
    }

//    public void seriesAlreadyInDatabase(Series s) throws Exception {
//        Connection conn = dbp.getConnection();
//        Statement stat = conn.createStatement();
//        String sqlString = "SELECT * "
//                + "FROM serieslist "
//                + "WHERE UPPER(title) LIKE UPPER('" + s.getTitle() + "');";
//
//        ResultSet rs = stat.executeQuery(sqlString);
//
//        if (rs.next()) {
//            System.out.println(rs.toString());
//            while (rs.next()) {
//                //String title, String released, String type, String poster, String runtime, String director, String plot, double imdbRating
//                String titleM = rs.getString("title");
//                String released = rs.getString("released");
//                String typeM = rs.getString("type");
//                String poster = rs.getString("poster");
//                String runtime = rs.getString("runtime");
//                String director = rs.getString("director");
//                String plot = rs.getString("plot");
//                Double imdbRating = rs.getDouble("imdbRating");
//
//
//                s = new Series(titleM, released, typeM, poster, runtime, director, plot, imdbRating);
//
//                returnSeries(s);
//            }
//
//        } else {
//
//            insertSeries(s);
//        }
//
//        dbp.releaseConnection(conn);
//
//    }
    public Movie returnMovie(Movie m)
    {
        return m;
    }

    public Movie returnSeries(Movie s)
    {
        return s;
    }

    public void insertMovie(Movie m) throws SQLException
    {

        try
        {
            System.out.println("MOOOOOOVIE");
            Connection conn = dbp.getConnection();
            insertMovieStmt = map.get(conn);

            if (insertMovieStmt == null)
            {
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
        } catch (Exception ex)
        {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertSeries(Movie s)
    {
        try
        {
            System.out.println("SEEEEEEEEEERIES");
            Connection conn = dbp.getConnection();
            insertSeriesStmt = map.get(conn);

            if (insertSeriesStmt == null)
            {
                insertSeriesStmt = conn.prepareStatement(insertSeriesStr);
                map.put(con, insertSeriesStmt);
            }
            String title = s.getTitle();
            String released = s.getRelease();
            String type = s.getType();
            String runtime = s.getRuntime();
            String plot = s.getPlot();
            String imdbRating = s.getImdbRating() + "";
            String poster = s.getPoster();
            String director = s.getDirector();

            insertSeriesStmt.setString(1, title);
            insertSeriesStmt.setString(2, released);
            insertSeriesStmt.setString(3, type);
            insertSeriesStmt.setString(4, poster);
            insertSeriesStmt.setString(5, runtime);
            insertSeriesStmt.setString(6, director);
            insertSeriesStmt.setString(7, plot);
            insertSeriesStmt.setString(8, imdbRating);
            insertSeriesStmt.executeUpdate();

//            Statement stat = conn.createStatement();
//            String sqlString = "SELECT movieid \n"
//                    + "FROM movielist "
//                    + "WHERE title = '" + s.getTitle() + "';";
            seriesList.add(s);

            dbp.releaseConnection(conn);
        } catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
    }

    public void insertUser(User u) throws NoSuchAlgorithmException, Exception
    {
//        Connection conn = dbp.getConnection();
//        Statement stat = conn.createStatement();
//        String sqlString = "INSERT INTO userlist(username, password)"
//                + "VALUES('"+username+"', '"+createPasswordHash(password)+"');";
//
//
//        ResultSet rs = stat.executeQuery(sqlString);
//
//            while (rs.next()) {
//                //String title, String released, String type, String poster, String runtime, String director, String plot, double imdbRating
//                String passwordU = rs.getString("password");
//                String usernameU = rs.getString("usernameU");
//                User u = new User(passwordU, usernameU);
//                userlist.add(u);
//            }
//        dbp.releaseConnection(conn); 

        try
        {
            Connection conn = dbp.getConnection();
            insertUserStmt = map.get(conn);

            if (insertUserStmt == null)
            {
                insertUserStmt = conn.prepareStatement(insertUserStr);
                map.put(con, insertUserStmt);
            }
            String username = u.getUsername();
            String password = createPasswordHash(u.getPassword());

            insertUserStmt.setString(1, username);
            insertUserStmt.setString(2, password);

            insertUserStmt.executeUpdate();

            userlist.add(u);

            dbp.releaseConnection(conn);
        } catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
    }
    
    public boolean loginUser(String passwordStr, String username) throws NoSuchAlgorithmException, SQLException, Exception
    {
        Connection conn = dbp.getConnection();
        Statement stat = conn.createStatement();
        String password = createPasswordHash(passwordStr);
        String sqlString = "SELECT username, password "
                + "FROM userlist "
                + "WHERE username LIKE '" + username+ "' AND password LIKE '" + password+"'";

        ResultSet rs = stat.executeQuery(sqlString);


        if (rs.next()) {
            User user = new User(password, username);
            userlist.add(user);
            return true;
        }
        else
        {
            return false;
        }
    }

    public String createPasswordHash(String password) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : result)
        {
            sb.append(String.format("%02x", b & 0xff));
        }
        String resultStr = sb.toString();
        return resultStr;
    }

    public void getFromText(String path)
    {
        BufferedReader bufferedReader = null;

        String filePath = path;
        try
        {

            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;

            while (null != (line = bufferedReader.readLine()))
            {
                if (line.contains("("))
                {
                    String moviename = line.split("\\(")[0].trim().replace(" ", "+");
                    System.out.println(line);
                    String year = line.split("\\(")[1].trim().split("\\,")[0].trim().replace(")", "");
                    System.out.println(year);
                    System.out.println(moviename);
                    String url = "http://www.omdbapi.com/?t=" + moviename + "&y=" + year + "&plot=full&r=xml";
                    System.out.println(JAXB.unmarshal(url, Root.class));
                    if (JAXB.unmarshal(url, Root.class).getMovie() != null)
                    {

                        Root r = JAXB.unmarshal(url, Root.class);
                        if (!(r.getMovie().getImdbRating() + "").toUpperCase().contains("N/A"))
                        {
                            newEntererdMovies.add(r.getMovie());
                        }

                    }
                }

            }
            bufferedReader.close();
            insertAllMovies(newEntererdMovies);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public LinkedList<Movie> getRandomMoviePics()
    {
        try
        {
            Connection conn = dbp.getConnection();
            Statement stat = conn.createStatement();
            String sqlString = "SELECT * FROM movielist\n"
                    + "WHERE poster LIKE ('http%')\n"
                    + "ORDER BY RANDOM()\n"
                    + "LIMIT 20";

            ResultSet rs = stat.executeQuery(sqlString);

            if (rs.next())
            {
                Movie m;
                while (rs.next())
                {
                    //String title, String released, String type, String poster, String runtime, String director, String plot, double imdbRating
                    String titleM = rs.getString("title");
                    String released = rs.getString("released");
                    String typeM = rs.getString("type");
                    String poster = rs.getString("poster");
                    System.out.println(poster);
                    String runtime = rs.getString("runtime");
                    String director = rs.getString("director");
                    String plot = rs.getString("plot");
                    Double imdbRating = rs.getDouble("imdbrating");

                    m = new Movie(titleM, released, typeM, poster, runtime, director, plot, imdbRating);
                    showPosters.add(m);
                    System.out.println("Random");
                }
            }

            dbp.releaseConnection(conn);

        } catch (Exception ex)
        {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return showPosters;

    }

    public void insertAllMovies(LinkedList<Movie> movies)
    {
        try
        {
            Connection conn = dbp.getConnection();
            insertMovieStmt = map.get(conn);

            if (insertMovieStmt == null)
            {
                insertMovieStmt = conn.prepareStatement(insertMovieStr);
                map.put(con, insertMovieStmt);
            }

            for (Movie m : movies)
            {
                String title = m.getTitle();
                String released = m.getRelease();
                String type = m.getType();
                String runtime = m.getRuntime() + "";
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
            }

            dbp.releaseConnection(conn);
        } catch (Exception ex)
        {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
