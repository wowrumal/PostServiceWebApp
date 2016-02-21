package by.bsuir.spp.controller.command;

import by.bsuir.spp.controller.command.impl.TestDataBaseCommand;
import by.bsuir.spp.controller.command.impl.passport.*;
import by.bsuir.spp.controller.command.impl.user.*;

import java.util.HashMap;

public class CommandHelper {
    private CommandHelper(){}

    private static HashMap<CommandName, Command> availableCommands = new HashMap<>();
    static {
        availableCommands.put(CommandName.DB_TEST, new TestDataBaseCommand());

        availableCommands.put(CommandName.LOAD_PASSPORTS, new LoadPassportsCommand());
        availableCommands.put(CommandName.UPDATE_PASSPORT, new UpdatePassportCommand());
        availableCommands.put(CommandName.ADD_PASSPORT, new AddPassportCommand());
        availableCommands.put(CommandName.SELECT_PASSPORT, new SelectPassportCommand());
        availableCommands.put(CommandName.DELETE_PASSPORT, new DeletePassportCommand());

        availableCommands.put(CommandName.PREPARE_DATA_FOR_CREATION_USER, new PrepareDataForUserCreationCommand());
        availableCommands.put(CommandName.ADD_USER, new AddUserCommand());
        availableCommands.put(CommandName.LOAD_USERS, new LoadUsersCommand());
        availableCommands.put(CommandName.SELECT_USER, new SelectUserCommand());
        availableCommands.put(CommandName.UPDATE_USER, new UpdateUserCommand());
        availableCommands.put(CommandName.DELETE_USER, new DeleteUserCommand());
    }

    public static Command getCommand(String commandName) {
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        return availableCommands.get(name);
    }
}
