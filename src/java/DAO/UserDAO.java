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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ngocn
 */
public class UserDAO extends AbstractDAO<User> {

    private static final UserDAO INSTANCE = new UserDAO();
    private static final String USERNAME = "userName";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    String sqlStatement = null;

    private UserDAO() {

    }

    public static UserDAO getInstance() {
        return INSTANCE;
    }

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

    public User getAccount(User user) throws SQLException {
        sqlStatement = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
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

    public boolean createAccount(User user) {
        sqlStatement = "INSERT INTO USERS(USERNAME,PASSWORD,EMAIL) VALUES (?,?,?)";
        PreparedStatement ps;
        try {
            ps = getConnection().prepareStatement(sqlStatement);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean cheackUsernameExist(String username) throws SQLException {
        sqlStatement = "SELECT * FROM USERS WHERE USERNAME = ?";
        PreparedStatement ps = getConnection().prepareStatement(sqlStatement);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        int check = 0;
        while (rs.next()) {
            check++;
        }
        if (check == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean cheackEmailExist(String email) throws SQLException {
        sqlStatement = "SELECT * FROM USERS WHERE EMAIL = ?";
        PreparedStatement ps = getConnection().prepareStatement(sqlStatement);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        int check = 0;
        while (rs.next()) {
            check++;
        }
        if (check == 0) {
            return true;
        } else {
            return false;
        }
    }
}
