package es.deusto.bspq.cinema.client.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.log4j.Logger;

import es.deusto.bspq.cinema.client.remote.CMServiceLocator;
import es.deusto.bspq.cinema.server.jdo.data.FilmDTO;
import es.deusto.bspq.cinema.server.jdo.data.MemberDTO;
import es.deusto.bspq.cinema.server.jdo.data.SessionDTO;
import es.deusto.bspq.cinema.server.jdo.data.TicketDTO;

public class CMController {
	
	final static Logger logger = Logger.getLogger(CMController.class);

	private CMServiceLocator cmsl;
	/**
	   * Class Constructor.
	   * @param args The command line arguments.
	   * @throws RemoteExeption
	   */
	public CMController(String[] args) throws RemoteException {
		cmsl = new CMServiceLocator();
		cmsl.setServices(args[0], args[1], args[2]);
	}
	/**
	   * Inserts a film to the DB.
	   * @param filmDTO Data Container.
	   * @return Successful or not.
	   */
	public boolean insertFilm(FilmDTO filmDTO) {
		boolean film = false;
		try {
			film = cmsl.getService().insertFilm(filmDTO);
		} catch (Exception e) {
			logger.error("Error inserting a film.");
		}
		return film;
	}
	/**
	   * Inserts a session to the DB.
	   * @param sessionDTO Data Container.
	   * @return Successful or not.
	   */
	public boolean insertSession(SessionDTO sessionDTO) {
		boolean session = false;
		try {
			session = cmsl.getService().insertSession(sessionDTO);
		} catch (Exception e) {
			logger.error("Error inserting a session.");
		}
		return session;
	}
	/**
	   * Updates a session.
	   * @param sessionDTO Data Container.
	   * @return Successful or not.
	   */
	public boolean updateSession(SessionDTO sessionDTO) {
		boolean updated = false;
		try {
			updated = cmsl.getService().updateSession(sessionDTO);
		} catch (RemoteException e) {
			logger.error("Error updating a session.");
		}
		return updated;
	}
	/**
	   * Deletes a session.
	   * @param sessionDTO Data Container.
	   * @return Successful or not.
	   */
	public boolean deleteSession(SessionDTO sessionDTO) {
		boolean deleted = false;
		try {
			deleted = cmsl.getService().deleteSession(sessionDTO);
		} catch (RemoteException e) {
			logger.error("Error deleting a session.");
		}
		return deleted;
	}
	/**
	   * Gets all the sessions of the DB.
	   * @return Successful or not.
	   */
	public List<SessionDTO> getAllSessions() {
		List<SessionDTO> sessions = null;
		try {
			sessions = cmsl.getService().getSessions();
		} catch (RemoteException e) {
			logger.error("Error getting sessions from server.");
		}
		return sessions;
	}
	/**
	   * Gets all the films of the DB.
	   * @return Successful or not.
	   */
	public List<FilmDTO> getAllFilms() {
		List<FilmDTO> films = null;
		try {
			films = cmsl.getService().getFilms();
		} catch (RemoteException e) {
			logger.error("Error getting films from server.");
		}
		return films;
	}
	/**
	   * Buy a ticket.
	   * @param sessionDTO Data Container.
	   * @return Successful or not.
	   */
	public boolean buyTicket(TicketDTO ticketDTO) {
		boolean ticket = false;
		try {
			ticket = cmsl.getService().buyTickets(ticketDTO);
		} catch (RemoteException e) {
			logger.error("Error buying a ticket.");
		}
		return ticket;
	}
	/**
	   * Identifies a member.
	   * @param email Email of the member.
	   * @param password Password of the member.
	   * @return Successful or not.
	   */
	public boolean identifyMember(String email, String password) {
		boolean login = false;
		try {
			login = cmsl.getService().loginMember(email, password);
		} catch (RemoteException e) {
			logger.error("Error identifying a member.");
		}
		return login;
	}
	/**
	   * Identifies a employee.
	   * @param email Email of the employee.
	   * @param password Password of the employee.
	   * @return Successful or not.
	   */
	public boolean identifyEmployee(String email, String password) {
		boolean login = false;
		try {
			login = cmsl.getService().loginEmployee(email, password);
		} catch (RemoteException e) {
			logger.error("Error identifying a employee.");
		}
		return login;
	}
	/**
	   * Gets all the members from server.
	   * @return Successful or not.
	   */
	public List<MemberDTO> getAllMembers() {
		List<MemberDTO> members = null;
		try {
			members = cmsl.getService().getMembers();
		} catch (RemoteException e) {
			logger.error("Error getting members from server.");
		}
		return members;
	}
	/**
	   * Cancels a membership.
	   * @param email Email of the member.
	   * @param password Password of the member
	   * @return Successful or not.
	   */
	public boolean cancelMembership(String email, String password) {
		boolean cancel = false;
		try {
			cancel = cmsl.getService().cancelMembership(email, password);
		} catch (RemoteException e) {
			logger.error("Error canceling a membership.");
		}
		return cancel;
	}
	/**
	   * Updates a member.
	   * @param memberDTO Data Container.
	   * @return Successful or not.
	   */
	public boolean updateMember(MemberDTO memberDTO) {
		boolean updated = false;
		try {
			updated = cmsl.getService().updateMember(memberDTO);
		} catch (RemoteException e) {
			logger.error("Error updating a member.");
		}
		return updated;
	}
	/**
	   * Deletes a member.
	   * @param memberDTO Data Container.
	   * @return Successful or not.
	   */
	public boolean deleteMember(MemberDTO memberDTO) {
		boolean deleted = false;
		try {
			deleted = cmsl.getService().deleteMember(memberDTO);
		} catch (RemoteException e) {
			logger.error("Error deleting a member.");
		}
		return deleted;
	}
	/**
	   * Register a member.
	   * @param memberDTO Data Container.
	   * @return Successful or not.
	   */
	public boolean registerMember(MemberDTO memberDTO) {
		boolean registered = false;
		try {
			registered = cmsl.getService().registerMember(memberDTO);
		} catch (RemoteException e) {
			logger.error("Error registering a member.");
		}
		return registered;
	}
	/**
	   * Exits current program.
	   */
	public void exit() {
		System.exit(0);
	}
	
}
