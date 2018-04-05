package es.deusto.bspq.cinema.client.remote;

public class CMServiceLocator {

//	private ICinemaManager cinemaManagerService;

	public CMServiceLocator() {

	}

	public void setServices(String args0, String args1, String args2) {
		try {

			String service = "//" + args0 + ":" + args1 + "/" + args2;

//			cinemaManagerService = (ICinemaManager) java.rmi.Naming.lookup(service);
			
//			System.out.println("Cinema Manager Service OK.");

		} catch (Exception e) {
//			System.out.println("Error trying to set Cinema Manager Service.");
		}
	}

//	public ICinemaManager getService() {
//		return cinemaManagerService;
//	}

}
