package v1.server.command;

import v1.server.Session;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private Map<String,Command> commands = new HashMap<>();

    public CommandManager() {
        registerCommand(new JoinCommand());
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