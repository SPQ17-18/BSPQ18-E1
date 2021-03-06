package es.deusto.bspq.cinema.server.jdo.data;

import java.util.ArrayList;

import es.deusto.bspq.cinema.server.jdo.DAO.ManagerDAO;

/**
 * Class destinated o transform DTO objects
 * 
 * @author anderarguinano
 *
 */
public class Assembler {
	/**
	 * Manager for the DB
	 */
	private ManagerDAO dao;

	/**
	 * Constructor for the assembler with the DB Manager
	 */
	public Assembler() {
		this.dao = new ManagerDAO();
	}

	/**
	 * Method for disassembling the ticket (From ticketDTO to ticket)
	 * 
	 * @param ticketDTO
	 *            TicketDTO we want to disassemble
	 * @return Returns the TicketDTO transformed to Ticket
	 */
	public Ticket disassembleTicket(TicketDTO ticketDTO) {
		Ticket t = new Ticket();
		ArrayList<Seat> seats = new ArrayList<>();
		for (int i = 0; i < ticketDTO.getListSeats().size(); i++) {
			seats.add(new Seat(ticketDTO.getListSeats().get(i)));
		}
		t.addSeats(seats);
		return t;
	}

	/**
	 * Method for disassembling the film
	 * 
	 * @param filmDTO
	 *            FilmDTO we want to disassemble
	 * @return Returns the FilmDTO transformed to Film
	 */
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

	/**
	 * Method for disassembling the member
	 * 
	 * @param memberDTO
	 *            MemberDTO we want to disassemble
	 * @return Returns the MemberDTO transformed to Member
	 */
	public Member disassembleMember(MemberDTO memberDTO) {
		Member m = new Member();
		m.setBirthday(memberDTO.getBirthday());
		m.setEmail(memberDTO.getEmail());
		m.setName(memberDTO.getName());
		m.setPassword(memberDTO.getPassword());
		m.setSurname(memberDTO.getSurname());
		m.setPoints(memberDTO.getPoints());
		return m;
	}

	/**
	 * Method for disassembling the employee
	 * 
	 * @param employeeDTO
	 *            EmployeeDTO we want to disassemble
	 * @return Returns the EmployeeDTO transformed to Employee
	 */
	public Employee disassembleEmployee(EmployeeDTO employeeDTO) {
		Employee e = new Employee();
		e.setName(employeeDTO.getName());
		e.setPassword(employeeDTO.getPassword());
		e.setSalary(employeeDTO.getSalary());
		e.setSurname(employeeDTO.getSurname());
		e.setUsername(employeeDTO.getUsername());
		return e;
	}

	/**
	 * Method for disassembling the session in order to add new one
	 * 
	 * @param sessionDTO
	 *            SessionDTO we want to transform
	 * @return Returns the SessionDTO transformed to Session
	 */
	public Session disassembleSession(SessionDTO sessionDTO) {
		Session s = new Session();
		s.setHour(sessionDTO.getHour());
		s.setPrice(sessionDTO.getPrice());
		s.setSession(dao.getLastSessionCode());
		s.setDate(sessionDTO.getDate());
		return s;
	}

	/**
	 * Method for disassembling the session in order to delete
	 * 
	 * @param sessionDTO
	 *            SessionDTO we want to transform
	 * @return Returns the SessionDTO transformed to Session
	 */
	public Session disassembleSessionDEL(SessionDTO sessionDTO) {
		Session s = new Session();
		s.setHour(sessionDTO.getHour());
		s.setPrice(sessionDTO.getPrice());
		s.setSession(dao.getSessionCode(sessionDTO.getDate(), sessionDTO.getHour(), sessionDTO.getTitleFilm()));
		s.setDate(sessionDTO.getDate());
		return s;
	}

	/**
	 * Method for disassembling the session in order to update
	 * 
	 * @param sessionDTO
	 *            SessionDTO we want to transform
	 * @return Returns the SessionDTO transformed to Session
	 */
	public Session disassembleSessionD(SessionDTO sessionDTO) {
		Session s = new Session();
		s.setHour(sessionDTO.getHour());
		s.setPrice(sessionDTO.getPrice());
		s.setSession(sessionDTO.getSession());
		s.setDate(sessionDTO.getDate());
		s.setFilm(dao.getFilm(sessionDTO.getTitleFilm()));
		s.setRoom(dao.getRoom(sessionDTO.getRoom()));
		return s;
	}

	/**
	 * Method for assembling the Film
	 * 
	 * @param films
	 *            ArrayList of Films we want to assemble
	 * @return Returns the ArrayList of Films transformed to an ArrayList of
	 *         FilmDTOs
	 */
	public ArrayList<FilmDTO> assembleFilm(ArrayList<Film> films) {
		ArrayList<FilmDTO> filmDTO = new ArrayList<FilmDTO>();
		for (int i = 0; i < films.size(); i++) {
			FilmDTO fDTO = new FilmDTO(films.get(i).getTitle(), films.get(i).getDirector(), films.get(i).getRating(),
					films.get(i).getDuration(), films.get(i).getCountry());
			filmDTO.add(fDTO);
		}
		return filmDTO;
	}

	/**
	 * Method for assembling the Members
	 * 
	 * @param members
	 *            ArrayList of Members to transform
	 * @return Returns the ArrayList of Members transformed to an ArrayList of
	 *         MemberDTOs
	 */
	public ArrayList<MemberDTO> assembleMember(ArrayList<Member> members) {
		ArrayList<MemberDTO> memberDTO = new ArrayList<MemberDTO>();
		for (int i = 0; i < members.size(); i++) {
			MemberDTO mDTO = new MemberDTO(members.get(i).getEmail(), members.get(i).getName(),
					members.get(i).getSurname(), members.get(i).getPassword(), members.get(i).getBirthday(),
					members.get(i).getPoints());
			memberDTO.add(mDTO);
		}
		return memberDTO;
	}

	/**
	 * Method for assembling Sessions
	 * 
	 * @param films
	 *            ArrayList of films from which we will obtain the Sessions
	 * @return Returns the ArrayList of Sessions transformed to SessionDTOs
	 */
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
						films.get(i).getCountry(), seats, films.get(i).getSessions().get(j).getSession()));
				sessionDTO.get(sessionDTO.size() - 1)
						.setRemainingSeatsCode(obtainSeats(films.get(i).getSessions().get(j)));
				sessionDTO.get(sessionDTO.size() - 1).setSession(films.get(i).getSessions().get(j).getSession());
			}
		}
		return sessionDTO;
	}

	/**
	 * Method for obtaining the free seats
	 * 
	 * @param session
	 *            Session from which obtain the free seats
	 * @return Returns an ArrayList of free seats code
	 */
	private ArrayList<String> obtainSeats(Session session) {
		ArrayList<String> seatsBuyed = new ArrayList<String>();
		for (int i = 0; i < session.getTickets().size(); i++) {
			for (int j = 0; j < session.getTickets().get(i).getSeats().size(); j++) {
				seatsBuyed.add(session.getTickets().get(i).getSeats().get(j).getSeatCode());
			}

		}
		ArrayList<String> seatsRemaining = new ArrayList<String>();
		char a = 65;
		int contador = 0;
		for (int i = 0; i < session.getRoom().getNumberSeats(); i++) {
			if (contador < 10) {
				contador = contador + 1;
				seatsRemaining.add("" + a + (contador));
			} else {
				a++;
				i--;
				contador = 0;
			}
		}
		for (int i = 0; i < seatsBuyed.size(); i++) {
			for (int j = 0; j < seatsRemaining.size(); j++) {
				if (seatsBuyed.get(i).equals(seatsRemaining.get(j))) {
					seatsRemaining.remove(j);
					j = 0;
				}
			}
		}
		return seatsRemaining;
	}

	/**
	 * Method for assembling the employeee
	 * 
	 * @param employee
	 *            Employee we would like to transform
	 * @return Returns the Employee transformed to EmployeeDTO
	 */
	public EmployeeDTO assembleEmployee(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setName(employee.getName());
		employeeDTO.setPassword(employee.getPassword());
		employeeDTO.setSalary(employee.getSalary());
		employeeDTO.setSurname(employee.getSurname());
		employeeDTO.setUsername(employee.getUsername());
		return employeeDTO;
	}

}
