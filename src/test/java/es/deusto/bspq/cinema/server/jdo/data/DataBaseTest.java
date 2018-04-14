package es.deusto.bspq.cinema.server.jdo.data;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.*;

import es.deusto.bspq.cinema.server.Server;
import es.deusto.bspq.cinema.server.jdo.DAO.ManagerDAO;
import es.deusto.bspq.cinema.server.jdo.data.Session;
import es.deusto.bspq.cinema.server.jdo.data.SessionDTO;

public class DataBaseTest {
	
    @Test
    public void checkGetSessions() throws Exception {
    	Server server = new Server();
    	ArrayList<SessionDTO> sessionsDTO = server.getSessions();
    	assertEquals(5, sessionsDTO.size());
    	
    	ManagerDAO dao = new ManagerDAO();
    	ArrayList<Session> sessions = dao.getSessions();
    	assertEquals(5, sessions.size());
    }

}
