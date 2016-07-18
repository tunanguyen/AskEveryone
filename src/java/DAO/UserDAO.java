/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ngocn
 */
public class UserDAO extends AbstractDAO<User> {

    private static final String USERNAME = "userName";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    String sqlStatement = null;
    @Override
    protected User castFromDB(ResultSet record) {
        if (record == null) {
            return null;
        }
        User user = new User();
        try {
            user.setUserName(record.getString(USERNAME).trim());
            user.setPassword(record.getString(PASSWORD).trim());
            user.setEmail(record.getString(EMAIL).trim());
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return user;
    }
    
    public User checkLogin(User user) throws SQLException{
        sqlStatement = "SELECT * FROM Users WHERE userName = ? AND password = ?";
        User rsUser = null;
        PreparedStatement ps = getConnection().prepareStatement(sqlStatement);
        ps.setString(1, user.getUserName());
        ps.setString(2, user.getPassword());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            rsUser = castFromDB(rs);
        }
        return rsUser;
    }
    public static void main(String[] args) throws SQLException {
        UserDAO userDAO = new UserDAO();
        User user = new User();
        user = userDAO.checkLogin(new User("admin", "admin", ""));
        System.out.println(user.getUserName() + "_____________");
    }
}
