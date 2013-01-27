package cz.cvut.warehouse.dao.jpa;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import cz.cvut.warehouse.dao.AddressDao;
import cz.cvut.warehouse.dao.UserDao;
import cz.cvut.warehouse.model.Address;
import cz.cvut.warehouse.services.Resources;
import cz.cvut.warehouse.test.util.Util;

@RunWith(Arquillian.class)
public class AddressDaoTest {

	@Inject
	private AddressDao service;
	
	@Inject
	private UserDao userService;
	
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class, "address.jar")
				.addClass(Resources.class)
				.addClass(Util.class)
				.addPackages(true, Address.class.getPackage())
				.addPackages(true, AddressDao.class.getPackage())
				.addPackages(true, AddressDaoImpl.class.getPackage())
				.addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	 @Test
	    public void callServiceToAddNewAddressToDB() {
	    	Address adr = Util.initAddress();
	        service.create(adr);
	        Assert.assertNotNull( "Address id should not be null!", adr.getId());
	    }
	    
	    @Test
	    public void callServiceToReadAddressFormDB(){
	    	Address adr = Util.initAddress();
	        service.create(adr);
	        Long id = adr.getId();
	    	adr = service.read(id);
	    	Assert.assertNotNull( "Address should not be null!", adr);
	    }
	    
	    
	    @Test
	    public void callServiceToUpdateAddressInDB(){
	    	Address adr = Util.initAddress();
	        service.create(adr);
	    	String _city = "updated city";
	    	adr.setCity(_city);
	    	Long id = adr.getId();
	    	service.update(adr);
	    	adr = service.read(id);
	    	Assert.assertEquals(_city, adr.getCity());    	
	    }
	    
	    @Test
	    public void callServiceToDeleteAddressFormDB(){
	    	Address adr = Util.initAddress();
	        service.create(adr);
	    	Long id = adr.getId();
	    	service.delete(id);
	    	adr = service.read(id);
	    	Assert.assertNull( "Address should be null!", adr);
	    }
	    
	    @Test
	    public void callServiceToFindAddressByUserFormDB(){
	    	Long id = Util.insertUser(Util.initUser("Jonh"), userService);
	    	Address adr = service.getAddress(id);
	    	Assert.assertNotNull( "Address should be null!", adr);
	    }
	    
}
