package cz.cvut.fit.zahrama8.crmaas.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import cz.cvut.fit.zahrama8.crmaas.dao.DaoManagerFactory;
import cz.cvut.fit.zahrama8.crmaas.dao.DaoManagerFactory.PersonDao;
import cz.cvut.fit.zahrama8.crmaas.model.Person;

@ManagedBean(name="person")
@RequestScoped
//@Named("person")
public class PersonManagedBean {

//	@Inject @Dao(Dao.Type.PERSON)
	private PersonDao personDao;
	
	private String firstName;
	
	@PostConstruct
	public void init() {
		personDao = DaoManagerFactory.getPersonDao();
//		person = new Person();
	}
	
	public List<Person> getPersonList(){
		return personDao.listAll();
	}
	
	public String savePerson(){
		System.out.println("saving person");
		Person p = new Person();
		p.setFirstName(firstName);
		FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage("Person successfully created", firstName));
		
		personDao.put(p);
		return "/faces/hello?faces-redirect=true";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
}
