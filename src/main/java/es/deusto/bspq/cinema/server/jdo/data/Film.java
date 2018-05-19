package es.deusto.bspq.cinema.server.jdo.data;

import java.util.List;
import java.util.ArrayList;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Class for the films
 * 
 * @author anderarguinano
 *
 */
@PersistenceCapable(detachable = "true")
public class Film {

	@PrimaryKey
	private String title;

	private String director;
	private int rating;
	private long duration;
	private String country;

	@Persistent(defaultFetchGroup = "true", mappedBy = "film", dependentElement = "true")
	@Join
	private List<Session> sessions = new ArrayList<>();

	/**
	 * Empty constructor
	 */
	public Film() {

	}

	/**
	 * Constructor for the film
	 * 
	 * @param title
	 *            Title of the film
	 * @param director
	 *            Director of the film
	 * @param rating
	 *            Minimum age for watching the film
	 * @param duration
	 *            Duration of the film
	 * @param country
	 *            Country where the film was recorded
	 */
	public Film(String title, String director, int rating, long duration, String country) {
		super();
		this.title = title;
		this.director = director;
		this.rating = rating;
		this.duration = duration;
		this.country = country;
	}

	/**
	 * Method for getting the title of the film
	 * 
	 * @return Returns the title of the film
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Method for setting the title of the film
	 * 
	 * @param title
	 *            Title of the film
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Method for getting the director of the film
	 * 
	 * @return Returns the director of the film
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Method for setting the director of the film
	 * 
	 * @param director
	 *            Director of the film
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * Method for getting the rating of the film
	 * 
	 * @return Returns the rating of the film
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Method for setting the rating
	 * 
	 * @param rating
	 *            Rating of the film
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * Method for getting the duration of the film
	 * 
	 * @return Returns the duration of the film
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * Method for setting the duration of the film
	 * 
	 * @param duration
	 *            Duration of the film
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}

	/**
	 * Method for getting the country
	 * 
	 * @return Returns the country of the film
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Method for setting the country of the film
	 * 
	 * @param country
	 *            Country of the film
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Method for getting the list of the sessions
	 * 
	 * @return Returns the list of sessions
	 */
	public List<Session> getSessions() {
		return sessions;
	}

	/**
	 * Method for setting the list of sessions
	 * 
	 * @param sessions
	 *            List of sessions
	 */
	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	/**
	 * Method for adding the sessions
	 * 
	 * @param session
	 *            Session we want to add
	 */
	public void addSession(Session session) {
		sessions.add(session);
		session.setFilm(this);
	}

	/**
	 * Method for copying the film to the DB
	 * 
	 * @param f
	 *            Film we want to copy
	 */
	public void copyFilm(Film f) {
		this.title = f.getTitle();
		this.director = f.getDirector();
		this.rating = f.getRating();
		this.duration = f.getDuration();
		this.country = f.getCountry();
		for (int i = 0; i < f.getSessions().size(); i++) {
			this.sessions.add(new Session());
			this.sessions.get(i).copySession(f.getSessions().get(i));
		}
	}

}
