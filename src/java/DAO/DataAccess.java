/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mikey Nguyen
 */
public class DataAccess {
    
    private static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_CONNECTION = "jdbc:sqlserver://localhost:1433;databaseName=PUBDB";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "sa";

    private static Connection connection;

    static {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    //close db connetion
    public static void closeConnection() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    //add new student to Student table
//    public int addNewPub(Pub p) throws Exception {
//        String sqlInsert = "INSERT INTO Publishers(pub_id, pub_name, city, country, isActivated) VALUES(?,?,?,?,?)";
//        PreparedStatement ps = conn.prepareStatement(sqlInsert);
//        //specify the value of parameter
//        ps.setString(1, p.getPubid());
//        ps.setString(2, p.getPubname());
//        ps.setString(3, p.getCity());
//        ps.setString(4, p.getCountry());
//        ps.setInt(5, p.getActivate());
//        //send sqlInsert to database server and execute
//        return ps.executeUpdate();  // delete, update, insert
//    }
}
