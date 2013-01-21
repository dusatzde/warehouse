package cz.cvut.warehouse.dao;

import java.io.Serializable;
import java.util.List;

import cz.cvut.warehouse.model.Product;

public interface ProductDao extends GenericDao<Product, Serializable>{

	public List<Product> getProducts(String categoryName);
}
