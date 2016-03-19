package by.bsuir.spp.controller.command.impl.user;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.bean.User;
import by.bsuir.spp.bean.UserType;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.UserDao;
import by.bsuir.spp.dao.impl.MySqlUserDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;

public class UpdateUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        User user = new User();

        user.setId(Integer.parseInt(request.getParameter(RequestParameterName.USER_ID)));
        user.setLogin(request.getParameter(RequestParameterName.LOGIN_FIELD));
        user.setPassword(DigestUtils.md5Hex(request.getParameter(RequestParameterName.PASSWORD)));
        user.setFirstName(request.getParameter(RequestParameterName.FIRST_NAME));
        user.setSecondName(request.getParameter(RequestParameterName.SEC_NAME));
        user.setMiddleName(request.getParameter(RequestParameterName.MIDDLE_NAME));
        user.setUserRole(UserType.valueOf(request.getParameter(RequestParameterName.USER_ROLE).toUpperCase()));

        Passport passport = new Passport();
        passport.setPassportId(Integer.parseInt(request.getParameter(RequestParameterName.PASSPORT_ID)));

        user.setPassport(passport);

        UserDao userDao = MySqlUserDao.getInstance();
        try {
            userDao.update(user);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return new LoadUsersCommand().execute(request);

    }
}
