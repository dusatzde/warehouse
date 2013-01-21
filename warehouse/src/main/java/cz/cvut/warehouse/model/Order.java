package cz.cvut.warehouse.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.codingcrayons.jformbuilder.annotations.UiIgnore;
import com.codingcrayons.jformbuilder.annotations.UiOrder;

@Entity
@Table(name = "orderentity")
public class Order extends EntityObject{

	private static final long serialVersionUID = 2478854019818433144L;
	
	private String uid;
	private double totalPrice;
	private Date date;
	private String state;
	
	private UserEntity user;
	private List<Product> products = new ArrayList<>();
	
	@Column(name="uid")
	@NotNull
	@UiOrder(1)
	public String getUid() {
		return uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	@Column(name="price")
	@NotNull
	@UiOrder(3)
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Column(name="date")
	@NotNull
	@UiOrder(2)
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name="state")
	@NotNull
	@UiIgnore
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
	
	

}
