/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project;
import java.sql.*;
/**
 *
 * @author fajri
 */
public class ConnectionProvider {    
public static Connection getCon()
{
    try{
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/qems", "root", "");
        return con;
    }
    catch(Exception e)
    {
        return null;
    }
}



    public static Connection getConnection() {
        // call getCon() method to return connection.
        return getCon();
    }
}
