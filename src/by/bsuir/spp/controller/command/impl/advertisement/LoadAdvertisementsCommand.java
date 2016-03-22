package by.bsuir.spp.controller.command.impl.advertisement;

import by.bsuir.spp.bean.document.Advertisement;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.AdvertisementDao;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.PassportDao;
import by.bsuir.spp.dao.impl.MySqlAdvertisementDao;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
import by.bsuir.spp.dao.impl.MySqlPassportDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LoadAdvertisementsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        AdvertisementDao advertisementDao = MySqlAdvertisementDao.getInstance();
        PackageDao packageDao = MySqlPackageDao.getInstance();
        PassportDao passportDao = MySqlPassportDao.getInstance();
        List<Advertisement> advertisements = advertisementDao.getAllAdvertisement();

        try {
            for (Advertisement advertisement : advertisements) {
                advertisement.setPostPackage(packageDao.read(advertisement.getPostPackage().getIdPackage()));
                advertisement.setPassport(passportDao.read(advertisement.getPassport().getPassportId()));
            }
        } catch (DaoException e) {
            return JspPageName.LOGIN_PAGE;
        }

        request.setAttribute(RequestParameterName.ADVERTISEMENTS, advertisements);

        return JspPageName.ADVERTISEMENTS;
    }
}
