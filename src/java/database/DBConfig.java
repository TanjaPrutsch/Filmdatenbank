/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *This config file stores all the static parameters used to make a connection.
 */
public interface DBConfig
{
    public static final String DB_NAME ="movie";
    public static final String DB_USER ="postgres";
    public static final String DB_PASSWD ="postgres";
    public static final String DB_URL ="jdbc:postgresql://localhost/";
    public static final String DB_DRIVER ="org.postgresql.Driver";
}
