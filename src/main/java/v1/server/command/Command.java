package v1.server.command;

import v1.server.Session;

import java.io.IOException;

public interface Command {
    void execute(Session session, String[] args);
    String getCommandName();


    default void sendInvalidCommandMessage(Session session) {
        try {
            session.sendMessage("명령어가 유효하지 않습니다. 명령어가 맞는지 다시 확인해 주세요");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
