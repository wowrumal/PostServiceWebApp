package by.bsuir.spp.controller.command.impl.advertisement;

import by.bsuir.spp.bean.User;
import by.bsuir.spp.bean.document.Advertisement;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.AdvertisementDao;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.impl.MySqlAdvertisementDao;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetUserAdvertisementsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = JspPageName.LOGIN_PAGE;

        try {
            AdvertisementDao advertisementDao = MySqlAdvertisementDao.getInstance();
            PackageDao packageDao = MySqlPackageDao.getInstance();
            int passportId = ((User)request.getSession().getAttribute(RequestParameterName.USER)).getPassport().getPassportId();
            List<Advertisement> advertisements = advertisementDao.getAdvertisementsByPassportId(passportId);

            for (Advertisement advertisement : advertisements) {
                advertisement.setPostPackage(packageDao.read(advertisement.getPostPackage().getIdPackage()));
            }

            request.setAttribute(RequestParameterName.ADVERTISEMENTS, advertisements);
            page = JspPageName.USER_ADVERTISEMENTS;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return page;
    }
}
