package es.deusto.bspq.cinema.server.jdo.data;

/**
 * Class for sending the employee across the network
 * @author anderarguinano
 *
 */
public class EmployeeDTO {
	
	private String username;
	private String name;
	private String surname;
	private String password;
	private float salary;

	/**
	 * Empty constructor for the employee
	 */
	public EmployeeDTO() {

	}

	/**
	 * Constructor for the employee
	 * @param username Username of the employee
	 * @param name Name of the employee
	 * @param surname Surname of the employee
	 * @param password Password of the employee
	 * @param salary Salary of the employee
	 */
	public EmployeeDTO(String username, String name, String surname, String password, float salary) {
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.salary = salary;
	}

	/**
	 * Method for getting the username
	 * @return Returns the username of the employee
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Method for setting the username of the employee
	 * @param username Username of the employee
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
	 * @param name Name of the employee
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method for getting the surname of the employee
	 * @return Returns the surname of the employee
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Method for setting the surname of the employee
	 * @param surname Surname of the employee
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
	 * @param password Password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Method for getting the salary
	 * @return Returns the salary of the employee
	 */
	public float getSalary() {
		return salary;
	}

	/**
	 * Method for setting the salary
	 * @param salary Salary of the employee
	 */
	public void setSalary(float salary) {
		this.salary = salary;
	}
	
}
