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
import cz.cvut.warehouse.dao.UserDao;
import cz.cvut.warehouse.model.UserEntity;
import cz.cvut.warehouse.services.Resources;
import cz.cvut.warehouse.test.util.Util;
import cz.cvut.warehouse.util.RoleType;

@RunWith(Arquillian.class)
public class UserDaoTest {
	
	   @Inject
	   private UserDao service;
	   
	   @Deployment
	   public static JavaArchive createDeployment() {
	      return ShrinkWrap.create(JavaArchive.class, "users.jar").
	    		  addClass(Resources.class).
	    		  addClass(Util.class).
	    		  addPackages(true, UserEntity.class.getPackage()).
	    		  addPackages(true, UserDao.class.getPackage()).
	    		  addPackages(true, UserDaoImpl.class.getPackage()).
	    		  addAsManifestResource("META-INF/persistence.xml", "persistence.xml").
	    		  addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	   }
	   
	    @Test
	    public void callServiceToAddNewUserToDB() {
	    	UserEntity user = Util.initUser("admin");
	        service.create(user);
	        Assert.assertNotNull( "User id should not be null!", user.getId());
	    }
	    
	    @Test
	    public void callServiceToReadUserFormDB(){
	    	Long id = Util.insertUser(Util.initUser("john"), service);
	    	UserEntity u = service.read(id);
	    	Assert.assertNotNull( "User should not be null!", u);
	    }
	    
	    @Test
	    public void callServiceToDeleteUserFormDB(){
	    	Long id = Util.insertUser(Util.initUser("temp-user"), service);
	    	UserEntity u = service.read(id);
	    	service.delete(u.getId());
	        u = service.read(id);
	    	Assert.assertNull( "User id should be null!", u);
	    }
	    
	    @Test
	    public void callServiceToUpdateUserInDB(){
	    	Util.insertUser(Util.initUser("manager"), service);
	    	UserEntity u = service.getUser("manager");
	    	String _firstname = "updated firstname";
	    	u.setFirstname(_firstname);
	    	service.update(u);
	    	u = service.getUser("manager");
	    	Assert.assertEquals(_firstname, u.getFirstname());    	
	    }
	    
	    @Test
	    public void callServiceToFindUserByUsernameFormDB(){
	    	Util.insertUser(Util.initUser("test-user"), service);
	    	UserEntity u = service.getUser("test-user");
	    	Assert.assertNotNull( "User should not be null!", u);
	    }
	    
	    @Test
	    public void callServiceToFindUsersByRoleFormDB(){
	    	Util.insertUser(Util.initUser("manager-user"), service);
	    	int size = service.getUsers(RoleType.MANAGER).size();
	    	Assert.assertTrue(size > 0);
	    }
}
