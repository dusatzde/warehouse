package cz.cvut.warehouse.dao.jpa;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import cz.cvut.warehouse.dao.ProductDao;
import cz.cvut.warehouse.model.Product;

@Stateless(name="productDaoImpl")
public class ProductDaoImpl extends GenericDaoJPAImpl<Product, Serializable> implements ProductDao{

	@Override
	public List<Product> getProducts(String categoryName) {
		List<Product> result=  em.createNamedQuery("Product.findByCategory", Product.class)
					.setParameter("name", categoryName)
					.getResultList();
		
		return result;
	}

}
