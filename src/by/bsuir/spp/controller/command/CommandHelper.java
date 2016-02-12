package by.bsuir.spp.controller.command;

import by.bsuir.spp.controller.command.impl.AddUserCommand;
import by.bsuir.spp.controller.command.impl.TestDataBaseCommand;

import java.util.HashMap;

public class CommandHelper {
    private CommandHelper(){}

    private static HashMap<CommandName, Command> availableCommands = new HashMap<>();
    static {
        availableCommands.put(CommandName.DB_TEST, new TestDataBaseCommand());
        availableCommands.put(CommandName.ADD_USER, new AddUserCommand());
    }

    public static Command getCommand(String commandName) {
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        return availableCommands.get(name);
    }
}
