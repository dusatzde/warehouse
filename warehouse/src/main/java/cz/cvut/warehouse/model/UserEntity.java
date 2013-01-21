package cz.cvut.warehouse.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.codingcrayons.jformbuilder.annotations.UiOrder;
import com.codingcrayons.jformbuilder.annotations.UiPassword;

@Entity
@Table(name = "userentity")
public class UserEntity extends EntityObject{

	private static final long serialVersionUID = 5241886855226308491L;
	
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String phone;
	private String role;
	
	private Address address = new Address();
	private List<Order> orders= new ArrayList<>();
	
	
	@Column(name = "firstname")
	@UiOrder(2)
	@NotNull
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	@Column(name = "lastname")
	@UiOrder(3)
	@NotNull
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	@Column(name = "email")
	@UiOrder(1)
	@NotNull
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Column(name = "password")
	@UiOrder(4)
	@NotNull
	@UiPassword
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "phone")
	@UiOrder(5)
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "role")
	@NotNull
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@OneToMany(mappedBy="user")
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	

}
