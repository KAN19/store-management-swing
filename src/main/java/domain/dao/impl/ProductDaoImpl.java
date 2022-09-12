package domain.dao.impl;

import domain.dao.ProductDao;
import domain.model.Category;
import domain.model.dto.ProductDto;
import domain.model.dto.UserDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<ProductDto> getAllProducts() {
        PreparedStatement statement = null;
        List<ProductDto> productsList;
        try {
            statement = connection
                    .prepareStatement("Select *, product.id as product_id from category, product " +
                            "where category.id = product.category_id");
            ResultSet result = statement.executeQuery();

            productsList = new ArrayList<>();

            while (result.next()) {
                Category category = new Category(
                        result.getLong("category_id"),
                        result.getString("category_name"));

                ProductDto productDto = new ProductDto();
                productDto.setProductName(result.getString("product_name"));
                productDto.setCategory(category);
                productDto.setPrice(result.getDouble("price"));
                productDto.setId(result.getLong("product_id"));

                productsList.add(productDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productsList;
    }

    @Override
    public List<ProductDto> getProductsByCategory(Category searchingCategory) {
        PreparedStatement statement = null;
        List<ProductDto> productsList;
        try {
            statement = connection
                    .prepareStatement("Select *, product.id as product_id from category, product " +
                            "where category.id = product.category_id AND product.category_id = ?");
            statement.setLong(1, searchingCategory.getCategoryId());
            ResultSet result = statement.executeQuery();

            productsList = new ArrayList<>();

            while (result.next()) {
                Category category = new Category(
                        result.getLong("category_id"),
                        result.getString("category_name"));

                ProductDto productDto = new ProductDto();
                productDto.setProductName(result.getString("product_name"));
                productDto.setCategory(category);
                productDto.setPrice(result.getDouble("price"));
                productDto.setId(result.getLong("product_id"));

                productsList.add(productDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productsList;
    }
}
