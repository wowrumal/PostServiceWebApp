package by.bsuir.spp.controller.command.impl.user;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.bean.UserType;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PassportDao;
import by.bsuir.spp.dao.impl.MySqlPassportDao;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PrepareDataForUserCreationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        PassportDao passportDao = MySqlPassportDao.getInstance();

        List<Passport> passports = passportDao.getAllPassports();

        request.setAttribute(RequestParameterName.PASSPORTS, passports);
        request.setAttribute(RequestParameterName.USER_ROLES, UserType.values());

        return JspPageName.VIEW_USER;
    }
}
