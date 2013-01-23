package cz.cvut.warehouse.dao.jpa;

import java.io.Serializable;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.Clustered;

import cz.cvut.warehouse.dao.CategoryDao;
import cz.cvut.warehouse.model.Category;

@Stateless(name="categoryDaoImpl")
@Clustered
public class CategoryDaoImpl extends GenericDaoJPAImpl<Category, Serializable> implements CategoryDao{

	@Override
	public Category find(String name) {
		return (Category) em.createNamedQuery("Category.findByName")
							.setParameter("name", name)
							.getSingleResult();

	}

}
