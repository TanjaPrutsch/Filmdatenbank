/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    private final HashMap<Connection, PreparedStatement> map = new HashMap<>();

    public static DBAccess getInstance() throws IOException, ClassNotFoundException, SQLException, Exception
    {
        if (theInstance == null)
        {
            theInstance = new DBAccess();
        }
        return theInstance;
    }

    //Prepared-Statements
    private DBAccess() throws IOException, ClassNotFoundException, SQLException, Exception
    {
        this.dbp = DBConnectionPool.getInstance();

    }
    //private PreparedStatement insertBookStmt = null;
  
   

}
