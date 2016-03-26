package by.bsuir.spp.controller.command.impl;

import by.bsuir.spp.bean.User;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.command.impl._package.GetNewPackagesCommand;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PassportDao;
import by.bsuir.spp.dao.UserDao;
import by.bsuir.spp.dao.impl.MySqlPassportDao;
import by.bsuir.spp.dao.impl.MySqlUserDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page;

        try {
            if (validateParams(request)) {
                UserDao userDao = MySqlUserDao.getInstance();
                PassportDao passportDao = MySqlPassportDao.getInstance();

                String login = request.getParameter(RequestParameterName.LOGIN_FIELD);
                String password = DigestUtils.md5Hex(request.getParameter(RequestParameterName.PASSWORD));

                User user = new User();
                user.setLogin(login);
                user.setPassword(password);

                User loggedUser = userDao.checkUser(user);

                if (loggedUser == null) {
                    return JspPageName.LOGIN_PAGE + "?" + RequestParameterName.MESSAGE + "=invalid";
                }
                else {
                    loggedUser.setPassport(passportDao.read(loggedUser.getPassport().getPassportId()));
                    request.getSession().setAttribute(RequestParameterName.USER, loggedUser);
                    switch (loggedUser.getUserRole()) {
                        case ADMIN: {
                            page = JspPageName.HOME_ADMIN_PAGE;
                            break;
                        }
                        case CLIENT: {
                            //new GetUserAdvertisementsCommand().execute(request);
                            page = JspPageName.HOME_PAGE;
                            break;
                        }

                        case POST_MANAGER: {
                            page = new GetNewPackagesCommand().execute(request);
                            break;
                        }
                        default: {
                            page = JspPageName.HOME_PAGE;
                        }
                    }
                }
            }
            else {
                page = JspPageName.LOGIN_PAGE + "?" + RequestParameterName.MESSAGE + "=invalid";
            }
        } catch (DaoException e) {
            page = JspPageName.LOGIN_PAGE;
        }


        return page;
    }

    private boolean validateParams(HttpServletRequest request) {
        String login = request.getParameter(RequestParameterName.LOGIN_FIELD);
        String password = DigestUtils.md5Hex(request.getParameter(RequestParameterName.PASSWORD));

        boolean idValid = true;

        if (login != null && password != null) {
            if (login.length() > 45) {
                idValid = false;
            }
            if (password.length() > 45) {
                idValid = false;
            }
        }
        else {
            idValid = false;
        }

        return idValid;
    }
}
