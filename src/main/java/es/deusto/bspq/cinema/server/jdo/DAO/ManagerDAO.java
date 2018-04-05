package es.deusto.bspq.cinema.server.jdo.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.datanucleus.api.jdo.JDOQuery;

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

	

	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Flight> getFlights() {
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		ArrayList <Flight> flightsA = new ArrayList<Flight>();
		
		pm.getFetchPlan().setMaxFetchDepth(3);
		
		try {
			tx.begin();			
			Query<?> q = pm.newQuery("SELECT FROM " + Flight.class.getName());
			List <Flight> result = (List<Flight>) q.execute();
			
			System.out.println("All flights retrieved.");
			
			for (int i = 0; i < result.size(); i++) {
				flightsA.add(new Flight());
				flightsA.get(i).copyFlight(result.get(i));
			}
			
			tx.commit();			
		} catch (Exception ex) {
	    	System.out.println("   $ Error retrieving all flights: " + ex.getMessage());
	    } finally {
	    	if (tx != null && tx.isActive()) {
	    		tx.rollback();
	    	}
    		pm.close(); 
	    }
	    				
		return flightsA;
	}

	@SuppressWarnings("unchecked")
	public List<Flight> getFlights(String origin, String destination, String date) {
		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();
		List<Flight> flights = new ArrayList<Flight>();

		pm.getFetchPlan().setMaxFetchDepth(4);

		try {
			tx.begin();			
			Query<?> q = pm.newQuery("SELECT FROM " + Flight.class.getName() + 
                    " WHERE origin=='"+origin+"'"+" && destination=='"+destination+"'"+"&& flightDate=='"+date+"'");
			List <Flight> result = (List<Flight>) q.execute();
			
			System.out.println("Flights that match the following criteria retrieved: ");
			System.out.println("Origin: " +origin);
			System.out.println("Destination: " +destination);
			System.out.println("Date: " +date);
			
			for (int i = 0; i < result.size(); i++) {
				flights.add(new Flight());
				flights.get(i).copyFlight(result.get(i));
			}
			
			tx.commit();	
		} catch (Exception ex) {
	    	System.out.println("   $ Error retrieving some flights: " + ex.getMessage());
	    } finally {
	    	if (tx != null && tx.isActive()) {
	    		tx.rollback();
	    	}
    		pm.close(); 
	    }
	    				
		return flights;
	}
	
	public ArrayList<Flight> getFreeFlights(String origin,String destination,String date) {
		List <Flight> flights = getFlights(origin, destination, date);
		ArrayList<Flight> flightsFree = new ArrayList<Flight>();
		int takenSeat;
		
		for (int i = 0; i < flights.size(); i++) {
			takenSeat = 0;
			
			for (int j = 0; j < flights.get(i).getReservations().size(); j++) {
				takenSeat = flights.get(i).getReservations().get(j).getSeats().size()+takenSeat;
			}
			
			if ((flights.get(i).getTotalSeats() - takenSeat) > 0) {
				flightsFree.add(new Flight());
				flightsFree.get(i).copyFlight(flights.get(i));
			}
		}
		
		return flightsFree;
	}
	
	public void updateflight(Flight flight) {
		PersistenceManager pm = pmf.getPersistenceManager();
		
	    Transaction tx = pm.currentTransaction();
	    
	    try {
	    	tx.begin();
	    	pm.makePersistent(flight);
	    	tx.commit();
	     } catch (Exception ex) {
	    	 System.out.println("   $ Error updating a flight: " + ex.getMessage());
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		   	}
	   		pm.close();
	     }
	}

	public void deleteAllFlights() {	
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		
		try {		
			tx.begin();
			JDOQuery<Flight> query = (JDOQuery<Flight>) pm.newQuery(Flight.class);
			System.out.println(" * '" + query.deletePersistentAll() + "' flights deleted from the DB.");
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error cleaning the DB: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}
	}
	
	public void deleteFlight(Flight flight) {		
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
			
		try {		
			tx.begin();

			Query<Flight> query = pm.newQuery(Flight.class, "flightCode =='"+flight.getFlightCode()+"'");

			Collection<?> result = (Collection<?>) query.execute();

			Flight f = (Flight) result.iterator().next();

			query.close(result);

			pm.deletePersistent(f);
			   
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error cleaning a flight: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}
	}
	
	public Flight getFlight(String flightCode){
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		Flight flight = new Flight();
	    
		pm.getFetchPlan().setMaxFetchDepth(3);
		
		try {
	    	tx.begin();
	    	Query <?> query = pm.newQuery("SELECT FROM " + Flight.class.getName() + " WHERE flightCode == '" + flightCode + "'");
	    	query.setUnique(true);
	    	Flight result = (Flight) query.execute();
	    	flight.copyFlight(result);
 	    	tx.commit();
	     } catch (Exception ex) {
	    	 System.out.println("   $ Error retrieving a flight: " + ex.getMessage());
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		 }
	   		pm.close();
	     }
		
	    return flight;
	}
	
	@SuppressWarnings("unchecked")
	public int getLastReservation() {
		PersistenceManager pm = pmf.getPersistenceManager();
		
		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		int reservationCode = -1;
		
		try {
			tx.begin();			
			Query<?> q = pm.newQuery("SELECT FROM " + Reservation.class.getName() + 
                    " ORDER BY reservation_cod");
			List <Reservation> result = (List<Reservation>) q.execute();
			reservationCode = result.get(result.size()-1).getReservation_cod()+1;
			tx.commit();					
		} catch (Exception ex) {
			System.out.println("   $ Error the last reservation: " + ex.getMessage());
	    } finally {
	    	if (tx != null && tx.isActive()) {
	    		tx.rollback();
	    	}
    		pm.close(); 
	    }
	    				
		return reservationCode;
	}
	
	public static void main(String[] args) {
		IManagerDAO dao= new ManagerDAO();
		
		if (args.length != 3) {
			System.out.println("Attention: arguments missing");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		Flight f1 = new Flight("BI1001","03-06-2018","12.20","10.20","BIO","MAD", 54.4f);
		Flight f2 = new Flight("BI1002","04-06-2018","15.30","12.20","BCN","BIO", 65.4f);
		Flight f3 = new Flight("BI1003","05-06-2018","16.40","13.30","BCN","MAD", 43.6f);
		Flight f4 = new Flight("BI1004","06-06-2018","19.10","15.50","SEV","MAD", 32.3f);
		Flight f5 = new Flight("BI1005","03-06-2018","14.20","10.20","BIO","MAD", 14.4f);
		Flight f6 = new Flight("BI1006","03-06-2018","17.20","12.20","BIO","MAD", 67.2f);
		Flight f7 = new Flight("BI1007","03-06-2018","18.20","15.20","BIO","MAD", 95.5f);
		Flight f8 = new Flight("BI1008","03-06-2018","20.20","19.20","BIO","MAD", 32.6f);
		Flight f9 = new Flight("BI1009","04-06-2018","18.30","14.50","BCN","BIO", 85.9f);
		Flight fA = new Flight("BI1010","04-06-2018","21.30","19.30","BCN","BIO", 60.0f);
		Flight fB = new Flight("BI1011","04-06-2018","22.30","20.30","BCN","BIO", 34.5f);
		Flight fC = new Flight("BI1012","04-06-2018","17.30","14.20","BCN","BIO", 20.3f);
		Flight fD = new Flight("BI1013","04-06-2018","16.30","12.50","BCN","BIO", 55.5f);
		
		Reservation r1 = new Reservation(1, "03-03-2018");
		Reservation r2 = new Reservation(2, "04-03-2018");
		Reservation r3 = new Reservation(3, "05-03-2018");
		Reservation r4 = new Reservation(4, "06-03-2018");
		
		Seat s1 = new Seat("A1", "Unai Bermejo");
		Seat s2 = new Seat("A2", "Maider Ibarra");
		r1.addSeat(s1);
		r1.addSeat(s2);
		
		Seat s3 = new Seat("B3", "Ander Arguiñano");
		Seat s4 = new Seat("B1", "Maider Ibarra");
		Seat s5 = new Seat("B2", "Iñigo Garcia");
		r2.addSeat(s3);
		r2.addSeat(s4);
		r2.addSeat(s5);
		
		Seat s6 = new Seat("E1", "Ariane Fernandez");
		Seat s7 = new Seat("E3", "Koldobika Pellicer");
		r3.addSeat(s6);
		r3.addSeat(s7);
		
		Seat s8 = new Seat("F2", "Markel Alvarez");
		Seat s9 = new Seat("F3", "Maider Ibarra");
		r4.addSeat(s8);
		r4.addSeat(s9);
		
		f1.addReservation(r1);
		f2.addReservation(r2);
		f3.addReservation(r3);
		f4.addReservation(r4);
		
		dao.storeFlight(f1);
		dao.storeFlight(f2);
		dao.storeFlight(f3);
		dao.storeFlight(f4);
		dao.storeFlight(f5);
		dao.storeFlight(f6);
		dao.storeFlight(f7);
		dao.storeFlight(f8);
		dao.storeFlight(f9);
		dao.storeFlight(fA);
		dao.storeFlight(fB);
		dao.storeFlight(fC);
		dao.storeFlight(fD);
	}

	
	public void storeFilm(Film film) {
		System.out.println("   * Storing a film: " + film.getTitle());
		 this.storeObject(film);
		
	}

	
	public void storeSession(Session session) {
		System.out.println("   * Storing a session: " + session.getRoom().getRoomNumber()+" - "+session.getDate().toString()+" "+session.getHour().toString());
		 this.storeObject(session);
	}

	
	public void storeMember(Member member) {
		System.out.println("   * Storing a member: " + member.getEmail());
		 this.storeObject(member);
		
	}

	
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