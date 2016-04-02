package by.bsuir.spp.controller.command.impl.advertisement;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.bean.document.*;
import by.bsuir.spp.bean.document.Package;
import by.bsuir.spp.controller.command.Command;
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

public class AddAdvertisementCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        AdvertisementDao advertisementDao = MySqlAdvertisementDao.getInstance();
        PackageDao packageDao = MySqlPackageDao.getInstance();
        UserDao userDao = MySqlUserDao.getInstance();
        PassportDao passportDao = MySqlPassportDao.getInstance();

        if (validateParam(request)) {

            Advertisement advertisement = new Advertisement();
            advertisement.setCost(Integer.parseInt(request.getParameter(RequestParameterName.COST)));
            advertisement.setAddressForGetting(request.getParameter(RequestParameterName.PACKAGE_ADDRESS));
            advertisement.setWeight(Integer.parseInt(request.getParameter(RequestParameterName.WEIGHT)));

            int packageId = Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID));
            Package packagee = null;
            Passport passport = null;
            try {
                packagee = packageDao.read(packageId);
                passport = passportDao.read(userDao.read(packagee.getGetterUser().getId()).getPassport().getPassportId());
            } catch (DaoException e) {
                e.printStackTrace();
            }
            advertisement.setPassport(passport);
            advertisement.setPostPackage(packagee);

            try {
                advertisementDao.create(advertisement);
            } catch (DaoException e) {
                e.printStackTrace();
            }

            packageDao.deleteNewPackage(packageId);
            packageDao.updateStatus(packageId, "принято");

            return new LoadAdvertisementsCommand().execute(request);
        }
        else {
            return new PrepareDataForAdvertisementCreationCommand().execute(request);
        }
    }

    private boolean validateParam(HttpServletRequest request) {

        if (getRequestParam(request, RequestParameterName.WEIGHT) == null ||
                getRequestParam(request, RequestParameterName.COST) == null) {
            return false;
        }

        if (getRequestParam(request, RequestParameterName.WEIGHT).length() > 6 ||
                getRequestParam(request, RequestParameterName.COST).length() > 6) {
            return false;
        }

        try {
            Integer.parseInt(request.getParameter(RequestParameterName.WEIGHT));
            Integer.parseInt(request.getParameter(RequestParameterName.COST));
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    private String getRequestParam(HttpServletRequest request, String parameterName) {
        return request.getParameter(parameterName);
    }

}
