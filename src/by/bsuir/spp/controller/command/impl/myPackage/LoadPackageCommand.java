package by.bsuir.spp.controller.command.impl.myPackage;

import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
import by.bsuir.spp.exception.controller.command.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Кирилл on 2/21/2016.
 */
public class LoadPackageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<by.bsuir.spp.bean.document.Package> packages = MySqlPackageDao.GetInstance().getAllPackages();
        request.setAttribute(RequestParameterName.PACKAGES, packages);

        return JspPageName.PACKAGES;
    }
}
