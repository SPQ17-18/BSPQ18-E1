package es.deusto.bspq.cinema.client.controller;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import es.deusto.bspq.cinema.server.Server;
import es.deusto.bspq.cinema.server.jdo.DAO.IManagerDAO;
import es.deusto.bspq.cinema.server.jdo.DAO.ManagerDAO;
import es.deusto.bspq.cinema.server.jdo.data.Employee;
import es.deusto.bspq.cinema.server.jdo.data.Film;
import es.deusto.bspq.cinema.server.jdo.data.FilmDTO;
import es.deusto.bspq.cinema.server.jdo.data.Member;
import es.deusto.bspq.cinema.server.jdo.data.MemberDTO;
import es.deusto.bspq.cinema.server.jdo.data.Room;
import es.deusto.bspq.cinema.server.jdo.data.Seat;
import es.deusto.bspq.cinema.server.jdo.data.Session;
import es.deusto.bspq.cinema.server.jdo.data.SessionDTO;
import es.deusto.bspq.cinema.server.jdo.data.Ticket;
import es.deusto.bspq.cinema.server.jdo.data.TicketDTO;
import es.deusto.bspq.cinema.server.remote.IRemoteFacade;
import junit.framework.JUnit4TestAdapter;

public class CMControllerTest {
	
	private static final Logger logger = Logger.getLogger(CMControllerTest.class);

	private static String cwd = CMControllerTest.class.getProtectionDomain().getCodeSource().getLocation().getFile();
	private static Thread rmiRegistryThread = null;
	private static Thread rmiServerThread = null;

	private CMController controller;
	private static IManagerDAO managerDAO;

	//This line is for Contiperf tests
	@Rule public ContiPerfRule rule = new ContiPerfRule();
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CMControllerTest.class);
	}

	@BeforeClass
	public static void setUpClass() {
		// Launch the RMI registry
		class RMIRegistryRunnable implements Runnable {
			public void run() {
				try {
					java.rmi.registry.LocateRegistry.createRegistry(1099);
					logger.info("BeforeClass: RMI registry ready.");
				} catch (Exception e) {
					logger.error("Exception starting RMI registry:");
					e.printStackTrace();
				}
			}
		}

		rmiRegistryThread = new Thread(new RMIRegistryRunnable());
		rmiRegistryThread.start();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

		// Launch the server
		class RMIServerRunnable implements Runnable {
			public void run() {
				System.setProperty("java.rmi.server.codebase", "file:" + cwd);
				System.setProperty("java.security.policy", "target//test-classes//security//java.policy");

				if (System.getSecurityManager() == null) {
					System.setSecurityManager(new SecurityManager());
				}

				String name = "//127.0.0.1:1099/CinemaManager";
				logger.info("BeforeClass - Setting the server ready name: " + name);

				try {
					IRemoteFacade server = new Server();
					Naming.rebind(name, server);
				} catch (RemoteException re) {
					logger.error(" # Server RemoteException: " + re.getMessage());
					re.printStackTrace();
					System.exit(-1);
				} catch (MalformedURLException murle) {
					logger.error(" # Server MalformedURLException: " + murle.getMessage());
					murle.printStackTrace();
					System.exit(-1);
				}
			}
		}
		
		rmiServerThread = new Thread(new RMIServerRunnable());
		rmiServerThread.start();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	@Before
	public void setUpClient() {
		// Launch the client
		try {
			System.setProperty("java.security.policy", "target//test-classes//security//java.policy");
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			String args[] = new String[3];
			args[0] = "127.0.0.1";
			args[1] = "1099";
			args[2] = "CinemaManager";
			logger.info("BeforeTest - Setting the client ready for calling name: " + args[2]);
			controller = new CMController(args);
		} catch (Exception re) {
			logger.error(" # Client RemoteException: " + re.getMessage());
			System.exit(-1);
		}
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
		Member m2 = new Member("unaibermejofdez@opendeusto.es", "Unai", "Bermejo", "unai", "23-08-1997", 30);
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
			managerDAO.storeFilm(f1);
			managerDAO.storeEmployee(e1);
			managerDAO.storeEmployee(e2);
			managerDAO.storeEmployee(e3);
			managerDAO.storeEmployee(e4);
			managerDAO.storeEmployee(e5);
		} catch(Exception e) {
			
		}
	}
	
	
	@Test
	@PerfTest(duration = 5000)
    @Required(max = 1550, average = 1500)
	public void testCreateTicket() {
		logger.info("Test Create a ticket - Creting a ticket - Valid");
		Member m = new Member();
		m.setBirthday("01-01-1998");
		m.setEmail("test@opendeusto.es");
		m.setName("test");
		m.setPassword("test");
		m.setPoints(0);
		m.setSurname("test");
		
		Session s = new Session();
		s.setDate("13-05-2018");
		s.setHour("14:00");
		s.setPrice((float)4.56);
		s.setSession("S8");
		
		ArrayList<Seat> seats = new ArrayList<Seat>();
		Seat s1= new Seat();
		s1.setSeatCode("A5");
		Seat s2 = new Seat("B6");
		seats.add(s1);
		seats.add(s2);
		Ticket t7 = new Ticket(m,s,seats);
		assertEquals("test", t7.getMember().getName());
		assertEquals("13-05-2018", t7.getSession().getDate());
		assertEquals("14:00", t7.getSession().getHour());
		assertTrue((float)4.56==t7.getSession().getPrice());
		assertEquals("A5", t7.getSeats().get(0).getSeatCode());
		
		TicketDTO ticket = new TicketDTO();
		
		ticket.setDate("13-05-2018");
		ticket.setEmail("test@opendeusto.es");
		ticket.setHour("14:00");
		ticket.setListSeats(new ArrayList<String>());
		ticket.getListSeats().add("A1");
		ticket.getListSeats().add("A2");
		ticket.setTitleFilm("Test");
		
		assertEquals("test@opendeusto.es", ticket.getEmail());
		assertEquals("13-05-2018", ticket.getDate());
		assertEquals("14:00", ticket.getHour());
		assertEquals("A1", ticket.getListSeats().get(0));
		
		
	}
	
	@Test
	@PerfTest(duration = 5000)
	@Required(throughput = 20)
	public void testInsertFilm() {
		logger.info("Test Insert a film - Inserting a film to the DB - Valid");
		FilmDTO filmDTO = new FilmDTO("Avengers Infinity War", "Anthony Russo", 3, 2, "EEUU");
		assertEquals(true, controller.insertFilm(filmDTO));
	}
	
	@Test
	@Required(totalTime = 5000)
	public void testRegisterMember() {
	
		logger.info("Test Register a member - Inserting a member to the DB - Valid");
		MemberDTO memberDTO = new MemberDTO("test@opendeusto.es", "test", "test", "test", "26-06-1997", 0);
		assertEquals(true, controller.registerMember(memberDTO));
	}
	
	@Test
	@PerfTest(duration = 5000)
	@Required(average = 100)
	public void testCancelMember() {
		logger.info("Test Cancel a member - Cancelling a member from the DB - Valid");
		MemberDTO memberDTO = new MemberDTO("ander.arguinano@opendeusto.es", "Ander", "Arguinano", "ander", "26-06-1997", 0);
		assertEquals(true, controller.cancelMembership(memberDTO.getEmail(), memberDTO.getPassword()));
	}
	
	@Test
	@PerfTest(duration = 1000)
	@Required(percentile90 = 250)
	public void testInsertFilm2() {
		logger.info("Test Insert a film - Inserting a film to the DB with set methods- Valid");
		FilmDTO filmDTO = new FilmDTO();
		filmDTO.setCountry("Spain");
		filmDTO.setDirector("Juan Garcia");
		filmDTO.setDuration(134);
		filmDTO.setRating(8);
		filmDTO.setTitle("Cuerpo de elite");
		assertEquals(true, controller.insertFilm(filmDTO));
	}
	
	@Test
	@PerfTest(duration = 1000)
	@Required(max = 120)
	public void testDeleteFilm() {
		logger.info("Test Delete a film - Deleting a film from the DB - Valid");
		String filmTitle = "Pacific Rim: Insurrecion";
		controller.deleteFilm(filmTitle);
		List<FilmDTO> films = controller.getAllFilms();
		assertEquals(4, films.size());
	}
	
	@Test
	@PerfTest(invocations = 5)
	@Required(totalTime = 550, average = 100)
	public void testDeleteFilm2() {
		logger.info("Test Delete a film - Deleting a film from the DB - Valid");
		String filmTitle = "Ready Player One";
		controller.deleteFilm(filmTitle);
		List<FilmDTO> films = controller.getAllFilms();
		assertEquals(4, films.size());
	}
	
	@Test
	@PerfTest(duration = 1000)
	@Required(max = 120, average = 100)
	public void testDeleteSession() {
		logger.info("Test Delete a session - Deleting a session from the DB - Valid");
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setSession("S2");
		sessionDTO.setRating(7);
		sessionDTO.setCountry("EE.UU");
		controller.deleteSession(sessionDTO);
		List<SessionDTO> sessions = controller.getAllSessions();
		assertEquals(14, sessions.size());
	}

	@Test
	@PerfTest(duration = 1000)
	@Required(average = 100)
	public void testInsertSession() {
		logger.info("Test Insert a session - Inserting a session to the DB - Valid");
		SessionDTO sessionDTO = new SessionDTO("28-05-2018", "20:00", 8f, 3, 25, "Inmersion");
		assertEquals(true, controller.insertSession(sessionDTO));
	}
	
	@Test
	@Required(totalTime = 5000)
	public void testInsertSession2() {
		logger.info("Test Insert a session - Inserting a session to the DB - Valid");
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setDate("28-05-2018");
		sessionDTO.setHour("20:00");
		sessionDTO.setPrice(8f);
		sessionDTO.setRoom(3);
		sessionDTO.setNumberSeats(25);
		sessionDTO.setTitleFilm("Inmersion");
		assertEquals(true, controller.insertSession(sessionDTO));
	}
	
	@Test
	@PerfTest(invocations = 5)
	@Required(average = 450, max = 550)
	public void testUpdateSession() {
		logger.info("Test Update a session - Updating a session from the DB - Valid");
		SessionDTO sessionDTO = new SessionDTO("05-05-2018", "17:05", 5f, 5, 22, "Campeones");
		sessionDTO.setSession("S1");
		assertEquals(true, controller.updateSession(sessionDTO));
	}
	
	@Test
	@PerfTest(invocations = 5)
	@Required(totalTime = 5000, max = 550)
	public void testUpdateSession2() {
		logger.info("Test Update a session - Updating a session from the DB - Valid");
		SessionDTO sessionDTO = new SessionDTO("05-05-2018", "17:05", 5f, 5, 22, "Campeones");
		sessionDTO.setSession("S1");
		assertEquals(true, controller.updateSession(sessionDTO));
	}
	
	@Test
	@PerfTest(invocations = 5)
	@Required(median = 450, max = 550)
	public void testUpdateSession3() {
		logger.info("Test Update a session - Updating a session from the DB - Valid");
		SessionDTO sessionDTO = new SessionDTO("05-05-2018", "17:05", 5f, 5, 22, "Campeones");
		sessionDTO.setSession("S1");
		assertEquals(true, controller.updateSession(sessionDTO));
	}
	
	@Test
	@PerfTest(duration = 2000)
	@Required(max = 200, average = 185)
	public void testUpdateFilm() {
		logger.info("Test Update a film - Updating a film from the DB - Valid");
		FilmDTO filmDTO = new FilmDTO("Inmersion", "Wim Wenders Test", 12, 111, "EE.UU.");
		assertEquals(true, controller.updateFilm(filmDTO));
	}
	
	@Test
	@PerfTest(duration = 2000)
	@Required(average = 450, max = 550)
	public void testGetMemberPoints() {
		logger.info("Test Get Member points - Getting points of a member from the DB - Valid");
		int points = controller.getMemberPoints("unaibermejofdez@opendeusto.es");
		assertEquals(30, points);
	}
	
	@Test
	@PerfTest(duration = 2000)
	@Required(percentile90 = 500, max = 550)
	public void testGetMemberPoints2() {
		logger.info("Test Get Member points - Getting points of a member from the DB - Valid");
		int points = controller.getMemberPoints("unaibermejofdez@opendeusto.es");
		assertEquals(30, points);
	}
	
	@Test
	@PerfTest(duration = 2000)
	@Required(median = 450, max = 550)
	public void testGetMemberPoints3() {
		logger.info("Test Get Member points - Getting points of a member from the DB - Valid");
		int points = controller.getMemberPoints("unaibermejofdez@opendeusto.es");
		assertEquals(30, points);
	}

	@Test
	@PerfTest(duration = 5000)
	@Required(max = 1100, average = 1000)
	public void testBuyTicket() {
		logger.info("Test Buy a ticket - Buying a ticket - Valid");
		ArrayList<String> listSeats =  new ArrayList<String>();
		listSeats.add("S3");
		TicketDTO ticketDTO = new TicketDTO("test@opendeusto.es", "Inmersion", "28-05-2018", "17:00", listSeats);
		assertEquals(true, controller.buyTicket(ticketDTO));
	}

	@Test
	@PerfTest(duration = 1000)
	@Required(max = 120, average = 30)
	public void testIdentifyMember() {
		logger.info("Test Identify a member - Logging as a member - Valid");
		assertEquals(false, controller.identifyMember("unaibermejofdez@opendeusto.es", "wrongpassword"));
		assertEquals(false, controller.identifyMember("unaibermejofdez@deusto.es", "unai"));
		assertEquals(false, controller.identifyMember("unaibermejofdez@deusto.es", "wrongpassword"));
		assertEquals(true, controller.identifyMember("unaibermejofdez@opendeusto.es", "unai"));
	}

	@Test
	@PerfTest(duration = 1000)
	@Required(max = 120, average = 30)
	public void testIdentifyEmployee() {
		logger.info("Test Identify an employee - Logging as an employee - Valid");
		assertEquals(false, controller.identifyEmployee("e1", "wrongpassword"));
		assertEquals(false, controller.identifyEmployee("wrongusername", "e1"));
		assertEquals(false, controller.identifyEmployee("wrongusername", "wrongpassword"));
		assertEquals(true, controller.identifyEmployee("e1", "e1"));
	}
	
	@Test
	@PerfTest(duration = 1000)
	@Required(max = 120, average = 30)
	public void testUpdateMember() {
		logger.info("Test Update a member - Updating a member from the DB - Valid");
		List<MemberDTO> members = null;
		MemberDTO memberDTO = new MemberDTO("ariane.fernandez@opendeusto.es", "Paloma", "Fernandez", "ariane", "26-04-1997", 0);
		controller.updateMember(memberDTO);
		members = controller.getAllMembers();
		for (int i=0;i<members.size();i++) {
			if (members.get(i).getEmail().equals("ariane.fernandez@opendeusto.es")) {
				assertEquals("Paloma", members.get(i).getName());
			}
		}
	}

	@Test
	@PerfTest(duration = 1000)
	@Required(max = 120)
	public void testDeleteMember() {
		logger.info("Test Delete a member - Deleting a member from the DB - Valid");
		List<MemberDTO> members = null;
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setBirthday("26-04-1997");
		memberDTO.setEmail("ariane.fernandez@opendeusto.es");
		memberDTO.setName("Ariane");
		memberDTO.setPassword("ariane");
		memberDTO.setSurname("Fernandez");
		memberDTO.setPoints(0);
		controller.deleteMember(memberDTO);
		members = controller.getAllMembers();
		assertEquals(4, members.size());
	}
	
	
	@Test
	@PerfTest(duration = 1000)
	@Required(max = 120, average = 30)
	public void testGetAllMembers() {
		logger.info("Test Get all members - Retrieving all the members from the DB - Valid");
		List<MemberDTO> members = null;
		members = controller.getAllMembers();
		assertEquals(5, members.size());
		assertEquals("ander.arguinano@opendeusto.es", members.get(0).getEmail());
		assertEquals("ariane.fernandez@opendeusto.es", members.get(1).getEmail());
		assertEquals("fischer.wolfgang@opendeusto.es", members.get(2).getEmail());
		assertEquals("inigogc@opendeusto.es", members.get(3).getEmail());
		assertEquals("unaibermejofdez@opendeusto.es", members.get(4).getEmail());
	}
	
	@Test
	@PerfTest(duration = 1000)
	@Required(max = 120, average = 30)
	public void testGetAllSessions() {
		logger.info("Test Get all sessions - Retrieving all the sessions from the DB - Valid");
		List<SessionDTO> sessions = null;
		sessions = controller.getAllSessions();
		assertEquals(15, sessions.size());
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
		try {
			rmiServerThread.join();
			rmiRegistryThread.join();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
}
