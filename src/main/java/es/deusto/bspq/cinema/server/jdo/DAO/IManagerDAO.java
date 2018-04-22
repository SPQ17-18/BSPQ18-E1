package es.deusto.bspq.cinema.server.jdo.DAO;

import java.util.ArrayList;

import es.deusto.bspq.cinema.server.jdo.data.Employee;
import es.deusto.bspq.cinema.server.jdo.data.Film;
import es.deusto.bspq.cinema.server.jdo.data.Member;
import es.deusto.bspq.cinema.server.jdo.data.Room;
import es.deusto.bspq.cinema.server.jdo.data.Session;
import es.deusto.bspq.cinema.server.jdo.data.Ticket;

public interface IManagerDAO {
	
	
	
	public void storeFilm(Film film)throws Exception;
	public void storeSession(Session session)throws Exception;
	public void storeMember(Member member) throws Exception;
	public void storeEmployee(Employee employee)throws Exception;
	public void storeRoom(Room room)throws Exception;
	public void storeTicket(Ticket ticket)throws Exception;
	
	public void insertSession(Session session,String film, int room);
	public void insertTicket(Ticket t,String session, String email);
	
	public ArrayList<Film> getFilms();
	public Film getFilm (String name);
	public ArrayList<Member> getMembers();
	public Member getMember (String email);
	public ArrayList<Session> getSessions();
	public Session getSession (Session s);
	public Session getSession (String film,String date, String hour);
	public ArrayList <Session>  getSessions (String film);
	public ArrayList<Employee> getEmployees();
	public Employee getEmployee (String username);
	public Room getRoom(int number);
	
	
	public void updateMember(Member member, Ticket t);
	public void updateEmployee(Employee employee);
	public void manageMember(Member member) throws Exception;
	
	public void deleteAllFilms();
	public void deleteRoom(int room);
	public void deleteFilm(Film film);
	public void deleteAllSessions();
	public void deleteSession(Session session);
	public void deleteAllMembers();
	public void deleteMember(Member member) throws Exception;
	public void deleteAllEmployees();
	public void deleteEmployee(Employee employee) throws Exception;
	
	public String getLastSessionCode();
	
}
