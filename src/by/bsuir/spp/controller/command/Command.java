package by.bsuir.spp.controller.command;

import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request) throws CommandException;
}
