package es.deusto.bspq.cinema.server.jdo.data;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
public class Ticket {
	
	@Persistent(defaultFetchGroup="true")
	private Member member;
	
	@Persistent(defaultFetchGroup="true")
	private Session session;
	
	@Persistent(defaultFetchGroup = "true", mappedBy = "ticket", dependentElement = "true")
	@Join
	private List<Seat> seats = new ArrayList<>();

	public Ticket() {
	
	}

	public Ticket(Member member, Session session, List<Seat> seats) {
		super();
		this.member = member;
		this.session = session;
		this.seats = seats;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	
	
	
	

}
