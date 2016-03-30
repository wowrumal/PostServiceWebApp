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
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RegistrationCommand implements Command {

    private static final String SALT = "Random$SaltValue#WithSpecialCharacters12@$@4&#%^$*";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;

        if (validateParams(request)) {
            UserDao userDao = MySqlUserDao.getInstance();
            PassportDao passportDao = MySqlPassportDao.getInstance();


            Passport passport = new Passport();

            passport.setPassportNumber(getRequestParam(request, RequestParameterName.PASSPORT_NUMBER));
            passport.setAddress(getRequestParam(request, RequestParameterName.PASSPORT_ADDRESS));
            try {
                passport.setIssueDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter(RequestParameterName.ISSUING_DATE)) );
            } catch (ParseException e) {
                e.printStackTrace();
            }

            passport.setIssuingInstitution(getRequestParam(request, RequestParameterName.INSTITUTION));

            try {
                int passportId = passportDao.create(passport);
                passport.setPassportId(passportId);
                User user = new User();

                user.setFirstName(getRequestParam(request, RequestParameterName.FIRST_NAME));
                user.setLogin(getRequestParam(request, RequestParameterName.LOGIN_FIELD));
                user.setPassword(DigestUtils.md5Hex(getRequestParam(request, RequestParameterName.PASSWORD) + SALT));
                user.setSecondName(getRequestParam(request, RequestParameterName.SEC_NAME));
                user.setMiddleName(getRequestParam(request, RequestParameterName.MIDDLE_NAME));
                user.setPassport(passport);

                if (!userDao.checkLogin(user.getLogin())) {
                    page = JspPageName.REGISTRATION + "?" + RequestParameterName.MESSAGE + "=login_exist";
                }
                else {
                    int userId = userDao.create(user);
                    user.setId(userId);
                    page = JspPageName.LOGIN_PAGE;
                }

            } catch (DaoException e) {
                page = JspPageName.REGISTRATION + "?" + RequestParameterName.MESSAGE + "=error";
            }
        }
        else {
            page = JspPageName.REGISTRATION + "?" + RequestParameterName.MESSAGE + "=invalid";
        }

        return page;
    }

    private boolean validateParams(HttpServletRequest request)
    {

        if (getRequestParam(request, RequestParameterName.LOGIN_FIELD) == null ||
                getRequestParam(request, RequestParameterName.PASSWORD) == null ||
                getRequestParam(request, RequestParameterName.FIRST_NAME) == null ||
                getRequestParam(request, RequestParameterName.SEC_NAME) == null ||
                getRequestParam(request, RequestParameterName.MIDDLE_NAME) == null ||
                getRequestParam(request, RequestParameterName.PASSPORT_NUMBER) == null ||
                getRequestParam(request, RequestParameterName.PASSPORT_ADDRESS) == null ||
                getRequestParam(request, RequestParameterName.INSTITUTION) == null ||
                getRequestParam(request, RequestParameterName.ISSUING_DATE) == null)
        {
            return false;
        }

        if (getRequestParam(request, RequestParameterName.LOGIN_FIELD).length() > 45 ||
                getRequestParam(request, RequestParameterName.PASSWORD).length() > 45 ||
                getRequestParam(request, RequestParameterName.FIRST_NAME).length() > 45 ||
                getRequestParam(request, RequestParameterName.SEC_NAME).length() > 45 ||
                getRequestParam(request, RequestParameterName.MIDDLE_NAME).length() > 45 ||
                getRequestParam(request, RequestParameterName.PASSPORT_NUMBER).length() > 45 ||
                getRequestParam(request, RequestParameterName.PASSPORT_ADDRESS).length() > 45 ||
                getRequestParam(request, RequestParameterName.INSTITUTION).length() > 200)
        {
            return false;
        }

        try {
            new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter(RequestParameterName.ISSUING_DATE));
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    private String getRequestParam(HttpServletRequest request, String parameterName) {
        return request.getParameter(parameterName);
    }
}
