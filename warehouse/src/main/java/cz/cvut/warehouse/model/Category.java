package cz.cvut.warehouse.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "category")
@NamedQueries({@NamedQuery(name="Category.findByName", query="SELECT c FROM Category c WHERE c.name = :name") })
public class Category extends EntityObject {

	private static final long serialVersionUID = 1548833429458482755L;
	
	private String name;
	private String decription;
	
	private List<Product> products = new ArrayList<>();
	
	@Column(name="name")
	@NotNull
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="decription")
	public String getDecription() {
		return decription;
	}
	
	public void setDecription(String decription) {
		this.decription = decription;
	}

	@OneToMany(mappedBy="category")
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	

}
