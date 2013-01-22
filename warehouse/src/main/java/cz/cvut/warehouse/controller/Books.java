package cz.cvut.warehouse.controller;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import cz.cvut.warehouse.controller.qualifiers.NewOrder;
import cz.cvut.warehouse.dao.ProductDao;
import cz.cvut.warehouse.model.Order;
import cz.cvut.warehouse.model.Product;
import cz.cvut.warehouse.util.CategoryType;
import cz.cvut.warehouse.util.UIDGenerator;

@Named("books")
@RequestScoped
public class Books {

	private List<Product> books;
	
	@Inject
	@NewOrder
	private Order order;
	

	@Inject
	private ProductDao productManager;

	@PostConstruct
	@SuppressWarnings("unused")
	private void init(){
		books = productManager.getProducts(CategoryType.BOOKS);
		order.setUid(UIDGenerator.getRandomUID());
		order.setDate(new Date());
	}
	
	public void buy(Product product){
		order.getUid();
		order.getProducts().add(product);
		Double price = order.getTotalPrice();
		price += product.getPrice();
		order.setTotalPrice(price);
	}


	public List<Product> getBooks() {
		return books;
	}


	public void setBooks(List<Product> books) {
		this.books = books;
	}

}
