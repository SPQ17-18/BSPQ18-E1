package es.deusto.bspq.cinema.server.jdo.DAO;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import es.deusto.bspq.cinema.server.jdo.data.Film;
import es.deusto.bspq.cinema.server.jdo.data.Member;
import es.deusto.bspq.cinema.server.jdo.data.Session;

public interface IManagerDAO {
	public void storeFilm(Film film);
	public void storeSession(Session session);
	public void storeMember(Member member);
	
	public ArrayList<Film> getFilms();
	public Film getFilm (String name);
	public ArrayList<Member> getMembers();
	public Film getMember (String email);
	public ArrayList<Session> getSessions();
	public Film getSession (Date date, Time hour);
	
	
	public void updateFilm(Film film);
	public void updateSession(Session session);
	public void updateMember(Member member);
	

	public void deleteAllFilms();
	public void deleteFilm(Film film);
	public void deleteAllSessions();
	public void deleteSession(Film film);
	public void deleteAllMembers();
	public void deleteMember(Member member);
	
	
}