package domain.dao.impl;

import domain.dao.CategoryDao;
import domain.model.Category;
import domain.model.dto.ProductDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> getCategories() {
        PreparedStatement statement = null;
        List<Category> categoryList;
        try {
            statement = connection
                    .prepareStatement("Select * FROM category");
            ResultSet result = statement.executeQuery();

            categoryList = new ArrayList<>();

            while (result.next()) {
                categoryList.add(
                        new Category(
                                result.getLong("id"),
                                result.getString("category_name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryList;
    }
}
