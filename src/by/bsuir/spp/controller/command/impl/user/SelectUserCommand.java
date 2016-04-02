package by.bsuir.spp.controller.command.impl.user;

import by.bsuir.spp.bean.User;
import by.bsuir.spp.bean.UserType;
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

public class SelectUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int userId = Integer.parseInt(request.getParameter(RequestParameterName.USER_ID));
        UserDao userDao = MySqlUserDao.getInstance();
        PassportDao passportDao = MySqlPassportDao.getInstance();
        User user = null;

        try {
            user = userDao.read(userId);
            user.setPassport(passportDao.read(user.getPassport().getPassportId()));
        } catch (DaoException e) {
            e.printStackTrace();
        }

        if (user != null) {
            request.setAttribute(RequestParameterName.USERR, user);
        }

        request.setAttribute(RequestParameterName.ADMINS_COUNT, getAdminsCount(userDao.getAllUsers()));

        if (request.getParameter(RequestParameterName.SUB_COMMAND) != null){
            return new PrepareDataForUserCreationCommand().execute(request);
        }
        else
            return JspPageName.USER;

    }

    private Integer getAdminsCount(List<User> users) {
        int count = 0;

        for (User user : users) {
            if (user.getUserRole() == UserType.ADMIN) {
                count++;
            }
        }

        return count;
    }
}
