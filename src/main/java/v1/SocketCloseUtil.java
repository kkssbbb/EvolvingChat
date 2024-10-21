package v1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketCloseUtil {
    private static final Logger log = LogManager.getLogger(SocketCloseUtil.class);

    public void closeAll(Socket Socket, DataInputStream in, DataOutputStream out) {
        close(Socket);
        close(in);
        close(out);
    }

    public void close(Socket socket){
        if(socket != null){
            try {
                socket.close();
                log.info("socket close complete");
            }catch (IOException e){
                log.info(e.getMessage());
            }
        }
    }
    public void close(DataInputStream in){
        if(in != null){
            try {
                in.close();
                log.info("in close complete");
            }catch (IOException e){
                log.info(e.getMessage());
            }
        }
    }
    public void close(DataOutputStream out){
        if(out != null){
            try{
                out.close();
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }
    }

}

