package domain.dao;

import domain.model.dto.ProductDto;

import java.util.List;

public interface ProductDao extends BaseDao {
    List<ProductDto> getAllProducts();
}
