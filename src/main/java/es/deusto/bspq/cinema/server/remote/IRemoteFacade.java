package es.deusto.bspq.cinema.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import es.deusto.bspq.cinema.server.jdo.data.EmployeeDTO;
import es.deusto.bspq.cinema.server.jdo.data.FilmDTO;
import es.deusto.bspq.cinema.server.jdo.data.MemberDTO;
import es.deusto.bspq.cinema.server.jdo.data.SessionDTO;
import es.deusto.bspq.cinema.server.jdo.data.TicketDTO;

public interface IRemoteFacade extends Remote{	
	
	public boolean deleteSession (SessionDTO sessionDTO) throws RemoteException;
	public boolean updateMember (MemberDTO memberDTO) throws RemoteException;
	public boolean insertFilm (FilmDTO filmDTO) throws RemoteException;
	public boolean insertSession (SessionDTO sessionDTO) throws RemoteException;
	public boolean deleteMember (MemberDTO memberDTO) throws RemoteException;
	public ArrayList<FilmDTO> getFilms() throws RemoteException;
	public ArrayList<SessionDTO> getSessions() throws RemoteException;
	public ArrayList<MemberDTO> getMembers() throws RemoteException;
	public boolean buyTickets(TicketDTO ticketDTO) throws RemoteException;
	public boolean loginMember(String email, String password) throws RemoteException;
	public boolean loginEmployee(String username, String password) throws RemoteException;
	public boolean registerMember (MemberDTO memberDTO) throws RemoteException;
	public boolean registerEmployee (EmployeeDTO employeeDTO) throws RemoteException;
	public boolean cancelMembership (String email, String password) throws RemoteException;
	public boolean cancelEmployee (String username) throws RemoteException;
	
}
