package by.bsuir.spp.controller.command.impl._package;

import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.PackageDao;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;

public class RejectPackageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        PackageDao packageDao = MySqlPackageDao.getInstance();
        int packageId = Integer.parseInt(request.getParameter(RequestParameterName.PACKAGE_ID));
        packageDao.updateStatus(packageId, "отклонено");
        packageDao.deleteNewPackage(packageId);

        return new LoadPackagesCommand().execute(request);
    }
}
