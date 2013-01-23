package cz.cvut.warehouse.dao.jpa;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.Clustered;

import cz.cvut.warehouse.dao.ProductDao;
import cz.cvut.warehouse.model.Product;

@Stateless(name="productDaoImpl")
@Clustered
public class ProductDaoImpl extends GenericDaoJPAImpl<Product, Serializable> implements ProductDao{

	@Override
	public List<Product> getProducts(String categoryName) {
		List<Product> result=  em.createNamedQuery("Product.findByCategory", Product.class)
					.setParameter("name", categoryName)
					.getResultList();
		
		return result;
	}

}
