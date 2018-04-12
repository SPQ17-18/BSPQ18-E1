package es.deusto.bspq.cinema.server.jdo.data;

public class Assembler {
private ManagerDAO dao;
	
	public Assembler() {
		this.dao = new ManagerDAO();
	}

	public Reservation disassembleReservation(ReservationDTO reservDTO) {
		int codReserva = this.dao.getLastReservation();
		Reservation r = new Reservation(codReserva, reservDTO.getDate());
		Flight f = this.dao.getFlight(reservDTO.getFlightCode());
		r.setFlight(f);
		
		ArrayList<String> seatCode = reservDTO.getSeatCodList();	
		ArrayList<String> nameList = reservDTO.getFullnameList();
					
		for (int i = 0; i < nameList.size(); i++) {
			r.addSeat(new Seat(seatCode.get(i), nameList.get(i)));
		}
		
		return r;	
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
