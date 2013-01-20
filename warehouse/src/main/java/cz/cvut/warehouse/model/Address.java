package cz.cvut.warehouse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.codingcrayons.jformbuilder.annotations.UiOrder;

@Entity
@Table(name = "address")
public class Address extends EntityObject{

	private static final long serialVersionUID = -2412481475801479420L;
	
	private String city;
	private String street;
	private String buildingNumber;
	private String ZIPCode;
	private String Country;
	
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
	public String getZIPCode() {
		return ZIPCode;
	}
	
	public void setZIPCode(String zIPCode) {
		ZIPCode = zIPCode;
	}
	
	@Column(name = "country")
	@UiOrder(5)
	public String getCountry() {
		return Country;
	}
	
	public void setCountry(String country) {
		Country = country;
	}

	@OneToOne(mappedBy = "address")
	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	

}
