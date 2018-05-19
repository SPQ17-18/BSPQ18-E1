package es.deusto.bspq.cinema.server.jdo.data;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Class for the sessions
 * @author anderarguinano
 *
 */
@PersistenceCapable(detachable = "true")
public class Session {
	
	@PrimaryKey
	private String session;
	
	private String date;
	private String hour;
	private float price;
	
	@Persistent(defaultFetchGroup="true")
	private Room room;
	
	@Persistent(defaultFetchGroup="true")
	private Film film;
	
	@Persistent(defaultFetchGroup = "true", mappedBy = "session", dependentElement = "true")
	@Join
	private List<Ticket> tickets = new ArrayList<>();

	/**
	 * Empty constructor
	 */
	public Session() {
		this.film = new Film();
	}

	/**
	 * Constructor for the sessions
	 * @param session Code of the session
	 * @param date Date of the session
	 * @param hour Hour of the session
	 * @param price Price of the session
	 */
	public Session(String session, String date, String hour, float price) {
		super();
		this.session = session;
		this.date = date;
		this.hour = hour;
		this.price = price;
	}

	/**
	 * Method to get the date of the session
	 * @return Returns the date of the session
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Method to set the date of the session
	 * @param date Date of the session
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Method to get the hour of the session
	 * @return Returns the hour of the session
	 */
	public String getHour() {
		return hour;
	}

	/**
	 * Method to set the hour of the session
	 * @param hour Hour of the session
	 */
	public void setHour(String hour) {
		this.hour = hour;
	}

	/**
	 * Method to get the price of the session
	 * @return Returns the price of the session
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Method to set the price of the session
	 * @param price Price of the session
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * Method to get the room associated with the session
	 * @return Returns the room
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * Method to set the room
	 * @param room Room of the session
	 */
	public void setRoom(Room room) {
		this.room = room;
	}

	/**
	 * Method to get the film associated with the session
	 * @return Returns the film associated with the session
	 */
	
	public Film getFilm() {
		return film;
	}

	/**
	 * Method to set the film
	 * @param film Film of the session
	 */
	public void setFilm(Film film) {
		this.film = film;
	}
	
	/**
	 * Method to get the session code
	 * @return Returns the session code
	 */
	public String getSession() {
		return session;
	}

	/**
	 * Method to set the session code
	 * @param session Code of the session
	 */
	public void setSession(String session) {
		this.session = session;
	}
	
	/**
	 * Method to get the list of seats bought to this session
	 * @return Returns the list of seats bought for this session
	 */
	public ArrayList<String> getSeats() {
		return new ArrayList<String>(); //TODO
	}
	
	/**
	 * Method to get the tickets bought for this session
	 * @return Returns the list of tickets
	 */
	public List<Ticket> getTickets() {
		return tickets;
	}

	/**
	 * Method to set the tickets
	 * @param tickets Tickets bought for this session
	 */
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	/**
	 * Method to add a new ticket
	 * @param t Ticket we want to add
	 */
	public void addTicket(Ticket t) {
		tickets.add(t);
		t.setSession(this);
	}

	/**
	 * Method to copy the data of a session from the DB
	 * @param s Session to copy data from
	 */
	public void copySession(Session s) {
		this.session=s.getSession();
		this.date = s.getDate();
		this.hour = s.getHour();
		this.price = s.getPrice();
		this.room=new Room();
		this.room.copyRoom(s.getRoom());
		
		for (int i = 0; i < s.getTickets().size(); i++) {
			this.tickets.add(new Ticket());
			this.tickets.get(i).copyTicket(s.getTickets().get(i));
		}
	}
	
}
