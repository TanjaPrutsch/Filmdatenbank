/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

/**
 * This class shows password and username of a user.
 */
public class User
{

    private String password;
    private String username;

    /**
     * @param password String (password of the user)
     * @param username String (username of the user)
     */
    public User(String password, String username)
    {
        this.password = password;
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

}
