package v1.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Session implements Runnable {

    private static final Logger log = LogManager.getLogger(Server.class);

    private final Socket socket;
    private final SessionManager sessionManager;
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;
    private final boolean closed = false;

    public Session(Socket socket, SessionManager sessionManager) throws IOException {
        this.socket = socket;
        this.sessionManager = sessionManager;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try{
            while (true){
                sessionManager.printSessions();
                //받고
                String receivedMsg = dataInputStream.readUTF();
                if(receivedMsg.equals("exit")){
                    break;
                }
                //뿌리기

                sessionManager.broadcast(receivedMsg);

            }
        } catch (IOException e) {
            log.info(e.getMessage());
        }finally {
            sessionManager.removeSession(this);
            close();
        }
    }

    public synchronized void sendMessage(String message) throws IOException {
        dataOutputStream.writeUTF(message);
    }


    public synchronized void close(){

        if(closed) return;
        new SocketCloseUtil().closeAll(socket,dataInputStream,dataOutputStream);
        log.info("Session closed complete");

    }
}