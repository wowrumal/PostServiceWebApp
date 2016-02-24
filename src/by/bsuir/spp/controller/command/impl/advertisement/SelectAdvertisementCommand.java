package by.bsuir.spp.controller.command.impl.advertisement;

import by.bsuir.spp.bean.document.Advertisement;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.AdvertisementDao;
import by.bsuir.spp.dao.impl.MySqlAdvertisementDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;

public class SelectAdvertisementCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        AdvertisementDao advertisementDao = MySqlAdvertisementDao.getInstance();

        int idPackageOfAdvertisement = Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID));
        try {
            Advertisement advertisement = advertisementDao.read(idPackageOfAdvertisement);
            request.setAttribute(RequestParameterName.ADVERTISEMENT, advertisement);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return JspPageName.VIEW_ADVERTISEMENT;
    }
}
