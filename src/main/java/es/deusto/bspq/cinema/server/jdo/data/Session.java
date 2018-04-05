package es.deusto.bspq.cinema.server.jdo.data;


import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
public class Session {
	
	private Date date;
	private Time hour;
	private float price;
	
	@Persistent(defaultFetchGroup="true")
	private Room room;
	
	@Persistent(defaultFetchGroup="true")
	private Film film;
	
	@Persistent(defaultFetchGroup = "true", mappedBy = "session", dependentElement = "true")
	@Join
	private List<Ticket> tickets = new ArrayList<>();

	public Session() {
		
	}

	public Session(Date date, Time hour, float price, Room room) {
		super();
		this.date = date;
		this.hour = hour;
		this.price = price;
		this.room = room;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getHour() {
		return hour;
	}

	public void setHour(Time hour) {
		this.hour = hour;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	

}
