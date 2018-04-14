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

import es.deusto.bspq.cinema.server.jdo.data.Employee;
import es.deusto.bspq.cinema.server.jdo.data.Film;
import es.deusto.bspq.cinema.server.jdo.data.Member;
import es.deusto.bspq.cinema.server.jdo.data.Room;
import es.deusto.bspq.cinema.server.jdo.data.Seat;
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
	    	ex.printStackTrace();
	    } finally {
	    	if (tx != null && tx.isActive()) {
	    		tx.rollback();
	    	}
    		pm.close();
	    }
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Film> getFilms() {
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		ArrayList <Film> films = new ArrayList<Film>();
		
		pm.getFetchPlan().setMaxFetchDepth(5);
		
		try {
			tx.begin();			
			Query<?> q = pm.newQuery("SELECT FROM " + Film.class.getName());
			List <Film> result = (List<Film>) q.execute();
			
			System.out.println("All films retrieved.");
			
			for (int i = 0; i < result.size(); i++) {
				films.add(new Film());
				films.get(i).copyFilm(result.get(i));
			}
			
			tx.commit();			
		} catch (Exception ex) {
	    	System.out.println("   $ Error retrieving all films: " + ex.getMessage());
	    } finally {
	    	if (tx != null && tx.isActive()) {
	    		tx.rollback();
	    	}
    		pm.close(); 
	    }
	    				
		return films;
	}


	public void deleteAllFilms() {	
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		
		try {		
			tx.begin();
			JDOQuery<Film> query = (JDOQuery<Film>) pm.newQuery(Film.class);
			System.out.println(" * '" + query.deletePersistentAll() + "' films deleted from the DB.");
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
	
	public void deleteFilm(Film film) {		
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
			
		try {		
			tx.begin();

			Query<Film> query = pm.newQuery(Film.class, "title =='"+film.getTitle()+"'");

			Collection<?> result = (Collection<?>) query.execute();

			Film f = (Film) result.iterator().next();

			query.close(result);

			pm.deletePersistent(f);
			   
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error cleaning a film: " + ex.getMessage());
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
	
	public Film getFilm(String name){
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		Film film = new Film();
	    
		pm.getFetchPlan().setMaxFetchDepth(3);
		
		try {
	    	tx.begin();
	    	Query <?> query = pm.newQuery("SELECT FROM " + Film.class.getName() + " WHERE title == '" + name + "'");
	    	query.setUnique(true);
	    	Film result = (Film) query.execute();
	    	film.copyFilm(result);
 	    	tx.commit();
	     } catch (Exception ex) {
	    	 System.out.println("   $ Error retrieving a film: " + ex.getMessage());
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		 }
	   		pm.close();
	     }
		
	    return film;
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

	
	public void updateFilm(Film film) {
		PersistenceManager pm = pmf.getPersistenceManager();
		
	    Transaction tx = pm.currentTransaction();
	    
	    try {
	    	tx.begin();
	    	pm.makePersistent(film);
	    	tx.commit();
	     } catch (Exception ex) {
	    	 System.out.println("   $ Error updating a film: " + ex.getMessage());
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		   	}
	   		pm.close();
	     }
		
	}

	
	public void updateSession(Session session) {
		PersistenceManager pm = pmf.getPersistenceManager();
		
	    Transaction tx = pm.currentTransaction();
	    
	    try {
	    	tx.begin();
	    	pm.makePersistent(session);
	    	tx.commit();
	     } catch (Exception ex) {
	    	 System.out.println("   $ Error updating a session: " + ex.getMessage());
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		   	}
	   		pm.close();
	     }
		
	}

	
	public void updateMember(Member member) {
		PersistenceManager pm = pmf.getPersistenceManager();
		
	    Transaction tx = pm.currentTransaction();
	    
	    try {
	    	tx.begin();
	    	pm.makePersistent(member);
	    	tx.commit();
	     } catch (Exception ex) {
	    	 System.out.println("   $ Error updating a member: " + ex.getMessage());
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		   	}
	   		pm.close();
	     }
		
	}


	public void deleteAllSessions() {
		
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		
		try {		
			tx.begin();
			JDOQuery<Session> query = (JDOQuery<Session>) pm.newQuery(Session.class);
			System.out.println(" * '" + query.deletePersistentAll() + "' sessions deleted from the DB.");
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

	
	public void deleteSession(Session session) {
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
			
		try {		
			tx.begin();

			Query<Session> query = pm.newQuery(Session.class, "session =='"+session.getSession()+"'");

			Collection<?> result = (Collection<?>) query.execute();

			Session s = (Session) result.iterator().next();

			query.close(result);

			pm.deletePersistent(s);
			   
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error cleaning a session: " + ex.getMessage());
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

	
	public void deleteAllMembers() {
		
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		
		try {		
			tx.begin();
			JDOQuery<Member> query = (JDOQuery<Member>) pm.newQuery(Member.class);
			System.out.println(" * '" + query.deletePersistentAll() + "' members deleted from the DB.");
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

	
	public void deleteMember(Member member) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
			
		try {		
			tx.begin();

			Query<Member> query = pm.newQuery(Member.class, "email =='"+member.getEmail()+"'");

			Collection<?> result = (Collection<?>) query.execute();

			Member m = (Member) result.iterator().next();

			query.close(result);

			pm.deletePersistent(m);
			   
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error cleaning a member: " + ex.getMessage());
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


	public void storeEmployee(Employee employee) {
		System.out.println("   * Storing an employee: " + employee.getUsername());
		 this.storeObject(employee);
	}

	
	public ArrayList<Member> getMembers() {
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		ArrayList <Member> members = new ArrayList<Member>();
		
		pm.getFetchPlan().setMaxFetchDepth(3);
		
		try {
			tx.begin();			
			Query<?> q = pm.newQuery("SELECT FROM " + Member.class.getName());
			List <Member> result = (List<Member>) q.execute();
			
			System.out.println("All members retrieved.");
			
			for (int i = 0; i < result.size(); i++) {
				members.add(new Member());
				members.get(i).copyMember(result.get(i));
			}
			
			tx.commit();			
		} catch (Exception ex) {
	    	System.out.println("   $ Error retrieving all members: " + ex.getMessage());
	    } finally {
	    	if (tx != null && tx.isActive()) {
	    		tx.rollback();
	    	}
    		pm.close(); 
	    }
	    				
		return members;
	}


	public Member getMember(String email) {
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		Member member = new Member();
	    
		pm.getFetchPlan().setMaxFetchDepth(3);
		
		try {
	    	tx.begin();
	    	Query <?> query = pm.newQuery("SELECT FROM " + Member.class.getName() + " WHERE email == '" + email + "'");
	    	query.setUnique(true);
	    	Member result = (Member) query.execute();
	    	member.copyMember(result);
 	    	tx.commit();
	     } catch (Exception ex) {
	    	 System.out.println("   $ Error retrieving a member: " + ex.getMessage());
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		 }
	   		pm.close();
	     }
		
	    return member;
	}

	
	@SuppressWarnings("unchecked")
	public ArrayList<Session> getSessions() {
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		ArrayList <Session> sessionsA = new ArrayList<Session>();
		
		pm.getFetchPlan().setMaxFetchDepth(3);
		
		try {
			tx.begin();			
			Query<?> q = pm.newQuery("SELECT FROM " + Session.class.getName());
			List <Session> result = (List<Session>) q.execute();
			
			System.out.println("All sessions retrieved: " +result.size());
			
			for (int i = 0; i < result.size(); i++) {
				Session s = new Session();
				s.copySession(result.get(i));
				sessionsA.add(s);
			}
			
			tx.commit();			
		} catch (Exception ex) {
	    	System.out.println("   $ Error retrieving all sessions: " + ex.getMessage());
	    	ex.printStackTrace();
	    } finally {
	    	if (tx != null && tx.isActive()) {
	    		tx.rollback();
	    	}
    		pm.close(); 
	    }
	    				
		return sessionsA;
	}

	@Override
	public Session getSession(String film,String date, String hour) {
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		Session session = new Session();
	    
		pm.getFetchPlan().setMaxFetchDepth(3);
		
		try {
	    	tx.begin();
	    	Query <?> query = pm.newQuery("SELECT FROM " + Session.class.getName() + " WHERE date == '" + date + "' AND hour=='"+hour+"' AND film.title='"+session.getFilm().getTitle()+"'");
	    	query.setUnique(true);
	    	Session result = (Session) query.execute();
	    	session.copySession(result);
 	    	tx.commit();
	     } catch (Exception ex) {
	    	 System.out.println("   $ Error retrieving a session: " + ex.getMessage());
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		 }
	   		pm.close();
	     }
		
	    return session;
	}

	
	public ArrayList<Employee> getEmployees() {
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		ArrayList <Employee> employees = new ArrayList<Employee>();
		
		pm.getFetchPlan().setMaxFetchDepth(3);
		
		try {
			tx.begin();			
			Query<?> q = pm.newQuery("SELECT FROM " + Employee.class.getName());
			List <Employee> result = (List<Employee>) q.execute();
			
			System.out.println("All employees retrieved.");
			
			for (int i = 0; i < result.size(); i++) {
				employees.add(new Employee());
				employees.get(i).copyEmployee(result.get(i));
			}
			
			tx.commit();			
		} catch (Exception ex) {
	    	System.out.println("   $ Error retrieving all employees: " + ex.getMessage());
	    } finally {
	    	if (tx != null && tx.isActive()) {
	    		tx.rollback();
	    	}
    		pm.close(); 
	    }
	    				
		return employees;
	}

	
	public Employee getEmployee(String username) {
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		Employee employee = new Employee();
	    
		pm.getFetchPlan().setMaxFetchDepth(3);
		
		try {
	    	tx.begin();
	    	Query <?> query = pm.newQuery("SELECT FROM " + Employee.class.getName() + " WHERE username == '" + username + "'");
	    	query.setUnique(true);
	    	Employee result = (Employee) query.execute();
	    	employee.copyEmployee(result);
 	    	tx.commit();
	     } catch (Exception ex) {
	    	 System.out.println("   $ Error retrieving an employee: " + ex.getMessage());
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		 }
	   		pm.close();
	     }
		
	    return employee;
	}

	
	public void updateEmployee(Employee employee) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
		
	    Transaction tx = pm.currentTransaction();
	    
	    try {
	    	tx.begin();
	    	pm.makePersistent(employee);
	    	tx.commit();
	     } catch (Exception ex) {
	    	 System.out.println("   $ Error updating an employee: " + ex.getMessage());
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		   	}
	   		pm.close();
	     }
	}

	
	public void deleteAllEmployees() {
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		
		try {		
			tx.begin();
			JDOQuery<Employee> query = (JDOQuery<Employee>) pm.newQuery(Employee.class);
			System.out.println(" * '" + query.deletePersistentAll() + "' employees deleted from the DB.");
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

	
	public void deleteEmployee(Employee employee) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
			
		try {		
			tx.begin();

			Query<Employee> query = pm.newQuery(Employee.class, "username =='"+employee.getUsername()+"'");

			Collection<?> result = (Collection<?>) query.execute();

			Employee e = (Employee) result.iterator().next();

			query.close(result);

			pm.deletePersistent(e);
			   
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error cleaning an employee: " + ex.getMessage());
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


	public ArrayList <Session> getSessions(String film) {
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		ArrayList <Session> sessions = new ArrayList<Session>();
		
		pm.getFetchPlan().setMaxFetchDepth(3);
		
		try {
			tx.begin();			
			Query<?> q = pm.newQuery("SELECT FROM " + Session.class.getName()+" WHERE film.title='"+film+"'");
			List <Session> result = (List<Session>) q.execute();
			
			System.out.println("All sessions retrieved from the film: "+film);
			
			for (int i = 0; i < result.size(); i++) {
				sessions.add(new Session());
				sessions.get(i).copySession(result.get(i));
			}
			
			tx.commit();			
		} catch (Exception ex) {
	    	System.out.println("   $ Error retrieving all sessions from the film: " + ex.getMessage());
	    } finally {
	    	if (tx != null && tx.isActive()) {
	    		tx.rollback();
	    	}
    		pm.close(); 
	    }
	    				
		return sessions;
		
	}

	
	public Session getSession(Session s) {
		
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		Session session = new Session();
	    
		pm.getFetchPlan().setMaxFetchDepth(5);
		
		try {
	    	tx.begin();
	    	Query <?> query = pm.newQuery("SELECT FROM " + Session.class.getName() + " WHERE session == '" + s.getSession() + "'");
	    	query.setUnique(true);
	    	Session result = (Session) query.execute();
	    	session.copySession(result);
 	    	tx.commit();
	     } catch (Exception ex) {
	    	 System.out.println("   $ Error retrieving a session: " + ex.getMessage());
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		 }
	   		pm.close();
	     }
		
	    return session;
	
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
		
		Film f1 = new Film("Inmersion", "Wim Wenders", 12, 111, "EE.UU.");
		Film f2 = new Film("Pacific Rim: Insurrecion", "Steven S. DeKnight", 7, 110, "EE.UU.");
		Film f3 = new Film("Leo Da Vinci", "Sergio Manfio", 0, 85, "Italia");
		Film f4 = new Film("Campeones", "Javier Fesser", 7, 100, "España");
		Film f5 = new Film("Ready Player One", "Steven Spielberg", 7, 140, "EE.UU.");
		Film f6 = new Film("Un pliegue en el tiempo", "Ava DuVernay", 7, 109, "EE.UU.");
		Film f7 = new Film("Tomb Raider", "Roar Uthaug", 7, 122, "EE.UU.");
		
		
		Employee e1 = new Employee("e1", "Juan", "Garcia Perez", "e1", 1500);
		Employee e2 = new Employee("e2", "Maria", "Martin Gomez", "e2", 1700);
		Employee e3 = new Employee("e3", "Paco", "Perez Gomez", "e3", 1300);
		Employee e4 = new Employee("e4", "Luis", "Lozano Esteban", "e4", 1800);
		Employee e5 = new Employee("e5", "Andrea", "Hernandez Sarria", "e5", 1500);
		
		
		Room r1 = new Room(1, 58);
		Room r2 = new Room(2, 56);
		Room r3 = new Room(3, 57);
		Room r4 = new Room(4, 59);
		Room r5 = new Room(5, 60);
		
//		Seat se1 = new Seat("G3");
//		Seat se2 = new Seat("G4");
//		Seat se3 = new Seat("G5");
//		Seat se4 = new Seat("E10");
//		Seat se5 = new Seat("E9");
//		Seat se6 = new Seat("E8");
//		Seat se7 = new Seat("D5");
//		Seat se8 = new Seat("D6");
//		Seat se9 = new Seat("H7");
//		Seat seA = new Seat("B8");
//		Seat seB = new Seat("B9");
//		Seat seC = new Seat("B10");
//		Seat seD = new Seat("G3");
//		Seat seE = new Seat("G4");
//		Seat seF = new Seat("G5");
//		
		
		
		Session s1 = new Session("S1","13-04-2018", "17:00",(float) 8.90);
		Session s2 = new Session("S2","13-04-2018", "18:00",(float) 8.90);
		Session s3 = new Session("S3","13-04-2018", "19:00",(float) 5.90);
		
		r1.addSession(s1);
		r2.addSession(s2);
		r3.addSession(s3);
		
		
		f1.addSession(s1);
		f1.addSession(s2);
		f1.addSession(s3);
		
			
		Session s4 = new Session("S4","14-04-2018", "17:00",(float) 8.90);
		Session s5 = new Session("S5","14-04-2018", "20:00",(float) 7.50);
		Session s6 = new Session("S6","14-04-2018", "22:00",(float) 10.90);
		
		r4.addSession(s4);
		r5.addSession(s5);
		r1.addSession(s6);
		
		f2.addSession(s4);
		f2.addSession(s5);
		f2.addSession(s6);
		
		Session s7 = new Session("S7","15-04-2018", "17:00",(float) 5.80);
		Session s8 = new Session("S8","15-04-2018", "19:00",(float) 6.60);
		Session s9 = new Session("S9","15-04-2018", "22:00",(float) 4.40);
		
		r2.addSession(s7);
		r3.addSession(s8);
		r4.addSession(s9);
		
		f3.addSession(s7);
		f3.addSession(s8);
		f3.addSession(s9);
		
		Session sA = new Session("S10","13-04-2018", "16:00",(float) 12.90);
		Session sB = new Session("S11","13-04-2018", "18:00",(float) 8.90);
		Session sC = new Session("S12","13-04-2018", "20:00",(float) 6.90);
		
		r5.addSession(sA);
		r1.addSession(sB);
		r2.addSession(sC);
		
		f4.addSession(sA);
		f4.addSession(sB);
		f4.addSession(sC);
		
		Session sD = new Session("S13","15-04-2018", "16:00",(float) 12.90);
		Session sE = new Session("S14","15-04-2018", "18:00",(float) 8.90);
		Session sF = new Session("S15","15-04-2018", "20:00",(float) 6.90);
		
		r3.addSession(sD);
		r4.addSession(sE);
		r5.addSession(sF);
		
		f5.addSession(sD);
		f5.addSession(sE);
		f5.addSession(sF);
		
		
		Member m1 = new Member("ariane.fernandez@opendeusto.es", "Ariane", "Fernandez", "ariane", "26-04-1997", 0);
		Member m2 = new Member("unai.bermejo@opendeusto.es", "Unai", "Bermejo", "unai", "23-08-1997", 0);
		Member m3 = new Member("ander.arguinano@opendeusto.es", "Ander", "Arguinano", "ander", "26-10-1997", 0);
		Member m4 = new Member("inigo.garcia@opendeusto.es", "Inigo", "Garcia", "inigo", "10-02-1997", 0);
		Member m5 = new Member("fischer.wolfgang@opendeusto.es", "Wolfgang ", "Fischer", "wolfgang", "05-09-1997", 0);
		
		
		dao.storeMember(m1);
		dao.storeMember(m2);
		dao.storeMember(m3);
		dao.storeMember(m4);
		dao.storeMember(m5);
		
		dao.storeFilm(f1);
//		dao.storeFilm(f2);
//		dao.storeFilm(f3);
//		dao.storeFilm(f4);
//		dao.storeFilm(f5);
//		dao.storeFilm(f6);
//		dao.storeFilm(f7);
		
		dao.storeEmployee(e1);
		dao.storeEmployee(e2);
		dao.storeEmployee(e3);
		dao.storeEmployee(e4);
		dao.storeEmployee(e5);
		
		
		
		ArrayList <Member> members = dao.getMembers();
		
		for (int i=0;i<members.size();i++) {
			System.out.println("Member "+(i+1));
			System.out.println("Name: "+members.get(i).getName());
			System.out.println("Surname: "+members.get(i).getSurname());
			System.out.println("Birthday: "+members.get(i).getBirthday());
		}
		
	
		
		ArrayList <Employee> employees = dao.getEmployees();
		
		for (int i=0;i<employees.size();i++) {
			System.out.println("Employee "+(i+1));
			System.out.println("Name: "+employees.get(i).getName());
			System.out.println("Surname: "+employees.get(i).getSurname());
			System.out.println("Salary: "+employees.get(i).getSalary());
		}
		
		
		
		ArrayList <Film> films = dao.getFilms();
		
		
		for (int i = 0; i<films.size();i++) {
			System.out.println("Film "+(i+1));
			System.out.println("Name: "+films.get(i).getTitle());
			System.out.println("Country: "+films.get(i).getCountry());
			System.out.println("Duration: "+films.get(i).getDuration());
			System.out.println("Estas son las sesiones de la pelicula: ");
			
			for (int j = 0;j<films.get(i).getSessions().size();j++) {
				System.out.println("Session "+ (j+1)+": "+films.get(i).getSessions().get(j).getDate()+" - "+films.get(i).getSessions().get(j).getHour()+" - "+films.get(i).getSessions().get(j).getRoom().getRoomNumber());
				
			}
			
		}
		
		Film prueba = dao.getFilm("Leo Da Vinci");
		
		System.out.println("PRUEBA");
		
		
		System.out.println("Name: "+prueba.getTitle());
		System.out.println("Country: "+prueba.getCountry());
		System.out.println("Duration: "+prueba.getDuration());
		System.out.println("Estas son las sesiones de la pelicula: ");
		
		for (int j = 0;j<prueba.getSessions().size();j++) {
			System.out.println("Session "+ (j+1)+": "+prueba.getSessions().get(j).getDate()+" - "+prueba.getSessions().get(j).getHour()+" - "+prueba.getSessions().get(j).getRoom().getRoomNumber());
			
		}
		
		Session sessions = dao.getSession("Leo Da Vinci","15-04-2018", "17:00");
		
		
			System.out.println("Session : "+sessions.getDate()+" - "+sessions.getHour()+" - "+sessions.getRoom().getRoomNumber());
			
		
		

//		Film film1 = new Film("G", "H", 1, 2, "I");
//		Room room1 = new Room(2, 25);
//		Session session1 = new Session("J", "K", "L", 5);
//		session1.setFilm(film1);
//		session1.setRoom(room1);
//		
//		Film film2 = new Film("M", "N", 1, 2, "O");
//		Room room2 = new Room(3, 25);
//		Session session2 = new Session("P", "Q", "R", 5);
//		session2.setFilm(film2);
//		session2.setRoom(room2);
//		
//		Film film3 = new Film("S", "T", 1, 2, "U");
//		Room room3 = new Room(4, 25);
//		Session session3 = new Session("V", "W", "X", 5);
//		session3.setFilm(film3);
//		session3.setRoom(room3);
//		
//		Film film4 = new Film("Y", "Z", 1, 2, "AA");
//		Room room4 = new Room(5, 25);
//		Session session4 = new Session("BB", "CC", "DD", 5);
//		session4.setFilm(film4);
//		session4.setRoom(room4);
//		
//
//		dao.storeSession(session1);
//		dao.storeSession(session2);
//		dao.storeSession(session3);
//		dao.storeSession(session4);

	}
}
