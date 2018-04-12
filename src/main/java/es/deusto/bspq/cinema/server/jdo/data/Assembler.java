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
	
	
	public ArrayList<FlightDTO> assembleFlights(ArrayList<Flight> flights) {
		ArrayList<FlightDTO> arrDTO = new ArrayList<FlightDTO>();
		
		for (Flight flight : flights) {
			ArrayList <String> remainingSeats = flight.getFreeSeats();
			
			int num = remainingSeats.size();
			
			FlightDTO fDTO = new FlightDTO(flight.getOrigin(), flight.getDestination(), 
					flight.getFlightDate(), flight.getArrivalTime(), flight.getDepartureTime(),
					flight.getFlightCode(), remainingSeats, num, flight.getPrice());
			arrDTO.add(fDTO);
		}
		
		return arrDTO;
	}

}
