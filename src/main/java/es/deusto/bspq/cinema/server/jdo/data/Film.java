package es.deusto.bspq.cinema.server.jdo.data;

import java.util.List;
import java.util.ArrayList;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
public class Film {

	private String title;
	private String director;
	private int rating;
	private long duration;
	private String country;

	@Persistent(defaultFetchGroup = "true", mappedBy = "film", dependentElement = "true")
	@Join
	private List<Session> sessions = new ArrayList<>();
	
	@Persistent(defaultFetchGroup = "true", mappedBy = "films", dependentElement = "true")
	@Join
	private List<Actor> actors = new ArrayList<>();

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



	public List<Actor> getActors() {
		return actors;
	}



	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	
	

}
