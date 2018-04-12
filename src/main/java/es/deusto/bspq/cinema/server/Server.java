package es.deusto.bspq.cinema.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.JDOHelper;
import javax.jdo.Transaction;

import es.deusto.bspq.cinema.server.remote.IRemoteFacade;
import es.deusto.bspq.cinema.server.remote.RemoteFacade;

public class Server{

	private static final long serialVersionUID = 1L;
	

	public static void main(String[] args) {
		if (args.length != 3) {
//			System.out.println("How to invoke: java [policy] [codebase] Server.Server [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			IRemoteFacade facade = new RemoteFacade();
			Naming.rebind(name, facade);
			System.out.println("Server '" + name + "' active and waiting...");
			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader (System.in);
			java.io.BufferedReader stdin = new java.io.BufferedReader (inputStreamReader);
			@SuppressWarnings("unused")
			String line  = stdin.readLine();
		} catch (Exception e) {
//			System.err.println("Hello exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
