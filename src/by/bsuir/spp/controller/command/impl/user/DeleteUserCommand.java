package by.bsuir.spp.controller.command.impl.user;

import by.bsuir.spp.bean.User;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.UserDao;
import by.bsuir.spp.dao.impl.MySqlUserDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int userId = Integer.parseInt(request.getParameter(RequestParameterName.USER_ID));
        User user = new User();
        user.setId(userId);
        UserDao userDao = MySqlUserDao.getInstance();

        try {
            userDao.delete(user);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return new LoadUsersCommand().execute(request);
    }
}
