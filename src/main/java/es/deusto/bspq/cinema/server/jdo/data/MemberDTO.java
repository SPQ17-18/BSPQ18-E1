package es.deusto.bspq.cinema.server.jdo.data;

import java.io.Serializable;

public class MemberDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String name;
	private String surname;
	private String password;
	private String birthday;
	private int points;
	
	public MemberDTO() {
		
	}

	public MemberDTO(String email, String name, String surname, String password, String birthday, int points) {
		super();
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.birthday = birthday;
		this.points = points;
	}
	
	public MemberDTO(String email, String name, String surname, String password, String birthday) {
		super();
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.birthday = birthday;
		this.points=0;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	
	
	
}
