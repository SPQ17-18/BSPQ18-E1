package es.deusto.bspq.cinema.server.jdo.data;

import java.io.Serializable;
import java.util.ArrayList;

public class FilmDTO implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private String title;
	private String director;
	private int rating;
	private long duration;
	private String country;
	
	private ArrayList <String> nameActors;

	public FilmDTO(String title, String director, int rating, long duration, String country,
			ArrayList<String> nameActors) {
		super();
		this.title = title;
		this.director = director;
		this.rating = rating;
		this.duration = duration;
		this.country = country;
		this.nameActors = nameActors;
	}

	public FilmDTO() {
		
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

	public ArrayList<String> getNameActors() {
		return nameActors;
	}

	public void setNameActors(ArrayList<String> nameActors) {
		this.nameActors = nameActors;
	}

	
}
