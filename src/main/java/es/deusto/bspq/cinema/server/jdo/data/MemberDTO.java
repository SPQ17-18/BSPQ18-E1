package es.deusto.bspq.cinema.server.jdo.data;

import java.io.Serializable;
/**
 * Class for sending members across the network
 * @author anderarguinano
 *
 */

public class MemberDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String name;
	private String surname;
	private String password;
	private String birthday;
	private int points;

	/**
	 * Empty constructor of the memberDTO
	 */
	public MemberDTO() {

	}

	/**
	 * Constructor for the memberDTO
	 * 
	 * @param email
	 *            Email of the member
	 * @param name
	 *            Name of the member
	 * @param surname
	 *            Surname of the member
	 * @param password
	 *            Password of the member
	 * @param birthday
	 *            Birthday of the member
	 * @param points
	 *            Points of the member
	 */
	public MemberDTO(String email, String name, String surname, String password, String birthday, int points) {
		super();
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.birthday = birthday;
		this.points = points;
	}

	/**
	 * Constructor of the memberDTO
	 * 
	 * @param email
	 *            Email of the member
	 * @param name
	 *            Name of the member
	 * @param surname
	 *            Surname of the member
	 * @param password
	 *            Password of the member
	 * @param birthday
	 *            Birthday of the member
	 */
	public MemberDTO(String email, String name, String surname, String password, String birthday) {
		super();
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.birthday = birthday;
		this.points = 0;
	}

	/**
	 * Method for getting the email
	 * 
	 * @return Returns the email of the member
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Method for setting the email
	 * 
	 * @param email
	 *            Email of the member
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Method for getting the name of the member
	 * 
	 * @return Returns the name of the member
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to set the name of the member
	 * 
	 * @param name
	 *            Name of the member
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method to get the surname
	 * 
	 * @return Returns the surname of the member
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Method to set the surname of the member
	 * 
	 * @param surname
	 *            Surname of the member
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Method to get the password of the member
	 * 
	 * @return Returns the password of the member
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Method to set the password of the member
	 * 
	 * @param password
	 *            Password of the member
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Method to get the birthday of the member
	 * 
	 * @return Returns the birthday of the member
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * Method to set the birthday of the member
	 * 
	 * @param birthday
	 *            Birthday of the member
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * Method to get the points of the member
	 * 
	 * @return Returns the points of the member
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Method to set the points of the member
	 * 
	 * @param points
	 *            Points of the member
	 */
	public void setPoints(int points) {
		this.points = points;
	}

}
