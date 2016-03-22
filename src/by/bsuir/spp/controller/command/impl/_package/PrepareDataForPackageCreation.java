package by.bsuir.spp.controller.command.impl._package;

import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.command.impl.user.LoadUsersCommand;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;

public class PrepareDataForPackageCreation implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Command command = new LoadUsersCommand();
        command.execute(request);
        //return JspPageName.VIEW_PACKAGE;
        return new SelectPackageCommand().execute(request);
    }
}
