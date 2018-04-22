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
import es.deusto.bspq.cinema.server.remote.IRemoteFacade;

public class Server extends UnicastRemoteObject implements IRemoteFacade {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(Server.class);

	private IManagerDAO dao;
	private Assembler assembler;

	public Server() throws RemoteException {
		dao = new ManagerDAO();
		assembler = new Assembler();
	}

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

	public boolean registerMember(MemberDTO memberDTO) throws RemoteException {
		try {
			Member member = assembler.disassembleMember(memberDTO);
			dao.storeMember(member);
			logger.info("Inserted a member to the DB called " + memberDTO.getName());
			return true;
		} catch (Exception e) {
			logger.error("Primary key duplicated: User already exits");
			return false;
		}

	}

	public boolean cancelMembership(String email, String password) throws RemoteException {

		try {
			Member m = dao.getMember(email);

			if (m.getPassword().equals(password)) {
				dao.deleteMember(m);
				logger.info("User with email " + email + " deleted succesfully");
				return true;
			} else {
				logger.info("Password incorrect, we cannot delete the user");
				return false;

			}

		} catch (Exception e) {
			logger.error("User with email " + email + " doesnt exist");
			return false;
		}

	}

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

	public boolean loginMember(String email, String password) throws RemoteException {
		try {
			Member m = dao.getMember(email);

			if (m.getPassword().equals(password)) {
				logger.info("User with email " + email + " logined succesfully");
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

	public Session getSession(TicketDTO ticketDTO, ArrayList<Film> films) {

		Session s = new Session();
		for (int i = 0; i < films.size(); i++) {
			if (ticketDTO.getTitleFilm().equals(films.get(i).getTitle())) {
				for (int j = 0; j < films.get(i).getSessions().size(); j++) {
					if (films.get(i).getSessions().get(j).getHour().equals(ticketDTO.getHour())) {
						if (films.get(i).getSessions().get(j).getDate().equals(ticketDTO.getDate())) {
							s.copySession(films.get(i).getSessions().get(j));
						}
					}
				}
			}
		}
		return s;

	}

	public ArrayList<FilmDTO> getFilms() throws RemoteException {

		ArrayList<Film> films = dao.getFilms();
		logger.info("Client asked for the films");

		return assembler.assembleFilm(films);

	}

	public ArrayList<SessionDTO> getSessions() throws RemoteException {

		ArrayList<Film> films = dao.getFilms();
		logger.info("Client asked for the sessions");
		return assembler.assembleSession(films);

	}

	public boolean buyTickets(TicketDTO ticketDTO) throws RemoteException {

		Ticket t = assembler.disassembleTicket(ticketDTO);
		Session s = getSession(ticketDTO, dao.getFilms());
		dao.insertTicket(t, s.getSession(), ticketDTO.getEmail());
		logger.info("Client" + ticketDTO.getEmail() + " buyed a ticket of " + ticketDTO.getListSeats().size()
				+ " seats for the film " + ticketDTO.getTitleFilm());
		return true;

	}

	public boolean loginEmployee(String username, String password) throws RemoteException {

		try {
			Employee e = dao.getEmployee(username);

			if (e.getPassword().equals(password)) {
				logger.info("Employee with username " + username + " logined succesfully");
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

	public boolean insertSession(SessionDTO sessionDTO) throws RemoteException {

		try {
			Session session = assembler.disassembleSession(sessionDTO, dao.getSessions());
			Film f = dao.getFilm(sessionDTO.getTitleFilm());
			logger.info("Inserted a session to the DB of the film " + sessionDTO.getTitleFilm() + " for the day "
					+ sessionDTO.getDate());
			dao.insertSession(session, f.getTitle(), sessionDTO.getRoom());
			return true;
		} catch (Exception e) {
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
