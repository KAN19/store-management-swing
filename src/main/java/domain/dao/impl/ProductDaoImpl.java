package domain.dao.impl;

import domain.dao.ProductDao;
import domain.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> getProductsByCategory(String category) {
        List<Product> products = getAllProductsFromFiles()
                .stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());

        return products;
    }
}
