package es.deusto.bspq.cinema.server.jdo.data;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class Employee {
	
	@PrimaryKey
	private String username;
	private String name;
	private String surname;
	private String password;
	private float salary;
	
	public Employee() {
	
	}

	public Employee(String username, String name, String surname, String password, float salary) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.salary = salary;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	
	
	
	

}
