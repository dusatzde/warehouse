package cz.cvut.warehouse.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.inject.Inject;
import javax.inject.Named;
import cz.cvut.warehouse.dao.CategoryDao;
import cz.cvut.warehouse.dao.ProductDao;
import cz.cvut.warehouse.model.Category;
import cz.cvut.warehouse.model.Product;

@Named("product")
@RequestScoped
public class ProductController extends BaseController {

	private static final long serialVersionUID = 5586140520097761015L;

	@Inject
	private CategoryDao categoryManager;
	
	@Inject
	private ProductDao productManager;

	private UIComponent registerButton;
	private Product product = new Product();
	
	public void add(String categoryName){
		Category category = categoryManager.find(categoryName);
		product.setCategory(category);
		productManager.create(product);
		product = new Product();
		initInfoMessage(registerButton, "OK", "A product has been added successfully.");
	}
	
	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}



	public UIComponent getRegisterButton() {
		return registerButton;
	}



	public void setRegisterButton(UIComponent registerButton) {
		this.registerButton = registerButton;
	}


	
	

}
