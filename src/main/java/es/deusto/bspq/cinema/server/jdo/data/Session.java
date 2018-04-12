package es.deusto.bspq.cinema.server.jdo.data;


import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
public class Session {
	
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

	public Session() {
		
	}

	public Session(String date, String hour, float price, Room room) {
		super();
		this.date = date;
		this.hour = hour;
		this.price = price;
		this.room = room;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
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
	
	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
		
	}

	
	
	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public void copySession(Session s) {
		
		this.date = s.getDate();
		this.hour = s.getHour();
		this.price = s.getPrice();
		this.room.copyRoom(s.getRoom());
		this.film.copyFilm(s.getFilm());
		
		for (int i = 0; i < s.getTickets().size(); i++) {
			this.tickets.add(new Ticket());
			this.tickets.get(i).copyTicket(s.getTickets().get(i));
		}
		
	}
}
