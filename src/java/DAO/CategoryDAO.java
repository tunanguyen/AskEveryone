/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Apollo
 */
public class CategoryDAO extends AbstractDAO<Category> {

    private static final String CATEGORY_ID = "categoryId";
    private static final String CATEGORY_NAME = "categoryName";

    @Override
    protected Category castFromDB(ResultSet record) {
        if (record == null) {
            return null;
        }
        Category category = new Category();
        try {
            category.setCategoryId(record.getInt(CATEGORY_ID));
            category.setCategoryName(record.getString(CATEGORY_NAME).trim());
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return category;
    }

    private static final String LIST_ALL_CATEGORIES
            = "SELECT * FROM CATEGORIES";

    public List<Category> listAllCategories() throws SQLException {
        List<Category> result;
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(LIST_ALL_CATEGORIES);
            result = castToList(resultSet);
        }
        return result;
    }

}
