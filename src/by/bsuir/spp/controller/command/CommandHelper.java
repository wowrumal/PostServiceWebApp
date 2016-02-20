package by.bsuir.spp.controller.command;

import by.bsuir.spp.controller.command.impl.AddUserCommand;
import by.bsuir.spp.controller.command.impl.passport.*;
import by.bsuir.spp.controller.command.impl.TestDataBaseCommand;

import java.util.HashMap;

public class CommandHelper {
    private CommandHelper(){}

    private static HashMap<CommandName, Command> availableCommands = new HashMap<>();
    static {
        availableCommands.put(CommandName.DB_TEST, new TestDataBaseCommand());
        availableCommands.put(CommandName.ADD_USER, new AddUserCommand());
        availableCommands.put(CommandName.LOAD_PASSPORTS, new LoadPassportsCommand());
        availableCommands.put(CommandName.UPDATE_PASSPORT, new UpdatePassportCommand());
        availableCommands.put(CommandName.ADD_PASSPORT, new AddPassportCommand());
        availableCommands.put(CommandName.SELECT_PASSPORT, new SelectPassportCommand());
        availableCommands.put(CommandName.DELETE_PASSPORT, new DeletePassportCommand());
    }

    public static Command getCommand(String commandName) {
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        return availableCommands.get(name);
    }
}
