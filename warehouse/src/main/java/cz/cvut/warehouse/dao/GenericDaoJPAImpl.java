package cz.cvut.warehouse.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public abstract class GenericDaoJPAImpl <T, PK extends Serializable> implements GenericDao<T, PK>{

	private Class<T> entityClass  = GenericsUtils.getSuperClassGenericType (this.getClass());
	
	@Inject
	protected EntityManager em;
	 
	@Override
	public void create(T entity) {
		em.persist(entity);
	}

	@Override
	public T read(PK id) {
		 return em.find(entityClass,id);

	}

	@Override
	public void update(T entity) {
		em.merge(entity);
		
	}

	@Override
	public void delete(T entity) {
		em.merge(entity);
		
	}

}
