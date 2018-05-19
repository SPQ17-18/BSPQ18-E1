package es.deusto.bspq.cinema.server;

import java.rmi.Naming;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.deusto.bspq.cinema.server.jdo.DAO.IManagerDAO;
import es.deusto.bspq.cinema.server.jdo.DAO.ManagerDAO;
import es.deusto.bspq.cinema.server.jdo.data.Assembler;
import es.deusto.bspq.cinema.server.jdo.data.Employee;
import es.deusto.bspq.cinema.server.jdo.data.EmployeeDTO;
import es.deusto.bspq.cinema.server.jdo.data.Film;
import es.deusto.bspq.cinema.server.jdo.data.FilmDTO;
import es.deusto.bspq.cinema.server.jdo.data.Member;
import es.deusto.bspq.cinema.server.jdo.data.MemberDTO;
import es.deusto.bspq.cinema.server.jdo.data.Session;
import es.deusto.bspq.cinema.server.jdo.data.SessionDTO;
import es.deusto.bspq.cinema.server.jdo.data.Ticket;
import es.deusto.bspq.cinema.server.jdo.data.TicketDTO;
import es.deusto.bspq.cinema.server.mail.MailSender;
import es.deusto.bspq.cinema.server.remote.IRemoteFacade;

/**
 * Class for the server
 * 
 * @author anderarguinano
 *
 */
public class Server extends UnicastRemoteObject implements IRemoteFacade {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(Server.class);

	private IManagerDAO dao;
	private Assembler assembler;

	/**
	 * Constructor for the server
	 * 
	 * @throws RemoteException
	 *             Throws a exception in case an error occurs
	 */
	public Server() throws RemoteException {
		dao = new ManagerDAO();
		assembler = new Assembler();
	}

	/**
	 * Method to update a film
	 * 
	 * @param filmDTO
	 *            Data to update the film
	 * @throws RemoteException
	 *             Throws a exception in case an error occurs
	 * @result Returns true when the film is correctly updated
	 */
	public boolean updateFilm(FilmDTO filmDTO) throws RemoteException {
		try {
			Film film = assembler.disassembleFilm(filmDTO);
			dao.updateFilm(film);
			logger.info("Updated the film with the title " + film.getTitle());
			return true;
		} catch (Exception e) {
			logger.error("Error updating the film");
			return false;
		}
	}

	/**
	 * Method to delete a film
	 * 
	 * @param title
	 *            Title of the film we want to delete
	 * @throws RemoteException
	 *             Throws a exception in case an error occurs
	 * @result Returns true when the film is correctly deleted
	 */
	public boolean deleteFilm(String title) throws RemoteException {
		try {

			dao.deleteFilm(title);
			logger.info("Deleted the film with the title " + title);
			return true;
		} catch (Exception e) {
			logger.error("Error deleting the film");
			return false;
		}

	}

	/**
	 * Method to update the session
	 * 
	 * @param sessionDTO
	 *            Data to update the session
	 * @throws RemoteException
	 *             Throws a exception in case an error occurs
	 * @result Returns true when the session is correctly updated
	 */
	public boolean updateSession(SessionDTO sessionDTO) throws RemoteException {
		try {
			Session session = assembler.disassembleSessionD(sessionDTO);
			dao.updateSession(session);
			logger.info("Updated the session with the code " + session.getSession());
			return true;
		} catch (Exception e) {
			logger.error("Error updating the session");
			return false;
		}
	}

	/**
	 * Method to get the points of the member
	 * 
	 * @param email
	 *            Email to obtain the points
	 * @throws RemoteException
	 *             Throws a exception in case an error occurs
	 * @result Returns the points of the member
	 */
	public int getMemberPoints(String email) throws RemoteException {
		int points = -1;
		try {
			points = dao.getMemberPoints(email);
			logger.info("Obtaining points of the member  " + email);
		} catch (Exception e) {
			logger.error("Error obtaining points of the member " + email);
		}
		return points;
	}

	/**
	 * Method to delete a session
	 * 
	 * @param sessionDTO
	 *            The session we want to delete
	 * @throws RemoteException
	 *             Throws a exception in case an error occurs
	 * @result Returns true when the session is correctly deleted
	 */
	public boolean deleteSession(SessionDTO sessionDTO) throws RemoteException {
		try {
			Session session = assembler.disassembleSessionDEL(sessionDTO);
			dao.deleteSession(session);
			logger.info("Deleted the session with the code " + session.getSession());
			return true;
		} catch (Exception e) {
			logger.error("Error deleting the session");
			return false;
		}

	}

	/**
	 * Method to get the sessions
	 * 
	 * @throws RemoteException
	 *             Throws a exception in case an error occurs
	 * @result Returns a list with the sessions
	 */
	public ArrayList<SessionDTO> getSessions() throws RemoteException {
		ArrayList<Film> films = dao.getFilms();
		logger.info("Client asked for the sessions");
		return assembler.assembleSession(films);
	}

	/**
	 * Method to get the films
	 * 
	 * @throws RemoteException
	 *             Throws a exception in case an error occurs
	 * @result Returns a list with the films
	 */
	public ArrayList<FilmDTO> getFilms() throws RemoteException {
		ArrayList<Film> films = dao.getFilms();
		logger.info("Client asked for the films");
		return assembler.assembleFilm(films);

	}

	/**
	 * Method to get the members
	 * 
	 * @throws RemoteException
	 *             Throws a exception in case an error occurs
	 * @result Returns a list with the members
	 */
	public ArrayList<MemberDTO> getMembers() throws RemoteException {
		ArrayList<Member> members = dao.getMembers();
		logger.info("Client asked for the members");
		return assembler.assembleMember(members);
	}

	/**
	 * Method to buy a ticket
	 * 
	 * @param ticketDTO
	 *            Data of the ticket
	 * @throws RemoteException
	 *             Throws a exception in case an error occurs
	 * @result Returns true when the ticket is correctly bought
	 */
	public boolean buyTickets(TicketDTO ticketDTO) throws RemoteException {
		Ticket t = assembler.disassembleTicket(ticketDTO);
		Session s = getSession(ticketDTO, dao.getFilms());
		dao.insertTicket(t, s.getSession(), ticketDTO.getEmail());

		MailSender mail = new MailSender(ticketDTO.getEmail());
		String text = "You have bought " + ticketDTO.getListSeats().size() + " tickets for the film "
				+ ticketDTO.getTitleFilm() + ":\n";
		text = text + "Title Film: " + ticketDTO.getTitleFilm() + "\n";
		text = text + "Seats: ";
		for (int i = 0; i < ticketDTO.getListSeats().size(); i++) {
			if (i == ticketDTO.getListSeats().size() - 1) {
				text = text + ticketDTO.getListSeats().get(i) + "\n";
			} else {
				text = text + ticketDTO.getListSeats().get(i) + ", ";
			}
		}
		text = text + "Date: " + ticketDTO.getDate() + "\tHour: " + ticketDTO.getHour();
		mail.sendMessage(text, "Ticket for the film " + ticketDTO.getTitleFilm());
		logger.info("Client " + ticketDTO.getEmail() + " buyed a ticket of " + ticketDTO.getListSeats().size()
				+ " seats for the film " + ticketDTO.getTitleFilm());
		return true;
	}

	/**
	 * Method to get a concrete session
	 * 
	 * @param ticketDTO
	 *            Data of a ticket
	 * @param films
	 *            List of films
	 * @return Returns a session
	 */
	private Session getSession(TicketDTO ticketDTO, ArrayList<Film> films) {
		Session session = new Session();
		for (int i = 0; i < films.size(); i++) {
			if (ticketDTO.getTitleFilm().equals(films.get(i).getTitle())) {
				for (int j = 0; j < films.get(i).getSessions().size(); j++) {
					if (films.get(i).getSessions().get(j).getHour().equals(ticketDTO.getHour())) {
						if (films.get(i).getSessions().get(j).getDate().equals(ticketDTO.getDate())) {
							session.copySession(films.get(i).getSessions().get(j));
						}
					}
				}
			}
		}
		return session;
	}

	/**
	 * Method to login as an employee
	 * 
	 * @param username
	 *            Username of the employee
	 * @param password
	 *            Password of the employee
	 * @throws RemoteException
	 *             Throws a exception in case an error occurs
	 * @result Returns true when the film is correctly updated
	 */
	public boolean loginEmployee(String username, String password) throws RemoteException {
		try {
			Employee e = dao.getEmployee(username);
			if (e.getPassword().equals(password)) {
				logger.info("Employee with username " + username + " logged succesfully");
				return true;
			} else {
				logger.info("Password incorrect");
				return false;
			}
		} catch (Exception e) {
			logger.error("Employee with username " + username + " doesnt exist");
			return false;
		}
	}

	/**
	 * Method to login as a member
	 * 
	 * @param email
	 *            Email of the member
	 * @param password
	 *            Password of the member
	 * @throws RemoteException
	 *             Throws a exception in case an error occurs
	 * @result Returns true when the login is correctly done
	 */
	public boolean loginMember(String email, String password) throws RemoteException {
		try {
			Member m = dao.getMember(email);
			if (m.getPassword().equals(password)) {
				logger.info("Member with email " + email + " logged succesfully");
				return true;
			} else {
				logger.info("Password incorrect");
				return false;
			}

		} catch (Exception e) {
			logger.error("User with email " + email + " doesnt exist");
			return false;
		}
	}

	/**
	 * Method to register as an employee
	 * 
	 * @param employeeDTO
	 *            Data to register
	 * @throws RemoteException
	 *             Throws a exception in case an error occurs
	 * @result Returns true when the employee is correctly registered
	 */
	public boolean registerEmployee(EmployeeDTO employeeDTO) throws RemoteException {
		try {
			Employee employee = assembler.disassembleEmployee(employeeDTO);
			dao.storeEmployee(employee);
			logger.info("Inserted an employee to the DB called " + employeeDTO.getName());
			return true;
		} catch (Exception e) {
			logger.error("Primary key duplicated: Employee already exits");
			return false;
		}
	}

	/**
	 * Method to register as a member
	 * 
	 * @param memberDTO
	 *            Data to register
	 * @throws RemoteException
	 *             Throws a exception in case an error occurs
	 * @result Returns true when the member is correctly registered
	 */
	public boolean registerMember(MemberDTO memberDTO) throws RemoteException {
		try {
			Member member = assembler.disassembleMember(memberDTO);
			dao.storeMember(member);
			logger.info("Inserted a member to the DB called " + memberDTO.getName());

			MailSender mail = new MailSender(memberDTO.getEmail());
			mail.sendMessage(
					"Welcome to our community! \nWe are very glad to have you here, "
							+ "now you can buy tickets or manage your personal information with our app. ",
					"Welcome " + memberDTO.getName() + " to our community");

			return true;
		} catch (Exception e) {
			logger.error("Primary key duplicated: Member already exits");
			return false;
		}
	}

	/**
	 * Method to delete an employee
	 * 
	 * @param username
	 *            Username to delete an employee
	 * @throws RemoteException
	 *             Throws a exception in case an error occurs
	 * @result Returns true when the film is correctly updated
	 */
	public boolean cancelEmployee(String username) throws RemoteException {
		try {
			Employee e = dao.getEmployee(username);
			dao.deleteEmployee(e);
			logger.info("Employee with username " + username + " deleted succesfully");
			return true;
		} catch (Exception e) {
			logger.error("Employee with username " + username + " doesnt exist");
			return false;
		}
	}

	/**
	 * Method to delete a member
	 * 
	 * @param email
	 *            Email of the member
	 * @param password
	 *            Password of the member
	 * @throws RemoteException
	 *             Throws a exception in case an error occurs
	 * @result Returns true when the member is correctly deleted
	 */
	public boolean cancelMembership(String email, String password) throws RemoteException {
		try {
			Member m = dao.getMember(email);
			if (m.getPassword().equals(password)) {
				dao.deleteMember(m);
				logger.info("Member with email " + email + " deleted succesfully");
				return true;
			} else {
				logger.info("Password incorrect, we cannot delete the user");
				return false;
			}
		} catch (Exception e) {
			logger.error("Member with email " + email + " doesnt exist");
			return false;
		}
	}

	/**
	 * Method to insert a film
	 * 
	 * @param filmDTO
	 *            Data to insert the film
	 * @throws RemoteException
	 *             Throws a exception in case an error occurs
	 * @result Returns true when the film is correctly inserted
	 */
	public boolean insertFilm(FilmDTO filmDTO) throws RemoteException {
		try {
			Film film = assembler.disassembleFilm(filmDTO);
			dao.storeFilm(film);
			logger.info("Inserted a film to the DB called " + filmDTO.getTitle());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Method to insert a session
	 * 
	 * @param sessionDTO
	 *            Data to insert a session
	 * @throws RemoteException
	 *             Throws a exception in case an error occurs
	 * @result Returns true when the session is correctly inserted
	 */
	public boolean insertSession(SessionDTO sessionDTO) throws RemoteException {
		try {
			Session session = assembler.disassembleSession(sessionDTO);
			Film f = dao.getFilm(sessionDTO.getTitleFilm());
			logger.info("Inserted a session to the DB of the film " + sessionDTO.getTitleFilm() + " for the day "
					+ sessionDTO.getDate());
			dao.insertSession(session, f.getTitle(), sessionDTO.getRoom());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Method to update a member
	 * 
	 * @param memberDTO
	 *            Data to update a member
	 * @throws RemoteException
	 *             Throws a exception in case an error occurs
	 * @result Returns true when the member is correctly updated
	 */
	public boolean updateMember(MemberDTO memberDTO) throws RemoteException {
		try {
			Member member = assembler.disassembleMember(memberDTO);
			dao.manageMember(member);
			logger.info("Updated the member with the email " + memberDTO.getEmail());
			return true;
		} catch (Exception e) {
			logger.error("Error updating the member");
			return false;
		}
	}

	/**
	 * Method to delete the member
	 * 
	 * @param memberDTO
	 *            Data to delete the member
	 * @throws RemoteException
	 *             Throws a exception in case an error occurs
	 * @result Returns true when the member is correctly deleted
	 */
	public boolean deleteMember(MemberDTO memberDTO) throws RemoteException {
		try {
			Member member = assembler.disassembleMember(memberDTO);
			dao.deleteMember(member);
			logger.info("Deleted the member with the email " + memberDTO.getEmail());
			return true;
		} catch (Exception e) {
			logger.error("Error deleting the member");
			return false;
		}
	}

	public static void main(String[] args) {
		if (args.length != 3) {
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			IRemoteFacade server = new Server();
			Naming.rebind(name, server);
			logger.info("Server '" + name + "' active and waiting...");
			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader(System.in);
			java.io.BufferedReader stdin = new java.io.BufferedReader(inputStreamReader);
			@SuppressWarnings("unused")
			String line = stdin.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
