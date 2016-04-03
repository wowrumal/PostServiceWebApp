package by.bsuir.spp.controller.command.impl.advertisement;

import by.bsuir.spp.bean.Passport;
import by.bsuir.spp.bean.document.*;
import by.bsuir.spp.bean.document.Package;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.AdvertisementDao;
import by.bsuir.spp.dao.impl.MySqlAdvertisementDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;

public class UpdateAdvertisementCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        AdvertisementDao advertisementDao = MySqlAdvertisementDao.getInstance();

        if (validateParam(request)) {

            Advertisement advertisement = new Advertisement();
            advertisement.setCost(Integer.parseInt(request.getParameter(RequestParameterName.COST)));
            advertisement.setAddressForGetting(request.getParameter(RequestParameterName.PACKAGE_ADDRESS));
            advertisement.setWeight(Integer.parseInt(request.getParameter(RequestParameterName.WEIGHT)));
            by.bsuir.spp.bean.document.Package packagee = new Package();
            packagee.setIdPackage(Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID)));
            Passport passport = new Passport();
            passport.setPassportId(Integer.parseInt(request.getParameter(RequestParameterName.PASSPORT_ID)));
            advertisement.setPassport(passport);
            advertisement.setPostPackage(packagee);

            try {
                advertisementDao.update(advertisement);
            } catch (DaoException e) {
                e.printStackTrace();
            }

            return "controller?command=load_advertisements";
        }
        else {
            return "controller?command=select_advertisement&package_id=" + Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID));
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
