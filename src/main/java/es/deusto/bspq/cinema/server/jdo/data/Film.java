package es.deusto.bspq.cinema.server.jdo.data;

import java.util.List;
import java.util.ArrayList;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

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

	public Film() {

	}
	
	public Film(String title, String director, int rating, long duration, String country) {
		super();
		this.title = title;
		this.director = director;
		this.rating = rating;
		this.duration = duration;
		this.country = country;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	
	public void addSession(Session session) {
		sessions.add(session);
	}
	
	public void copyFilm(Film f) {
		this.title = f.getTitle();
		this.director = f.getDirector();
		this.rating = f.getRating();
		this.duration = f.getDuration();
		this.country = f.getCountry();
		this.sessions = f.getSessions();
	}

}
