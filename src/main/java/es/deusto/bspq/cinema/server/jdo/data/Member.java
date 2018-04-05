package es.deusto.bspq.cinema.server.jdo.data;

import java.util.List;
import java.sql.Date;
import java.util.ArrayList;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class Member {

	@PrimaryKey
	private String email;
	private String name;
	private String surname;
	private String password;
	private Date birthday;
	private int points;
	
	@Persistent(defaultFetchGroup = "true", mappedBy = "member", dependentElement = "true")
	@Join
	private List<Ticket> tickets = new ArrayList<>();

	public Member() {
		
	}

	public Member(String email, String name, String surname, String password, Date birthday, int points,
			List<Ticket> tickets) {
		super();
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.birthday = birthday;
		this.points = points;
		this.tickets = tickets;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	public void copyMember(Member m) {
		
		this.email = m.getEmail();
		this.name = m.getName();
		this.password = m.getPassword();
		this.surname = m.getSurname();
		this.birthday = m.getBirthday();
		this.points=m.getPoints();
		
		for (int i = 0; i < m.getTickets().size(); i++) {
			this.tickets.add(new Ticket());
			this.tickets.get(i).copyTicket(m.getTickets().get(i));
		}
		
		
	}
	
	
	
}
