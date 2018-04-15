package es.deusto.bspq.cinema.client.controller;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import es.deusto.bspq.cinema.client.remote.CMServiceLocator;
import es.deusto.bspq.cinema.server.jdo.data.FilmDTO;
import es.deusto.bspq.cinema.server.jdo.data.SessionDTO;

public class CMAController {

	final static Logger logger = Logger.getLogger(CMController.class);

	private CMServiceLocator cmsl;
	
	public CMAController(String[] args) throws RemoteException {
		cmsl = new CMServiceLocator();
		cmsl.setServices(args[0], args[1], args[2]);
	}
	
	public boolean insertFilm(FilmDTO filmDTO) {
		boolean b = false;
		try {
			b = cmsl.getService().insertFilm(filmDTO);
		} catch (Exception e) {
			logger.error("Error getting sessions from server.");
		}
		return b;
	}
	
	public boolean insertSession(SessionDTO sessionDTO) {
		boolean b = false;
		try {
			b = cmsl.getService().insertSession(sessionDTO);
		} catch (Exception e) {
			logger.error("Error getting sessions from server.");
		}
		return b;
	}
 
	public void exit() {
    	System.exit(0);
    }
	
}
