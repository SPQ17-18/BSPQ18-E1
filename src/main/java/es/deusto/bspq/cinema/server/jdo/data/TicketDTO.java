package es.deusto.bspq.cinema.server.jdo.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class for creating tickets to cross the network
 * 
 * @author anderarguinano
 *
 */
public class TicketDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;

	private String titleFilm;
	private String date;
	private String hour;

	private ArrayList<String> listSeats;

	/**
	 * Constructor for the ticketDTO
	 * 
	 * @param email
	 *            Email of the member
	 * @param titleFilm
	 *            Title of the film
	 * @param date
	 *            Date of the session
	 * @param hour
	 *            Hour of the session
	 * @param listSeats
	 *            List of seats
	 */
	public TicketDTO(String email, String titleFilm, String date, String hour, ArrayList<String> listSeats) {
		super();
		this.email = email;
		this.titleFilm = titleFilm;
		this.date = date;
		this.hour = hour;
		this.listSeats = listSeats;
	}

	/**
	 * Empty constructor
	 */
	public TicketDTO() {

	}

	/**
	 * Method to get the email
	 * 
	 * @return Returns the email of the member
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Method to set the email
	 * 
	 * @param email
	 *            Email of the member
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Method to get the title of the film
	 * 
	 * @return Returns the title of the film
	 */
	public String getTitleFilm() {
		return titleFilm;
	}

	/**
	 * Method to set the title of the film
	 * 
	 * @param titleFilm
	 *            Title of the film
	 */
	public void setTitleFilm(String titleFilm) {
		this.titleFilm = titleFilm;
	}

	/**
	 * Method to get the date of the session
	 * 
	 * @return Returns the date of the session
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Method to set the date of the session
	 * 
	 * @param date
	 *            Date of the session
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Method to get the hour of the session
	 * 
	 * @return Returns the hour of the session
	 */
	public String getHour() {
		return hour;
	}

	/**
	 * Method to set the hour of the session
	 * 
	 * @param hour
	 *            Hour of the session
	 */
	public void setHour(String hour) {
		this.hour = hour;
	}

	/**
	 * Method to get the list of seats
	 * 
	 * @return Returns the list of seats
	 */
	public ArrayList<String> getListSeats() {
		return listSeats;
	}

	/**
	 * Method to set the list of seats
	 * 
	 * @param listSeats
	 *            List of seats
	 */
	public void setListSeats(ArrayList<String> listSeats) {
		this.listSeats = listSeats;
	}

}
