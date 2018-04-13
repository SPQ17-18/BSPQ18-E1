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

import es.deusto.bspq.cinema.server.jdo.data.Actor;
import es.deusto.bspq.cinema.server.jdo.data.Employee;
import es.deusto.bspq.cinema.server.jdo.data.Film;
import es.deusto.bspq.cinema.server.jdo.data.Member;
import es.deusto.bspq.cinema.server.jdo.data.Room;
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
	public ArrayList<Film> getFilms() {
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		ArrayList <Film> films = new ArrayList<Film>();
		
		pm.getFetchPlan().setMaxFetchDepth(3);
		
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

			Query<Session> query = pm.newQuery(Session.class, "hour =='"+session.getHour()+"'");

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

	
	public ArrayList<Session> getSessions() {
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		ArrayList <Session> sessions = new ArrayList<Session>();
		
		pm.getFetchPlan().setMaxFetchDepth(3);
		
		try {
			tx.begin();			
			Query<?> q = pm.newQuery("SELECT FROM " + Session.class.getName());
			List <Session> result = (List<Session>) q.execute();
			
			System.out.println("All sessions retrieved.");
			
			for (int i = 0; i < result.size(); i++) {
				sessions.add(new Session());
				sessions.get(i).copySession(result.get(i));
			}
			
			tx.commit();			
		} catch (Exception ex) {
	    	System.out.println("   $ Error retrieving all sessions: " + ex.getMessage());
	    } finally {
	    	if (tx != null && tx.isActive()) {
	    		tx.rollback();
	    	}
    		pm.close(); 
	    }
	    				
		return sessions;
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

	@Override
	public Session getSession(Session s) {
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		Session session = new Session();
	    
		pm.getFetchPlan().setMaxFetchDepth(3);
		
		try {
	    	tx.begin();
	    	Query <?> query = pm.newQuery("SELECT FROM " + Session.class.getName() + " WHERE date == '" + session.getDate() + "' AND hour=='"+session.getHour()+"' AND film.title='"+session.getFilm().getTitle()+"'");
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
		
		Actor a1 = new Actor("Reese", "Witherspoon", "EE.UU.");
		Actor a2 = new Actor("Chris", "Pine", "EE.UU.");
		
		f6.addActor(a1);
		f6.addActor(a2);
		
		Actor a3 = new Actor("Alicia", "Vikander", "EE.UU.");
		Actor a4 = new Actor("Walton", "Goggins", "EE.UU.");
		
		f7.addActor(a3);
		f7.addActor(a4);
		
	
		Actor a5 = new Actor("Tye", "Sheridan", "EE.UU.");
		Actor a6 = new Actor("Olivia", "Cooke", "EE.UU.");
		
		f5.addActor(a5);
		f5.addActor(a6);
		
		
		Actor a7 = new Actor("Javier", "Gutiérrez", "España");
		Actor a8 = new Actor("Juan", "Margallo", "España");
		
		f4.addActor(a7);
		f4.addActor(a8);
		
		Actor a9 = new Actor("Juan", "Garcia", "Italia");
		Actor aA = new Actor("Paolo", "Linares", "Italia");
		
		f3.addActor(a9);
		f3.addActor(aA);
		
		Actor aB = new Actor("Scott", "Eastwood", "EE.UU.");
		Actor aC = new Actor("John", "Boyega", "EE.UU.");
		
		f2.addActor(aB);
		f2.addActor(aC);
		
		Actor aD = new Actor("James", "McAvoy", "EE.UU.");
		Actor aE = new Actor("Alicia", "Vikander", "EE.UU.");
		
		f1.addActor(aD);
		f1.addActor(aE);
		
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
		
		dao.storeFilm(f1);
		dao.storeFilm(f2);
		dao.storeFilm(f3);
		dao.storeFilm(f4);
		dao.storeFilm(f5);
		dao.storeFilm(f6);
		dao.storeFilm(f7);
		
		dao.storeEmployee(e1);
		dao.storeEmployee(e2);
		dao.storeEmployee(e3);
		dao.storeEmployee(e4);
		dao.storeEmployee(e5);
		
		
		
	}
}
