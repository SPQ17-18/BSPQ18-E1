package es.deusto.bspq.cinema.server.jdo.DAO;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import org.datanucleus.api.jdo.JDOQuery;

import es.deusto.bspq.cinema.server.jdo.data.Employee;
import es.deusto.bspq.cinema.server.jdo.data.Film;
import es.deusto.bspq.cinema.server.jdo.data.Member;
import es.deusto.bspq.cinema.server.jdo.data.Room;
import es.deusto.bspq.cinema.server.jdo.data.Seat;
import es.deusto.bspq.cinema.server.jdo.data.Session;
import es.deusto.bspq.cinema.server.jdo.data.Ticket;

public class ManagerDAO implements IManagerDAO {

	private static final Logger logger = Logger.getLogger(ManagerDAO.class);

	private PersistenceManagerFactory pmf;

	public ManagerDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	private void storeObject(Object object) throws Exception{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(object);
			pm.detachCopy(object);
			tx.commit();
		} catch (Exception ex) {
			logger.error("Problem occurred trying to store the object");
			throw new Exception();
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
		ArrayList<Film> films = new ArrayList<Film>();

		pm.getFetchPlan().setMaxFetchDepth(5);

		try {
			tx.begin();
			Query<?> q = pm.newQuery("SELECT FROM " + Film.class.getName());
			List<Film> result = (List<Film>) q.execute();

			for (int i = 0; i < result.size(); i++) {
				films.add(new Film());
				films.get(i).copyFilm(result.get(i));
			}

			tx.commit();
		} catch (Exception ex) {
			logger.error("Error trying to get the films");
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
			logger.info(" * '" + query.deletePersistentAll() + "' films deleted from the DB.");
			tx.commit();
		} catch (Exception ex) {
			logger.error("   $ Error cleaning the DB: " + ex.getMessage());
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

			Query<Film> query = pm.newQuery(Film.class, "title =='" + film.getTitle() + "'");

			Collection<?> result = (Collection<?>) query.execute();

			Film f = (Film) result.iterator().next();

			query.close(result);

			pm.deletePersistent(f);

			tx.commit();
		} catch (Exception ex) {
			logger.error( "Error cleaning a film: " + ex.getMessage());
		
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}
	}

	public Film getFilm(String name) {
		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();
		Film film = new Film();

		pm.getFetchPlan().setMaxFetchDepth(3);

		try {
			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Film.class.getName() + " WHERE title == '" + name + "'");
			query.setUnique(true);
			Film result = (Film) query.execute();
			film.copyFilm(result);

			tx.commit();
		} catch (Exception ex) {
			logger.error("Error retrieving a film: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return film;
	}

	public void storeFilm(Film film) throws Exception{
		logger.info( "Storing a film called "+film.getTitle());
		this.storeObject(film);
	}

	public void storeSession(Session session) throws Exception {
		logger.info( "Storing a session: " + session.getRoom().getRoomNumber() + " - "
				+ session.getDate().toString() + " " + session.getHour().toString());
		this.storeObject(session);
	}

	public void storeTicket(Ticket ticket) throws Exception{
		logger.info("Storing a ticket: " + ticket.getMember().getEmail() + "");
		this.storeObject(ticket);
	}

	public void storeMember(Member member)throws Exception {
		logger.info("Storing a member: " + member.getEmail());
		this.storeObject(member);

	}

	public void insertSession(Session session,String film, int room) {

		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Query<?> query = pm
					.newQuery("SELECT FROM " + Film.class.getName() + " WHERE title == '" + film + "'");
			query.setUnique(true);
			Film result = (Film) query.execute();

			session.setFilm(result);

			result.addSession(session);

			tx.commit();

			tx.begin();

			Query<?> query2 = pm.newQuery("SELECT FROM " + Room.class.getName() + " WHERE roomNumber == " + room + "");
			query2.setUnique(true);
			Room result2 = (Room) query2.execute();
			session.setRoom(result2);
			result2.addSession(session);

			tx.commit();

		} catch (Exception ex) {
			logger.error( "   $ Error updating a film: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

	}

	public void insertTicket(Ticket t,String session, String email) {
		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Query<?> query = pm.newQuery(
					"SELECT FROM " + Session.class.getName() + " WHERE session == '" + session + "'");
			query.setUnique(true);
			Session result = (Session) query.execute();

			t.setSession(result);

			result.addTicket(t);

			tx.commit();

			tx.begin();

			Query<?> query2 = pm.newQuery("SELECT FROM " + Member.class.getName() + " WHERE email == '" + email + "'");
			query2.setUnique(true);
			Member result2 = (Member) query2.execute();
			t.setMember(result2);
			result2.addTicket(t);
			result2.setPoints(result2.getPoints()+(t.getSeats().size()*3));
			tx.commit();

		} catch (Exception ex) {
			logger.error( "Error updating a session: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

	}

	public void updateMember(Member member, Ticket t) {
		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Query<?> query = pm
					.newQuery("SELECT FROM " + Member.class.getName() + " WHERE  email== '" + member.getEmail() + "'");
			query.setUnique(true);
			Member result = (Member) query.execute();

			result.addTicket(t);
			
			tx.commit();

		} catch (Exception ex) {
			logger.error("Error updating a member: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

	}
	
	public void manageMember(Member member) throws Exception {
		
		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Query<?> query = pm
					.newQuery("SELECT FROM " + Member.class.getName() + " WHERE  email== '" + member.getEmail() + "'");
			query.setUnique(true);
			Member result = (Member) query.execute();

			result.setBirthday(member.getBirthday());
			result.setName(member.getName());
			result.setSurname(member.getSurname());
			result.setPassword(member.getPassword());

			tx.commit();

		} catch (Exception ex) {
			logger.error("Error updating a member: " + ex.getMessage());
			throw new Exception();
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
			logger.info("* '" + query.deletePersistentAll() + "' sessions deleted from the DB.");
			tx.commit();
		} catch (Exception ex) {
			logger.error("Error cleaning the DB: " + ex.getMessage());
			
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

			Query<Session> query = pm.newQuery(Session.class, "session =='" + session.getSession() + "'");

			Collection<?> result = (Collection<?>) query.execute();

			Session s = (Session) result.iterator().next();

			query.close(result);

			pm.deletePersistent(s);

			tx.commit();
		} catch (Exception ex) {
			logger.error("Error cleaning a session: " + ex.getMessage());
			
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
			logger.info(" * '" + query.deletePersistentAll() + "' members deleted from the DB.");
			tx.commit();
		} catch (Exception ex) {
			logger.error("Error cleaning the DB: " + ex.getMessage());
			
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}

	}

	public void deleteMember(Member member) throws Exception {

		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			Query<Member> query = pm.newQuery(Member.class, "email =='" + member.getEmail() + "'");

			Collection<?> result = (Collection<?>) query.execute();

			Member m = (Member) result.iterator().next();

			query.close(result);

			pm.deletePersistent(m);

			tx.commit();
		} catch (Exception ex) {
			logger.error("Error cleaning a member: " + ex.getMessage());
			throw new Exception();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}

	}

	public void storeEmployee(Employee employee) throws Exception{
		logger.info("* Storing an employee: " + employee.getUsername());
		this.storeObject(employee);
	}

	public void storeRoom(Room room) throws Exception{
		logger.info("* Storing a room: " + room.getRoomNumber());
		this.storeObject(room);
	}

	public ArrayList<Member> getMembers() {
		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();
		ArrayList<Member> members = new ArrayList<Member>();

		pm.getFetchPlan().setMaxFetchDepth(3);

		try {
			tx.begin();
			Query<?> q = pm.newQuery("SELECT FROM " + Member.class.getName());
			@SuppressWarnings("unchecked")
			List<Member> result = (List<Member>) q.execute();

			logger.info("All members retrieved.");

			for (int i = 0; i < result.size(); i++) {
				members.add(new Member());
				members.get(i).copyMember(result.get(i));
			}

			tx.commit();
		} catch (Exception ex) {
			logger.error("Error retrieving all members: " + ex.getMessage());
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
			Query<?> query = pm.newQuery("SELECT FROM " + Member.class.getName() + " WHERE email == '" + email + "'");
			query.setUnique(true);
			Member result = (Member) query.execute();
			member.copyMember(result);
			tx.commit();
		} catch (Exception ex) {
			logger.error("Error retrieving a member: " + ex.getMessage());
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
		ArrayList<Session> sessionsA = new ArrayList<Session>();

		pm.getFetchPlan().setMaxFetchDepth(3);

		try {
			tx.begin();
			Query<?> q = pm.newQuery("SELECT FROM " + Session.class.getName());
			List<Session> result = (List<Session>) q.execute();

			logger.info("All sessions retrieved: " + result.size());

			for (int i = 0; i < result.size(); i++) {
				Session s = new Session();
				s.copySession(result.get(i));
				sessionsA.add(s);
			}

			tx.commit();
		} catch (Exception ex) {
			logger.error("Error retrieving all sessions: " + ex.getMessage());
		
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return sessionsA;
	}

	@Override
	public Session getSession(String film, String date, String hour) {
		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();
		Session session = new Session();

		pm.getFetchPlan().setMaxFetchDepth(3);

		try {
			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Session.class.getName() + " WHERE date == '" + date
					+ "' AND hour=='" + hour + "' AND film.title='" + session.getFilm().getTitle() + "'");
			query.setUnique(true);
			Session result = (Session) query.execute();
			session.copySession(result);
			tx.commit();
		} catch (Exception ex) {
			logger.error("Error retrieving a session: " + ex.getMessage());
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
		ArrayList<Employee> employees = new ArrayList<Employee>();

		pm.getFetchPlan().setMaxFetchDepth(3);

		try {
			tx.begin();
			Query<?> q = pm.newQuery("SELECT FROM " + Employee.class.getName());
			@SuppressWarnings("unchecked")
			List<Employee> result = (List<Employee>) q.execute();

			logger.info("All employees retrieved.");

			for (int i = 0; i < result.size(); i++) {
				employees.add(new Employee());
				employees.get(i).copyEmployee(result.get(i));
			}

			tx.commit();
		} catch (Exception ex) {
			logger.error("Error retrieving all employees: " + ex.getMessage());
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
			Query<?> query = pm
					.newQuery("SELECT FROM " + Employee.class.getName() + " WHERE username == '" + username + "'");
			query.setUnique(true);
			Employee result = (Employee) query.execute();
			employee.copyEmployee(result);
			tx.commit();
		} catch (Exception ex) {
			logger.error("Error retrieving an employee: " + ex.getMessage());
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
			logger.error("Error updating an employee: " + ex.getMessage());
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
			logger.info(" * '" + query.deletePersistentAll() + "' employees deleted from the DB.");
			tx.commit();
		} catch (Exception ex) {
			logger.error("Error cleaning the DB: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}

	}

	public void deleteEmployee(Employee employee) throws Exception {

		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			Query<Employee> query = pm.newQuery(Employee.class, "username =='" + employee.getUsername() + "'");

			Collection<?> result = (Collection<?>) query.execute();

			Employee e = (Employee) result.iterator().next();

			query.close(result);

			pm.deletePersistent(e);

			tx.commit();
		} catch (Exception ex) {
			logger.info("   $ Error cleaning an employee: " + ex.getMessage());
			throw new Exception();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}

	}

	public ArrayList<Session> getSessions(String film) {
		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();
		ArrayList<Session> sessions = new ArrayList<Session>();

		pm.getFetchPlan().setMaxFetchDepth(3);

		try {
			tx.begin();
			Query<?> q = pm.newQuery("SELECT FROM " + Session.class.getName() + " WHERE film.title=='" + film + "'");
			@SuppressWarnings("unchecked")
			List<Session> result = (List<Session>) q.execute();

			logger.info("All sessions retrieved from the film: " + film);

			for (int i = 0; i < result.size(); i++) {
				sessions.add(new Session());
				sessions.get(i).copySession(result.get(i));
			}

			tx.commit();
		} catch (Exception ex) {
			logger.error("Error retrieving all sessions from the film: " + ex.getMessage());
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
			Query<?> query = pm
					.newQuery("SELECT FROM " + Session.class.getName() + " WHERE session == '" + s.getSession() + "'");
			query.setUnique(true);
			Session result = (Session) query.execute();
			session.copySession(result);
			tx.commit();
		} catch (Exception ex) {
			logger.error("Error retrieving a session: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return session;

	}

	public Room getRoom(int number) {

		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();
		Room room = new Room();

		pm.getFetchPlan().setMaxFetchDepth(5);

		try {
			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Room.class.getName() + " WHERE roomNumber == " + number + "");
			query.setUnique(true);
			Room result = (Room) query.execute();
			room.copyRoom(result);

			tx.commit();
		} catch (Exception ex) {
			logger.error("Error retrieving a session: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return room;

	}

	public void deleteRoom(int room) {
		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			Query<Room> query = pm.newQuery(Room.class, "roomNumber ==" + room + "");

			Collection<?> result = (Collection<?>) query.execute();

			Room r = (Room) result.iterator().next();

			query.close(result);

			pm.deletePersistent(r);

			tx.commit();

			logger.info("Cleaned the room: " + room);
		} catch (Exception ex) {
			logger.error("Error cleaning a room: " + ex.getMessage());
			
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}

	}
	
	public void deleteAllRooms() {
		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			JDOQuery<Room> query = (JDOQuery<Room>) pm.newQuery(Room.class);
			logger.info(" * '" + query.deletePersistentAll() + "' rooms deleted from the DB.");
			tx.commit();
		} catch (Exception ex) {
			logger.error("   $ Error cleaning the DB: " + ex.getMessage());
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

	public String getLastSessionCode() {
		PersistenceManager pm = pmf.getPersistenceManager();

		pm.getFetchPlan().setMaxFetchDepth(4);
		Transaction tx = pm.currentTransaction();
		String sessionCode = "";

		try {
			tx.begin();
			Query<?> q = pm.newQuery("SELECT FROM " + Session.class.getName() + " ORDER BY session");
			@SuppressWarnings("unchecked")
			List<Session> result = (List<Session>) q.execute();
			ArrayList<Integer> codes = new ArrayList<>();
			for (int i = 0; i < result.size(); i++) {
				codes.add(
						Integer.parseInt(result.get(i).getSession().substring(1, result.get(i).getSession().length())));
			}

			Collections.sort(codes);

			int number = codes.get(codes.size() - 1);
			number++;
			sessionCode = "S" + number;
			tx.commit();
		} catch (Exception ex) {
			logger.error("Error the last session code: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return sessionCode;
	}
	
	public void deleteAllTickets() {
		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			JDOQuery<Ticket> query = (JDOQuery<Ticket>) pm.newQuery(Ticket.class);
			logger.info(" * '" + query.deletePersistentAll() + "' tickets deleted from the DB.");
			tx.commit();
		} catch (Exception ex) {
			logger.error("   $ Error cleaning the DB: " + ex.getMessage());
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
	
	public void deleteAllSeats() {
		PersistenceManager pm = pmf.getPersistenceManager();

		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			JDOQuery<Seat> query = (JDOQuery<Seat>) pm.newQuery(Seat.class);
			logger.info(" * '" + query.deletePersistentAll() + "' seats deleted from the DB.");
			tx.commit();
		} catch (Exception ex) {
			logger.error("   $ Error cleaning the DB: " + ex.getMessage());
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

	public static void main(String[] args) {
		IManagerDAO dao = new ManagerDAO();

		if (args.length != 3) {
			logger.error("Attention: arguments missing");
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

		Employee e1 = new Employee("e1", "Juan", "Garcia Perez", "e1", 1500);
		Employee e2 = new Employee("e2", "Maria", "Martin Gomez", "e2", 1700);
		Employee e3 = new Employee("e3", "Paco", "Perez Gomez", "e3", 1300);
		Employee e4 = new Employee("e4", "Luis", "Lozano Esteban", "e4", 1800);
		Employee e5 = new Employee("e5", "Andrea", "Hernandez Sarria", "e5", 1500);

		Room r1 = new Room(1, 60);
		Room r2 = new Room(2, 70);
		Room r3 = new Room(3, 80);
		Room r4 = new Room(4, 60);
		Room r5 = new Room(5, 60);

		Seat se1 = new Seat("A3");
		Seat se2 = new Seat("A4");
		Seat se3 = new Seat("A5");
		Seat se4 = new Seat("E10");
		Seat se5 = new Seat("E9");
		Seat se6 = new Seat("E8");
		Seat se7 = new Seat("D5");
		Seat se8 = new Seat("D6");
		Seat se9 = new Seat("C7");
		Seat seA = new Seat("B8");
		Seat seB = new Seat("B9");
		Seat seC = new Seat("B10");
		Seat seD = new Seat("F3");
		Seat seE = new Seat("F4");
		Seat seF = new Seat("F5");

		ArrayList<Seat> ss1 = new ArrayList<Seat>();
		ss1.add(se1);
		ss1.add(se2);
		ss1.add(se3);

		ArrayList<Seat> ss2 = new ArrayList<Seat>();
		ss2.add(se4);
		ss2.add(se5);
		ss2.add(se6);

		ArrayList<Seat> ss3 = new ArrayList<Seat>();
		ss3.add(se7);
		ss3.add(se8);

		ArrayList<Seat> ss4 = new ArrayList<Seat>();
		ss4.add(se9);

		ArrayList<Seat> ss5 = new ArrayList<Seat>();
		ss5.add(seA);
		ss5.add(seB);
		ss5.add(seC);

		ArrayList<Seat> ss6 = new ArrayList<Seat>();
		ss6.add(seD);
		ss6.add(seE);
		ss6.add(seF);

		Ticket t1 = new Ticket();
		t1.addSeats(ss1);
		Ticket t2 = new Ticket();
		t2.addSeats(ss2);
		Ticket t3 = new Ticket();
		t3.addSeats(ss3);
		Ticket t4 = new Ticket();
		t4.addSeats(ss4);
		Ticket t5 = new Ticket();
		t5.addSeats(ss5);
		Ticket t6 = new Ticket();
		t6.addSeats(ss6);

		Member m1 = new Member("ariane.fernandez@opendeusto.es", "Ariane", "Fernandez", "ariane", "26-04-1997", 0);
		Member m2 = new Member("unaibermejofdez@opendeusto.es", "Unai", "Bermejo", "unai", "23-08-1997", 0);
		Member m3 = new Member("ander.arguinano@opendeusto.es", "Ander", "Arguinano", "ander", "26-10-1997", 0);
		Member m4 = new Member("inigogc@opendeusto.es", "Inigo", "Garcia", "inigo", "10-02-1997", 0);
		Member m5 = new Member("fischer.wolfgang@opendeusto.es", "Wolfgang ", "Fischer", "wolfgang", "05-09-1997", 0);

		m1.addTicket(t1);
		m1.addTicket(t2);
		m3.addTicket(t3);
		m4.addTicket(t5);
		m5.addTicket(t6);
		m2.addTicket(t4);

		Session s1 = new Session("S1", "13-04-2018", "17:00", (float) 8.90);
		Session s2 = new Session("S2", "13-04-2018", "18:00", (float) 8.90);
		Session s3 = new Session("S3", "13-04-2018", "19:00", (float) 5.90);

		s1.addTicket(t1);
		s2.addTicket(t2);
		s1.addTicket(t3);
		s2.addTicket(t4);
		s3.addTicket(t5);
		s1.addTicket(t6);

		r1.addSession(s1);
		r2.addSession(s2);
		r3.addSession(s3);

		f1.addSession(s1);
		f1.addSession(s2);
		f1.addSession(s3);

		Session s4 = new Session("S4", "14-04-2018", "17:00", (float) 8.90);
		Session s5 = new Session("S5", "14-04-2018", "20:00", (float) 7.50);
		Session s6 = new Session("S6", "14-04-2018", "22:00", (float) 10.90);

		r4.addSession(s4);
		r5.addSession(s5);
		r1.addSession(s6);

		f2.addSession(s4);
		f2.addSession(s5);
		f2.addSession(s6);

		Session s7 = new Session("S7", "15-04-2018", "17:00", (float) 5.80);
		Session s8 = new Session("S8", "15-04-2018", "19:00", (float) 6.60);
		Session s9 = new Session("S9", "15-04-2018", "22:00", (float) 4.40);

		r2.addSession(s7);
		r3.addSession(s8);
		r4.addSession(s9);

		f3.addSession(s7);
		f3.addSession(s8);
		f3.addSession(s9);

		Session sA = new Session("S10", "13-04-2018", "16:00", (float) 12.90);
		Session sB = new Session("S11", "13-04-2018", "18:00", (float) 8.90);
		Session sC = new Session("S12", "13-04-2018", "20:00", (float) 6.90);

		r5.addSession(sA);
		r1.addSession(sB);
		r2.addSession(sC);

		f4.addSession(sA);
		f4.addSession(sB);
		f4.addSession(sC);

		Session sD = new Session("S13", "15-04-2018", "16:00", (float) 12.90);
		Session sE = new Session("S14", "15-04-2018", "18:00", (float) 8.90);
		Session sF = new Session("S15", "15-04-2018", "20:00", (float) 6.90);

		r3.addSession(sD);
		r4.addSession(sE);
		r5.addSession(sF);

		f5.addSession(sD);
		f5.addSession(sE);
		f5.addSession(sF);
		
		try {
			dao.storeFilm(f1);
	
			dao.storeEmployee(e1);
			dao.storeEmployee(e2);
			dao.storeEmployee(e3);
			dao.storeEmployee(e4);
			dao.storeEmployee(e5);
		} catch(Exception e) {
			
		}
		logger.info( "DB filled completely");
	}
}
