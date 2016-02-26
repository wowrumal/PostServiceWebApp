package by.bsuir.spp.controller.command.impl._package;

import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Кирилл on 2/21/2016.
 */
public class SelectPackageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        int packageId = Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID));

        PackageDao packageDao = MySqlPackageDao.getInstance();
        by.bsuir.spp.bean.document.Package myPackage = null;

        try {
            myPackage = packageDao.read(packageId);

        } catch (DaoException e) {
            e.printStackTrace();
        }

        if(myPackage != null) {
            request.setAttribute(RequestParameterName.PACKAGE, myPackage);
        }

        return JspPageName.VIEW_PACKAGE;
    }
}
