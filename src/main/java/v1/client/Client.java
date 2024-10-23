package v1.client;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {
    private static final Logger log = LogManager.getLogger(Client.class);
    private static final int  PORT = 12345;

    public static void main(String[] args) {

        try(Socket socket = new Socket("localhost", PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        ) {


            log.info("client start");
            log.info("listening port : " + PORT);

            //메시지 수신을 위한 별도의 스레드 생성
            new Thread(() -> {
                try {
                    while (true) {

                        String receivedMessage = in.readUTF();
                        log.info("received message : " + receivedMessage);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();

            //메인 스레드 사용자 입력 처리
            Scanner sc = new Scanner(System.in);
            String sender = null;
            while (true) {
                System.out.println("user 1 : ");
                String sendMsg = sc.nextLine();
                out.writeUTF(sendMsg);

                if(sendMsg.equals("exit")) {
                    break;
                }

            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
