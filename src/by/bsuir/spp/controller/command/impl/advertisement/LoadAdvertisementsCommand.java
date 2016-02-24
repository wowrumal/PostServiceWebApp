package by.bsuir.spp.controller.command.impl.advertisement;

import by.bsuir.spp.bean.document.Advertisement;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.AdvertisementDao;
import by.bsuir.spp.dao.impl.MySqlAdvertisementDao;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LoadAdvertisementsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        AdvertisementDao advertisementDao = MySqlAdvertisementDao.getInstance();

        List<Advertisement> advertisements = advertisementDao.getAllAdvertisement();

        request.setAttribute(RequestParameterName.ADVERTISEMENTS, advertisements);

        return JspPageName.ADVERTISEMENTS;
    }
}
