package v1.server.command;

import v1.server.Session;
import v1.server.User;

import java.io.IOException;
import java.util.Arrays;

public class JoinCommand implements Command {

    @Override
    public void execute(Session session, String[] args) {
        if(args.length >= 2 ){
            String name = String.join(" ", Arrays.copyOfRange(args,1,args.length)).trim();
            if(!name.isEmpty()){
                session.setUser(new User(name));
            }
            try{
                session.getSessionManager().broadcast(name + "님 안녕하세요. Evolving Chat 입니다.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            sendInvalidCommandMessage(session);
        }

    }

    private void sendInvalidCommandMessage(Session session) {
        try {
            session.sendMessage("명령어가 유효하지 않습니다 '/join | {name}' 명령어가 맞는지 다시 확인해 주세요");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getCommandName() {
        return "join";
    }
}
