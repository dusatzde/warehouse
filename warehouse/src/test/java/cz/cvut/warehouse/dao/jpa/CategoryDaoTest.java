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
import cz.cvut.warehouse.dao.CategoryDao;
import cz.cvut.warehouse.model.Category;
import cz.cvut.warehouse.services.Resources;
import cz.cvut.warehouse.test.util.Util;
import cz.cvut.warehouse.util.CategoryType;

@RunWith(Arquillian.class)
public class CategoryDaoTest {

	   @Inject
	   private CategoryDao service;
	   
	   @Deployment
	   public static JavaArchive createDeployment() {
	      return ShrinkWrap.create(JavaArchive.class, "categories.jar").
	    		  addClass(Resources.class).
	    		  addClass(Util.class).
	    		  addPackages(true, Category.class.getPackage()).
	    		  addPackages(true, CategoryDao.class.getPackage()).
	    		  addPackages(true, CategoryDaoImpl.class.getPackage()).
	    		  addAsManifestResource("META-INF/persistence.xml", "persistence.xml").
	    		  addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	   }
	   
	    @Test
	    public void callServiceToAddNewCategoryToDB() {
	    	Category cat = Util.initCategory(CategoryType.BOOKS);
	        service.create(cat);
	        Assert.assertNotNull( "Category id should not be null!", cat.getId());
	    }
	    
	    @Test
	    public void callServiceToReadCategoryFromDB(){
	    	Category cat = Util.initCategory(CategoryType.MUSIC);
	    	service.create(cat);
	    	Long id =  cat.getId();
	    	
	    	cat = service.read(id);
	    	Assert.assertNotNull( "Category should not be null!", cat);
	    }
	    
	    @Test
	    public void callServiceToDeleteCategoryFromDB(){
	    	Category cat = Util.initCategory(CategoryType.MOVIES);
	    	service.create(cat);
	    	Long id =  cat.getId();
	    	
	    	service.delete(id);
	        cat = service.read(id);
	    	Assert.assertNull( "Category id should be null!", cat);
	    }
	    
	    @Test
	    public void callServiceToUpdateCategoryInDB(){
	    	Category cat = Util.initCategory(CategoryType.BOOKS);
	    	service.create(cat);
	    	Long id =  cat.getId();
	    	
	    	String _description = "updated description";
	    	cat.setDecription(_description);
	    	service.update(cat);
	    	cat = service.read(id);
	    	Assert.assertEquals(_description, cat.getDecription());    	
	    }
	    
	    @Test
	    public void callServiceToFingCategoryByNameFormDB(){
	    	Category cat = Util.initCategory(CategoryType.MUSIC);
	    	service.create(cat);
	    	cat = service.find(CategoryType.MUSIC);
	    	Assert.assertEquals(CategoryType.MUSIC, cat.getName());
	    }
}
