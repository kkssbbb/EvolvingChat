package v1.server.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import v1.server.Server;
import v1.server.Session;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private Map<String,Command> commands = new HashMap<>();
    private static final Logger log = LogManager.getLogger(Server.class);

    public CommandManager() {
        initializeCommands();
    }

    private void initializeCommands(){
        //CommandFactory에 등록된 모든 커맨드를 생성하고 등록
        for (String commandName : CommandFactory.getRegisteredCommandNames()) {
            try{
                Command command = CommandFactory.createCommand(commandName);
                registerCommand(command);
                log.info("Registered command: " + commandName);
            }catch (Exception e){
                log.error("Failed to register command : " + commandName, e);
                e.printStackTrace();
            }
        }
    }

    private void registerCommand(Command command) {
        commands.put(command.getCommandName(), command);
    }

    public boolean executeCommand(Session session, String command) {

        if (!command.startsWith("/") || command.equals("exit")) {
            return false;
        }
        // "/join | 김 승빈" -> parts=[join,김 승빈]
        String[] parts = command.substring(1).trim().split("\\|");
        String commandName = parts[0].trim();

        Command findedCommand  = commands.get(commandName);
        if (findedCommand != null) {
            findedCommand.execute(session,parts);
            return true;
        }

        return false;
    }

}