package by.bsuir.spp.controller.command.impl.user;

import by.bsuir.spp.bean.User;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PassportDao;
import by.bsuir.spp.dao.UserDao;
import by.bsuir.spp.dao.impl.MySqlPassportDao;
import by.bsuir.spp.dao.impl.MySqlUserDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LoadUsersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        UserDao userDao = MySqlUserDao.getInstance();
        PassportDao passportDao = MySqlPassportDao.getInstance();
        List<User> users = userDao.getAllUsers();

        for (User user : users) {
            try {
                user.setPassport(passportDao.read(user.getPassport().getPassportId()));
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
        request.setAttribute(RequestParameterName.USERS, users);

        return JspPageName.USERS;
    }
}
