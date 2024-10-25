package v1.server.command;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class CommandFactory {

    private final static Map<String,Supplier<Command>> COMMANDS = new HashMap<>();

    static{
        COMMANDS.put("join", JoinCommand::new);
        COMMANDS.put("change", ChangeCommand::new);
    }

    public static Command createCommand(String command){
        Supplier<Command> creator = COMMANDS.get(command);
        if(creator == null){
            throw new IllegalArgumentException("unknown command" + command);
        }
        return creator.get();
    }

    // 등록된 모든 커맨드 이름 목록 반환
    public static Set<String> getRegisteredCommandNames() {
        return Collections.unmodifiableSet(COMMANDS.keySet());
    }

}
