package es.deusto.bspq.cinema.server.jdo.data;

import java.io.Serializable;

/**
 * Class to send Film across the network
 * @author anderarguinano
 *
 */
public class FilmDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String title;
	private String director;
	private int rating;
	private long duration;
	private String country;

	/**
	 * Constructor for the filmDTO
	 * @param title Title of the film
	 * @param director Director of the film
	 * @param rating Rating of the film
	 * @param duration Duration of the film	
	 * @param country Country where the film was recorded
	 */
	public FilmDTO(String title, String director, int rating, long duration, String country) {
		super();
		this.title = title;
		this.director = director;
		this.rating = rating;
		this.duration = duration;
		this.country = country;
	}

	/**
	 * Empty constructor of the filmDTO
	 */
	public FilmDTO() {
		
	}

	/**
	 * Method for getting the title of the film
	 * @return Returns the title of the film
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Method for setting the title
	 * @param title Title of the film
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Method for getting the director of the film
	 * @return Returns the director of the film
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Method for setting the director
	 * @param director Director of the film
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * Method for getting the rating of the film
	 * @return Returns the rating of the film
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Method for setting the rating
	 * @param rating Rating of the film
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * Method for getting the duration of the film
	 * @return Returns the duration of the film
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * Method for setting the duration
	 * @param duration Duration of the film
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}

	/**
	 * Method for getting the country
	 * @return Returns the country where the film was produced
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Method for setting the country
	 * @param country Country of the film
	 */
	public void setCountry(String country) {
		this.country = country;
	}

}
