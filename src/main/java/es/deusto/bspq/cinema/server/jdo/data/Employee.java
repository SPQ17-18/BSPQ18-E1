package es.deusto.bspq.cinema.server.jdo.data;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

/**
 * Class for the employee
 * @author anderarguinano
 *
 */
@PersistenceCapable(detachable = "true")
public class Employee {
	
	@PrimaryKey
	private String username;
	
	private String name;
	private String surname;
	private String password;
	private float salary;
	
	/**
	 * Empty constructor for the employee
	 */
	public Employee() {
	
	}
	
	/**
	 * Constructor for the Employee
	 * @param username Username of the employee
	 * @param name Name of the employee
	 * @param surname Surname of the employee
	 * @param password Password of the employee
	 * @param salary Salary of the employee
	 */

	public Employee(String username, String name, String surname, String password, float salary) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.salary = salary;
	}

	/**
	 * Method for obtaining the username
	 * @return Returns the username of the employee
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Method for setting the username
	 * @param username New username for the employee
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Method for getting the name of the employee
	 * @return Returns the name of the employee
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method for setting the name of the employee
	 * @param name New name for the employee
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method for getting the surname of the user
	 * @return Returns the surname of the employee
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Method for setting the surname of the employee
	 * @param surname New surname of the employee
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Method for getting the password of the employee
	 * @return Returns the password of the employee
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Method for setting the password of the employee
	 * @param password New password for the employee
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Method for getting the salary of the employee
	 * @return Returns the salary of the employee
	 */
	public float getSalary() {
		return salary;
	}

	/**
	 * Method for setting the salary of the employee
	 * @param salary New salary of the employee
	 */
	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	/**
	 * Method to obtain the data from the DB
	 * @param e Employee from which we want to copy the data
	 */
	
	public void copyEmployee(Employee e) {
		this.username = e.getUsername();
		this.name = e.getName();
		this.surname = e.getSurname();
		this.password = e.getPassword();
		this.salary = e.getSalary();
	}
	
}
