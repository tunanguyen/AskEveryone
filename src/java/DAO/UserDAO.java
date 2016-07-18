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
    private static final String GET_ACCOUNT_SQL = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";

    public User getAccount(User user) throws SQLException {
        User rsUser = null;
        PreparedStatement ps = getConnection().prepareStatement(GET_ACCOUNT_SQL);
        ps.setString(1, user.getUserName());
        ps.setString(2, user.getPassword());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            rsUser = castFromDB(rs);
        }
        return rsUser;
    }
    private static final String CREATE_ACCOUNT_SQL = "INSERT INTO USERS(USERNAME,PASSWORD,EMAIL) VALUES (?,?,?)";

    public boolean createAccount(User user) {
        PreparedStatement ps;
        try {
            ps = getConnection().prepareStatement(CREATE_ACCOUNT_SQL);
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
    private static final String CHECK_USERNAME_SQL = "SELECT * FROM USERS WHERE USERNAME = ?";

    public boolean cheackUsernameExist(String username) throws SQLException {
        PreparedStatement ps = getConnection().prepareStatement(CHECK_USERNAME_SQL);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next() == false) {
            return false;
        } else {
            return true;
        }
    }
    private static final String CHECK_EMAIL_SQL = "SELECT * FROM USERS WHERE EMAIL = ?";

    public boolean cheackEmailExist(String email) throws SQLException {
        PreparedStatement ps = getConnection().prepareStatement(CHECK_EMAIL_SQL);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next() == false) {
            return false;
        } else {
            return true;
        }
    }
}
