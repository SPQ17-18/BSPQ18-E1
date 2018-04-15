package es.deusto.bspq.cinema.server.jdo.data;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
public class Seat {
	
	private String seatCode;
	
	@Persistent(defaultFetchGroup="true")
	private Ticket ticket;

	public Seat() {
		
	}

	public Seat(String seatCode) {
		super();
		this.seatCode = seatCode;
	}

	public String getSeatCode() {
		return seatCode;
	}

	public void setSeatCode(String seatCode) {
		this.seatCode = seatCode;
	}
	
	
	
	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public void copySeat(Seat s) {
		
		this.seatCode = s.getSeatCode();
		
		
	}

}
