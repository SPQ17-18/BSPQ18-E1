package es.deusto.bspq.cinema.server.jdo.data;


import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Class for the rooms of the cinema
 * @author anderarguinano
 *
 */
@PersistenceCapable(detachable = "true")
public class Room {

	@PrimaryKey
	private int roomNumber;
	
	private int numberSeats;
	
	@Persistent(defaultFetchGroup="true", mappedBy="room", dependentElement = "true")
	@Join
	private List<Session> sessions = new ArrayList<>();

	/**
	 * Empty constructor
	 */
	public Room() {
	
	}

	/**
	 * Constructor for the room
	 * @param roomNumber Number of the room
	 * @param numberSeats Seats for this room
	 */
	public Room(int roomNumber, int numberSeats) {
		super();
		this.roomNumber = roomNumber;
		this.numberSeats = numberSeats;
	}

	/**
	 * Method to get the room number
	 * @return Returns the number of the room
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	/**
	 * Method to set the number of the room
	 * @param roomNumber Number of the room
	 */
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	/**
	 * Method to get the seats of a room
	 * @return Returns the number of seats in a room
	 */
	public int getNumberSeats() {
		return numberSeats;
	}
	
	/**
	 * Method to set the number of seats of a room
	 * @param numberSeats Number of seats
	 */

	public void setNumberSeats(int numberSeats) {
		this.numberSeats = numberSeats;
	}

	/**
	 * Method to get the sessions of a room
	 * @return Returns the sessions of a room
	 */
	public List<Session> getSessions() {
		return sessions;
	}

	/**
	 * Method to set the sessions of a room
	 * @param sessions Sessions of a room
	 */
	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	
	/**
	 * Method to add a session to a room
	 * @param session Session to which we want to add a session
	 */
	public void addSession(Session session) {
		sessions.add(session);
		session.setRoom(this);
	}
	
	/**
	 * Method to copy data from the DB
	 * @param r Room from which to copy data
	 */
	public void copyRoom(Room r) {
		this.roomNumber = r.getRoomNumber();
		this.numberSeats = r.getNumberSeats();
	}
	
	/**
	 * Method to copy data including the sessions
	 * @param r Room from which to copy data
	 */
	public void obtainRoom(Room r) {
		this.roomNumber = r.getRoomNumber();
		this.numberSeats = r.getNumberSeats();
		for (int i=0;i<r.getSessions().size();i++) {
			sessions.add(r.getSessions().get(i));
			r.getSessions().get(i).setRoom(this);
		}
	}

}
