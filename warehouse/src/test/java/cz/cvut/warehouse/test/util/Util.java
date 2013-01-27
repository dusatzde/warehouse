package cz.cvut.warehouse.test.util;

import cz.cvut.warehouse.dao.UserDao;
import cz.cvut.warehouse.model.Address;
import cz.cvut.warehouse.model.Category;
import cz.cvut.warehouse.model.UserEntity;
import cz.cvut.warehouse.util.RoleType;

public class Util {
	
	public static Address initAddress(){
    	Address adr = new Address();
    	adr.setBuildingNumber("323/3");
    	adr.setCity("Prague");
    	adr.setCountry("CZ");
    	adr.setStreet("Kralicka");
    	adr.setZipCode("100 00");

        return adr;
    }
	
	public static Long insertUser(UserEntity user, UserDao service){
    	service.create(user);
    	return user.getId();
    }
    
	public static UserEntity initUser(String username){
    	UserEntity user = new UserEntity();
        user.setEmail(username);
        user.setFirstname("firstname-test");
        user.setLastname("lastname-test");
        user.setPassword("password-test");
        user.setPhone("489392929");
        user.setRole(RoleType.MANAGER);
        user.getAddress().setBuildingNumber("323/3");
        user.getAddress().setCity("Prague");
        user.getAddress().setCountry("CZ");
        user.getAddress().setStreet("Kralicka");
        user.getAddress().setZipCode("100 00");
    	
        return user;
    }
	
	public static Category initCategory(String name){
		Category cat = new Category();
		cat.setName(name);
		cat.setDecription("Description description");
		return cat;
	}

}
