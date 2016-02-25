package by.bsuir.spp.controller.command.impl.searchstatement;

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

public class PrepareDataForSearchStatementCreation implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        PassportDao passportDao = MySqlPassportDao.getInstance();
        PackageDao packageDao = MySqlPackageDao.getInstance();

        List<Integer> passportIds = passportDao.getIdPassports();
        List<Integer> packageIds = packageDao.getPackageIds();

        request.setAttribute(RequestParameterName.PASSPORT_IDS, passportIds);
        request.setAttribute(RequestParameterName.PACKAGE_IDS, packageIds);

        return JspPageName.VIEW_SEARCH_STATEMENT;
    }
}
