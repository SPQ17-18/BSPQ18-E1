package es.deusto.bspq.cinema.server.jdo.data;


import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class Room {

	@PrimaryKey
	private int roomNumber;
	
	private int numberSeats;
	
	@Persistent(defaultFetchGroup="true", mappedBy="room", dependentElement = "true")
	@Join
	private List<Session> sessions = new ArrayList<>();

	public Room() {
	
	}

	public Room(int roomNumber, int numberSeats) {
		super();
		this.roomNumber = roomNumber;
		this.numberSeats = numberSeats;
	}

	public int getRoomNumber() {
		return roomNumber;
	}


	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}


	public int getNumberSeats() {
		return numberSeats;
	}


	public void setNumberSeats(int numberSeats) {
		this.numberSeats = numberSeats;
	}


	public List<Session> getSessions() {
		return sessions;
	}


	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	
	public void addSession(Session session) {
		sessions.add(session);
		session.setRoom(this);
	}
	
	public void copyRoom(Room r) {
		this.roomNumber = r.getRoomNumber();
		this.numberSeats = r.getNumberSeats();
		this.sessions = r.getSessions();
	}
	
}
