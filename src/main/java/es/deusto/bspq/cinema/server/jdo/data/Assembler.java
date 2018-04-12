package es.deusto.bspq.cinema.server.jdo.data;


import java.util.ArrayList;


import es.deusto.bspq.cinema.server.jdo.DAO.ManagerDAO;

public class Assembler {

	private ManagerDAO dao;
	
	public Assembler() {
		this.dao = new ManagerDAO();
	}

	public Ticket disassembleTicket(TicketDTO ticketDTO) {
		
		Ticket t = new Ticket();
		
		Member m = this.dao.getMember(ticketDTO.getEmail());
		
		t.setMember(m);
		
		Session s = this.dao.getSession(ticketDTO.getTitleFilm(), ticketDTO.getDate(), ticketDTO.getHour());
		t.setSession(s);
		
		
		ArrayList<String> seatCode = ticketDTO.getListSeats();
		
					
		for (int i = 0; i < seatCode.size(); i++) {
			t.addSeat(new Seat(seatCode.get(i)));
		}
		
		return t;	
	}
	
	
	public ArrayList<FilmDTO> assembleFilm(ArrayList<Film> films) {
		ArrayList<FilmDTO> filmDTO = new ArrayList<FilmDTO>();
		
		for (Film film : films) {
	
			ArrayList <String> actors = (ArrayList)film.getActors();
			
			FilmDTO fDTO = new FilmDTO(film.getTitle(),film.getDirector(),film.getRating(),film.getDuration(),film.getCountry(),actors);
			filmDTO.add(fDTO);
		}
		
		return filmDTO;
	}
	
	public ArrayList<SessionDTO> assembleSession(ArrayList<Session> sessions) {
		ArrayList<SessionDTO> sessionDTO = new ArrayList<SessionDTO>();
		
		for (Session session : sessions) {
	
			ArrayList<String> seats = obtainSeats (session);
			
			SessionDTO sDTO = new SessionDTO(session.getDate(),session.getHour(),session.getPrice(),session.getRoom().getRoomNumber(),session.getRoom().getNumberSeats(),session.getFilm().getTitle(),seats);
			sessionDTO.add(sDTO);
		}
		
		return sessionDTO;
	}

	private ArrayList <String> obtainSeats (Session session){
		
		ArrayList <String> seats = new ArrayList<String>();
		
		for (int i=0;i<session.getTickets().size();i++) {
			
			for (int j=0;j<session.getTickets().get(i).getSeats().size();j++) {
				
				seats.add(session.getTickets().get(i).getSeats().get(j).getSeatCode());
			}
			
		}
		
		return seats;
		
	}
}
