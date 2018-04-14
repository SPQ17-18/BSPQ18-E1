package es.deusto.bspq.cinema.server.jdo.data;

import java.util.ArrayList;

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
	
	

	public Session() {
		
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

	public void copySession(Session s) {
		this.date = s.getDate();
		this.hour = s.getHour();
		this.price = s.getPrice();
		// this.room.copyRoom(s.getRoom());
		// this.film.copyFilm(s.getFilm());
	}
	
}
