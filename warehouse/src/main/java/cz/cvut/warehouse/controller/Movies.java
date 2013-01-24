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

@Named("movies")
@RequestScoped
public class Movies extends BaseController {

	private static final long serialVersionUID = 8453056775844274647L;

	private List<Product> movies;
	
	@Inject
	@NewOrder
	private Order order;
	

	@Inject
	private ProductDao productManager;

	@PostConstruct
	@SuppressWarnings("unused")
	private void init(){
		setMovies(productManager.getProducts(CategoryType.MOVIES));
		order.setUid(UIDGenerator.getRandomUID());
		order.setDate(new Date());
	}
	
	public void buy(Product product){
		order.getUid();
		order.getProducts().add(product);
		Double price = order.getTotalPrice();
		price += product.getPrice();
		price = roundTwoDecimals(price);
		order.setTotalPrice(price);
	}

	public List<Product> getMovies() {
		return movies;
	}

	public void setMovies(List<Product> movies) {
		this.movies = movies;
	}
}
