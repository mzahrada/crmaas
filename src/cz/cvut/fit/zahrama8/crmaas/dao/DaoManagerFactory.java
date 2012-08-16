package cz.cvut.fit.zahrama8.crmaas.dao;

import com.googlecode.objectify.ObjectifyService;

import cz.cvut.fit.zahrama8.crmaas.model.Customer;
import cz.cvut.fit.zahrama8.crmaas.model.Person;

public class DaoManagerFactory {
	
	// Entity-specific versions of each Generic interface
	public interface PersonDao extends GenericDAO<Person> {}
	public interface CustomerDao extends GenericDAO<Customer> {}
	
	// static missing
//	@Dao(Dao.Type.PERSON)
//	@Named("personDao")
	private static class PersonDaoImpl extends ObjectifyGenericDao<Person> implements PersonDao {}
	private static class CustomerDaoImpl extends ObjectifyGenericDao<Customer> implements CustomerDao {}
	
	static {
		ObjectifyService.register(Customer.class);
		ObjectifyService.register(Person.class);
	}
	
	// Static-only usage pattern
	protected DaoManagerFactory() {}
	
	public static PersonDao getPersonDao() {		// static missing
		return new PersonDaoImpl();
	}
	public static CustomerDao getCustomerDao() {	// static missing
		return new CustomerDaoImpl();
	}
	
	// usage 
//	01	// Create new user
//	02	User u = new User();
//	03	u.setEmailAddress("test@example.com");
//	04	u.setFirstName("Test");
//	05	u.setLastName("User");
//	06	u.setGoogleAccountId("testAccountId");
//	07	UserDao userDao = new UserDao();
//	08	OKey<User> key = userDao.add(u);
//	09	 
//	10	// Retrieve user by ID
//	11	dao.get(u.getId());
//	12	 
//	13	// Find users matching an email address
//	14	users = dao.listByProperty("emailAddress", "test@example.com");
	
//	And finally, my generic base DAO (a work in progress, caveat emptor). 
//	Note that I’ve created a couple methods to allow query by example, 
//	which I generally prefer to enumerating methods for every use case 
//	(getByEmail, getByLastName, getByFirstAndLastName, etc.). The getByProperty and 
//	listByProperty methods take a single property name and value on which to filter, 
//	and getByExample / listByExample allow you to specify multiple property names on which to filter.
}
