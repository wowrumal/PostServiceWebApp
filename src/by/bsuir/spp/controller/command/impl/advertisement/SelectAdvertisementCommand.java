package by.bsuir.spp.controller.command.impl.advertisement;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.bean.User;
import by.bsuir.spp.bean.document.Advertisement;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.AdvertisementDao;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.PassportDao;
import by.bsuir.spp.dao.UserDao;
import by.bsuir.spp.dao.impl.MySqlAdvertisementDao;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
import by.bsuir.spp.dao.impl.MySqlPassportDao;
import by.bsuir.spp.dao.impl.MySqlUserDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;

public class SelectAdvertisementCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        AdvertisementDao advertisementDao = MySqlAdvertisementDao.getInstance();
        PackageDao packageDao = MySqlPackageDao.getInstance();
        PassportDao passportDao = MySqlPassportDao.getInstance();
        UserDao userDao = MySqlUserDao.getInstance();

        int idPackageOfAdvertisement = Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID));
        try {
            by.bsuir.spp.bean.document.Package packagee = packageDao.read(
                    Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID)));
            User user = userDao.read(packagee.getGetterUser().getId());
            packagee.setGetterUser(user);
            Passport passport = passportDao.read(packagee.getPassportId());
            request.setAttribute(RequestParameterName.PASSPORT, passport);
            request.setAttribute(RequestParameterName.PACKAGE, packagee);
            Advertisement advertisement = advertisementDao.read(idPackageOfAdvertisement);
            request.setAttribute(RequestParameterName.ADVERTISEMENT, advertisement);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return JspPageName.ADVERTISEMENT;
    }
}
