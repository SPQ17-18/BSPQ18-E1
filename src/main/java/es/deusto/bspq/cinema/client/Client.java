package es.deusto.bspq.cinema.client;

import org.apache.log4j.Logger;

import es.deusto.bspq.cinema.client.gui.LoginWindow;

public class Client {
	
	final static Logger logger = Logger.getLogger(Client.class);

	public static void main(String[] args) {
		if (args.length != 3) {
			logger.error("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		final LoginWindow loginWindow = new LoginWindow(args);
		loginWindow.centreWindow();
		loginWindow.setVisible(true);
	}
	
}
