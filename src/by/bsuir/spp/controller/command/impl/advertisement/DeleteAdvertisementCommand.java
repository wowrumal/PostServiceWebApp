package by.bsuir.spp.controller.command.impl.advertisement;

import by.bsuir.spp.bean.document.*;
import by.bsuir.spp.bean.document.Package;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.AdvertisementDao;
import by.bsuir.spp.dao.impl.MySqlAdvertisementDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;

public class DeleteAdvertisementCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int idPackageForAdvertisement = Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID));

        AdvertisementDao advertisementDao = MySqlAdvertisementDao.getInstance();
        Advertisement advertisement = new Advertisement();
        by.bsuir.spp.bean.document.Package packagee = new Package();
        packagee.setIdPackage(idPackageForAdvertisement);
        advertisement.setPostPackage(packagee);
        try {
            advertisementDao.delete(advertisement);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return new LoadAdvertisementsCommand().execute(request);
    }
}
