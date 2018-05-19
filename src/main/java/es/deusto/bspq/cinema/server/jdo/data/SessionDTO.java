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

	/**
	 * Empty constructor
	 */
	public SessionDTO() {

	}

	/**
	 * Constructor for the sessionDTO
	 * 
	 * @param date
	 *            Date of the session
	 * @param hour
	 *            Hour of the session
	 * @param price
	 *            Price of the session
	 * @param room
	 *            Room of the session
	 * @param numberSeats
	 *            Number of seats
	 * @param titleFilm
	 *            Title of the film
	 * @param director
	 *            Director of the film
	 * @param rating
	 *            Rating of the film
	 * @param duration
	 *            Duration of the film
	 * @param country
	 *            Country of the film
	 * @param seatsCode
	 *            List of seats code
	 */
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

	/**
	 * Constructor for the sessionDTO
	 * 
	 * @param date
	 *            Date of the session
	 * @param hour
	 *            Hour of the session
	 * @param price
	 *            Price of the session
	 * @param room
	 *            Room of the session
	 * @param numberSeats
	 *            Number of seats for the session
	 * @param titleFilm
	 *            Title of the film
	 */
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

	/**
	 * Constructor for the sessionDTO
	 * 
	 * @param date
	 *            Date of the session
	 * @param hour
	 *            Hour of the session
	 * @param price
	 *            Price of the session
	 * @param room
	 *            Room of the session
	 * @param numberSeats
	 *            Number of seats
	 * @param titleFilm
	 *            Title of the film
	 * @param director
	 *            Director of the film
	 * @param rating
	 *            Rating for the film
	 * @param duration
	 *            Duration of the film
	 * @param country
	 *            Country of the film
	 * @param seatsCode
	 *            Code of seats
	 * @param session
	 *            Session code
	 */
	public SessionDTO(String date, String hour, float price, int room, int numberSeats, String titleFilm,
			String director, int rating, long duration, String country, ArrayList<String> seatsCode, String session) {
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
		this.session = session;
	}

	/**
	 * Constructor for the sessionDTO
	 * 
	 * @param date
	 *            Date of the session
	 * @param hour
	 *            Hour of the session
	 * @param price
	 *            Price of the session
	 * @param room
	 *            Room of the session
	 * @param numberSeats
	 *            Number of seats
	 * @param titleFilm
	 *            Title of the film
	 * @param session
	 *            Session code
	 */
	public SessionDTO(String date, String hour, float price, int room, int numberSeats, String titleFilm,
			String session) {
		super();
		this.date = date;
		this.hour = hour;
		this.price = price;
		this.room = room;
		this.numberSeats = numberSeats;
		this.titleFilm = titleFilm;
		this.remainingSeatsCode = new ArrayList<String>();
		this.session = session;
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
	 *            Returns the date of the session
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
	 * Method to get the price of the session
	 * 
	 * @return Returns the price of the session
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Method to set the price of the session
	 * 
	 * @param price
	 *            Price of the session
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * Method to get the room of the session
	 * 
	 * @return Returns the room of the session
	 */
	public int getRoom() {
		return room;
	}

	/**
	 * Method to set the room of a session
	 * 
	 * @param room
	 *            Room of a session
	 */
	public void setRoom(int room) {
		this.room = room;
	}

	/**
	 * Method to get the number of seats
	 * 
	 * @return Returns the number of seats
	 */
	public int getNumberSeats() {
		return numberSeats;
	}

	/**
	 * Method to set the number of seats
	 * 
	 * @param numberSeats
	 *            Number of seats
	 */
	public void setNumberSeats(int numberSeats) {
		this.numberSeats = numberSeats;
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
	 *            Returns the title of the film
	 */
	public void setTitleFilm(String titleFilm) {
		this.titleFilm = titleFilm;
	}

	/**
	 * Method to get the director of the film
	 * 
	 * @return Returns the director of the film
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Method to set the director of a film
	 * 
	 * @param director
	 *            Director of the film
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * Method to get the rating of the film
	 * 
	 * @return Returns the rating of the film
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Method to set the rating of the film
	 * 
	 * @param rating
	 *            Rating of the film
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * Method to get the duration of a film
	 * 
	 * @return Returns the duration of the film
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * Method to set the duration of the film
	 * 
	 * @param duration
	 *            Duration of the film
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}

	/**
	 * Method to get the country of the film
	 * 
	 * @return Returns the country of the film
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Method to set the country of the film
	 * 
	 * @param country
	 *            Country of the film
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Method to get the remaining seats code
	 * 
	 * @return Returns a list with the remaining seats code
	 */
	public ArrayList<String> getRemainingSeatsCode() {
		return remainingSeatsCode;
	}

	/**
	 * Method to set the remaining seats code
	 * 
	 * @param remainingSeatsCode
	 *            List with the remaining seats code
	 */
	public void setRemainingSeatsCode(ArrayList<String> remainingSeatsCode) {
		this.remainingSeatsCode = remainingSeatsCode;
	}

	/**
	 * Method to get the session code
	 * 
	 * @return Returns the code of the session
	 */
	public String getSession() {
		return session;
	}

	/**
	 * Method to set the session code
	 * 
	 * @param session
	 *            Code of the session
	 */
	public void setSession(String session) {
		this.session = session;
	}

}
