package cz.cvut.fit.zahrama8.crmaas.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.googlecode.objectify.Key;

@Entity
public class Customer {
	
	@Id
	Long id;
	String name;
	Key<Person> persons;
	
	public Customer() {}

	public Customer(String name, Key<Person> persons) {
		super();
		this.name = name;
		this.persons = persons;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Key<Person> getPersons() {
		return persons;
	}

	public void setPersons(Key<Person> persons) {
		this.persons = persons;
	}
	
	

}
