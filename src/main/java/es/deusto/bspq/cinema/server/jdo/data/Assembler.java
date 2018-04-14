package es.deusto.bspq.cinema.server.jdo.data;

import java.util.ArrayList;

import es.deusto.bspq.cinema.server.jdo.DAO.ManagerDAO;

public class Assembler {

	private ManagerDAO dao;
	
	public Assembler() {
		this.dao = new ManagerDAO();
	}
	
	public ArrayList<SessionDTO> assembleSession(ArrayList<Session> sessions) {
		ArrayList<SessionDTO> sessionDTO = new ArrayList<SessionDTO>();
		
		for (Session session : sessions) {
	
			ArrayList<String> seats = session.getSeats();
			
			SessionDTO sDTO = new SessionDTO(session.getDate(),session.getHour(),session.getPrice(),
					session.getRoom().getRoomNumber(),session.getRoom().getNumberSeats(),
					session.getFilm().getTitle(),seats);
			sessionDTO.add(sDTO);
		}
		
		return sessionDTO;
	}

}
