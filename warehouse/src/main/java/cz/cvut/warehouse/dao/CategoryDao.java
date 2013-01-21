package cz.cvut.warehouse.dao;

import java.io.Serializable;

import cz.cvut.warehouse.model.Category;
import cz.cvut.warehouse.model.UserEntity;

public interface CategoryDao extends GenericDao<Category, Serializable> {
		
		Category find(String name);
}
