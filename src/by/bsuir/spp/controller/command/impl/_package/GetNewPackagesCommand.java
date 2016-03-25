package by.bsuir.spp.controller.command.impl._package;

import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetNewPackagesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        PackageDao packageDao = MySqlPackageDao.getInstance();

        List<Integer> packageIds = packageDao.getNewPackageIds();
        request.setAttribute(RequestParameterName.NEW_PACKAGE_IDS, packageIds);

        return JspPageName.HOME_MANAGER_PAGE;
    }
}
