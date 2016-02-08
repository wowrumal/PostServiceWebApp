package by.bsuir.spp.exception.controller.command;


import by.bsuir.spp.exception.ApplicationException;

public class CommandException extends ApplicationException {
    public CommandException(String message, Exception e) {
        super(message, e);
    }

    public CommandException(String message) {
        super(message);
    }
}
