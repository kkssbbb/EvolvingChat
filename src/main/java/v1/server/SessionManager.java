package v1.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SessionManager {

    List<Session> sessions = new ArrayList<>();

    private static final Logger log = LogManager.getLogger(SessionManager.class);

    public synchronized void  broadcast(String message) throws IOException {
        for(Session session : sessions) {
            session.sendMessage(message);
        }
    }

    public synchronized void addSession(Session session) {
        sessions.add(session);
    }
    public synchronized void removeSession(Session session) {
        sessions.remove(session);
    }
    public synchronized void closeAllSessions() {
        for(Session session : sessions){
            session.close();
        }
        sessions.clear();
    }

    public void printSessions(){
        sessions.forEach(session -> log.info("sessions Lists : " + session.toString()));
    }
}
