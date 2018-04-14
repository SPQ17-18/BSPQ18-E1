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
	
		return assembler.assembleSessionS(sessions);
	}

	public boolean buyTickets(TicketDTO ticketDTO) throws RemoteException {
		return true;
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
			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader (System.in);
  			java.io.BufferedReader stdin = new java.io.BufferedReader (inputStreamReader);
  			@SuppressWarnings("unused")
 			String line  = stdin.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
