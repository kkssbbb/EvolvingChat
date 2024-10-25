package v1.server.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import v1.server.Session;
import v1.server.SessionManager;
import v1.server.User;

import java.io.IOException;
import java.util.Arrays;

public class ChangeCommand implements Command{

    private static final Logger log = LogManager.getLogger(ChangeCommand.class);

    @Override
    public void execute(Session session, String[] args) {

        changeName(session, args);
    }

    public void changeName(Session session, String[] args) {
        if(args.length >= 2){
            // "/change | 김 승빈" -> 이름 변경
            // args = [join,김 승빈]
            String currentName = session.getUser().getName();
            String[] nameCopy = Arrays.copyOfRange(args, 1, args.length);
            String changedName = String.join(" ", nameCopy).trim();


            if (!changedName.isEmpty()) {
                User user = session.getUser();
                user.setName(changedName);
                try {
                    session.getSessionManager().broadcast(currentName+"님 이름이 "+ "\"" + changedName + "\"" +" 으로 변경 되었습니다." );
                } catch (IOException e) {
                    throw new RuntimeException("이름 변경에 실패 하였습니다. 에러 메시지 :" + e );
                }
                //TODO : 이름이 바뀌고 이르바꾼 해당 클라이언트에 이름 바뀐거 로그(안내) 전달
            }
        }else{
            sendInvalidCommandMessage(session);
        }
    }

    @Override
    public String getCommandName() {
        return "change";
    }
}
