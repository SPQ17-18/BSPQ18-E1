package es.deusto.bspq.cinema.server.jdo.data;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * Class for the ticket
 * 
 * @author anderarguinano
 *
 */
@PersistenceCapable(detachable = "true")
public class Ticket {

	@Persistent(defaultFetchGroup = "true")
	private Member member;

	@Persistent(defaultFetchGroup = "true")
	private Session session;

	@Persistent(defaultFetchGroup = "true", mappedBy = "ticket", dependentElement = "true")
	@Join
	private List<Seat> seats = new ArrayList<>();

	/**
	 * Empty constructor
	 */
	public Ticket() {

	}

	/**
	 * Constructor for the ticket
	 * 
	 * @param member
	 *            Member who has bought the ticket
	 * @param session
	 *            Session related to the ticket
	 * @param seats
	 *            List of seats bought
	 */
	public Ticket(Member member, Session session, List<Seat> seats) {
		super();
		this.member = member;
		this.session = session;
		this.seats = seats;
	}

	/**
	 * Constructor of a ticket
	 * 
	 * @param seats
	 *            List of seats bought
	 */
	public Ticket(List<Seat> seats) {
		this.seats = seats;
	}

	/**
	 * Method to get the member
	 * 
	 * @return Returns the member who bought the ticket
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * Method to set the member
	 * 
	 * @param member
	 *            Member who bought the ticket
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * Method to get the session
	 * 
	 * @return Returns the session for the ticket
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * Method to set the session
	 * 
	 * @param session
	 *            Session of the ticket
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	/**
	 * Method to get the list of seats bought
	 * 
	 * @return Returns the list of seats
	 */
	public List<Seat> getSeats() {
		return seats;
	}

	/**
	 * Method to set the seats
	 * 
	 * @param seats
	 *            List of seats
	 */
	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	/**
	 * Method to add a seat
	 * 
	 * @param seat
	 *            Seat to add
	 */
	public void addSeat(Seat seat) {
		seats.add(seat);
		seat.setTicket(this);
	}

	/**
	 * Method to add list of seats
	 * 
	 * @param seat
	 *            List of seats
	 */
	public void addSeats(ArrayList<Seat> seat) {
		for (int i = 0; i < seat.size(); i++) {
			this.seats.add(seat.get(i));
			seat.get(i).setTicket(this);
		}
	}

	/**
	 * Method to copy the data from the DB
	 * 
	 * @param t
	 *            Ticket to copy
	 */
	public void copyTicket(Ticket t) {
		for (int i = 0; i < t.getSeats().size(); i++) {
			this.seats.add(new Seat());
			this.seats.get(i).copySeat(t.getSeats().get(i));
		}
	}

}
