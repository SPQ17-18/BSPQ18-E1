package es.deusto.bspq.cinema.server.jdo.data;

import java.io.Serializable;
import java.util.ArrayList;

public class SessionDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Data of the session
	private String date;
	private String hour;
	private float price;
	
	//Data of the room
	private int room;
	private int numberSeats;
	
	//Data of the film
	private String titleFilm;
	
	//Data of the tickets
	private ArrayList<String> seatsCode;
	
	

	public SessionDTO() {

	}

	public SessionDTO(String date, String hour, float price, int room, int numberSeats, String titleFilm,
			ArrayList<String> seatsCode) {
		super();
		this.date = date;
		this.hour = hour;
		this.price = price;
		this.room = room;
		this.numberSeats = numberSeats;
		this.titleFilm = titleFilm;
		this.seatsCode = seatsCode;
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

	public ArrayList<String> getRemainingSeatsCode() {
		return seatsCode;
	}

	public void setRemainingSeatsCode(ArrayList<String> SeatsCode) {
		this.seatsCode = SeatsCode;
	}

}
