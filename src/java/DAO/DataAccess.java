/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Mikey Nguyen
 */
public class DataAccess {

    Connection conn = null;
    Statement state = null;
    ResultSet result = null;
    PreparedStatement pre = null;

    public DataAccess() throws Exception {
        //1. load jdbc driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2. open db connection - connection string
        String url = "jdbc:sqlserver://localhost:1433;databaseName=PUBDB";
        conn = DriverManager.getConnection(url, "sa", "123456789");
    }

    //close db connetion
    public void closeConnection() throws Exception {
        if (conn != null) {
            conn.close();
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
