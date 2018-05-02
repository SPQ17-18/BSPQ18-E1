package es.deusto.bspq.cinema.server.jdo.data;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

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

	public Session() {
		this.film = new Film();
	}

	public Session(String session, String date, String hour, float price) {
		super();
		this.session = session;
		this.date = date;
		this.hour = hour;
		this.price = price;
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

	
	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}
	
	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}
	
	public ArrayList<String> getSeats() {
		return new ArrayList<String>(); //TODO
	}
	
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	public void addTicket(Ticket t) {
		tickets.add(t);
		t.setSession(this);
	}

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
