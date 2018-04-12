package es.deusto.bspq.cinema.server.remote;

import java.rmi.Remote;
import java.util.ArrayList;

public interface IRemoteFacade extends Remote{
	
	public ArrayList <FilmDTO> getFilms;

}
