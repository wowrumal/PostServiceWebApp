package by.bsuir.spp.controller.command.impl;

import by.bsuir.spp.bean.Passport;
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

public class AddUserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            User user = new User();

            user.setLogin(request.getParameter(RequestParameterName.LOGIN_FIELD));
            user.setPassword(request.getParameter(RequestParameterName.PASSWORD));
            user.setFirstName(request.getParameter(RequestParameterName.FIRST_NAME));
            user.setMiddleName(request.getParameter(RequestParameterName.MIDDLE_NAME));
            user.setSecondName(request.getParameter(RequestParameterName.SEC_NAME));

            Passport passport = new Passport();
            passport.setIssueDate(java.sql.Date.valueOf(request.getParameter(RequestParameterName.ISSUING_DATE)));
            passport.setAddress(request.getParameter(RequestParameterName.ADDRESS));
            passport.setPassportNumber(request.getParameter(RequestParameterName.PASSPORT_NUMBER));

            PassportDao passportDao = MySqlPassportDao.getInstance();
            Integer idPassport = passportDao.create(passport);

            UserDao userDao = MySqlUserDao.getInstance();
            passport.setPassportId(idPassport);
            user.setPassport(passport);

            Integer idUser = userDao.create(user);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return JspPageName.INDEX;

    }
}
