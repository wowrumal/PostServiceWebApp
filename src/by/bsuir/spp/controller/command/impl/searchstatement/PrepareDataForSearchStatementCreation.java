package by.bsuir.spp.controller.command.impl.searchstatement;

import by.bsuir.spp.bean.User;
import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PrepareDataForSearchStatementCreation implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        PackageDao packageDao = MySqlPackageDao.getInstance();

        List<by.bsuir.spp.bean.document.Package> packages = packageDao.
                getPackagesByPassportId(((User)request.getSession().
                        getAttribute(RequestParameterName.USER)).getPassport().getPassportId());

        request.setAttribute(RequestParameterName.PACKAGES, packages);

        return JspPageName.SEARCH_STATEMENT;

    }
}
