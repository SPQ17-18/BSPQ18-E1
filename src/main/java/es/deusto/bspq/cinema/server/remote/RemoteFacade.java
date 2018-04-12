package es.deusto.bspq.cinema.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import es.deusto.bspq.cinema.server.jdo.DAO.IManagerDAO;
import es.deusto.bspq.cinema.server.jdo.DAO.ManagerDAO;
import es.deusto.bspq.cinema.server.jdo.data.Assembler;
import es.deusto.bspq.cinema.server.jdo.data.Film;
import es.deusto.bspq.cinema.server.jdo.data.FilmDTO;
import es.deusto.bspq.cinema.server.jdo.data.Session;
import es.deusto.bspq.cinema.server.jdo.data.SessionDTO;
import es.deusto.bspq.cinema.server.jdo.data.Ticket;
import es.deusto.bspq.cinema.server.jdo.data.TicketDTO;

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade{


	private static final long serialVersionUID = 1L;
	
	private IManagerDAO dao;
	private Assembler assembler;
	
	public RemoteFacade() throws RemoteException {
		dao = new ManagerDAO();
		assembler = new Assembler();	
	}


	public ArrayList<FilmDTO> getFilms() throws RemoteException {
		ArrayList<Film> films = dao.getFilms();		
		return assembler.assembleFilm(films);
	}


	public ArrayList<SessionDTO> getSessions(String titleFilm) throws RemoteException {
		ArrayList<Session> session = dao.getSessions(titleFilm);		
		return assembler.assembleSession(session);
	}

	
	public boolean buyTickets(TicketDTO ticketDTO) throws RemoteException {
		
		Ticket ticket = assembler.disassembleTicket(ticketDTO);
		System.out.println("I have received a ticket for the film: " + ticket.getSession().getFilm().getTitle());
		
		Session s = dao.getSession(ticket.getSession());
		System.out.println("This is the session I want to reserve: " + s.getFilm().getTitle()+" - "+s.getDate()+" "+s.getHour());

		s.addTicket(ticket);
		System.out.println("These are now the number of tickets of the season: " + s.getTickets().size());

		dao.deleteSession(s);
		dao.updateSession(s);

		return true;
	}

}
