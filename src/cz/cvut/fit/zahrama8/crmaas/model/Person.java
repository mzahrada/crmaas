package cz.cvut.fit.zahrama8.crmaas.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {

	@Id
	Long id;
	String firstName;
	String lastName;
	
	public Person() {}
		
	public Person(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
