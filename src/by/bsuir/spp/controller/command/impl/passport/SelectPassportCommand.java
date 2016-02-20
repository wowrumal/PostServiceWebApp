package by.bsuir.spp.controller.command.impl.passport;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PassportDao;
import by.bsuir.spp.dao.impl.MySqlPassportDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;

public class SelectPassportCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int passportId = Integer.parseInt(request.getParameter(RequestParameterName.PASSPORT_ID));
        PassportDao passportDao = MySqlPassportDao.getInstance();
        Passport passport = null;
        try {
            passport = passportDao.read(passportId);
            request.setAttribute(RequestParameterName.PASSPORT_NUMBER, passport.getPassportNumber());
            request.setAttribute(RequestParameterName.PASSPORT_ADDRESS, passport.getAddress());
            request.setAttribute(RequestParameterName.INSTITUTION, passport.getIssuingInstitution());
            request.setAttribute(RequestParameterName.ISSUING_DATE, passport.getIssueDate());
        } catch (DaoException e) {
            e.printStackTrace();
        }
        if (passport != null) {
            request.setAttribute(RequestParameterName.PASSPORT, passport);
        }

        return JspPageName.VIEW_PASSPORT;
    }
}
