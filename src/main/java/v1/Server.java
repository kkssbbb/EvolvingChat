package v1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    private static final int PORT = 12345;
    private static final Logger log = LogManager.getLogger(Server.class);
    public static void main(String[] args) throws IOException {
        log.info("start server");
        ServerSocket serverSocket = new ServerSocket(PORT);
        log.info("server port: " + PORT);

        SessionManager sessionManager = new SessionManager();
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                Session session = new Session(socket,sessionManager);
                sessionManager.addSession(session);
                new Thread(session).start();

            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
