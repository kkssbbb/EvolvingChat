package v1.server.command;

import v1.server.Session;

public interface Command {
    void execute(Session session, String[] args);
    String getCommandName();
}
