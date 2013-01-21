package cz.cvut.warehouse.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.inject.Inject;
import javax.inject.Named;

import cz.cvut.warehouse.dao.ProductDao;
import cz.cvut.warehouse.model.Product;

@Named("books")
@RequestScoped
public class Books {

	private List<Product> books;

	@Inject
	private ProductDao productManager;
	
	
	@PostConstruct
	@SuppressWarnings("unused")
	private void init(){
		books = productManager.getProducts(CategoryType.BOOKS);
	}


	public List<Product> getBooks() {
		return books;
	}


	public void setBooks(List<Product> books) {
		this.books = books;
	}
}
