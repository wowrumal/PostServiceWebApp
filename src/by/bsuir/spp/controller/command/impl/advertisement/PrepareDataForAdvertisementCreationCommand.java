package by.bsuir.spp.controller.command.impl.advertisement;

import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.PassportDao;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
import by.bsuir.spp.dao.impl.MySqlPassportDao;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PrepareDataForAdvertisementCreationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        PackageDao packageDao = MySqlPackageDao.getInstance();
        PassportDao passportDao = MySqlPassportDao.getInstance();


        List<Integer> packageIds = packageDao.getPackageIds();
        List<Integer> passportIds = passportDao.getIdPassports();
        request.setAttribute(RequestParameterName.PACKAGE_ID, Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID)));
        request.setAttribute(RequestParameterName.PASSPORT_IDS, passportIds);
        request.setAttribute(RequestParameterName.PACKAGE_IDS, packageIds);

        return JspPageName.VIEW_ADVERTISEMENT;
    }
}
