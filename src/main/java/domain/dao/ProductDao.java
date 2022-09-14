package domain.dao;

import domain.model.Category;
import domain.model.Product;

import java.util.List;

public interface ProductDao extends BaseDao {
    List<Product> getProductsByCategory(String category);

}
