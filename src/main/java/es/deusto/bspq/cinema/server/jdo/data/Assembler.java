package es.deusto.bspq.cinema.server.jdo.data;

import java.util.ArrayList;

import es.deusto.bspq.cinema.server.jdo.DAO.ManagerDAO;

public class Assembler {
	
	public Assembler() {
	}
	
	public ArrayList<SessionDTO> assembleSessions(ArrayList<Session> sessions) {
		ArrayList<SessionDTO> sessionsDTO = new ArrayList<SessionDTO>();
		
		for (Session session : sessions) {
//			SessionDTO sDTO = new SessionDTO(session.getDate(),session.getHour(),session.getPrice(),
//					session.getRoom().getRoomNumber(),session.getRoom().getNumberSeats(),
//					session.getFilm().getTitle(),session.getSeats());
			ArrayList<String> a = new ArrayList<String>();
			a.add("S1");
			a.add("S2");
			a.add("S3");
			SessionDTO sDTO = new SessionDTO("A","B",1,2,3,"C",a);
			sessionsDTO.add(sDTO);
		}
		
		return sessionsDTO;
	}

}
