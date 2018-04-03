package es.deusto.bspq.cinema.server.jdo;

import javax.jdo.annotations.PersistenceCapable;
import java.util.Date;

/**
* This is a sample Object, just for the purpose of showing some pieces of code related to JDO functionalities
*
*/
@PersistenceCapable
public class Message {
	
	User user = null;
	String text = null;
	long timestamp;

    public Message(String text) {
        this.text = text;
		this.timestamp = System.currentTimeMillis();
    }

	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        return "Message: message --> " + this.text + ", timestamp -->  " + new Date(this.timestamp);
    }
}