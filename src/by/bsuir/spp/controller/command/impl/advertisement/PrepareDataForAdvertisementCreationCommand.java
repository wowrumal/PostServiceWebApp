package by.bsuir.spp.controller.command.impl.advertisement;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.bean.User;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.PassportDao;
import by.bsuir.spp.dao.UserDao;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
import by.bsuir.spp.dao.impl.MySqlPassportDao;
import by.bsuir.spp.dao.impl.MySqlUserDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PrepareDataForAdvertisementCreationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        PackageDao packageDao = MySqlPackageDao.getInstance();
        PassportDao passportDao = MySqlPassportDao.getInstance();
        UserDao userDao = MySqlUserDao.getInstance();

        List<Integer> packageIds = packageDao.getPackageIds();
        List<Integer> passportIds = passportDao.getIdPassports();

        try {
            by.bsuir.spp.bean.document.Package packagee = packageDao.read(
                    Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID)));
            User user = userDao.read(packagee.getGetterUser().getId());
            packagee.setGetterUser(user);
            Passport passport = passportDao.read(packagee.getPassportId());
            request.setAttribute(RequestParameterName.PASSPORT, passport);
            request.setAttribute(RequestParameterName.PACKAGE, packagee);
            request.setAttribute(RequestParameterName.PACKAGE_ID, packagee.getIdPackage());
        } catch (DaoException e) {
            e.printStackTrace();
        }


       
        request.setAttribute(RequestParameterName.PASSPORT_IDS, passportIds);
        request.setAttribute(RequestParameterName.PACKAGE_IDS, packageIds);

        return JspPageName.VIEW_ADVERTISEMENT;
    }
}
