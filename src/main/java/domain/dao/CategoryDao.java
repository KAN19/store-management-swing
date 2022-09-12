package domain.dao;

import domain.model.Category;

import java.util.List;

public interface CategoryDao extends BaseDao{

    List<Category> getCategories();
}
