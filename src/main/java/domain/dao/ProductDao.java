package domain.dao;

import domain.model.Category;
import domain.model.dto.ProductDto;

import java.util.List;

public interface ProductDao extends BaseDao {
    List<ProductDto> getAllProducts();
    List<ProductDto> getProductsByCategory(Category category);
}
