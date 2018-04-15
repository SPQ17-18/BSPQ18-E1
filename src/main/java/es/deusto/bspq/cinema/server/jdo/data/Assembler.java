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

	public Film disassembleFilm(FilmDTO filmDTO) {

		Film f = new Film();

		f.setCountry(filmDTO.getCountry());

		f.setDirector(filmDTO.getDirector());

		f.setDuration(filmDTO.getDuration());

		f.setRating(filmDTO.getRating());

		f.setSessions(new ArrayList<Session>());

		f.setTitle(filmDTO.getTitle());

		return f;
	}

	public Session disassembleSession(SessionDTO sessionDTO, ArrayList<Film> films) {

		Session s = new Session();

		s.setDate(sessionDTO.getDate());

		s.setHour(sessionDTO.getHour());

		s.setPrice(sessionDTO.getPrice());

		s.setSession(dao.getLastSessionCode());

		System.out.println("LAST SESSION CODE: " + s.getSession());

		s.setRoom(new Room());

		s.getRoom().copyRoom(dao.getRoom(sessionDTO.getRoom()));

		System.out.println("ROOM: " + s.getRoom().getRoomNumber());

//		System.out.println("Sessions: ");

//		for (int i = 0; i < films.size(); i++) {
//			for (int j = 0; j < films.get(i).getSessions().size(); j++) {
//				if (films.get(i).getSessions().get(j).getRoom().getRoomNumber()==sessionDTO.getRoom()){
//					s.getRoom().getSessions().add(films.get(i).getSessions().get(j));
//					System.out.println("Date: " + s.getRoom().getSessions().get(i).getDate());
//				}
//				
//			}
//		}

		return s;

	}

	public ArrayList<FilmDTO> assembleFilm(ArrayList<Film> films) {
		ArrayList<FilmDTO> filmDTO = new ArrayList<FilmDTO>();

		for (int i = 0; i < films.size(); i++) {
			FilmDTO fDTO = new FilmDTO(films.get(i).getTitle(), films.get(i).getDirector(), films.get(i).getRating(),
					films.get(i).getDuration(), films.get(i).getCountry());
			filmDTO.add(fDTO);
		}

		return filmDTO;
	}

	public ArrayList<SessionDTO> assembleSession(ArrayList<Film> films) {
		ArrayList<SessionDTO> sessionDTO = new ArrayList<SessionDTO>();

		for (int i = 0; i < films.size(); i++) {

			for (int j = 0; j < films.get(i).getSessions().size(); j++) {
				ArrayList<String> seats = new ArrayList<String>();
				sessionDTO.add(new SessionDTO(films.get(i).getSessions().get(j).getDate(),
						films.get(i).getSessions().get(j).getHour(), films.get(i).getSessions().get(j).getPrice(),
						films.get(i).getSessions().get(j).getRoom().getRoomNumber(),
						films.get(i).getSessions().get(j).getRoom().getNumberSeats(), films.get(i).getTitle(),
						films.get(i).getDirector(), films.get(i).getRating(), films.get(i).getDuration(),
						films.get(i).getCountry(), seats));
			}
		}

		return sessionDTO;
	}

	private ArrayList<String> obtainSeats(Session session) {

		ArrayList<String> seats = new ArrayList<String>();

		for (int i = 0; i < session.getTickets().size(); i++) {

			for (int j = 0; j < session.getTickets().get(i).getSeats().size(); j++) {

				seats.add(session.getTickets().get(i).getSeats().get(j).getSeatCode());
			}

		}

		return seats;

	}
}
