package es.deusto.bspq.cinema.server.jdo.DAO;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import es.deusto.bspq.cinema.server.jdo.data.Employee;
import es.deusto.bspq.cinema.server.jdo.data.Film;
import es.deusto.bspq.cinema.server.jdo.data.Member;
import es.deusto.bspq.cinema.server.jdo.data.Room;
import es.deusto.bspq.cinema.server.jdo.data.Seat;
import es.deusto.bspq.cinema.server.jdo.data.Session;
import es.deusto.bspq.cinema.server.jdo.data.Ticket;

public class ManagerDAOTest {
	
	private static ManagerDAO managerDAO;
	
	//We use this for Contiperf tests
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	@BeforeClass
	public static void setUpClass() {
		managerDAO = new ManagerDAO();
	}
	
	@Before
	public void setUpDB() {
		// Initial DB for all the tests
		managerDAO = new ManagerDAO();
		// Clean the DB
		managerDAO.deleteAllEmployees();
		managerDAO.deleteAllMembers();
		managerDAO.deleteAllTickets();
		managerDAO.deleteAllSessions();
		managerDAO.deleteAllFilms();
		managerDAO.deleteAllRooms();
		managerDAO.deleteAllSeats();
		// Fill the DB
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
		Room r5 = new Room();
		r5.setNumberSeats(60);
		r5.setRoomNumber(5);

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
		Member m3 = new Member("ander.arguinano@opendeusto.es", "Ander", "Arguinano", "ander", "26-10-1997", 20);
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
			managerDAO.storeFilm(f1);
			managerDAO.storeEmployee(e1);
			managerDAO.storeEmployee(e2);
			managerDAO.storeEmployee(e3);
			managerDAO.storeEmployee(e4);
			managerDAO.storeEmployee(e5);
		} catch(Exception e) {
			
		}
	}
	
	//This test has to fulfil the maximum of 120 and the average 30
	@Test
    @Required(max = 120, average = 30)
	public void testStoreRoom() throws Exception {
		Room room = new Room(15, 55);
		managerDAO.storeRoom(room);
		Room r = managerDAO.getRoom(15);
		assertEquals(15, r.getRoomNumber());
		assertEquals(55, r.getNumberSeats());
	}
	
	@Test
	@Required(max = 120, average = 30)
	public void testStoreFilm() throws Exception {
		Film film = new Film("Test", "Testy Tester", 12, 111, "EE.UU.");
		managerDAO.storeFilm(film);
		Film f = managerDAO.getFilm("Test");
		assertEquals("Test", f.getTitle());
		assertEquals("Testy Tester", f.getDirector());
		assertEquals("EE.UU.", f.getCountry());
	}
	
	@Test
	@Required(max = 120, average = 30)
	public void testStoreEmployee() throws Exception {
		Employee employee = new Employee("e10", "Laura", "García", "e10pass", 20000f);
		managerDAO.storeEmployee(employee);
		Employee e = managerDAO.getEmployee("e10");
		assertEquals("Laura", e.getName());
		assertEquals("García", e.getSurname());
		assertEquals("e10pass", e.getPassword());
		assertEquals(true, e.getSalary() == 20000f);
	}
	
	@Test
	@Required(max = 120, average = 30)
	public void testStoreMember() throws Exception {
		Member member = new Member("test@opendeusto.es", "Leire", "Rementeria", "leire", "05-12-1995", 0);
		managerDAO.storeMember(member);
		Member e = managerDAO.getMember("test@opendeusto.es");
		assertEquals("Leire", e.getName());
		assertEquals("Rementeria", e.getSurname());
		assertEquals("leire", e.getPassword());
		assertEquals("05-12-1995", e.getBirthday());
	}
	
	@Test
	@Required(max = 120, average = 30)
	public void testStoreTicket() throws Exception {
		Seat s1 = new Seat("F3");
		Seat s2 = new Seat("F4");
		Seat s3 = new Seat("F5");
		ArrayList<Seat> seats = new ArrayList<Seat>();
		seats.add(s1);
		seats.add(s2);
		seats.add(s3);
		Ticket ticket = new Ticket();
		ticket.addSeats(seats);
		Member member = new Member("test@opendeusto.es", "Test", "Testy", "test", "05-12-1995", 0);
		Session session = new Session("S25", "14-12-2018", "17:00", (float) 8.90);
		ticket.setMember(member);
		ticket.setSession(session);
		managerDAO.storeTicket(ticket);
		Member m = managerDAO.getMember("test@opendeusto.es");
		assertEquals(1, m.getTickets().size());
	}
	
	@Test
	@Required(max = 120, average = 30)
	public void testDeleteRoom() throws Exception {
		managerDAO.deleteRoom(2);
		assertEquals(0, managerDAO.getRoom(2).getRoomNumber());
	}
	
	//This test failed in my computer
	@Test
	@Required(max = 120, average = 30)
	public void testDeleteFilm() throws Exception {
		Film film = new Film("Inmersion", "Wim Wenders", 12, 111, "EE.UU.");
		managerDAO.deleteFilm(film);
		assertEquals(4, managerDAO.getFilms().size());
	}
	
	@Test
	@Required(max = 120, average = 30)
	public void testDeleteEmployee() throws Exception {
		Employee employee = new Employee("e1", "Juan", "Garcia Perez", "e1", 1500);
		managerDAO.deleteEmployee(employee);
		assertEquals(4, managerDAO.getEmployees().size());
	}
	
	@Test
	@Required(throughput = 20)
	public void testDeleteMember() throws Exception {
		Member member = new Member("unaibermejofdez@opendeusto.es", "Unai", "Bermejo", "unai", "23-08-1997", 0);
		managerDAO.deleteMember(member);
		assertEquals(4, managerDAO.getMembers().size());
	}
	
	@Test
	@Required(totalTime = 5000)
	public void testDeleteSession() throws Exception {
		
		Session session = new Session("S4", "14-04-2018", "17:00", (float) 8.90);
		assertEquals("S4",managerDAO.getSession(session).getSession());
		managerDAO.deleteSession(session);
		assertEquals(14, managerDAO.getSessions().size());
		assertEquals(2, managerDAO.getSessions("Pacific Rim: Insurrecion").size());
	}
	
	@Test
	@Required(average = 100)
	public void testInsertTicket() throws Exception {
		// ------------- avoid e-mail spam -------------
		Member member = new Member("test@opendeusto.es", "Test", "Testy", "test", "05-12-1995", 0);
		managerDAO.storeMember(member);
		// ---------------------------------------------
		Seat s1 = new Seat("F3");
		Seat s2 = new Seat("F4");
		Seat s3 = new Seat("F5");
		ArrayList<Seat> seats = new ArrayList<Seat>();
		seats.add(s1);
		seats.add(s2);
		seats.add(s3);
		Ticket ticket = new Ticket();
		ticket.addSeats(seats);
		managerDAO.insertTicket(ticket, "S4", "test@opendeusto.es");
		Member e = managerDAO.getMember("test@opendeusto.es");
		assertEquals(1, e.getTickets().size());
	}
	
	@Test
	@Required(average = 100)
	public void testUpdateSession() throws Exception {
		Session s = new Session("S1", "15-07-2018", "11:30", 15f);
		s.setFilm(managerDAO.getFilm("Campeones"));
		s.setRoom(managerDAO.getRoom(4));
		
		managerDAO.updateSession(s);
		
		Session sNew = managerDAO.getSession(s);
		
		assertEquals("15-07-2018", sNew.getDate());
		assertEquals("11:30", sNew.getHour());
		assertEquals(true,15f	==sNew.getPrice());
		
	}
	
	@Test
	@Required(percentile90 = 3000)
	public void testManageMember() throws Exception {
		Member member = new Member("unaibermejofdez@opendeusto.es", "Unai", "Fernández", "test", "23-04-1997", 0);
		managerDAO.manageMember(member);
		Member m = managerDAO.getMember("unaibermejofdez@opendeusto.es");
		assertEquals("Fernández", m.getSurname());
		assertEquals("test", m.getPassword());
		assertEquals("23-04-1997", m.getBirthday());
	}
	
	@Test
	@Required(percentile90 = 3000)
	public void testGetSessionCode() throws Exception {

		assertEquals("S15", managerDAO.getSessionCode("15-04-2018", "20:00", "Ready Player One"));
	
	}
	
	@Test
	@Required(percentile90 = 3000)
	public void testGetMemberPoints() throws Exception {

		assertEquals(20, managerDAO.getMemberPoints("ander.arguinano@opendeusto.es"));
		assertEquals(0, managerDAO.getMemberPoints("unaibermejofdez@opendeusto.es"));
		
	
	}
	
	@AfterClass
	public static void tearDownClass() {
		// Clean the DB
		managerDAO.deleteAllEmployees();
		managerDAO.deleteAllMembers();
		managerDAO.deleteAllTickets();
		managerDAO.deleteAllSessions();
		managerDAO.deleteAllFilms();
		managerDAO.deleteAllRooms();
		managerDAO.deleteAllSeats();
	}

}
