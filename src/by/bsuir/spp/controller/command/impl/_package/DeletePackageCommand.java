package by.bsuir.spp.controller.command.impl._package;

import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Кирилл on 2/21/2016.
 */
public class DeletePackageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        int packageId = Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID));

        by.bsuir.spp.bean.document.Package myPackage = new by.bsuir.spp.bean.document.Package();
        myPackage.setIdPackage(packageId);

        PackageDao packageDao = MySqlPackageDao.getInstance();

        try {
            packageDao.delete(myPackage);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return new LoadPackagesCommand().execute(request);
    }
}
