package es.deusto.bspq.cinema.client.controller;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.Test;

import es.deusto.bspq.cinema.server.Server;
import es.deusto.bspq.cinema.server.jdo.data.MemberDTO;
import es.deusto.bspq.cinema.server.remote.IRemoteFacade;
import junit.framework.JUnit4TestAdapter;


import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;


@SuppressWarnings("unused")
public class CMControllerTest {
	
	private static final Logger logger = Logger.getLogger(CMControllerTest.class);


	private static String cwd = CMControllerTest.class.getProtectionDomain().getCodeSource().getLocation().getFile();
	private static Thread rmiRegistryThread = null;
	private static Thread rmiServerThread = null;

	private IRemoteFacade server;

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CMControllerTest.class);
	}

	@BeforeClass
	static public void setUp() {
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

		class RMIServerRunnable implements Runnable {

			public void run() {
				logger.info(
						"This is a test to check how mvn test executes this test without external interaction; JVM properties by program");
				logger.info("**************: " + cwd);
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
		try {
			System.setProperty("java.security.policy", "target//test-classes//security//java.policy");

			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}

			String name = "//127.0.0.1:1099/CinemaManager";
			logger.info("BeforeTest - Setting the client ready for calling name: " + name);
			server = (IRemoteFacade) java.rmi.Naming.lookup(name);
		} catch (Exception re) {
			logger.error(" # Client RemoteException: " + re.getMessage());
			// re.printStackTrace();
			System.exit(-1);
		}

	}


//	@Test
//	public void testInsertFilm() {
//		fail("Not yet implemented");
//	}

//	@Test
//	public void testInsertSession() {
//		fail("Not yet implemented");
//	}

//	@Test
//	public void testGetAllSessions() {
//		fail("Not yet implemented");
//	}

//	@Test
//	public void testBuyTicket() {
//		fail("Not yet implemented");
//	}

//	@Test
//	public void testIdentifyMember() {
//		fail("Not yet implemented");
//	}

//	@Test
//	public void testIdentifyEmployee() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetAllMembers() {
		logger.info("Test GET ALL MEMBERS - Retrieving all the members from the DB - Valid");
		List<MemberDTO> members = null;
		try {
			members =server.getMembers();
		} catch (RemoteException e) {
			logger.error("Error trying to get all the members");
		}
		assertEquals(6, members.size());
		assertEquals("ander.arguinano@opendeusto.es", members.get(0).getEmail());
		assertEquals("ariane.fernandez@opendeusto.es", members.get(1).getEmail());
		assertEquals("fischer.wolfgang@opendeusto.es", members.get(2).getEmail());
		assertEquals("inigogc@opendeusto.es", members.get(3).getEmail());
		assertEquals("unaibermejofdez@opendeusto.es", members.get(5).getEmail());
		
	}

	@Test
	public void testUpdateMember() {
		logger.info("Test Update a member - Updating a member from the DB - Valid");
		List<MemberDTO> members = null;
		try {
			MemberDTO memberDTO = new MemberDTO("ariane.fernandez@opendeusto.es", "Paloma", "Fernandez", "ariane", "26-04-1997", 0);
			server.updateMember(memberDTO);
			members = server.getMembers();
			
		} catch (RemoteException e) {
			logger.error("Error trying to update a member");
		}
		for (int i=0;i<members.size();i++) {
			if (members.get(i).getEmail().equals("ariane.fernandez@opendeusto.es"))
			{
				assertEquals("Paloma",members.get(i).getName());
			}
		}
	}

	@Test
	public void testDeleteMember() {
		
		logger.info("Test Delete a member - Deleting a member from the DB - Valid");
		List<MemberDTO> members = null;
		try {
			MemberDTO memberDTO = new MemberDTO("ariane.fernandez@opendeusto.es", "Ariane", "Fernandez", "ariane", "26-04-1997");
			server.deleteMember(memberDTO);
			members = server.getMembers();
			
		} catch (RemoteException e) {
			logger.error("Error trying to delete a member");
		}
		for (int i=0;i<members.size();i++) {
			assertNotEquals("ariane.fernandez@opendeusto.es", members.get(i).getEmail());
		}
	}


	@AfterClass
	static public void tearDown() {
		try {
			rmiServerThread.join();
			rmiRegistryThread.join();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

	}
}
