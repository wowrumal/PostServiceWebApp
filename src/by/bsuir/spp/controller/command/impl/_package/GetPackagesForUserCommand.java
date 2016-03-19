package by.bsuir.spp.controller.command.impl._package;

import by.bsuir.spp.bean.User;
import by.bsuir.spp.bean.document.Package;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetPackagesForUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = JspPageName.LOGIN_PAGE;

        try {
            int passportId = ((User)request.getSession().getAttribute(RequestParameterName.USER)).getPassport().getPassportId();
            PackageDao packageDao = MySqlPackageDao.getInstance();
            List<Package> packages = packageDao.getPackagesByPassportId(passportId);
            request.setAttribute(RequestParameterName.PACKAGES, packages);

            page = JspPageName.USER_PACKAGES;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return page;
    }
}
