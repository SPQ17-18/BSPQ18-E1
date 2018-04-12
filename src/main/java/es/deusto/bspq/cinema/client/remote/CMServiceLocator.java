package es.deusto.bspq.cinema.client.remote;

import org.apache.log4j.Logger;

import es.deusto.bspq.cinema.client.gui.LoginWindow;

public class CMServiceLocator {
	
	final static Logger logger = Logger.getLogger(CMServiceLocator.class);

//	private ICinemaManager cinemaManagerService;

	public CMServiceLocator() {

	}

	public void setServices(String args0, String args1, String args2) {
		try {
			String service = "//" + args0 + ":" + args1 + "/" + args2;
//			ICinemaManager server = (ICinemaManager) java.rmi.Naming.lookup(service);
			logger.info("Server OK.");
		} catch (Exception e) {
			logger.info("Error trying to set Server.");
		}
	}

//	public ICinemaManager getService() {
//		return cinemaManagerService;
//	}

}
