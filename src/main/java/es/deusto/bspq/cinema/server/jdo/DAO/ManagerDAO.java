package es.deusto.bspq.cinema.server.jdo.DAO;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.bspq.cinema.server.jdo.data.Film;
import es.deusto.bspq.cinema.server.jdo.data.Member;
import es.deusto.bspq.cinema.server.jdo.data.Session;

public class ManagerDAO implements IManagerDAO {
	
private PersistenceManagerFactory pmf;
	
	public ManagerDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	
	private void storeObject(Object object) {
		PersistenceManager pm = pmf.getPersistenceManager();
	    Transaction tx = pm.currentTransaction();
	    try {
	       tx.begin();
	       pm.makePersistent(object);
	       tx.commit();
	    } catch (Exception ex) {
	    	System.out.println("   $ Error storing an object: " + ex.getMessage());
	    } finally {
	    	if (tx != null && tx.isActive()) {
	    		tx.rollback();
	    	}
    		pm.close();
	    }
	}

	@Override
	public void storeFilm(Film film) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeSession(Session session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeMember(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Film> getFilms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Film getFilm(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Member> getMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Film getMember(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Session> getSessions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Film getSession(Date date, Time hour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateFilm(Film film) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSession(Session session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMember(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllFilms() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFilm(Film film) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllSessions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSession(Film film) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllMembers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMember(Member member) {
		// TODO Auto-generated method stub
		
	}

}