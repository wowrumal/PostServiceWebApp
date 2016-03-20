package by.bsuir.spp.controller.command.impl;

import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = JspPageName.LOGIN_PAGE;

        HttpSession session = request.getSession();

        //session.removeAttribute(RequestParameterName.USER);

        session.invalidate();

        return page;
    }
}
