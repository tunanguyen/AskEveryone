/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.AbstractEntity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Apollo
 */
public abstract class AbstractDAO<E extends AbstractEntity> {

    private static final Connection CONNECTION = DataAccess.getConnection();

    protected Connection getConnection() {
        return CONNECTION;
    }

    protected void closeConnection() throws Exception {
        DataAccess.closeConnection();
    }

    protected abstract E castFromDB(ResultSet record);

    protected List<E> castToList(ResultSet resultSet) throws SQLException {
        List<E> result = new ArrayList<>();
        while (resultSet.next()) {
            E e = castFromDB(resultSet);
            result.add(e);
        }
        return result;
    }
}
