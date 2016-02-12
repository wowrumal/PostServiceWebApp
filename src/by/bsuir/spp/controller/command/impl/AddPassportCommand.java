package by.bsuir.spp.controller.command.impl;

import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;

public class AddPassportCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
