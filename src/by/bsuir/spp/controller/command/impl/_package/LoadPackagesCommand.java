package by.bsuir.spp.controller.command.impl._package;

import by.bsuir.spp.controller.command.Command;
import by.bsuir.spp.controller.constant.JspPageName;
import by.bsuir.spp.controller.constant.RequestParameterName;
import by.bsuir.spp.dao.UserDao;
import by.bsuir.spp.dao.impl.MySqlPackageDao;
import by.bsuir.spp.dao.impl.MySqlUserDao;
import by.bsuir.spp.exception.controller.command.CommandException;
import by.bsuir.spp.exception.dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Кирилл on 2/21/2016.
 */
public class LoadPackagesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<by.bsuir.spp.bean.document.Package> packages = MySqlPackageDao.getInstance().getAllPackages();
        UserDao userDao = MySqlUserDao.getInstance();
        for (by.bsuir.spp.bean.document.Package pack : packages) {
            try {
                pack.setGetterUser(userDao.read(pack.getGetterUser().getId()));
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
        request.setAttribute(RequestParameterName.PACKAGES, packages);

        return JspPageName.PACKAGES;
    }
}
