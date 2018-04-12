package es.deusto.bspq.cinema.server.jdo.data;

import java.io.Serializable;
import java.util.ArrayList;

public class TicketDTO implements Serializable{
	

	private static final long serialVersionUID = 1L;

	//Data about the buyer
	private String email;
	
	//Data about the session
	
	private String titleFilm;
	private String date;
	private String hour;
	
	//Data about the seats
	
	private ArrayList <String> listSeats;

	public TicketDTO(String email, String titleFilm, String date, String hour, ArrayList<String> listSeats) {
		super();
		this.email = email;
		this.titleFilm = titleFilm;
		this.date = date;
		this.hour = hour;
		this.listSeats = listSeats;
	}

	public TicketDTO() {
	
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitleFilm() {
		return titleFilm;
	}

	public void setTitleFilm(String titleFilm) {
		this.titleFilm = titleFilm;
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

	public ArrayList<String> getListSeats() {
		return listSeats;
	}

	public void setListSeats(ArrayList<String> listSeats) {
		this.listSeats = listSeats;
	}
	
	
	
}
