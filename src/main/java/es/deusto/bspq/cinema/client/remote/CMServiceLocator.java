package es.deusto.bspq.cinema.client.remote;

import org.apache.log4j.Logger;

import es.deusto.bspq.cinema.server.remote.IRemoteFacade;

public class CMServiceLocator {
	
	final static Logger logger = Logger.getLogger(CMServiceLocator.class);

	private IRemoteFacade cinemaManagerService;

	/**
	 * Class Constructor.
	 */
	public CMServiceLocator() {

	}

	/**
	 * Sets the connection with the server.
	 * @param args0 IP
	 * @param args1 Port
	 * @param args2 Name
	 */
	public void setServices(String args0, String args1, String args2) {
		String service = "//" + args0 + ":" + args1 + "/" + args2;
		try {
			cinemaManagerService = (IRemoteFacade) java.rmi.Naming.lookup(service);
			logger.info("Server OK: " + service);
		} catch (Exception e) {
			logger.error("Error trying to set Server: " + service);
		}
	}

	/**
	 * Gets the Remote Facade to communicate with the server.
	 * @return Remote Facade
	 */
	public IRemoteFacade getService() {
		return cinemaManagerService;
	}

}
