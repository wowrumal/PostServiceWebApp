package by.bsuir.spp.controller;

import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.command.CommandHelper;
import by.bsuir.spp.controller.command.CommandName;
import by.bsuir.spp.controller.command.impl.LogoutCommand;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*resp.setHeader("Cache-Control", "no-cache, no-store");
        resp.setHeader("Pragma", "no-cache");
        String page = null;
        String commandName = req.getParameter(RequestParameterName.COMMAND_NAME);
        if (commandName == null) {
            page = JspPageName.LOGIN_PAGE;
            resp.sendRedirect(page);
        }
        else {
            Command command = CommandHelper.getCommand(commandName);
            try {
                page = command.execute(req);
            } catch (CommandException e) {
                e.printStackTrace();
            }
            if (page != null) {
                resp.sendRedirect(page);
            }
        }*/

        String commandName = req.getParameter(RequestParameterName.COMMAND_NAME);
        Command command = CommandHelper.getCommand(commandName);
        String page = null;

        try {
            if (command == null || command.getClass() == LogoutCommand.class) {
                new LogoutCommand().execute(req);
                resp.sendRedirect(JspPageName.LOGIN_PAGE);
                return;
            }
            else {
                page = command.execute(req);
            }
        } catch (CommandException e) {
            e.printStackTrace();
        }


        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter(RequestParameterName.COMMAND_NAME);
        Command command = CommandHelper.getCommand(commandName);
        String page = null;

        try {
            if (command == null) {
                new LogoutCommand().execute(req);
                resp.sendRedirect(JspPageName.LOGIN_PAGE);
                return;
            }
            else {
                page = command.execute(req);

                switch (CommandName.valueOf(commandName.toUpperCase())) {
                    case LOGIN_COMMAND : {}
                    case REGISTRATION_COMMAND: {
                        resp.sendRedirect(page);
                        return;
                    }
                }

            }
        } catch (CommandException e) {
            e.printStackTrace();
        }

        resp.sendRedirect(page);
        //return;
        /*RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, resp);*/
    }
}
