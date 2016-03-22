package by.bsuir.spp.controller.command;

import by.bsuir.spp.controller.command.impl.LoginCommand;
import by.bsuir.spp.controller.command.impl.LogoutCommand;
import by.bsuir.spp.controller.command.impl.RegistrationCommand;
import by.bsuir.spp.controller.command.impl.TestDataBaseCommand;
import by.bsuir.spp.controller.command.impl._package.*;
import by.bsuir.spp.controller.command.impl.advertisement.*;
import by.bsuir.spp.controller.command.impl.passport.*;
import by.bsuir.spp.controller.command.impl.prepaymentbook.*;
import by.bsuir.spp.controller.command.impl.receipt.*;
import by.bsuir.spp.controller.command.impl.searchstatement.*;
import by.bsuir.spp.controller.command.impl.user.*;

import java.util.HashMap;

public class CommandHelper {
    private CommandHelper(){}

    private static HashMap<CommandName, Command> availableCommands = new HashMap<>();
    static {
        availableCommands.put(CommandName.DB_TEST, new TestDataBaseCommand());

        availableCommands.put(CommandName.LOAD_PACKAGES, new LoadPackagesCommand());
        availableCommands.put(CommandName.ADD_PACKAGE, new AddPackageCommand());
        availableCommands.put(CommandName.DELETE_PACKAGE, new DeletePackageCommand());
        availableCommands.put(CommandName.SELECT_PACKAGE, new SelectPackageCommand());
        availableCommands.put(CommandName.UPDATE_PACKAGE, new UpdatePackageCommand());
        availableCommands.put(CommandName.GET_PACKAGES_FOR_USER, new GetPackagesForUserCommand());

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

        availableCommands.put(CommandName.LOAD_RECEIPTS, new LoadReceiptsCommand());
        availableCommands.put(CommandName.ADD_RECEIPT, new AddReceiptCommand());
        availableCommands.put(CommandName.PREPARE_DATA_FOR_CREATION_RECEIPT, new PrepareDataForReceiptCreationCommand());
        availableCommands.put(CommandName.SELECT_RECEIPT, new SelectReceiptCommand());
        availableCommands.put(CommandName.DELETE_RECEIPT, new DeleteReceiptCommand());
        availableCommands.put(CommandName.GET_USER_RECEIPTS, new GetUserReceiptsCommand());

        availableCommands.put(CommandName.PREPARE_DATA_FOR_CREATION_ADVERTISEMENT, new PrepareDataForAdvertisementCreationCommand());
        availableCommands.put(CommandName.LOAD_ADVERTISEMENTS, new LoadAdvertisementsCommand());
        availableCommands.put(CommandName.ADD_ADVERTISEMENT, new AddAdvertisementCommand());
        availableCommands.put(CommandName.DELETE_ADVERTISEMENT, new DeleteAdvertisementCommand());
        availableCommands.put(CommandName.SELECT_ADVERTISEMENT, new SelectAdvertisementCommand());
        availableCommands.put(CommandName.UPDATE_ADVERTISEMENT, new UpdateAdvertisementCommand());
        availableCommands.put(CommandName.GET_USER_ADVERTISEMENTS, new GetUserAdvertisementsCommand());

        availableCommands.put(CommandName.PREPARE_DATA_FOR_CREATION_PREPAYMENT_BOOK, new PrepareDataForPrepaymentBookCreationCommand());
        availableCommands.put(CommandName.ADD_PREPAYMENT_BOOK, new AddPrepaymentBookCommand());
        availableCommands.put(CommandName.SELECT_PREPAYMENT_BOOK, new SelectPrepaymentBookCommand());
        availableCommands.put(CommandName.LOAD_PREPAYMENT_BOOKS, new LoadPrepaymentBooksCommand());
        availableCommands.put(CommandName.UPDATE_PREPAYMENT_BOOK, new UpdatePrepaymentBookCommand());
        availableCommands.put(CommandName.DELETE_PREPAYMENT_BOOK, new DeletePrepaymentBookCommand());
        availableCommands.put(CommandName.GET_USER_PREPAYMENT_BOOKS, new GetUserPrepaymentBooksCommand());

        availableCommands.put(CommandName.PREPARE_DATA_FOR_CREATION_SEARCH_STATEMENT, new PrepareDataForSearchStatementCreation());
        availableCommands.put(CommandName.ADD_SEARCH_STATEMENT, new AddSearchStatementCommand());
        availableCommands.put(CommandName.SELECT_SEARCH_STATEMENT, new SelectSearchStatementCommand());
        availableCommands.put(CommandName.UPDATE_SEARCH_STATEMENT, new UpdateSearchStatementCommand());
        availableCommands.put(CommandName.DELETE_SEARCH_STATEMENT, new DeleteSearchStatementCommand());
        availableCommands.put(CommandName.LOAD_SEARCH_STATEMENTS, new LoadSearchStatementsCommand());
        availableCommands.put(CommandName.GET_USER_SEARCH_STATEMENTS, new GetUserSearchStatementsCommand());

        availableCommands.put(CommandName.LOGIN_COMMAND, new LoginCommand());
        availableCommands.put(CommandName.REGISTRATION_COMMAND, new RegistrationCommand());
        availableCommands.put(CommandName.LOGOUT_COMMAND, new LogoutCommand());
        availableCommands.put(CommandName.PREPARE_DATA_FOR_CREATION_PACKAGE, new PrepareDataForPackageCreation());
    }

    public static Command getCommand(String commandName) {
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        return availableCommands.get(name);
    }
}
