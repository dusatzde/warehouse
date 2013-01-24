package cz.cvut.warehouse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.codingcrayons.jformbuilder.annotations.UiOrder;

@Entity
@Table(name = "address")
@NamedQueries({@NamedQuery(name="Address.findByUser", query="SELECT a FROM Address a WHERE a.user.id = :user")})
public class Address extends EntityObject{

	private static final long serialVersionUID = -2412481475801479420L;
	
	private String city;
	private String street;
	private String buildingNumber;
	private String zipCode;
	private String country;
	
	private UserEntity user;
	
	
	@Column(name = "city")
	@UiOrder(1)
	@NotNull
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "street")
	@NotNull
	@UiOrder(2)
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	@Column(name = "bnr")
	@NotNull
	@UiOrder(3)
	public String getBuildingNumber() {
		return buildingNumber;
	}
	
	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}
	
	@Column(name = "zip")
	@NotNull
	@UiOrder(4)
	public String getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Column(name = "country")
	@UiOrder(5)
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

	@OneToOne(mappedBy = "address")
	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	

}
