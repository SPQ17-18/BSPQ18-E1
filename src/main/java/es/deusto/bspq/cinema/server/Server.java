package es.deusto.bspq.cinema.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import es.deusto.bspq.cinema.server.jdo.DAO.IManagerDAO;
import es.deusto.bspq.cinema.server.jdo.DAO.ManagerDAO;
import es.deusto.bspq.cinema.server.jdo.data.Assembler;
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
	
	private static final Logger logger = Logger.getLogger(Server.class.getName());

	private IManagerDAO dao;
	private Assembler assembler;	
	
	public Server() throws RemoteException {
		dao = new ManagerDAO();
		assembler = new Assembler();	
	}
	

	public boolean registerMember(MemberDTO memberDTO) throws RemoteException {
		try {
			Member member = assembler.disassembleMember(memberDTO);
			dao.storeMember(member);
			logger.log(Level.INFO, "Inserted a member to the DB called "+memberDTO.getName());
			return true;
			}catch (Exception e) {
				logger.log(Level.ERROR, "Primary key duplicated: User already exits");
				return false;
			}
			
	}
	
	public boolean loginMember (String email, String password) throws RemoteException {
		try {
			Member m = dao.getMember(email);
			
			if (m.getPassword().equals(password)) {
				logger.log(Level.INFO,"User with email "+email+" logined succesfully");
				return true;
			}else {
				logger.log(Level.INFO, "Password incorrect");
				return false;
				
			}
			
			}catch (Exception e) {
				logger.log(Level.ERROR, "User with email "+email+" doesnt exist");
				return false;
			}
			
	}

	public Session getSession(TicketDTO ticketDTO, ArrayList <Film> films){

		Session s = new Session();
		for (int i = 0; i < films.size(); i++) {	
			if(ticketDTO.getTitleFilm().equals(films.get(i).getTitle())) {
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
		logger.log(Level.INFO, "Client asked for the films");
		
		return assembler.assembleFilm(films);
	
	}
	
	public ArrayList<SessionDTO> getSessions() throws RemoteException {

		ArrayList<Film> films = dao.getFilms();
		logger.log(Level.INFO, "Client asked for the sessions");
		return assembler.assembleSession(films);

	}

	public boolean buyTickets(TicketDTO ticketDTO) throws RemoteException {
		
		Ticket t = assembler.disassembleTicket(ticketDTO);
		Session s = getSession(ticketDTO, dao.getFilms());
		dao.insertTicket( t,s.getSession(),ticketDTO.getEmail());
		logger.log(Level.INFO, "Client"+ticketDTO.getEmail()+" buyed a ticket of "+ticketDTO.getListSeats().size()+" seats for the film "+ticketDTO.getTitleFilm());
		return true;
	
	}
	
	
	public boolean insertFilm(FilmDTO filmDTO) throws RemoteException {
		
		try {
		Film film = assembler.disassembleFilm(filmDTO);
		dao.storeFilm(film);
		logger.log(Level.INFO, "Inserted a film to the DB called "+filmDTO.getTitle());
		return true;
		}catch (Exception e) {
			return false;
		}
		
	}
	
	public boolean insertSession(SessionDTO sessionDTO) throws RemoteException {
		
		try {
		Session session = assembler.disassembleSession(sessionDTO,dao.getSessions());
		Film f = dao.getFilm(sessionDTO.getTitleFilm());
		logger.log(Level.INFO, "Inserted a session to the DB of the film "+sessionDTO.getTitleFilm()+" for the day "+sessionDTO.getDate());
		dao.insertSession(session,f.getTitle(), sessionDTO.getRoom());
		return true;
		}catch (Exception e) {
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
			logger.log(Level.INFO,"Server '" + name + "' active and waiting...");
			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader (System.in);
  			java.io.BufferedReader stdin = new java.io.BufferedReader (inputStreamReader);
  			@SuppressWarnings("unused")
 			String line  = stdin.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	
}
