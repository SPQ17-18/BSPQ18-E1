package es.deusto.bspq.cinema.server.jdo.data;

import java.util.List;
import java.util.ArrayList;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Class for the customers
 * 
 * @author anderarguinano
 *
 */
@PersistenceCapable(detachable = "true")
public class Member {

	@PrimaryKey
	private String email;
	private String name;
	private String surname;
	private String password;
	private String birthday;
	private int points;

	@Persistent(defaultFetchGroup = "true", mappedBy = "member", dependentElement = "true")
	@Join
	private List<Ticket> tickets = new ArrayList<>();

	/**
	 * Empty constructor
	 */
	public Member() {

	}

	/**
	 * Constructor for the member
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
	public Member(String email, String name, String surname, String password, String birthday, int points) {
		super();
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.birthday = birthday;
		this.points = points;
		this.tickets = new ArrayList<Ticket>();
	}

	/**
	 * Constructor for the member
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
	 * @param tickets
	 *            Tickets bought by the member
	 */
	public Member(String email, String name, String surname, String password, String birthday, int points,
			List<Ticket> tickets) {
		super();
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.birthday = birthday;
		this.points = points;
		this.tickets = tickets;
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
	 * Method for getting the name
	 * 
	 * @return Returns the name of the member
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method for setting the name of the member
	 * 
	 * @param name
	 *            Name of the member
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method for getting the surname of the member
	 * 
	 * @return Returns the surname of the member
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Method for sestting the surname of the member
	 * 
	 * @param surname
	 *            Surname of the member
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Method for getting the password of the member
	 * 
	 * @return Returns the password of the member
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Method for setting the password of the member
	 * 
	 * @param password
	 *            Password of the member
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Method for getting the birthday of the member
	 * 
	 * @return Returns the birthday of the member
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * Method for setting the birthday
	 * 
	 * @param birthday
	 *            Birthday of the member
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * Method for getting the points
	 * 
	 * @return Returns the points of the member
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Method for setting the points
	 * 
	 * @param points
	 *            Points of the member
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * Method to get the tickets bought by the member
	 * 
	 * @return Returns the list of tickets
	 */
	public List<Ticket> getTickets() {
		return tickets;
	}

	/**
	 * Method to set the tickets
	 * 
	 * @param tickets
	 *            Tickets bought by the member
	 */
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	/**
	 * Method to copy a member from the DB
	 * 
	 * @param m
	 *            Member from which to copy the data
	 */
	public void copyMember(Member m) {
		this.email = m.getEmail();
		this.name = m.getName();
		this.password = m.getPassword();
		this.surname = m.getSurname();
		this.birthday = m.getBirthday();
		this.points = m.getPoints();
		for (int i = 0; i < m.getTickets().size(); i++) {
			this.tickets.add(new Ticket());
			this.tickets.get(i).copyTicket(m.getTickets().get(i));
		}
	}

	/**
	 * Method to add a ticket
	 * 
	 * @param t
	 *            Ticket we want to add
	 */
	public void addTicket(Ticket t) {
		tickets.add(t);
		t.setMember(this);
	}

}
