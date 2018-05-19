package es.deusto.bspq.cinema.server.jdo.data;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
public class Seat {
	
	private String seatCode;
	
	@Persistent(defaultFetchGroup="true")
	private Ticket ticket;

	/**
	 * Empty constructor
	 */
	public Seat() {
		
	}

	/**
	 * Constructor of the seat
	 * @param seatCode Code of the seat
	 */
	public Seat(String seatCode) {
		super();
		this.seatCode = seatCode;
	}

	/**
	 * Method to obtain the code of the seats
	 * @return Returns the code of a seat
	 */
	public String getSeatCode() {
		return seatCode;
	}

	/**
	 * Method to set the code of a seat
	 * @param seatCode Code of the seat
	 */
	public void setSeatCode(String seatCode) {
		this.seatCode = seatCode;
	}
	
	/**
	 * Method to get the ticket for the seat
	 * @return Returns the ticket for the seat
	 */
	public Ticket getTicket() {
		return ticket;
	}

	/**
	 * Method to set the ticket
	 * @param ticket Ticket to set to the seat
	 */
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	/**
	 * Method to copy data from the DB
	 * @param s Seat from which to copy data
	 */
	public void copySeat(Seat s) {
		this.seatCode = s.getSeatCode();
	}

}
