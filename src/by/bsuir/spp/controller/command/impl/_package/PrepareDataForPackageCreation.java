package by.bsuir.spp.controller.command.impl._package;

import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.command.impl.user.LoadUsersCommand;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PrepareDataForPackageCreation implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Command command = new LoadUsersCommand();
        command.execute(request);

        List<String> packageTypes = new ArrayList<>();
        packageTypes.add("Письмо");
        packageTypes.add("Бандероль");
        packageTypes.add("Крупногабаритная");

        request.setAttribute(RequestParameterName.PACKAGE_TYPES, packageTypes);

        //return JspPageName.VIEW_PACKAGE;
        if (request.getParameter(RequestParameterName.SUB_COMMAND) != null) {
            return JspPageName.VIEW_PACKAGE;
        }
        return new SelectPackageCommand().execute(request);
    }
}
