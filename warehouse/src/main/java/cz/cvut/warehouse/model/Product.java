package cz.cvut.warehouse.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.codingcrayons.jformbuilder.annotations.UiOrder;

@Entity
@Table(name = "product")
public class Product extends EntityObject{

	private static final long serialVersionUID = -2209693898441797165L;
	
	private String name;
	private String description;
	private Double price;
	private int count;
	
	private Category category;
	private List<Order> orders = new ArrayList<>();
	
	@Column(name="name")
	@NotNull
	@UiOrder(1)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="description")
	@UiOrder(4)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="price")
	@NotNull
	@UiOrder(2)
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Column(name="count")
	@NotNull
	@UiOrder(3)
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

	@ManyToOne( fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToMany(mappedBy="products")
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
