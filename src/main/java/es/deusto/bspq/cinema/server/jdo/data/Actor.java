package es.deusto.bspq.cinema.server.jdo.data;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
public class Actor {
	
	private String name;
	private String surname;
	private String country;
	
	@Persistent(defaultFetchGroup="true")
	private List<Film> films = new ArrayList<>();
	
	public Actor() {
	
	}

	public Actor(String name, String surname, String country) {
		super();
		this.name = name;
		this.surname = surname;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}
	
	public void addFilm(Film film) {
		films.add(film);
		
	}
	
	public void copyActor(Actor a) {
		
		this.name = a.getName();
		this.surname = a.getSurname();
		this.country = a.getCountry();
		for (int i = 0; i < a.getFilms().size(); i++) {
			this.films.add(new Film());
			this.films.get(i).copyFilm(a.getFilms().get(i));
		}
		
	
	}
	
	
	

}
