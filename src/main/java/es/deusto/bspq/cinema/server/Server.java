package es.deusto.bspq.cinema.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import es.deusto.bspq.cinema.server.jdo.DAO.IManagerDAO;
import es.deusto.bspq.cinema.server.jdo.DAO.ManagerDAO;
import es.deusto.bspq.cinema.server.jdo.data.Assembler;
import es.deusto.bspq.cinema.server.jdo.data.Film;
import es.deusto.bspq.cinema.server.jdo.data.FilmDTO;
import es.deusto.bspq.cinema.server.jdo.data.Room;
import es.deusto.bspq.cinema.server.jdo.data.Session;
import es.deusto.bspq.cinema.server.jdo.data.SessionDTO;
import es.deusto.bspq.cinema.server.jdo.data.TicketDTO;
import es.deusto.bspq.cinema.server.remote.IRemoteFacade;

public class Server extends UnicastRemoteObject implements IRemoteFacade {
	
	private static final long serialVersionUID = 1L;
	
	private IManagerDAO dao;
	private Assembler assembler;
	
	public Server() throws RemoteException {
		dao = new ManagerDAO();
		assembler = new Assembler();	
	}

	public ArrayList<FilmDTO> getFilms() throws RemoteException {
		ArrayList<Film> films = dao.getFilms();
		
		return assembler.assembleFilm(films);
	}
	
	public ArrayList<SessionDTO> getSessions() throws RemoteException {
		ArrayList<Film> films = dao.getFilms();
		
		return assembler.assembleSession(films);
	}

	public boolean buyTickets(TicketDTO ticketDTO) throws RemoteException {
		return true;
	}
	
	
	public boolean insertFilm(FilmDTO filmDTO) throws RemoteException {
		
		try {
		Film film = assembler.disassembleFilm(filmDTO);
		
		System.out.println("Prueba Film");
		System.out.println("Titulo: "+film.getTitle());
		System.out.println("Pais: "+film.getCountry());
		System.out.println("Rating: "+film.getRating());
		
		dao.storeFilm(film);
		return true;
		}catch (Exception e) {
			return false;
		}
		
	}
	
	public boolean insertSession(SessionDTO sessionDTO) throws RemoteException {
		
		try {
		Session session = assembler.disassembleSession(sessionDTO,dao.getFilms());
		
		System.out.println("Prueba Session");
		System.out.println("Hour: "+session.getHour());
		System.out.println("Date: "+session.getDate());
		
		
		
		Film f = dao.getFilm(sessionDTO.getTitleFilm());
		
		System.out.println("Title film: "+f.getTitle());
		
		f.addSession(session);
	
//		dao.deleteRoom(sessionDTO.getRoom());
		
		//dao.deleteFilm(f);
		
		dao.storeFilm(f);
		
//		dao.deleteRoom(sessionDTO.getRoom());
//		
//		
//		
//		session.getRoom().addSession(session);
//		
//		dao.storeRoom(session.getRoom());
	
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
			System.out.println("Server '" + name + "' active and waiting...");
			FilmDTO f = new FilmDTO("Maria de la o", "Paco Salas", 15, 123, "España");
			server.insertFilm(f);
			SessionDTO s = new SessionDTO("15-04-2018","13:00",(float)3.40,5,60,"Maria de la o");
			server.insertSession(s);
			
			ArrayList<FilmDTO> films = server.getFilms();
			
			for (int i = 0;i<films.size();i++)
			{
				System.out.println("Pelicula "+(i+1));
				System.out.println("Title: "+films.get(i).getTitle());
				System.out.println("Director: "+films.get(i).getDirector());
				
			}	
			
			ArrayList<SessionDTO> sessions = server.getSessions();
			
			for (int j=0;j<sessions.size();j++) {
				System.out.println("Session "+(j+1));
				System.out.println("Date: "+sessions.get(j).getDate());
				System.out.println("Hour: "+sessions.get(j).getHour());
				System.out.println("Film: " +sessions.get(j).getTitleFilm());
				System.out.println("Room: "+sessions.get(j).getRoom());
				
			}
			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader (System.in);
  			java.io.BufferedReader stdin = new java.io.BufferedReader (inputStreamReader);
  			@SuppressWarnings("unused")
 			String line  = stdin.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
