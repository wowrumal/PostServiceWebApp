package by.bsuir.spp.controller;

import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.command.CommandHelper;
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
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter(RequestParameterName.COMMAND_NAME);
        Command command = CommandHelper.getCommand(commandName);
        String page = null;

        try {
            page = command.execute(req);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }
}
