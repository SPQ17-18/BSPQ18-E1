package es.deusto.bspq.cinema.server.jdo.data;

import java.io.Serializable;
import java.util.ArrayList;

public class SessionDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String session;

	private String date;
	private String hour;
	private float price;
	
	private int room;
	private int numberSeats;
	
	private String titleFilm;
	private String director;
	private int rating;
	private long duration;
	private String country;

	private ArrayList<String> remainingSeatsCode;

	public SessionDTO() {

	}

	public SessionDTO(String date, String hour, float price, int room, int numberSeats, String titleFilm,
			String director, int rating, long duration, String country, ArrayList<String> seatsCode) {
		super();
		this.date = date;
		this.hour = hour;
		this.price = price;
		this.room = room;
		this.numberSeats = numberSeats;
		this.titleFilm = titleFilm;
		this.director = director;
		this.rating = rating;
		this.duration = duration;
		this.country = country;
		this.remainingSeatsCode = seatsCode;
	}
	
	public SessionDTO(String date, String hour, float price, int room, int numberSeats, String titleFilm) {
		super();
		this.date = date;
		this.hour = hour;
		this.price = price;
		this.room = room;
		this.numberSeats = numberSeats;
		this.titleFilm = titleFilm;
		this.remainingSeatsCode = new ArrayList<String>();
	}
	
	public SessionDTO(String date, String hour, float price, int room, int numberSeats, String titleFilm,
			String director, int rating, long duration, String country, ArrayList<String> seatsCode,String session) {
		super();
		this.date = date;
		this.hour = hour;
		this.price = price;
		this.room = room;
		this.numberSeats = numberSeats;
		this.titleFilm = titleFilm;
		this.director = director;
		this.rating = rating;
		this.duration = duration;
		this.country = country;
		this.remainingSeatsCode = seatsCode;
		this.session=session;
	}
	
	public SessionDTO(String date, String hour, float price, int room, int numberSeats, String titleFilm,String session) {
		super();
		this.date = date;
		this.hour = hour;
		this.price = price;
		this.room = room;
		this.numberSeats = numberSeats;
		this.titleFilm = titleFilm;
		this.remainingSeatsCode = new ArrayList<String>();
		this.session=session;
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

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public int getNumberSeats() {
		return numberSeats;
	}

	public void setNumberSeats(int numberSeats) {
		this.numberSeats = numberSeats;
	}

	public String getTitleFilm() {
		return titleFilm;
	}

	public void setTitleFilm(String titleFilm) {
		this.titleFilm = titleFilm;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public ArrayList<String> getRemainingSeatsCode() {
		return remainingSeatsCode;
	}

	public void setRemainingSeatsCode(ArrayList<String> remainingSeatsCode) {
		this.remainingSeatsCode = remainingSeatsCode;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}
	
}
